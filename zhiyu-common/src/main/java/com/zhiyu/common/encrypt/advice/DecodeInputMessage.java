package com.zhiyu.common.encrypt.advice;

import cn.hutool.json.JSONUtil;
import com.zhiyu.common.encrypt.config.KeyConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.util.StringUtils;

import top.lrshuai.encryption.AesUtils;
import top.lrshuai.encryption.RsaUtils;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

/**
 * decrypt post data  info
 * @author dzq
 */
@Slf4j
public class DecodeInputMessage implements HttpInputMessage {

    private HttpHeaders headers;

    private InputStream body;

    public DecodeInputMessage(HttpInputMessage httpInputMessage, KeyConfig keyConfig) {
        // get key from headers
        this.headers = httpInputMessage.getHeaders();
        String encodeAesKey = "";
        List<String> keys = this.headers.get("x-magic-front-header");
        if (keys != null && keys.size() > 0) {
            encodeAesKey = keys.get(0);
        }
        try {
            // 1. decrypt key from encodeAesKey
            String decodeAesKey = RsaUtils.decodeBase64ByPrivate(keyConfig.getRsaPrivateKey(), encodeAesKey);
            // 2. get encrypted content from request body
            String encodeAesContent = new BufferedReader(new InputStreamReader(httpInputMessage.getBody())).lines().collect(Collectors.joining(System.lineSeparator()));
            encodeAesContent=JSONUtil.parseObj(encodeAesContent).get("data").toString();
            // 3. decrypt request body  info  by  CBC
            String aesDecode = AesUtils.decodeBase64(encodeAesContent, decodeAesKey, keyConfig.getAesIv().getBytes(), AesUtils.CIPHER_MODE_CBC_PKCS5PADDING);
            if (!StringUtils.isEmpty(aesDecode)) {
                // 4. reset decrypted request body
                this.body = new ByteArrayInputStream(aesDecode.getBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
            String err=" decrypt request body failed"+e.getMessage();
            this.body=new ByteArrayInputStream(err.getBytes());
        }
    }

    @Override
    public InputStream getBody() throws IOException {
        return body;
    }

    @Override
    public HttpHeaders getHeaders() {
        return headers;
    }


    public static void main(String[] args) {
        System.out.printf("");
    }
}
