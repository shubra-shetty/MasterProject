package com.example.finalproject_try1;
import com.example.finalproject_try1.mycalculationsendpoint.Mycalculationsendpoint;
import com.example.finalproject_try1.mycalculationsendpoint.model.GeoPt;
import com.example.finalproject_try1.mycalculationsendpoint.model.MyCalculations;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.json.jackson2.JacksonFactory;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.location.Geocoder; 
import android.location.Address;

import java.io.IOException;
import java.lang.Exception;
import java.util.List;

public class RegisterActivity extends Activity {
	 private Button btnRegister;
	 private TextView txtName;
	 private TextView txtAddress;
	  private Mycalculationsendpoint endpoint = null;
	  double userLat, userLong;
	  String valName;
	  String valAddr;
	  
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set View to register.xml
        setContentView(R.layout.register);
        
        TextView loginScreen = (TextView) findViewById(R.id.link_to_login);
        txtName = (TextView) findViewById(R.id.reg_fullname);
        txtAddress = (TextView) findViewById(R.id.reg_address);
             
        // Listening to Login Screen link
        loginScreen.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View arg0) {
				// Switching to Login Screen/closing register screen
				finish();
			}
		});    
  	 
 		 
  		btnRegister = (Button) findViewById(R.id.btnRegister);
		 
			//if click on me, then display the current rating value.
  		btnRegister.setOnClickListener(new OnClickListener() {
		 
			
				public void onClick(View v) {
					valName = txtName.getText().toString();
					valAddr = txtAddress.getText().toString();
					
					if(valName == null || valAddr == null)
						System.out.println("Meet-n-Eat ERROR : Valid Name and Address needed for Registration");
					else
						System.out.println("Meet-n-Eat: Name = " + valName + " Address = " + valAddr);   
		 			//if(!RegisterScreenHasErrors()){ 	
					//Get the Lat/Long from the user Address
					Geocoder geoCoder = new Geocoder(getApplicationContext());
					    if (valAddr != null && !valAddr.isEmpty()) {
					        try {
					            List<Address> addressList = geoCoder. getFromLocationName(valAddr, 1);
					            if (addressList != null && addressList.size() > 0) {
					                userLat = addressList.get(0).getLatitude();
					                userLong = addressList.get(0).getLongitude();
					                System.out.println("Meet-n-Eat: Geocoder result = " + userLat + userLong); 
					            }
					        } catch (Exception e) {
					            e.printStackTrace();
					        } // end catch
					    } // end if	
					    
				Mycalculationsendpoint.Builder builder = new Mycalculationsendpoint.Builder(
				                AndroidHttp.newCompatibleTransport(), new JacksonFactory(), 	
				            new HttpRequestInitializer() {
				                public void initialize(HttpRequest httpRequest) { }
				              });

				            endpoint = CloudEndpointUtils.updateBuilder(builder).build();
					//Insert the GPS location into Google datastore on the cloud
					new MyCalculationsTask(RegisterActivity.this, endpoint).execute();
					//Go back to Login page
					finish();
                   // }   
				}
		 
			});
		 
		  
    }
    /**
     * AsyncTask for calling MyCalculations API to calculate the midPoint (e.g., a store).
     */
    private class MyCalculationsTask extends AsyncTask<Void, Void, Void> {
    	Mycalculationsendpoint myCalcendpoint;

    	    public MyCalculationsTask(Activity activity, Mycalculationsendpoint endpoint) {
    	      this.myCalcendpoint = endpoint;
    	    }
      /**
       * Calls appropriate CloudEndpoint to indicate that user checked into a place.
       *
       * @param params the place where the user is checking in.
       */
      @Override
      protected Void doInBackground(Void... params) {
        MyCalculations myCalc = new MyCalculations();

        // Set the ID of the store where the user is.
        float lat =  (float) userLat;	//37.4075209f;
        float lon =  (float) userLong;	//-121.9376168f;	//end number with f to indicate single precision
        GeoPt friend = new GeoPt();
        friend.setLatitude(lat);
        friend.setLongitude(lon);
        
        myCalc.setLocation(friend);// MidpointLat(37.4075209); // N gps.getLatitude());
     //   myCalc.setMidpointLon(-121.9376168); // W gps.getLongitude());
        myCalc.setAddress(valAddr);
        myCalc.setName(valName); 
        
//		Key k = KeyFactory.stringToKey(myCalc.getPlaceId());			
//		System.out.println("Meet-n-Eat: Generated key for string : " + KeyFactory.keyToString(k));
//		myCalc.setKey(k);
        
    	System.out.println("Meet-n-Eat:  " + myCalc.getLocation().getLatitude());
    	System.out.println("Meet-n-Eat:  " + myCalc.getLocation().getLongitude()); 
    	System.out.println("Meet-n-Eat:  " + myCalc.getName());
  /* 	   CollectionResponseMyCalculations result;
  	
      try {
    	   System.out.println("Meet-n-Eat: Start deleting entries in datastore.");
           result = myCalcendpoint.listMyCalculations().execute();
           List<MyCalculations> places = result.getItems();
           int i = 0;
           if(places.size() >= 1) {
        	   for (MyCalculations place : places){
        		   myCalcendpoint.removeMyCalculations(place.getKey().getId());
        		  	i++;           
        	   }
            	System.out.println("Meet-n-Eat: Number of entries deleted from datastore = " + i);
           }
       } catch (IOException e) {
           // TODO Auto-generated catch block
         	  System.out.println("Meet-n-Eat: IO Exception.");
           e.printStackTrace();
           result = null;
         }	*/
       
   try {        
  //  	   myCalcendpoint.removeMyCalculations(id);
        	 System.out.println("Meet-n-Eat: inserMyCalculations() CALL.");        	 
          myCalcendpoint.insertMyCalculations(myCalc).execute();
        } catch (IOException e) {
          // TODO Auto-generated catch block
        	  System.out.println("Meet-n-Eat: IO Exception.");
          e.printStackTrace();
        }
        System.out.println("Meet-n-Eat: inserMyCalculations() DONE.");
        return null;
      }
    }
/*	
	public boolean RegisterScreenHasErrors() {
		
		boolean HasErr=false;
						
	if(txtName.getText().toString().isEmpty() && txtAddress.getText().toString().isEmpty() && PassInput.getText().toString().isEmpty()) {
				Toast.makeText(RegisterActivity.this,
						"Please Enter Your details to register..",
						Toast.LENGTH_SHORT).show();	
				txtName.setText("");
	            txtAddress.setText("");
	            PassInput.setText("");
	            HasErr = true;
			
		} else if(txtName.getText().toString().isEmpty()) {
		
			Toast.makeText(RegisterActivity.this,
					"Name is blank. Please Try again.",
					Toast.LENGTH_SHORT).show();	
            txtName.setText("");
            HasErr = true;
		} else if(txtAddress.getText().toString().isEmpty()) {
		
			Toast.makeText(RegisterActivity.this,
					"Email Address is blank. Please Try again.",
					Toast.LENGTH_SHORT).show();	
            txtAddress.setText("");
            HasErr = true;
		} else if ((!txtAddress.getText().toString().contains("@")) &&   // Error Check email id to check if its a valid email address
				  (!txtAddress.getText().toString().contains(".")) && 
				  (!txtAddress.getText().toString().contains("com")) &&
				   (!txtAddress.getText().toString().startsWith("@")) &&
				   (!txtAddress.getText().toString().contains("@."))){
			Toast.makeText(RegisterActivity.this,
					"Invalid Email address. Please Try again.",
					Toast.LENGTH_SHORT).show();	
			txtAddress.setText("");
			HasErr = true;
	    } else if (PassInput.getText().toString().isEmpty()) {
			Toast.makeText(RegisterActivity.this,
					"Password is blank. Please Try again.",
					Toast.LENGTH_SHORT).show();	
			PassInput.setText("");
			HasErr = true;
		}
		if(HasErr)
		  return true;
        else 
          return false;
	}
	*/
}