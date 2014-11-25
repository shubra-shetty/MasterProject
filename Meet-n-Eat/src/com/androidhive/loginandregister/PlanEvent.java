package com.androidhive.loginandregister;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.RatingBar.OnRatingBarChangeListener;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class PlanEvent extends Activity{
	 private RatingBar ratingBar;
	  private TextView txtRatingValue;
	  private Button btnSubmit;
	  private Spinner spinner1, spinner2;
	  
	  @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.plan);
	        
	      /*  TextView registerScreen = (TextView) findViewById(R.id.link_to_register);
	        
	        // Listening to register new account link
	        registerScreen.setOnClickListener(new View.OnClickListener() {
				
				public void onClick(View v) {
					// Switching to Register screen
					Intent i = new Intent(getApplicationContext(), RegisterActivity.class);
					startActivity(i);
				}
			});*/
	  
	        addListenerOnRatingBar();
	       // addItemsOnSpinner2();
	    	addListenerOnButton();
	    	addListenerOnSpinnerItemSelection();
	    	//addListenerOnButton();
	  }  
	    	 public void addListenerOnRatingBar() {
	    		 
	    			ratingBar = (RatingBar) findViewById(R.id.ratingBar);
	    			txtRatingValue = (TextView) findViewById(R.id.txtRatingValue);
	    		 
	    			//if rating value is changed,
	    			//display the current rating value in the result (textview) automatically
	    			ratingBar.setOnRatingBarChangeListener(new OnRatingBarChangeListener() {
	    				public void onRatingChanged(RatingBar ratingBar, float rating,
	    					boolean fromUser) {
	    		 
	    					txtRatingValue.setText(String.valueOf(rating));
	    		 
	    				}
	    			});
	    		  }
	    		 
	    		  public void addListenerOnButton() {
	    		 
	    			ratingBar = (RatingBar) findViewById(R.id.ratingBar);
	    			spinner1 = (Spinner) findViewById(R.id.spinner1);
	    			spinner2 = (Spinner) findViewById(R.id.spinner2);
	    			btnSubmit = (Button) findViewById(R.id.btnSubmit);
	    		 
	    			//if click on me, then display the current rating value.
	    			btnSubmit.setOnClickListener(new OnClickListener() {
	    		 
	    			
	    				public void onClick(View v) {
	    		 
	    					Toast.makeText(PlanEvent.this,
	    							"OnClickListener : " + 
	    							String.valueOf(ratingBar.getRating()) + "\nSpinner 1 : "+ String.valueOf(spinner1.getSelectedItem()) + 
	    			                "\nSpinner 2 : "+ String.valueOf(spinner2.getSelectedItem()),
	    							Toast.LENGTH_SHORT).show();
	    		 
	    					Intent intent = new Intent(getApplicationContext(), MainActivity.class);
	    	                startActivity(intent);
	    		 
	    				}
	    		 
	    			});
	    		 
	    		  }
	    		  
	    		  
	    		  public void addListenerOnSpinnerItemSelection() {
	    				spinner1 = (Spinner) findViewById(R.id.spinner1);
	    				spinner1.setOnItemSelectedListener(new CustomOnItemSelectedListener());
	    				
	    				spinner2 = (Spinner) findViewById(R.id.spinner2);
	    				spinner2.setOnItemSelectedListener(new CustomOnItemSelectedListener());
	    			  }
	    		  
	    		}