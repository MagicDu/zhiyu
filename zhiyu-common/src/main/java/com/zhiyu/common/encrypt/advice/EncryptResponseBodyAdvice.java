package com.zhiyu.common.encrypt.advice;

import cn.hutool.json.JSONUtil;
import com.zhiyu.common.encrypt.config.KeyConfig;
import com.zhiyu.common.encrypt.utils.NeedCrypto;
import com.zhiyu.common.utils.ApiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import top.lrshuai.encryption.AesUtils;
import top.lrshuai.encryption.RsaUtils;


/**
 * encrypt response data  advice
 * @author dzq
 */
@ControllerAdvice
public class EncryptResponseBodyAdvice implements ResponseBodyAdvice<Object> {

    @Autowired
    private KeyConfig keyConfig;
    /**
     * needed encrypt or not
     */
    private boolean isEncode;

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        // response data need encrypted or not by annotation @EncryptResponse
        isEncode= NeedCrypto.needEncrypt(returnType);
        return isEncode ;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {

        if( !isEncode ){
            return body;
        }
        if(!(body instanceof ApiResult)){
            return body;
        }

        //only encrypt data of ApiResult
        ApiResult result = (ApiResult) body;
        Object data = result.getData();
        if(null == data){
            return body;
        }

        String returnData=JSONUtil.toJsonStr(data);
        try {
            // 1. random aes key
            String randomAesKey = AesUtils.generateSecret(256);
            // 2. encrypt data  by aes
            String aesEncode = AesUtils.encodeBase64(returnData, randomAesKey, keyConfig.getAesIv().getBytes(), AesUtils.CIPHER_MODE_CBC_PKCS5PADDING);
            // 4. reset response data
            result.setData(aesEncode);
            // 5.encrypt aes key by front ras public key
            String key=RsaUtils.encodeBase64PublicKey(keyConfig.getFrontRsaPublicKey(), randomAesKey);
            // 6. put encrypted key into header
            response.getHeaders().set("x-magic-header",key);
            // 7. set header expose to  front
            response.getHeaders().add("Access-Control-Expose-Headers","x-magic-header");
        }catch (Exception e){
            // if throw exception, return msg to front end
            e.printStackTrace();
            result.setData("data encrypt failed"+e.getMessage());
        }
        return result;
    }
}
