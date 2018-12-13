package com.example.admin.notificationtest;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.AudioAttributes;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;

//8.0提醒发生改变，需要channel帮助，具体改动还得研究。
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private NotificationChannel mChannel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "通知都在这控制管理";
            String description = "我不管，我就是要通知";
            String channelId = "channelId1";//渠道id
            int importance = NotificationManager.IMPORTANCE_DEFAULT;//级别重要性
            mChannel = new NotificationChannel(channelId, name, importance);
            mChannel.setDescription(description);
            mChannel.enableLights(true);
            mChannel.enableVibration(true);

            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(mChannel);
        }
        Button sendNotice = findViewById(R.id.send_notice);
        sendNotice.setOnClickListener(this);
        Button cancelNotice = findViewById(R.id.cancel_notice);
        cancelNotice.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.send_notice:
                Intent intent = new Intent(this, NotificationActivity.class);
                PendingIntent pi = PendingIntent.getActivity(this, 0, intent, 0);

                NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                Notification notification = new NotificationCompat.Builder(this, "default")
                        .setContentTitle("标题")
                        .setContentText("正文")
                        .setContentText("正宗好凉茶正宗好声音欢迎收看由凉茶领导品牌加多宝为您冠名的加多宝凉茶中国好声音喝启力添动力娃哈哈启力精神保健品为中国好声音加油。本届中国好声音当中四位导师最得意的门生将踏上娃哈哈启力音乐梦想之旅。发短信参与互动立即获得苏宁易购的100元优惠券感谢苏宁易购对本节目的大力支持。我们的好声音学员如果获得三位或者三位以上导师认可即可获得苏宁易购提供的1万元音乐梦想基金。感谢上海新锦江大酒店为中国好声音导师提供的酒店赞助。")
                        .setStyle(new NotificationCompat.BigTextStyle().bigText("正宗好凉茶正宗好声音欢迎收看由凉茶领导品牌加多宝为您冠名的加多宝凉茶中国好声音喝启力添动力娃哈哈启力精神保健品为中国好声音加油。本届中国好声音当中四位导师最得意的门生将踏上娃哈哈启力音乐梦想之旅。发短信参与互动立即获得苏宁易购的100元优惠券感谢苏宁易购对本节目的大力支持。我们的好声音学员如果获得三位或者三位以上导师认可即可获得苏宁易购提供的1万元音乐梦想基金。感谢上海新锦江大酒店为中国好声音导师提供的酒店赞助。"))
                        .setStyle(new NotificationCompat.BigPictureStyle().bigPicture(BitmapFactory.decodeResource(getResources(),R.drawable.timg)))
                        .setWhen(System.currentTimeMillis())
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                        .setChannelId("channelId1")
                        .setContentIntent(pi)
                        .setAutoCancel(true)
                        .setSound(Uri.fromFile(new File("/system/media/audio/ringtones/Blues.ogg")))
                        .setVibrate(new long[] {0,5000,1000,1000})
                        .setLights(Color.BLUE,1000,1000)

                        .build();
                manager.notify(1, notification);
                break;
            case R.id.cancel_notice:
                manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                manager.cancel(1);
                break;
            default:
                break;
        }

    }
}
