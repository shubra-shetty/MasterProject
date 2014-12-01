package com.example.finalproject_try1;


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
	  private String friend1;
	  private String friend2;
	  @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.plan);
	        
			Intent i = getIntent();
			
			// Users current geo location
			friend1 = i.getStringExtra("friend1");
			friend2 = i.getStringExtra("friend2");
			
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
<<<<<<< HEAD
	    			//txtRatingValue = (TextView) findViewById(R.id.txtRatingValue);
=======
	    			txtRatingValue = (TextView) findViewById(R.id.txtRatingValue);
>>>>>>> e83148c69b6179eee336db2be81a0fdb78299acc
	    		 
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
<<<<<<< HEAD
	    			//spinner1 = (Spinner) findViewById(R.id.spinner1);
=======
	    			spinner1 = (Spinner) findViewById(R.id.spinner1);
>>>>>>> e83148c69b6179eee336db2be81a0fdb78299acc
	    			spinner2 = (Spinner) findViewById(R.id.spinner2);
	    			btnSubmit = (Button) findViewById(R.id.btnSubmit);
	    		 
	    			//if click on me, then display the current rating value.
	    			btnSubmit.setOnClickListener(new OnClickListener() {
	    		 
	    			
	    				public void onClick(View v) {
	    		 
<<<<<<< HEAD
	    					/*Toast.makeText(PlanEvent.this,
	    							"OnClickListener : " + 
	    							String.valueOf(ratingBar.getRating()) + "\nSpinner 1 : "+ String.valueOf(spinner1.getSelectedItem()) + 
	    			                "\nSpinner 2 : "+ String.valueOf(spinner2.getSelectedItem()),
	    							Toast.LENGTH_SHORT).show();*/
=======
	    					Toast.makeText(PlanEvent.this,
	    							"OnClickListener : " + 
	    							String.valueOf(ratingBar.getRating()) + "\nSpinner 1 : "+ String.valueOf(spinner1.getSelectedItem()) + 
	    			                "\nSpinner 2 : "+ String.valueOf(spinner2.getSelectedItem()),
	    							Toast.LENGTH_SHORT).show();
>>>>>>> e83148c69b6179eee336db2be81a0fdb78299acc
	    		 
	    					Intent intent = new Intent(getApplicationContext(), MainActivity.class);
	    				 	intent.putExtra("friend1", friend1);
	    					intent.putExtra("friend2", friend2);
<<<<<<< HEAD
	    					intent.putExtra("rating",String.valueOf(ratingBar.getRating()));
	    					intent.putExtra("typec",String.valueOf(spinner2.getSelectedItem()));
	    					
	    					/*
	    					//added to pass values to the next activity--start
	    					Bundle choices = new Bundle();
	    					choices.putString("rating", String.valueOf(ratingBar.getRating()));
	    					//choices.putString("cuisine",String.valueOf(spinner1.getSelectedItem()));
	    					choices.putString("typec",String.valueOf(spinner2.getSelectedItem()));
	    					int[] myLittleArray = { 1, 2, 3, 4};
	    					choices.putIntArray("myIntArray1", myLittleArray);

	    					intent.putExtras(choices);
	    					
	    					//added to pass values to the next activity--end
	    					
	    					*/
=======
>>>>>>> e83148c69b6179eee336db2be81a0fdb78299acc
	    					startActivity(intent);
	    		 
	    				}
	    		 
	    			});
	    		 
	    		  }
	    		  
	    		  
	    		  public void addListenerOnSpinnerItemSelection() {
<<<<<<< HEAD
	    				//spinner1 = (Spinner) findViewById(R.id.spinner1);
	    				//spinner1.setOnItemSelectedListener(new CustomOnItemSelectedListener());
=======
	    				spinner1 = (Spinner) findViewById(R.id.spinner1);
	    				spinner1.setOnItemSelectedListener(new CustomOnItemSelectedListener());
>>>>>>> e83148c69b6179eee336db2be81a0fdb78299acc
	    				
	    				spinner2 = (Spinner) findViewById(R.id.spinner2);
	    				spinner2.setOnItemSelectedListener(new CustomOnItemSelectedListener());
	    			  }
	    		  
	    		}