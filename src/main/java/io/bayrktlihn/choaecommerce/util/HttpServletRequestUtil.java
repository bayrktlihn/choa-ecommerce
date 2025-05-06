package io.bayrktlihn.choaecommerce.util;

import jakarta.servlet.http.HttpServletRequest;

public class HttpServletRequestUtil {

    private static HttpServletRequestUtil instance;

    private static int instanceCounter = 0;

    private HttpServletRequestUtil() {

        synchronized (ExceptionUtil.class) {
            instanceCounter++;
            if (instanceCounter != 1) {
                throw new IllegalStateException("Instance counter should be 1");
            }
        }

    }

    public static HttpServletRequestUtil getInstance() {
        if (instance == null) {
            synchronized (ExceptionUtil.class) {
                if (instance == null) {
                    instance = new HttpServletRequestUtil();
                }
            }
        }
        return instance;
    }

    public String getHeader(HttpServletRequest request, String name) {
        String headerValue = request.getHeader(name);
        return headerValue;
    }

}
