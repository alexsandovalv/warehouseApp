package pe.edu.upc.warehouse;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashActivity extends AppCompatActivity {

    private ImageView imgLogo;
    private TextView txtView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        imgLogo = (ImageView)findViewById(R.id.splashActivityIvLogo);
        txtView = (TextView) findViewById(R.id.splashActivitytvLogo);

        Animation animation = AnimationUtils.loadAnimation(this, R.anim.animation);

        imgLogo.startAnimation(animation);
        txtView.startAnimation(animation);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                finish();
            }
        }, 4000);
    }
}
