package com.shadowfax.apps.ciphertext;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
	public static final int navigate_to_encrypt_page=100;
	public static final int navigate_to_decrypt_page=101;
	
	Button encryptButton, decryptButton;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //call initialize function
        init();
        
        //calling the pages when button is pressed
        encryptButton.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent navigateToEncryptPage = new Intent(MainActivity.this, EncryptPage.class);
				startActivityForResult(navigateToEncryptPage, navigate_to_encrypt_page);	
			}
		});
        decryptButton.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent navigateToDecryptPage = new Intent(MainActivity.this, DecryptPage.class);
				startActivityForResult(navigateToDecryptPage, navigate_to_decrypt_page);
			}
		});
        
    }

    private void init()
    {
    	encryptButton=(Button)findViewById(R.id.encrypt_button);
    	decryptButton=(Button)findViewById(R.id.decrypt_button);
    	
    	
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
