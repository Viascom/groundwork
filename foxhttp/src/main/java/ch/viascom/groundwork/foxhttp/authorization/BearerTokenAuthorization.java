package ch.viascom.groundwork.foxhttp.authorization;

import ch.viascom.groundwork.foxhttp.type.HeaderTypes;
import lombok.*;

import java.net.URLConnection;

/**
 * Bearer Token for FoxHttp
 * <p>
 * This FoxHttpAuthorization will create a header with data for a Bearer Token authentication.
 *
 * @author patrick.boesch@viascom.ch
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BearerTokenAuthorization implements FoxHttpAuthorization {

    private String token;
    private String headerPrefix = "Bearer";

    public BearerTokenAuthorization(String token){
        this.token = token;
    }

    @Override
    public void doAuthorization(URLConnection connection, FoxHttpAuthorizationScope foxHttpAuthorizationScope) {
        connection.setRequestProperty(HeaderTypes.AUTHORIZATION.toString(), headerPrefix + " " + token);
    }
}
