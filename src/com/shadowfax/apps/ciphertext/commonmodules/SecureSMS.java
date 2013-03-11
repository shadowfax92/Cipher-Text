package com.shadowfax.apps.ciphertext.commonmodules;


import android.content.Intent;

public class SecureSMS {
	String smsContent;
	public SecureSMS(String sms_content)
	{
		smsContent=sms_content;
	}
	public String getSMSContent()
	{
		return smsContent;
				
	}

}
