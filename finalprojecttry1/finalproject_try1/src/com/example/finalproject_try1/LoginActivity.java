package com.example.finalproject_try1;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends Activity {
	// Use this DEBUG token to enable debugging in code.
	// true - to enable debug messages.
	// false - to disable debug messages.
	private static final boolean DEBUG = false;
	EditText EmailInput;
	EditText PassInput;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
		EmailInput = (EditText) findViewById(R.id.reg_lemailid);
		PassInput = (EditText) findViewById(R.id.reg_lpassword);
		TextView registerScreen = (TextView) findViewById(R.id.link_to_register);

		addListenerOntextfields();
       
        
        // Listening to register new account link
        registerScreen.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// Switching to Register screen
				Intent i = new Intent(getApplicationContext(), RegisterActivity.class);
				startActivity(i);
			}
		});
        
       
        TextView homeScreen = (TextView) findViewById(R.id.btnLogin);   
        
        
        // Listening to register new account link
        homeScreen.setOnClickListener(new View.OnClickListener() {

			//Added this to go to home page
			public void onClick(View v)
			{
if(DEBUG) {
				Toast.makeText(LoginActivity.this,
						"Entered Values: Email: "+EmailInput.getText().toString()+", Password: "+PassInput.getText().toString(),
						Toast.LENGTH_SHORT).show();
}
				if(!LoginScreenHasErrors()){
					
					//Send values to google app engnie to authenticate
					
					//receive ack to know if authentication was successful. If not, print message and reload this page.
					
					// If login was successful, load the home screen
					Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
					startActivity(intent);
				}
			}
		});

    }
	public boolean LoginScreenHasErrors() {
		
		boolean HasErr=false;
						
	if(EmailInput.getText().toString().isEmpty() && PassInput.getText().toString().isEmpty()) {
				Toast.makeText(LoginActivity.this,
						"Please Enter Email address and Password to continue..",
						Toast.LENGTH_SHORT).show();	
	            EmailInput.setText("");
	            PassInput.setText("");
	            HasErr = true;
			
		} else if(EmailInput.getText().toString().isEmpty()) {
		
			Toast.makeText(LoginActivity.this,
					"Email Address is blank. Please Try again.",
					Toast.LENGTH_SHORT).show();	
            EmailInput.setText("");
            HasErr = true;
		} else if ((!EmailInput.getText().toString().contains("@")) &&   // Error Check email id to check if its a valid email address
				  (!EmailInput.getText().toString().contains(".")) && 
				  (!EmailInput.getText().toString().contains("com")) &&
				   (!EmailInput.getText().toString().startsWith("@")) &&
				   (!EmailInput.getText().toString().contains("@."))){
			Toast.makeText(LoginActivity.this,
					"Invalid Email address. Please Try again.",
					Toast.LENGTH_SHORT).show();	
			EmailInput.setText("");
			HasErr = true;
	    } else if (PassInput.getText().toString().isEmpty()) {
			Toast.makeText(LoginActivity.this,
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

	public void addListenerOntextfields() {


		EmailInput = (EditText) findViewById(R.id.reg_lemailid);
		PassInput = (EditText) findViewById(R.id.reg_lpassword);

	}


}