package com.zhiyu.common.filter;

import cn.hutool.json.JSONUtil;
import com.zhiyu.common.utils.ApiResult;
import com.zhiyu.common.utils.NeedCrypto;
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



@ControllerAdvice
public class EncryptResponseBodyAdvice implements ResponseBodyAdvice<Object> {

    @Autowired
    private KeyConfig keyConfig;

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return NeedCrypto.needEncrypt(returnType);
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        boolean encrypt = NeedCrypto.needEncrypt(returnType);

        if( !encrypt ){
            return body;
        }
        if(!(body instanceof ApiResult)){
            return body;
        }

        //只针对ResponseMsg的data进行加密
        ApiResult responseMsg = (ApiResult) body;
        Object data = responseMsg.getData();
        if(null == data){
            return body;
        }

        String xx=JSONUtil.toJsonStr(data);
        try {
            // 1、随机aes密钥
            String randomAesKey = AesUtils.generateSecret(256);
            // 4、aes加密数据体
            String aesEncode = AesUtils.encodeBase64(xx, randomAesKey, keyConfig.getAesIv().getBytes(), AesUtils.CIPHER_MODE_CBC_PKCS5PADDING);
            // 5、重新设置数据体
            responseMsg.setData(aesEncode);
            // 6、使用前端的rsa公钥加密 aes密钥 返回给前端
            String key=RsaUtils.encodeBase64PublicKey(keyConfig.getFrontRsaPublicKey(), randomAesKey);
            response.getHeaders().set("x-magic-header",key);
            //responseMsg.setKey(key);
        }catch (Exception e){
            responseMsg.setData("数据加密失败");
        }
        return responseMsg;
    }
}
