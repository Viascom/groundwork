package ch.viascom.groundwork.foxhttp.body.request;

import ch.viascom.groundwork.foxhttp.body.FoxHttpBody;
import ch.viascom.groundwork.foxhttp.exception.FoxHttpException;
import ch.viascom.groundwork.foxhttp.exception.FoxHttpRequestException;
import ch.viascom.groundwork.foxhttp.interceptor.FoxHttpInterceptorExecutor;
import ch.viascom.groundwork.foxhttp.interceptor.request.context.FoxHttpRequestBodyInterceptorContext;
import ch.viascom.groundwork.foxhttp.type.ContentType;
import lombok.Getter;

import java.io.ByteArrayOutputStream;

/**
 * Abstract FoxHttpRequestBody
 *
 * @author patrick.boesch@viascom.ch
 */
public abstract class FoxHttpRequestBody implements FoxHttpBody {
    @Getter
    protected ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    ContentType outputContentType = ContentType.WILDCARD;

    public abstract void setBody(FoxHttpRequestBodyContext context) throws FoxHttpRequestException;

    public abstract boolean hasBody();

    public abstract ContentType getOutputContentType();

    protected void executeInterceptor(FoxHttpRequestBodyContext context) throws FoxHttpException {
        context.getRequest().getFoxHttpClient().getFoxHttpLogger().log("executeRequestBodyInterceptor()");
        FoxHttpInterceptorExecutor.executeRequestBodyInterceptor(
                new FoxHttpRequestBodyInterceptorContext(context.getUrlConnection(), this, context.getRequest(), context.getClient())
        );
    }
}
