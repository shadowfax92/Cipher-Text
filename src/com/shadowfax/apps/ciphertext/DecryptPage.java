package com.shadowfax.apps.ciphertext;

import java.math.BigInteger;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DecryptPage extends Activity {
    private String encrypted_data = "", key = "";
    private Button decryptMessageButton;
    private EditText keyEditText, decryptionMessageEditText,
	    decryptedMessageEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	setContentView(R.layout.decrypt_page);
	init();
	decryptMessageButton.setOnClickListener(new OnClickListener() {

	    public void onClick(View v) {
		// TODO Auto-generated method stub
		DES des = new DES();
		key = keyEditText.getText().toString();
		if (key.length() == 8) {
		    encrypted_data = decryptionMessageEditText.getText()
			    .toString();
		    if(encrypted_data!="")
		    {
		    BigInteger encryptedInput = new BigInteger(encrypted_data);
		    byte[] reverseEncryptedData = encryptedInput.toByteArray();
		    byte[] decryptedData = des.decrypt(reverseEncryptedData,
			    key.getBytes());
		    decryptedMessageEditText.setText(new String(decryptedData));
		    }
		    else
		    {
			Toast.makeText(getApplicationContext(),
				    "Enter data to be decrypted!",
				    Toast.LENGTH_LONG).show();
		    }
		}
		else
		{
		    Toast.makeText(getApplicationContext(),
			    "Enter a key with 8 characters only!",
			    Toast.LENGTH_LONG).show();
		}
	    }
	});

	// code for reading message

    }

    private void init() {
	decryptMessageButton = (Button) findViewById(R.id.decrypt_message_button);
	keyEditText = (EditText) findViewById(R.id.decryption_key);
	decryptionMessageEditText = (EditText) findViewById(R.id.decryption_message);
	decryptedMessageEditText = (EditText) findViewById(R.id.decrypted_message);
    }
}
