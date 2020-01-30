package util;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

public class LoggerForWebapp {
    @AroundInvoke
    public Object printLog(InvocationContext invocationContext) throws Exception {
        System.out.println("The called method is " + invocationContext.getMethod().getName());
        return invocationContext.proceed();
    }
}
