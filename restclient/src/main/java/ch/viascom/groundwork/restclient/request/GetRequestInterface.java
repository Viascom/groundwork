package ch.viascom.groundwork.restclient.request;

import ch.viascom.groundwork.restclient.response.generic.Response;

/**
 * @author patrick.boesch@viascom.ch
 */
public interface GetRequestInterface<T extends Response> extends RequestInterface<T> {
    Object request() throws Exception;

}
