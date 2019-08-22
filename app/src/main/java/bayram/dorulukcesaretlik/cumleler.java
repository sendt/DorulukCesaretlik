package bayram.dorulukcesaretlik;

import android.app.DialogFragment;
import android.content.res.Resources;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.Random;

public class cumleler extends DialogFragment {
        TextView tx,who_text;
        ImageView im;
        Button btn,ask_new;
        String drm,hh,cv_kisi;
        String dare[],truth[];
    ArrayList<String> d = new ArrayList<String>();
    ArrayList<String> c = new ArrayList<String>();

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

         View view=inflater.inflate(R.layout.activity_cumleler,null);
        Resources res = getResources();
        dare = res.getStringArray(R.array.dare);
        truth = res.getStringArray(R.array.truth);

        who_text=(TextView)view.findViewById(R.id.whos_turn_txt);
        im=(ImageView)view.findViewById(R.id.i_vi);
        tx=(TextView)view.findViewById(R.id.textalani);
        ask_new=(Button)view.findViewById(R.id.ask_new);
        btn=(Button)view.findViewById(R.id.btntamam);
        //btn.setOnClickListener(this);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //cevir cvr=new cevir();

                if(v.getId()==R.id.btntamam){
                    //cevir c=new cevir();
                    //hh="tamam";
                    EventBus.getDefault().postSticky(new ooyle(cv_kisi));
                    dismiss();

                }
            }
        });
        ask_new.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v.getId()==R.id.ask_new && (drm.equals("DARE") ||  drm.equals("Cesaretlik") )){ //|| drm.equals("CESARET")

                    Random r=new Random(); //random sınıfı
                    int a=r.nextInt(dare.length);
                    tx.setText(dare[a]);
                   // who_text.setText(drm);
                }
                else if(v.getId()==R.id.ask_new && (drm.equals("TRUE") ) || drm.equals("Doğruluk") ){ //|| drm.equals("DOĞRULUK")

                    Random r=new Random(); //random sınıfı
                    int a=r.nextInt(truth.length);
                    tx.setText(truth[a]);
                    //who_text.setText(drm);

                }
            }
        });

        setCancelable(false);

        return  view;

    }

    @Subscribe (sticky = true)
    public void onDataEvent(DataEvent event){
        Log.e("durum bilgisi","alındı");
    drm=event.getDurum();
    cv_kisi=event.getKisi_Adi();
        who_text.setText(drm);
       // int sayi = (int)(Math.random()*4);

    if(drm.equals("DARE") || drm.equals("Cesaretlik") ){  // diger diller
        //Resources res = getResources();
        //String[] dare = res.getStringArray(R.array.dare);
        Random r=new Random(); //random sınıfı
        int a=r.nextInt(dare.length);
        tx.setText(dare[a]);
        im.setImageResource(R.mipmap.dar);

    }

    if(drm.equals("TRUE") || drm.equals("Doğruluk")){  //|| drm.equals("DOĞRULUK")
        //Resources res = getResources();
       // String[] truth = res.getStringArray(R.array.truth);
        Random r=new Random(); //random sınıfı
        int a=r.nextInt(truth.length);
        tx.setText(truth[a]);
        im.setImageResource(R.mipmap.tru);

    }


    }
    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

}
