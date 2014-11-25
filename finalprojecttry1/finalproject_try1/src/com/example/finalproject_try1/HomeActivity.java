package com.example.finalproject_try1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.widget.ArrayAdapter;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;
import android.widget.ListView;

import java.util.Collections;

import com.example.finalproject_try1.mycalculationsendpoint.Mycalculationsendpoint;
import com.example.finalproject_try1.mycalculationsendpoint.model.CollectionResponseMyCalculations;
import com.example.finalproject_try1.mycalculationsendpoint.model.GeoPt;
import com.example.finalproject_try1.mycalculationsendpoint.model.MyCalculations;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.json.jackson2.JacksonFactory;
import android.app.ProgressDialog;

import android.widget.Toast;

public class HomeActivity extends Activity {
	ArrayList arrList;
	TextView selectedOpt;
   // String[] friends = {"Shubra", "Shruthi", "Richard", "Yang", "Sandy"};
    String[] newFriends ;
	ArrayAdapter<String> adapter;
	ListView listView;
	Mycalculationsendpoint endpoint;
	// Progress dialog
	public ProgressDialog pDialog;
	private String friend1;
	private String friend2;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
  
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        
      	Mycalculationsendpoint.Builder builder = new Mycalculationsendpoint.Builder(
                AndroidHttp.newCompatibleTransport(), new JacksonFactory(), 	
            new HttpRequestInitializer() {
                public void initialize(HttpRequest httpRequest) { }
              });

        endpoint = CloudEndpointUtils.updateBuilder(builder).build();
        //Retrieve the List of Registered Users from datastore on the cloud          
        new ListOfFriendsAsyncRetriever(this, endpoint).execute();
		//---- Communication with Server above -------
        System.out.println("Meet-n-Eat: Received List of Registered Users from Server, back to HomeActivity.");        
        

      /*  TextView registerScreen = (TextView) findViewById(R.id.link_to_register);
        
        // Listening to register new account link
        registerScreen.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// Switching to Register screen
				Intent i = new Intent(getApplicationContext(), RegisterActivity.class);
				startActivity(i);
			}
		});*/
        
        TextView planScreen = (TextView) findViewById(R.id.btnPlan);
        
        // Listening to register new account link
        planScreen.setOnClickListener(new View.OnClickListener() {
			
        	 //Added this to go to plan an event page
            public void onClick(View v)
            {
                Intent intent = new Intent(getApplicationContext(), PlanEvent.class);
			 	intent.putExtra("friend1", friend1);
				intent.putExtra("friend2", friend2);
                startActivity(intent);
            }
		});
    }
    /**
     * AsyncTask for retrieving the list of places (e.g., stores) and updating the
     * corresponding results list.
     */
    private class ListOfFriendsAsyncRetriever extends AsyncTask<Void, Void, CollectionResponseMyCalculations> {
     	Mycalculationsendpoint myCalcendpoint;

	    public ListOfFriendsAsyncRetriever(Activity activity, Mycalculationsendpoint endpoint) {
	      this.myCalcendpoint = endpoint;
	    }
	    
		/**
		 * Before starting background thread Show Progress Dialog
		 * */
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(HomeActivity.this);
			pDialog.setMessage(Html.fromHtml("<b>Search</b><br/>Loading Registered Users..."));
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
			pDialog.show();
		} 

      @Override
      protected CollectionResponseMyCalculations doInBackground(Void... params) {


      CollectionResponseMyCalculations result;

  //    MyCalculations myCalc = new MyCalculations();
     
       try {      
    //    	result = endpoint.listMyCalculations().execute();
      	  System.out.println("Meet-n-Eat: listMyCalculation() CALL.");
          result = myCalcendpoint.listMyCalculations().execute();
        } catch (IOException e) {
          // TODO Auto-generated catch block
        	  System.out.println("Meet-n-Eat: IO Exception.");
          e.printStackTrace();
          result = null;
        }
       System.out.println("Meet-n-Eat: listMyCalculation() DONE.");
       return result;
      }

      @Override
  //    @SuppressWarnings("null")
      protected void onPostExecute(CollectionResponseMyCalculations result) {
        
			// dismiss the dialog after getting all products
			pDialog.dismiss();
			
        if (result == null || result.getItems() == null || result.getItems().size() < 1) {
          if (result == null) {
        	  System.out.println("Meet-n-Eat: Retrieving FriendsList failed.");
      //      resultsList.setText("Retrieving places failed.");
          } else {
        	  System.out.println("Meet-n-Eat: No Registered Friends found.");
      //      resultsList.setText("No places found.");
          }

          return;
        }
        System.out.println("Meet-n-Eat: Retrieving FriendsList successful.");
        List<MyCalculations> places = result.getItems();
        StringBuffer placesFound = new StringBuffer();
   //     StringBuilder placesFound = new StringBuilder();
        int Nentries = result.getItems().size();
        newFriends = new String[Nentries];
        int i = 0;
        for (MyCalculations place : places){
        	placesFound.append(place.getName() + " "); 
        	System.out.println("Meet-n-Eat:  " + place.getName() + " " );
        	newFriends[i] =  place.getName();
         	i++;        	
     //     placesFound.append(Double.toString(place.getMidpointLat()) + " " + Double.toString(place.getMidpointLon()) + "\r\n");
        }        
        
        System.out.println("Meet-n-Eat: Number of entries from datastore = " + i);   
        System.out.println("Meet-n-Eat: ListOfPlacesAsyncRetriever::onPostExecute() DONE.");
        for (i =0 ; i < newFriends.length ; i++ )
        {        	
        	System.out.println("Meet-n-Eat:  " + i + " " + newFriends[i]);
        }
      	arrList = new ArrayList();
        //    final String[] friendsArray = getResources().getStringArray(R.array.friends);
        ListView friendsList = (ListView)findViewById(R.id.friends);
        
  //      ArrayAdapter<String> arrayAdpt = new ArrayAdapter<String>(this,
  //      	    android.R.layout.simple_list_item_multiple_choice,friendsArray);
        ArrayAdapter<String> arrayAdpt = new ArrayAdapter<String>(HomeActivity.this,
        		android.R.layout.simple_list_item_multiple_choice ,newFriends);
 
        friendsList.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        friendsList.setAdapter(arrayAdpt);
       // friendsList.
        friendsList.setOnItemClickListener(new OnItemClickListener() 
        {

			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				  if(arrList.contains(arg2))
	                {
	                    arrList.remove((Integer)arg2);
	                }
	                else
	                {
	                    arrList.add(arg2);
	                }
	                
	               Collections.sort(arrList); 
	                String strText = "";
	                
	                for(int i=0 ; i<arrList.size(); i++)	{                   
	                       strText += newFriends[(Integer) arrList.get(i)] + ",";
	                       if(i==0)
	                    	   friend1 = newFriends[(Integer) arrList.get(0)];
	                       else if(i==1)
	                    	   friend2 = newFriends[(Integer) arrList.get(1)];
	                       else
	                    	   System.out.println("Meet-n-Eat:  Incorrect selection - MAX Limit of 2 friends ");
	                }
	                	                
	                Toast.makeText(HomeActivity.this, "Friends Selected: "+ strText, Toast.LENGTH_SHORT).show();                
	            }				
			});
      }
    }
}