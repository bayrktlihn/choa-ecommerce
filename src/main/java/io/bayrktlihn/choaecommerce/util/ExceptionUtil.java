package io.bayrktlihn.choaecommerce.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

public final class ExceptionUtil {

    private static ExceptionUtil instance;

    private static int instanceCounter = 0;

    private ExceptionUtil() {

        synchronized (ExceptionUtil.class) {
            instanceCounter++;
            if (instanceCounter != 1) {
                throw new IllegalStateException("Instance counter should be 1");
            }
        }

    }

    public static ExceptionUtil getInstance() {
        if (instance == null) {
            synchronized (ExceptionUtil.class) {
                if (instance == null) {
                    instance = new ExceptionUtil();
                }
            }
        }
        return instance;
    }


    public String getStackTrace(Exception ex) {

        try (StringWriter sw = new StringWriter(); PrintWriter pw = new PrintWriter(sw)) {
            ex.printStackTrace(pw);
            return sw.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
