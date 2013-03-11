package com.shadowfax.apps.ciphertext;

import com.shadowfax.apps.ciphertext.commonmodules.PreferenceOperations;
import com.shadowfax.apps.ciphertext.commonmodules.RetrieveSMS;
import com.shadowfax.apps.ciphertext.commonmodules.SecureSMS;

import java.math.BigInteger;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EncryptPage extends Activity {
    private String key = "";
    private String data = "";

    private Button encryptMessageButton, sendMessageButton;
    private EditText keyEditText, dataEditText, encryptedEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	setContentView(R.layout.encrypt_page);
	init();
	encryptMessageButton.setOnClickListener(new OnClickListener() {

	    public void onClick(View v) {
		// TODO Auto-generated method stub
		key = keyEditText.getText().toString();
		if (key.length() == 8) {
		    data = dataEditText.getText().toString();
		    if (data != "") {
			DES des = new DES();
			byte[] encryptedMessage = des.encrypt(data.getBytes(),
				key.getBytes());
			BigInteger sendingEncryptedMessage = new BigInteger(
				encryptedMessage);
			encryptedEditText.setText(sendingEncryptedMessage
				.toString());
		    } else {
			Toast.makeText(getApplicationContext(),
				    "Enter data to be encrypted!",
				    Toast.LENGTH_LONG).show();

		    }
		} else {
		    Toast.makeText(getApplicationContext(),
			    "Enter a key with 8 characters only!",
			    Toast.LENGTH_LONG).show();
		}

	    }
	});
	sendMessageButton.setOnClickListener(new OnClickListener() {

	    public void onClick(View v) {
		// TODO Auto-generated method stub
		sendSMS(new SecureSMS(encryptedEditText.getText().toString()));

	    }
	});
    }

    private void init() {
	keyEditText = (EditText) findViewById(R.id.encryption_key);
	dataEditText = (EditText) findViewById(R.id.encryption_message);
	encryptedEditText = (EditText) findViewById(R.id.encryption_message_output);

	encryptMessageButton = (Button) findViewById(R.id.encrypt_message_button);
	sendMessageButton = (Button) findViewById(R.id.send_encrypted_message_button);

    }

    private void sendSMS(SecureSMS sms) {
	Intent sendIntent = new Intent(Intent.ACTION_VIEW);
	String sms_content = null;

	sms_content = sms.getSMSContent();
	try {
	    sendIntent.putExtra("sms_body", sms_content);
	    sendIntent.setType("vnd.android-dir/mms-sms");
	    startActivity(sendIntent);
	} catch (Exception e) {
	    // Toast.makeText(getApplicationContext(),"Error Sending the Message: "+e.getMessage(),
	    // Toast.LENGTH_LONG).show();
	}
    }
}
