package ch.viascom.groundwork.foxhttp.interceptor;

import ch.viascom.groundwork.foxhttp.exception.FoxHttpException;
import ch.viascom.groundwork.foxhttp.interceptor.request.FoxHttpRequestBodyInterceptor;
import ch.viascom.groundwork.foxhttp.interceptor.request.FoxHttpRequestHeaderInterceptor;
import ch.viascom.groundwork.foxhttp.interceptor.request.FoxHttpRequestInterceptor;
import ch.viascom.groundwork.foxhttp.interceptor.request.context.FoxHttpRequestBodyInterceptorContext;
import ch.viascom.groundwork.foxhttp.interceptor.request.context.FoxHttpRequestHeaderInterceptorContext;
import ch.viascom.groundwork.foxhttp.interceptor.request.context.FoxHttpRequestInterceptorContext;
import ch.viascom.groundwork.foxhttp.interceptor.response.FoxHttpResponseBodyInterceptor;
import ch.viascom.groundwork.foxhttp.interceptor.response.FoxHttpResponseCodeInterceptor;
import ch.viascom.groundwork.foxhttp.interceptor.response.FoxHttpResponseInterceptor;
import ch.viascom.groundwork.foxhttp.interceptor.response.context.FoxHttpResponseBodyInterceptorContext;
import ch.viascom.groundwork.foxhttp.interceptor.response.context.FoxHttpResponseCodeInterceptorContext;
import ch.viascom.groundwork.foxhttp.interceptor.response.context.FoxHttpResponseInterceptorContext;

/**
 * @author patrick.boesch@viascom.ch
 */
public class FoxHttpInterceptorExecutor {

    public static void executeRequestInterceptor(FoxHttpRequestInterceptorContext context) throws FoxHttpException {
        if (context.getClient().getFoxHttpInterceptors().containsKey(FoxHttpInterceptorType.REQUEST)) {
            (context.getClient().getFoxHttpInterceptors().get(FoxHttpInterceptorType.REQUEST)).sort(new FoxHttpInterceptorComparator());
            for (FoxHttpInterceptor interceptor : context.getClient().getFoxHttpInterceptors().get(FoxHttpInterceptorType.REQUEST)) {
                context.getClient().getFoxHttpLogger().log("-> [REQUEST] " + interceptor);
                ((FoxHttpRequestInterceptor) interceptor).onIntercept(context);
            }
        }
    }

    public static void executeRequestHeaderInterceptor(FoxHttpRequestHeaderInterceptorContext context) throws FoxHttpException {
        if (context.getClient().getFoxHttpInterceptors().containsKey(FoxHttpInterceptorType.REQUEST_HEADER)) {
            (context.getClient().getFoxHttpInterceptors().get(FoxHttpInterceptorType.REQUEST_HEADER)).sort(new FoxHttpInterceptorComparator());
            for (FoxHttpInterceptor interceptor : context.getClient().getFoxHttpInterceptors().get(FoxHttpInterceptorType.REQUEST_HEADER)) {
                context.getClient().getFoxHttpLogger().log("-> [REQUEST_HEADER] " + interceptor);
                ((FoxHttpRequestHeaderInterceptor) interceptor).onIntercept(context);
            }
        }
    }

    public static void executeRequestBodyInterceptor(FoxHttpRequestBodyInterceptorContext context) throws FoxHttpException {
        if (context.getClient().getFoxHttpInterceptors().containsKey(FoxHttpInterceptorType.REQUEST_BODY)) {
            (context.getClient().getFoxHttpInterceptors().get(FoxHttpInterceptorType.REQUEST_BODY)).sort(new FoxHttpInterceptorComparator());
            for (FoxHttpInterceptor interceptor : context.getClient().getFoxHttpInterceptors().get(FoxHttpInterceptorType.REQUEST_BODY)) {
                context.getClient().getFoxHttpLogger().log("-> [REQUEST_BODY] " + interceptor);
                ((FoxHttpRequestBodyInterceptor) interceptor).onIntercept(context);
            }
        }
    }

    public static void executeResponseInterceptor(FoxHttpResponseInterceptorContext context) throws FoxHttpException {
        if (context.getClient().getFoxHttpInterceptors().containsKey(FoxHttpInterceptorType.RESPONSE)) {
            (context.getClient().getFoxHttpInterceptors().get(FoxHttpInterceptorType.RESPONSE)).sort(new FoxHttpInterceptorComparator());
            for (FoxHttpInterceptor interceptor : context.getClient().getFoxHttpInterceptors().get(FoxHttpInterceptorType.RESPONSE)) {
                context.getClient().getFoxHttpLogger().log("-> [RESPONSE] " + interceptor);
                ((FoxHttpResponseInterceptor) interceptor).onIntercept(context);
            }
        }
    }

    public static void executeResponseBodyInterceptor(FoxHttpResponseBodyInterceptorContext context) throws FoxHttpException {
        if (context.getClient().getFoxHttpInterceptors().containsKey(FoxHttpInterceptorType.RESPONSE_BODY)) {
            (context.getClient().getFoxHttpInterceptors().get(FoxHttpInterceptorType.RESPONSE_BODY)).sort(new FoxHttpInterceptorComparator());
            for (FoxHttpInterceptor interceptor : context.getClient().getFoxHttpInterceptors().get(FoxHttpInterceptorType.RESPONSE_BODY)) {
                context.getClient().getFoxHttpLogger().log("-> [RESPONSE_BODY] " + interceptor);
                ((FoxHttpResponseBodyInterceptor) interceptor).onIntercept(context);
            }
        }
    }

    public static void executeResponseCodeInterceptor(FoxHttpResponseCodeInterceptorContext context) throws FoxHttpException {
        if (context.getClient().getFoxHttpInterceptors().containsKey(FoxHttpInterceptorType.RESPONSE_CODE)) {
            (context.getClient().getFoxHttpInterceptors().get(FoxHttpInterceptorType.RESPONSE_CODE)).sort(new FoxHttpInterceptorComparator());
            for (FoxHttpInterceptor interceptor : context.getClient().getFoxHttpInterceptors().get(FoxHttpInterceptorType.RESPONSE_CODE)) {
                context.getClient().getFoxHttpLogger().log("-> [RESPONSE_CODE] " + interceptor);
                ((FoxHttpResponseCodeInterceptor) interceptor).onIntercept(context);
            }
        }
    }

}
