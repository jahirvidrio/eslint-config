class A {
  private void sendNotification(String messageTitle, String messageBody, Map<String, String> data) {
    Intent intent = HomeActivity.getHomeActivityIntent(this,data.get(Constants.PUSH_URL));
    PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,PendingIntent.FLAG_ONE_SHOT);

    NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this, "1")
      .setSmallIcon(R.drawable.icon_notification)
      .setContentTitle(messageTitle)
      .setContentText(messageBody)
      .setContentIntent(pendingIntent)
      .setDefaults(DEFAULT_SOUND | DEFAULT_VIBRATE) //Important for heads-up notification
      .setPriority(Notification.PRIORITY_MAX); //Important for heads-up notification

    Notification buildNotification = mBuilder.build();

    int notifyId = (int) System.currentTimeMillis(); //For each push the older one will not be replaced for this unique id
    // Since android Oreo notification channel is needed.
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
      String name = getString(R.string.channel_name);
      String description = getString(R.string.channel_description);
      int importance = NotificationManager.IMPORTANCE_HIGH; //Important   for heads-up notification
      NotificationChannel channel = new NotificationChannel(getResources().getString(R.string.default_notification_channel_id),name,importance);
      channel.setDescription(description);
      channel.setShowBadge(true);
      channel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);
      NotificationManager notificationManager = getSystemService(NotificationManager.class);
      if (notificationManager != null) {
        notificationManager.createNotificationChannel(channel);
        notificationManager.notify(notifyId, buildNotification);
      }
    } else {
      NotificationManager mNotifyMgr =   (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
      if (mNotifyMgr != null) {
        mNotifyMgr.notify(notifyId, buildNotification);
      }
    }
  }
}