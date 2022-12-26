package com.zhiyu.common.encrypt.utils;



import com.zhiyu.common.encrypt.annotaion.DecryptRequest;
import com.zhiyu.common.encrypt.annotaion.EncryptResponse;
import org.springframework.core.MethodParameter;

/**
 * is response data need encrypted or not
 * is request data need decrypted or not
 * @author dzq
 */
public class NeedCrypto {
    private NeedCrypto(){}

    /**
     * response data need encrypted or not by annotation @EncryptResponse
     * @param returnType
     * @return
     */
   public static boolean needEncrypt(MethodParameter returnType) {
        boolean encrypt = false;
        boolean classPresentAnno  = returnType.getContainingClass().isAnnotationPresent(EncryptResponse.class);
        boolean methodPresentAnno = returnType.getMethod().isAnnotationPresent(EncryptResponse.class);

        if(classPresentAnno){
            // class annotation judge
            encrypt = returnType.getContainingClass().getAnnotation(EncryptResponse.class).value();
            if(!encrypt){
                return false;
            }
        }
        if(methodPresentAnno){
            //method annotation judge
            encrypt = returnType.getMethod().getAnnotation(EncryptResponse.class).value();
        }
        return encrypt;
    }

    /**
     * request data need decrypted or not by annotation @DecryptRequest
     * @param parameter
     * @return
     */
    public static boolean needDecrypt(MethodParameter parameter) {
        boolean encrypt = false;
        boolean classPresentAnno  = parameter.getContainingClass().isAnnotationPresent(DecryptRequest.class);
        boolean methodPresentAnno = parameter.getMethod().isAnnotationPresent(DecryptRequest.class);

        if(classPresentAnno){
            // class annotation judge
            encrypt = parameter.getContainingClass().getAnnotation(DecryptRequest.class).value();
            if(!encrypt){
                return false;
            }
        }
        if(methodPresentAnno){
            //method annotation judge
            encrypt = parameter.getMethod().getAnnotation(DecryptRequest.class).value();
        }
        return encrypt;
    }
}
