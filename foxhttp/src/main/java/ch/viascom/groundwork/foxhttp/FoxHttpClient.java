package ch.viascom.groundwork.foxhttp;

import ch.viascom.groundwork.foxhttp.authorization.DefaultAuthorizationStrategy;
import ch.viascom.groundwork.foxhttp.authorization.FoxHttpAuthorizationStrategy;
import ch.viascom.groundwork.foxhttp.component.FoxHttpComponent;
import ch.viascom.groundwork.foxhttp.cookie.DefaultCookieStore;
import ch.viascom.groundwork.foxhttp.cookie.FoxHttpCookieStore;
import ch.viascom.groundwork.foxhttp.exception.FoxHttpException;
import ch.viascom.groundwork.foxhttp.interceptor.FoxHttpInterceptor;
import ch.viascom.groundwork.foxhttp.interceptor.FoxHttpInterceptorType;
import ch.viascom.groundwork.foxhttp.log.DefaultFoxHttpLogger;
import ch.viascom.groundwork.foxhttp.log.FoxHttpLogger;
import ch.viascom.groundwork.foxhttp.parser.FoxHttpParser;
import ch.viascom.groundwork.foxhttp.placeholder.DefaultPlaceholderStrategy;
import ch.viascom.groundwork.foxhttp.placeholder.FoxHttpPlaceholderStrategy;
import ch.viascom.groundwork.foxhttp.proxy.FoxHttpProxyStrategy;
import ch.viascom.groundwork.foxhttp.ssl.DefaultHostTrustStrategy;
import ch.viascom.groundwork.foxhttp.ssl.DefaultSSLTrustStrategy;
import ch.viascom.groundwork.foxhttp.ssl.FoxHttpHostTrustStrategy;
import ch.viascom.groundwork.foxhttp.ssl.FoxHttpSSLTrustStrategy;
import ch.viascom.groundwork.foxhttp.timeout.DefaultTimeoutStrategy;
import ch.viascom.groundwork.foxhttp.timeout.FoxHttpTimeoutStrategy;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

/**
 * @author patrick.boesch@viascom.ch
 */
public class FoxHttpClient {

    @Getter
    @Setter
    //Response parser
    private FoxHttpParser foxHttpResponseParser;

    @Getter
    @Setter
    //Request parser
    private FoxHttpParser foxHttpRequestParser;

    @Getter
    @Setter
    //Interceptors
    private Map<FoxHttpInterceptorType, ArrayList<FoxHttpInterceptor>> foxHttpInterceptors = new EnumMap<>(FoxHttpInterceptorType.class);

    //@Getter
    //Caching
    //private FoxHttpCacheStrategy foxHttpCacheStrategy;

    @Getter
    @Setter
    //Cookies
    private FoxHttpCookieStore foxHttpCookieStore = new DefaultCookieStore();

    @Getter
    @Setter
    //Authorization
    private FoxHttpAuthorizationStrategy foxHttpAuthorizationStrategy = new DefaultAuthorizationStrategy();

    @Getter
    @Setter
    //Timeouts
    private FoxHttpTimeoutStrategy foxHttpTimeoutStrategy = new DefaultTimeoutStrategy();

    @Getter
    @Setter
    //HostnameVerifier
    private FoxHttpHostTrustStrategy foxHttpHostTrustStrategy = new DefaultHostTrustStrategy();

    @Getter
    @Setter
    //SSL
    private FoxHttpSSLTrustStrategy foxHttpSSLTrustStrategy = new DefaultSSLTrustStrategy();

    @Getter
    @Setter
    //Proxy
    private FoxHttpProxyStrategy foxHttpProxyStrategy;

    @Getter
    @Setter
    //Placeholder
    private FoxHttpPlaceholderStrategy foxHttpPlaceholderStrategy = new DefaultPlaceholderStrategy();

    @Getter
    @Setter
    //Components
    private List<FoxHttpComponent> foxHttpComponents = new ArrayList<>();

    @Getter
    @Setter
    //Logger
    private FoxHttpLogger foxHttpLogger = new DefaultFoxHttpLogger(false);

    @Getter
    @Setter
    //UserAgent
    private String foxHttpUserAgent = "FoxHTTP v1.2";


    /**
     * Register an interceptor
     *
     * @param interceptorType    Type of the interceptor
     * @param foxHttpInterceptor Interceptor instance
     * @throws FoxHttpException Throws an exception if the interceptor does not match the type
     */
    public void register(FoxHttpInterceptorType interceptorType, FoxHttpInterceptor foxHttpInterceptor) throws FoxHttpException {
        FoxHttpInterceptorType.verifyInterceptor(interceptorType, foxHttpInterceptor);
        if (foxHttpInterceptors.containsKey(interceptorType)) {
            foxHttpInterceptors.get(interceptorType).add(foxHttpInterceptor);
        } else {
            foxHttpInterceptors.put(interceptorType, new ArrayList<>(Arrays.asList(foxHttpInterceptor)));
        }
    }

    public void activateComponent(FoxHttpComponent foxHttpComponent) throws FoxHttpException {
        foxHttpComponents.add(foxHttpComponent);
        foxHttpComponent.initiation(this);
    }
}
