package cn.alan.app.log.flume;

import org.apache.flume.Context;
import org.apache.flume.Event;
import org.apache.flume.interceptor.Interceptor;

import java.util.List;
import java.util.Map;

import static cn.alan.app.log.flume.LogCollInterceptor.Constants.PRESERVE;
import static cn.alan.app.log.flume.LogCollInterceptor.Constants.PRESERVE_DFLT;


public class LogCollInterceptor implements Interceptor {
    private final boolean preserveExisting;

    private LogCollInterceptor(boolean preserveExisting) {
        this.preserveExisting = preserveExisting;
    }

    public void initialize() {

    }

    /**
     * Modifies events in-place.
     */
    public Event intercept(Event event) {
        // 1获取flume接收消息头
        Map<String, String> headers = event.getHeaders();

        // 2获取flume接收的json数据数组
        byte[] json = event.getBody();
        // 将json数组转换为字符串
        String jsonStr = new String(json);

        // pageLog
        String logType = "" ;
        if(jsonStr.contains("pageId")){
            logType = "page" ;
        }
        // eventLog
        else if (jsonStr.contains("eventId")) {
            logType = "event";
        }
        // usageLog
        else if (jsonStr.contains("singleUseDurationSecs")) {
            logType = "usage";
        }
        // error
        else if (jsonStr.contains("errorBrief")) {
            logType = "error";
        }
        // startup
        else if (jsonStr.contains("network")) {
            logType = "startup";
        }

        // 3将日志类型存储到flume头中
        headers.put("logType", logType);

        return event;
    }

    /**
     * Delegates to {@link #intercept(Event)} in a loop.
     * @param events
     * @return
     */
    public List<Event> intercept(List<Event> events) {
        for (Event event : events) {
            intercept(event);
        }
        return events;
    }

    public void close() {
    }

    public static class Builder implements Interceptor.Builder {

        private boolean preserveExisting = PRESERVE_DFLT;

        public Interceptor build() {
            return new LogCollInterceptor(preserveExisting);
        }

        public void configure(Context context) {
            preserveExisting = context.getBoolean(PRESERVE, PRESERVE_DFLT);
        }
    }

    public static class Constants {
        public static String TIMESTAMP = "timestamp";
        public static String PRESERVE = "preserveExisting";
        public static boolean PRESERVE_DFLT = false;
    }

}
