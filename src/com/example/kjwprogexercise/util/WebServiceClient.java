package com.example.kjwprogexercise.util;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

import com.example.kjwprogexercise.model.Offer;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class WebServiceClient {
	private static WebServiceClient INSTANCE = null;
	public String fetchOffersUrl = "http://sheltered-bastion-2512.herokuapp.com/feed.json";
	private Gson gson;

	public static WebServiceClient getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new WebServiceClient();
		}
		return INSTANCE;
	}

	private WebServiceClient() {
		gson = new Gson();
	}

	public List<Offer> fetchOffers() {
		String result = doSimpleGet(fetchOffersUrl);
		if (result == null) {
			return null;
		}
		Type collectionType = new TypeToken<List<Offer>>() {
		}.getType();
		List<Offer> offers = gson.fromJson(result, collectionType);
		return offers;
	}

	private String doSimpleGet(String url) {
		DefaultHttpClient httpClient = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(url);
		ResponseHandler<String> responseHandler = new BasicResponseHandler();

		try {
			return httpClient.execute(httpGet, responseHandler);
		} catch (ClientProtocolException e) {
			return null;
		} catch (IOException e) {
			return null;
		}
	}
}
