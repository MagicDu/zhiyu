package com.zhiyu.common.utils;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * 异常工具
 * @author magicdu
 * @since 2021/12/20
 */
public class ThrowableUtil {

    /**
     * 获取堆栈信息
     * @param throwable
     * @return
     */
    public static String getStackTrace(Throwable throwable){
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        try {
            throwable.printStackTrace(pw);
            return "\n"+sw.toString();
        } finally {
            pw.close();
        }
    }
}
