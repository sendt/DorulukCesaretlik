package bayram.dorulukcesaretlik;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class splash extends AppCompatActivity {
private TextView tv;
private ImageView im;
Typeface f1,f2,f3,f4,f5,f6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        tv=(TextView)findViewById(R.id.byram);
        im=(ImageView)findViewById(R.id.resmm);
        f1=Typeface.createFromAsset(getAssets(),"fonts/SEASRN__.ttf");
        f2=Typeface.createFromAsset(getAssets(),"fonts/DancingScript-Regular.otf");
        f3=Typeface.createFromAsset(getAssets(),"fonts/CaviarDreams.ttf");
        f4=Typeface.createFromAsset(getAssets(),"fonts/CaviarDreams_Italic.ttf");
        f5=Typeface.createFromAsset(getAssets(),"fonts/CaviarDreams_BoldItalic.ttf");
        f6=Typeface.createFromAsset(getAssets(),"fonts/Caviar_Dreams_Bold.ttf");


        Animation myanim= AnimationUtils.loadAnimation(this,R.anim.mytransition);
        tv.setTypeface(f2);
        tv.startAnimation(myanim);
        im.startAnimation(myanim);
        final Intent ii=new Intent(this,cevir.class);
        Thread timer=new Thread(){
            public void run(){
                 try {
                     sleep(3000);
                 } catch (InterruptedException e) {
                     e.printStackTrace();
                 }
                 finally {
                            startActivity(ii);
                            finish();
                 }
            }
        };
        timer.start();

    }
}
