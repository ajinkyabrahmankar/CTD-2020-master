package yalantis.com.sidemenu.sample;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    public static int ID=1;


    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        generateNotification(remoteMessage.getNotification().getBody(), remoteMessage.getNotification().getTitle());

    }

    private void generateNotification(String body, String title) {

        Intent intent=new Intent(this,MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent=PendingIntent.getActivity(this,0,intent, PendingIntent.FLAG_ONE_SHOT);
        Uri soundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder=new NotificationCompat.Builder(this)
                .setLargeIcon(BitmapFactory.decodeResource(getApplicationContext().getResources(),R.drawable.ic_stat_name))
                .setSmallIcon(R.drawable.ic_stat_name)
                //.setColor(getResources().getColor(R.color.colorAccent))
                .setContentTitle(title)
                .setContentText(body)
                .setAutoCancel(true)
                .setSound(soundUri)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        if(ID>1073741824)
            ID=0;
        notificationManager.notify(ID++,notificationBuilder.build());
    }
}
