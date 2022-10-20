package com.example.map_my_sona;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
    private static int sleeptimer=2700;
    TextView text1;
//    TextView text2;
    LottieAnimationView lottieAnimationView;
    DatabaseReference databaseReference;
    Boolean boo_str;
    String strb;
    private static int SPLASH_TIME_OUT=3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text1 = findViewById(R.id.text1);
//        text2 = findViewById(R.id.text2);
        lottieAnimationView= findViewById(R.id.lottie);

        YoYo.with(Techniques.FadeIn).duration(2000).playOn(text1);
        text1.animate().translationY(2200).setDuration(1000).setStartDelay(4000);
//        text2.animate().translationY(1400).setDuration(1000).setStartDelay(4000);
        lottieAnimationView.animate().translationY(2200).setDuration(1000).setStartDelay(4000);

//
//        Animation move_anim = AnimationUtils.loadAnimation(getApplicationContext(),
//                R.anim.move_anim);
//        text1.startAnimation(move_anim);


        new Handler().postDelayed(new Runnable() {
         @Override
           public void run() {

             databaseReference = FirebaseDatabase.getInstance().getReference("SonaStars");
             databaseReference.addValueEventListener(new ValueEventListener() {
                 @Override
                 public void onDataChange(@NonNull DataSnapshot snapshot) {

                     String OldDate1=snapshot.child("SonaCollege").child("init_date").getValue(String.class);
                     strb=snapshot.child("SonaCollege").child("bool").getValue(String.class);
                     boo_str=Boolean.parseBoolean(strb);

                     String pattern = "MM-dd-yyyy";
                     SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
                     String date1 = simpleDateFormat.format(new Date());

                     int dateDifference = (int) getDateDiff(new SimpleDateFormat("MM-dd-yyyy"), OldDate1, date1);
                     System.out.println("dateDifference: " + dateDifference);
                     System.out.println(date1);
                     System.out.println(OldDate1);

                     if(dateDifference<=15 && boo_str){
                         Intent i=new Intent(MainActivity.this,loginpage.class);
                         i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                         startActivity(i);
                         finish();
                     }
                     else{
                         Intent i=new Intent(MainActivity.this,SubsribtionPeriodOver.class);
                         i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                         startActivity(i);
                         finish();
                     }
                 }

                 @Override
                 public void onCancelled(@NonNull DatabaseError error) {

                 }
             });
//             SharedPreferences sharedPreferences = getSharedPreferences(loginpage.PREFS_NAME,0);
//             boolean hasLoggedIn = sharedPreferences.getBoolean("hasLoggedIn",false);
//
//             if(hasLoggedIn){
//                 Intent i=new Intent(MainActivity.this,dashboard.class);
//                 startActivity(i);
//                 finish();
//             } else  {
//                 Intent a=new Intent(MainActivity.this,loginpage.class);
//                 startActivity(a);
//             }
              }
             },SPLASH_TIME_OUT
        );
    }
    public static long getDateDiff(SimpleDateFormat format, String oldDate, String newDate) {
        try {
            return TimeUnit.DAYS.convert(format.parse(newDate).getTime() - format.parse(oldDate).getTime(), TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    }
