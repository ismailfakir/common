package net.cloudcentrik.common.restclient;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.Date;

// For the HMAC

public class OAuthAuthenticator {
    private String host;
    private String path;
    private String consumerKey;
    private String consumerSecret;

    public OAuthAuthenticator(String host,
                              String path,
                              String consumerKey,
                              String consumerSecret) {
        this.host = host;
        this.path = path;
        this.consumerKey = consumerKey;
        this.consumerSecret = consumerSecret;
    }

    public String generateOauthHeader(String method,
                                      String callback,
                                      String[] additionalParameters) throws Exception{
        long timestamp = new Date().getTime() / 1000;
        // XXX this should be different than the timestamp, but
        // good enough for demonstration purposes
        String nonce = Long.toString(timestamp);

        ArrayList<String> parameters = new ArrayList<String>();
        parameters.add("oauth_consumer_key=" + consumerKey);
        parameters.add("oauth_nonce=" + nonce);
        parameters.add("oauth_signature_method=HMAC-SHA1");
        parameters.add("oauth_timestamp=" + timestamp);
        // Note this is URL encoded twice
        parameters.add("oauth_callback=" + URLEncoder.encode(callback,"UTF-8"));
        parameters.add("oauth_version=1.0");

        for (String additionalParameter : additionalParameters) {
            parameters.add(additionalParameter);
        }

        Collections.sort(parameters);

        StringBuffer parametersList = new StringBuffer();

        for (int i = 0; i < parameters.size(); i++) {
            parametersList.append(((i > 0) ? "&" : "") + parameters.get(i));
        }

        String signatureString =
                method + "&" +
                        URLEncoder.encode("https://" + host + path,"UTF-8") + "&" +
                        URLEncoder.encode(parametersList.toString(),"UTF-8");

        String signature = null;

        try {
            SecretKeySpec signingKey = new SecretKeySpec(
                    (consumerSecret + "&").getBytes(), "HmacSHA1");
            Mac mac = Mac.getInstance("HmacSHA1");
            mac.init(signingKey);
            byte[] rawHMAC = mac.doFinal(signatureString.getBytes());
            signature = Base64.getEncoder().encodeToString((rawHMAC));
        } catch (Exception e) {
            System.err.println("Unable to append signature");
            System.exit(0);
        }

        System.out.println(signature);

        String authorizationLine =
                "Authorization: OAuth " +
                        "oauth_consumer_key=\"" + consumerKey + "\", " +
                        "oauth_nonce=\"" + nonce + "\", " +
                        "oauth_timestamp=\"" + timestamp + "\", " +
                        "oauth_signature_method=\"HMAC-SHA1\", " +
                        "oauth_signature=\"" + URLEncoder.encode(signature,"UTF-8") + "\", " +
                        "oauth_version=\"1.0\"";
        authorizationLine += ", oauth_callback=\"" +
                URLEncoder.encode(callback,"UTF-8") + "\"";

        return authorizationLine;
    }
}