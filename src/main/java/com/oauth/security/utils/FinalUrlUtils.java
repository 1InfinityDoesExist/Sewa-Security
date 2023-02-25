package com.oauth.security.utils;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import com.mashape.unirest.http.HttpMethod;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class FinalUrlUtils {

	public static String getFinalURL(String url) throws MalformedURLException, IOException {

		log.info("--------Initial URL : {}", url);
		HttpURLConnection httpURLConnection;
		String finalURL = url;

		do {
			httpURLConnection = (HttpURLConnection) new URL(finalURL).openConnection();
			httpURLConnection.setInstanceFollowRedirects(false);
			httpURLConnection.setUseCaches(false);
			httpURLConnection.setRequestMethod(HttpMethod.GET.toString());
			httpURLConnection.connect();

			int responseCode = httpURLConnection.getResponseCode();

			if (responseCode >= 300 && responseCode < 400) {
				String redirectUrl = httpURLConnection.getHeaderField("Location");
				if (ObjectUtils.isEmpty(redirectUrl)) {
					break;
				}
				finalURL = redirectUrl;

			} else {
				break;
			}
		} while (httpURLConnection.getResponseCode() != HttpURLConnection.HTTP_OK);

		log.info("-----Final URL : {}", finalURL);
		httpURLConnection.disconnect();
		return finalURL.replaceAll(" ", "%20");
	}
}
