package com.androidhive.loginandregister;
import java.util.ArrayList;

import android.app.Activity;
import android.app.ListActivity;
import android.widget.ArrayAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;
import android.widget.ListView;

import java.util.Collections;

import android.widget.Toast;

public class HomeActivity extends Activity {
	ArrayList arrList;
	TextView selectedOpt;
    String[] friends={"Shubra", "Shruthi", "Richard", "Yang", "Sandy"};
    
	ArrayAdapter<String> adapter;
	ListView listView;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	arrList = new ArrayList();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        final String[] friendsArray = getResources().getStringArray(R.array.friends);
        ListView friendsList = (ListView)findViewById(R.id.friends);
        ArrayAdapter<String> arrayAdpt = new ArrayAdapter<String>(this,
        	    android.R.layout.simple_list_item_multiple_choice,friendsArray);
        
        friendsList.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        friendsList.setAdapter(arrayAdpt);
        
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
	                
	                for(int i=0 ; i<arrList.size(); i++)                   
	                       strText += friends[(Integer) arrList.get(i)] + ",";
	                
	                
	                Toast.makeText(HomeActivity.this, "Friends Selected: "+ strText, Toast.LENGTH_SHORT).show();                
	            }
				
			}
        	
        );
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
                startActivity(intent);
            }
		});
    }
}