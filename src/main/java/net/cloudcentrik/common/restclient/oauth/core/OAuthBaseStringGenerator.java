package net.cloudcentrik.common.restclient.oauth.core;



import net.cloudcentrik.common.restclient.oauth.HttpMethod;
import net.cloudcentrik.common.restclient.oauth.HttpParameter;
import net.cloudcentrik.common.restclient.oauth.OAuthField;

import java.util.*;
import java.util.stream.Collectors;

class OAuthBaseStringGenerator {

	public String getBaseString(HttpMethod httpMethod, String sanitizedUrl, String sortedAndUrlEncodedParams) {

		return String.format("%s&%s&%s", OAuthEncoder.encode(httpMethod.name()), OAuthEncoder.encode(sanitizedUrl), OAuthEncoder.encode(sortedAndUrlEncodedParams));
	}
	
	public String getSanitizedUrl(String url) {

		if (url.startsWith("http://") && (url.endsWith(":80") || url.contains(":80/"))) {
			return url.replaceAll("\\?.*", "").replaceAll(":80", "");
		} else if (url.startsWith("https://") && (url.endsWith(":443") || url.contains(":443/"))) {
			return url.replaceAll("\\?.*", "").replaceAll(":443", "");
		} else {
			return url.replaceAll("\\?.*", "");
		}
	}

	public List<HttpParameter> getSortedParams(Map<OAuthField, String> oauthParameters, Set<HttpParameter> queryParams, Set<HttpParameter> bodyParams) {

		List<HttpParameter> params = new ArrayList<>();
		params.addAll(queryParams);
		params.addAll(bodyParams);

		List<HttpParameter> oauthParams = oauthParameters.entrySet().stream()
				.map(entry -> new HttpParameter(entry.getKey().fieldName(), entry.getValue()))
				.collect(Collectors.toList());

		params.addAll(oauthParams);

		Collections.sort(params);
		
		return params;
	}

	public String getUrlEncodedParams(List<HttpParameter> params) {
		
		String urlEncodedParams = params.stream()
                .map(param -> String.format("%s=%s", OAuthEncoder.encode(param.getName()), OAuthEncoder.encode(param.getValue())))
                .collect(Collectors.joining("&"));

		return urlEncodedParams;
	}
}
