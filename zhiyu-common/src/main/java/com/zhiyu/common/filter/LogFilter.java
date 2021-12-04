package com.zhiyu.common.filter;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.classic.spi.IThrowableProxy;
import ch.qos.logback.core.filter.Filter;
import ch.qos.logback.core.spi.FilterReply;
import com.zhiyu.common.model.LogMessage;
import com.zhiyu.common.model.LoggerQueue;

import java.text.DateFormat;
import java.util.Date;

/**
 * 定义Logfilter拦截输出日志
 * @author magicdu
 * @since 2021-12-04
 */
public class LogFilter extends Filter<ILoggingEvent> {

    @Override
    public FilterReply decide(ILoggingEvent event) {
        String exception = "";
        IThrowableProxy iThrowableProxy1 = event.getThrowableProxy();
        LogMessage loggerMessage = new LogMessage(
                event.getFormattedMessage(),
                DateFormat.getDateTimeInstance().format(new Date(event.getTimeStamp())),
                event.getThreadName(),
                event.getLoggerName(),
                event.getLevel().levelStr
        );
        LoggerQueue.getInstance().push(loggerMessage);
        return FilterReply.ACCEPT;
    }
}