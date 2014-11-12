package com.example.notificationdemo;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RemoteViews;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		findViewById(R.id.button1).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				onButton1Click();
			}
		});
		findViewById(R.id.button2).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				onButton2Click();
			}
		});
		findViewById(R.id.button3).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				onButton3Click();
			}
		});
		findViewById(R.id.button4).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				onButton4Click();
			}
		});
		findViewById(R.id.button5).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				onButton5Click();
			}
		});
	}

	public void onButton1Click() {
		NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(
				this).setSmallIcon(R.drawable.ic_launcher)
				.setContentTitle("My notification")
				.setContentText("Hello World!");
		// Creates an explicit intent for an Activity in your app
		Intent resultIntent = new Intent(this, SecondActivity.class);

		// The stack builder object will contain an artificial back stack for
		// the
		// started Activity.
		// This ensures that navigating backward from the Activity leads out of
		// your application to the Home screen.
		TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
		// Adds the back stack for the Intent (but not the Intent itself)
		stackBuilder.addParentStack(MainActivity.class);
		// Adds the Intent that starts the Activity to the top of the stack
		stackBuilder.addNextIntent(resultIntent);
		PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0,
				PendingIntent.FLAG_UPDATE_CURRENT);
		mBuilder.setContentIntent(resultPendingIntent);
		NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		// mId allows you to update the notification later on.
		mNotificationManager.notify(1000, mBuilder.build());
	}

	public void onButton2Click() {
		NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(
				this).setSmallIcon(R.drawable.ic_launcher)
				.setContentTitle("My notification")
				.setContentText("Hello World!");
		NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();
		String[] events = new String[3];
		// Sets a title for the Inbox in expanded layout
		inboxStyle.setBigContentTitle("Event tracker details:");
		// Moves events into the expanded layout
		for (int i = 0; i < events.length; i++) {
			inboxStyle.addLine("sssssssssssss");
			inboxStyle.setSummaryText("mtwain@android.com");
		}
		// Moves the expanded layout object into the notification object.
		mBuilder.setStyle(inboxStyle);

		Intent intent = new Intent(this, SecondActivity.class);
		PendingIntent resultPendingIntent = PendingIntent.getActivity(this,
				123, intent, PendingIntent.FLAG_UPDATE_CURRENT);
		mBuilder.setContentIntent(resultPendingIntent);
		NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		mNotificationManager.notify(1000, mBuilder.build());
	}

	public void onButton3Click() {
		final NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(
				this).setSmallIcon(R.drawable.ic_launcher)
				.setContentTitle("My notification")
				.setContentText("Hello World!").setAutoCancel(true);
		final NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

		// 通过一个子线程，动态增加进度条刻度
		new Thread(new Runnable() {
			@Override
			public void run() {
				int incr;
				for (incr = 0; incr <= 100; incr += 5) {
					mBuilder.setProgress(100, incr, false);
					mNotificationManager.notify(1000, mBuilder.build());
					try {
						Thread.sleep(300);
					} catch (InterruptedException e) {
						Log.i("MainActivity", "sleep failure");
					}
				}
				mBuilder.setContentText("Download complete").setProgress(0, 0,
						false);
				mNotificationManager.notify(1000, mBuilder.build());
			}
		}).start();

	}

	public void onButton4Click() {
		final NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(
				this).setSmallIcon(R.drawable.ic_launcher)
				.setContentTitle("My notification")
				.setContentText("Hello World!").setAutoCancel(true);
		final NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		mBuilder.setProgress(0, 0, true);// 设置为true，表示流动
		mNotificationManager.notify(1000, mBuilder.build());

		// 通过一个子线程，动态增加进度条刻度
		new Thread(new Runnable() {
			@Override
			public void run() {

				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					Log.i("MainActivity", "sleep failure");
				}

				mBuilder.setProgress(100, 100, true);// 设置为true，表示刻度
				mNotificationManager.notify(1000, mBuilder.build());

			}
		}).start();

	}

	public void onButton5Click() {
		RemoteViews contentViews = new RemoteViews(getPackageName(),
				R.layout.custom_notifcation);
		// 通过控件的Id设置属性
		contentViews.setImageViewResource(R.id.imageNo, R.drawable.ic_launcher);
		contentViews.setTextViewText(R.id.titleNo, "自定义通知标题");
		contentViews.setTextViewText(R.id.textNo, "自定义通知内容");
		Intent intent = new Intent(MainActivity.this, SecondActivity.class);
		PendingIntent pendingIntent = PendingIntent
				.getActivity(MainActivity.this, 0, intent,
						PendingIntent.FLAG_CANCEL_CURRENT);
		NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(
				MainActivity.this).setSmallIcon(R.drawable.ic_launcher)
				.setContentTitle("My notification").setTicker("new message");
		mBuilder.setAutoCancel(true);
		mBuilder.setContentIntent(pendingIntent);
		mBuilder.setContent(contentViews);
		mBuilder.setAutoCancel(true);
		NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		mNotificationManager.notify(1000, mBuilder.build());

	}
}
