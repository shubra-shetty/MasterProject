package com.example.finalproject_try1;
import org.apache.http.client.HttpResponseException;

import android.content.Context;
import android.util.Log;

import com.google.api.client.googleapis.*;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.http.json.JsonHttpContent;
import com.google.api.client.json.JsonObjectParser;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.http.HttpHeaders;
import com.google.api.client.util.Key;
<<<<<<< HEAD
import com.google.api.client.*;

=======
>>>>>>> e83148c69b6179eee336db2be81a0fdb78299acc

@SuppressWarnings("deprecation")
public class GooglePlaces {

	/** Global instance of the HTTP transport. */
	private static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();

	// Google API Key
<<<<<<< HEAD
	private static final String API_KEY ="AIzaSyAv4UWccQK5dmk6-hh9AtHgvauBzIdz2wU";//"AIzaSyBM_-33-FPe9WAf6D49bCietEpPSVszRcs"; //"AIzaSyAv4UWccQK5dmk6-hh9AtHgvauBzIdz2wU";// // place your API key here
=======
	private static final String API_KEY = "AIzaSyBM_-33-FPe9WAf6D49bCietEpPSVszRcs"; // place your API key here
>>>>>>> e83148c69b6179eee336db2be81a0fdb78299acc

	// Google Places serach url's
	private static final String PLACES_SEARCH_URL = "https://maps.googleapis.com/maps/api/place/search/json?";
	private static final String PLACES_TEXT_SEARCH_URL = "https://maps.googleapis.com/maps/api/place/search/json?";
	private static final String PLACES_DETAILS_URL = "https://maps.googleapis.com/maps/api/place/details/json?";

	private double _latitude;
	private double _longitude;
	private double _radius;
	
	/**
	 * Searching places
	 * @param latitude - latitude of place
	 * @params longitude - longitude of place
	 * @param radius - radius of searchable area
	 * @param types - type of place to search
	 * @return list of places
	 * */
<<<<<<< HEAD
	public PlacesList search(double latitude, double longitude, double radius, String types,String rating)
=======
	public PlacesList search(double latitude, double longitude, double radius, String types)
>>>>>>> e83148c69b6179eee336db2be81a0fdb78299acc
			throws Exception {

		this._latitude = latitude;
		this._longitude = longitude;
<<<<<<< HEAD
		//this._latitude = 37.426808;
		//this._longitude = -121.94347;
		this._radius = radius;
		
		System.out.println("Check passed lat long.........." + latitude + longitude);

=======
		this._radius = radius;
>>>>>>> e83148c69b6179eee336db2be81a0fdb78299acc

		try {

			HttpRequestFactory httpRequestFactory = createRequestFactory(HTTP_TRANSPORT);
			HttpRequest request = httpRequestFactory
					.buildGetRequest(new GenericUrl(PLACES_SEARCH_URL));
			request.getUrl().put("key", API_KEY);
			request.getUrl().put("location", _latitude + "," + _longitude);
			request.getUrl().put("radius", _radius); // in meters
			request.getUrl().put("sensor", "false");
			if(types != null)
				request.getUrl().put("types", types);
<<<<<<< HEAD
			if(rating!= null) // added for rating
				request.getUrl().put("rating", rating);

			PlacesList list = request.execute().parseAs(PlacesList.class);
			System.out.println("Check1........" + list.results.size());
=======

			PlacesList list = request.execute().parseAs(PlacesList.class);
>>>>>>> e83148c69b6179eee336db2be81a0fdb78299acc
			// Check log cat for places response status
			Log.d("Places Status", "" + list.status);
			return list;

		} catch (HttpResponseException e) {
			Log.e("Error:", e.getMessage());
			return null;
		}

	}

	/**
	 * Searching single place full details
	 * @param refrence - reference id of place
	 * 				   - which you will get in search api request
	 * */
	public PlaceDetails getPlaceDetails(String reference) throws Exception {
		try {

			HttpRequestFactory httpRequestFactory = createRequestFactory(HTTP_TRANSPORT);
			HttpRequest request = httpRequestFactory
					.buildGetRequest(new GenericUrl(PLACES_DETAILS_URL));
			request.getUrl().put("key", API_KEY);
			request.getUrl().put("reference", reference);
			request.getUrl().put("sensor", "false");
<<<<<<< HEAD
			System.out.println("Check2...........");
			PlaceDetails place = request.execute().parseAs(PlaceDetails.class);
			System.out.println("Check2........" + place.toString());
=======

			PlaceDetails place = request.execute().parseAs(PlaceDetails.class);
			
>>>>>>> e83148c69b6179eee336db2be81a0fdb78299acc
			return place;

		} catch (HttpResponseException e) {
			Log.e("Error in Perform Details", e.getMessage());
			throw e;
		}
	}

	/**
	 * Creating http request Factory
	 * */
	public static HttpRequestFactory createRequestFactory(
			final HttpTransport transport) {
		return transport.createRequestFactory(new HttpRequestInitializer() {
			public void initialize(HttpRequest request) {
				//GoogleHeaders headers = new GoogleHeaders();
				//headers.setApplicationName("AndroidHive-Places-Test");
				//request.setHeaders(headers);
<<<<<<< HEAD
				//GoogleHeaders headers = new GoogleHeaders();
				//headers.setApplicationName("AndroidHive-Places-Test");
				//request.setHeaders(headers);
=======
>>>>>>> e83148c69b6179eee336db2be81a0fdb78299acc
				JsonObjectParser parser = new JsonObjectParser(new JacksonFactory());
				request.setParser(parser);
			}
		});
	}

}
