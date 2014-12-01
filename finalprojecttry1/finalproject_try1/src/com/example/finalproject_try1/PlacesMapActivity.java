package com.example.finalproject_try1;

<<<<<<< HEAD
import android.content.Intent;
//import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
//import android.util.Log;
import android.widget.Toast;

//import com.androidhive.googleplacesandmaps.Place;
//import com.androidhive.googleplacesandmaps.R;
//import com.google.android.maps.GeoPoint;
//import com.google.android.maps.MapActivity;
//import com.google.android.maps.MapController;
//import com.google.android.maps.MapView; 
//import com.google.android.maps.Overlay;
//import com.google.android.maps.OverlayItem;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
//import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
//import com.google.android.gms.maps.SupportMapFragment; 
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

//public class PlacesMapActivity extends FragmentActivity 
public class PlacesMapActivity extends FragmentActivity 
//implements OnMarkerClickListener
{
//MapActivity{ //removed for v2
//MapFragment {
	//added for v2
	GoogleMap mGoogleMap;
	
=======
import java.util.List;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

public class PlacesMapActivity extends MapActivity {
>>>>>>> e83148c69b6179eee336db2be81a0fdb78299acc
	// Nearest places
	PlacesList nearPlaces;

	// Map view
<<<<<<< HEAD
//	MapView mapView;
	
	//googlemap
	//GoogleMap mV;

	// Map overlay items
	//List<Overlay> mapOverlays;

	//AddItemizedOverlay itemizedOverlay;

	//GeoPoint geoPoint;
	// Map controllers
	//MapController mc;
	
	double latitude;
	double longitude;
	//OverlayItem overlayitem;
	
	//added for directions
	private MarkerOptions markers;
=======
	MapView mapView;

	// Map overlay items
	List<Overlay> mapOverlays;

	AddItemizedOverlay itemizedOverlay;

	GeoPoint geoPoint;
	// Map controllers
	MapController mc;
	
	double latitude;
	double longitude;
	OverlayItem overlayitem;
>>>>>>> e83148c69b6179eee336db2be81a0fdb78299acc

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.map_places);

		// Getting intent data
		Intent i = getIntent();
		
		// Users current geo location
		String user_latitude = i.getStringExtra("user_latitude");
		String user_longitude = i.getStringExtra("user_longitude");
		
		// Nearplaces list
		nearPlaces = (PlacesList) i.getSerializableExtra("near_places");
<<<<<<< HEAD
		
		//added for v2
		double dblat= Double.parseDouble(user_latitude);
		double dblong= Double.parseDouble(user_longitude);
		
		// Loading map
		initilizeMap();

		// Changing map type
		mGoogleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
		
		// Showing / hiding your current location
		mGoogleMap.setMyLocationEnabled(true);

		// Enable / Disable zooming controls
		mGoogleMap.getUiSettings().setZoomControlsEnabled(false);

		// Enable / Disable my location button
		mGoogleMap.getUiSettings().setMyLocationButtonEnabled(true);

		// Enable / Disable Compass icon
		mGoogleMap.getUiSettings().setCompassEnabled(true);

		// Enable / Disable Rotate gesture
		mGoogleMap.getUiSettings().setRotateGesturesEnabled(true);

		// Enable / Disable zooming functionality
		mGoogleMap.getUiSettings().setZoomGesturesEnabled(true);
		
		

		// create marker
		MarkerOptions marker = new MarkerOptions().position(new LatLng(dblat, dblong)).title("Midpoint is here!");
		 
		// Changing marker icon
		marker.icon(BitmapDescriptorFactory.fromResource(R.drawable.mark_red));
		 
		// adding marker
		mGoogleMap.addMarker(marker);
		
		CameraPosition cameraPosition = new CameraPosition.Builder().target(
              new LatLng(dblat, dblong)).zoom(14).build();

mGoogleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

//check for null in case it is null
if (nearPlaces.results != null) {
	// loop through all the places
	for (Place place : nearPlaces.results) {
		latitude = place.geometry.location.lat; // latitude
		longitude = place.geometry.location.lng; // longitude
		//System.out.println(latitude + "+" + longitude);
		//MarkerOptions markers = new MarkerOptions().position(new LatLng(latitude, longitude)).title(place.name);
		 markers = new MarkerOptions().position(new LatLng(latitude, longitude)).title(place.name);
		 
		// Changing marker icon
		markers.icon(BitmapDescriptorFactory.fromResource(R.drawable.mark_blue));
		 
		// adding marker
		mGoogleMap.addMarker(markers);
	}
}
	}
		//removed for v2
/*
		//mapView = (MapView) findViewById(R.id.mapView);
		//mapView = (MapView) findViewById(R.id.map);
		//mV = ((MapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
		//mapView.setBuiltInZoomControls(true);
		//mV.setBuiltInZoomControls(true);

		//mapOverlays = mapView.getOverlays();
		//mapOverlays = mV.getOverlays();
=======

		mapView = (MapView) findViewById(R.id.mapView);
		mapView.setBuiltInZoomControls(true);

		mapOverlays = mapView.getOverlays();
>>>>>>> e83148c69b6179eee336db2be81a0fdb78299acc
		
		// Geopoint to place on map
		geoPoint = new GeoPoint((int) (Double.parseDouble(user_latitude) * 1E6),
				(int) (Double.parseDouble(user_longitude) * 1E6));
		
		// Drawable marker icon
		Drawable drawable_user = this.getResources()
				.getDrawable(R.drawable.mark_red);
		
		itemizedOverlay = new AddItemizedOverlay(drawable_user, this);
		
		// Map overlay item
		overlayitem = new OverlayItem(geoPoint, "Your Location",
				"That is you!");

		itemizedOverlay.addOverlay(overlayitem);
		
		mapOverlays.add(itemizedOverlay);
		itemizedOverlay.populateNow();
		
		// Drawable marker icon
		Drawable drawable = this.getResources()
				.getDrawable(R.drawable.mark_blue);
		
		itemizedOverlay = new AddItemizedOverlay(drawable, this);

		mc = mapView.getController();

		// These values are used to get map boundary area
		// The area where you can see all the markers on screen
		int minLat = Integer.MAX_VALUE;
		int minLong = Integer.MAX_VALUE;
		int maxLat = Integer.MIN_VALUE;
		int maxLong = Integer.MIN_VALUE;

		// check for null in case it is null
		if (nearPlaces.results != null) {
			// loop through all the places
			for (Place place : nearPlaces.results) {
				latitude = place.geometry.location.lat; // latitude
				longitude = place.geometry.location.lng; // longitude
				
				// Geopoint to place on map
				geoPoint = new GeoPoint((int) (latitude * 1E6),
						(int) (longitude * 1E6));
				
				// Map overlay item
				overlayitem = new OverlayItem(geoPoint, place.name,
						place.vicinity);

				itemizedOverlay.addOverlay(overlayitem);
				
				
				// calculating map boundary area
				minLat  = (int) Math.min( geoPoint.getLatitudeE6(), minLat );
			    minLong = (int) Math.min( geoPoint.getLongitudeE6(), minLong);
			    maxLat  = (int) Math.max( geoPoint.getLatitudeE6(), maxLat );
			    maxLong = (int) Math.max( geoPoint.getLongitudeE6(), maxLong );
			}
			mapOverlays.add(itemizedOverlay);
			
			// showing all overlay items
			itemizedOverlay.populateNow();
		}
		
		// Adjusting the zoom level so that you can see all the markers on map
		mapView.getController().zoomToSpan(Math.abs( minLat - maxLat ), Math.abs( minLong - maxLong ));
		
		// Showing the center of the map
		mc.animateTo(new GeoPoint((maxLat + minLat)/2, (maxLong + minLong)/2 ));
		mapView.postInvalidate();

<<<<<<< HEAD
	}*/

//	@Override
=======
	}

	@Override
>>>>>>> e83148c69b6179eee336db2be81a0fdb78299acc
	protected boolean isRouteDisplayed() {
		return false;
	}

<<<<<<< HEAD




/**
* function to load map If map is not created it will create it for you
* */
	/**
	 * function to load map If map is not created it will create it for you
	 * */
	private void initilizeMap() {
		if (mGoogleMap == null) {
			mGoogleMap = ((MapFragment) getFragmentManager().findFragmentById(
					R.id.map)).getMap();

			// check if map is created successfully or not
			if (mGoogleMap == null) {
				Toast.makeText(getApplicationContext(),
						"Sorry! unable to create maps", Toast.LENGTH_SHORT)
						.show();
			}
		}
	}

	/*@Override
    public boolean onMarkerClick(final Marker marker) {

        if (marker.equals(markers)) 
        {
            //handle click here
        	return true;
        }
        return false;
    }*/

}
=======
}

>>>>>>> e83148c69b6179eee336db2be81a0fdb78299acc
