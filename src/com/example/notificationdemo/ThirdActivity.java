package com.example.notificationdemo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

public class ThirdActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Toast.makeText(this, "���BUG", Toast.LENGTH_LONG).show();
		//���ֵĲ���
	}

}
