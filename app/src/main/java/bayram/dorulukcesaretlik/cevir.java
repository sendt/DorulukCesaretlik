package bayram.dorulukcesaretlik;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import bayram.dorulukcesaretlik.Adapter.WheelImageAdapter;
import bayram.dorulukcesaretlik.Adapter.WheelTextAdapter;
import bayram.dorulukcesaretlik.Data.ImageData;
import bayram.dorulukcesaretlik.Data.MenuItemData;
import github.hellocsl.cursorwheel.CursorWheelLayout;

public class cevir extends AppCompatActivity implements CursorWheelLayout.OnMenuSelectedListener, MyListener {
    cumleler c=new cumleler();
    CursorWheelLayout wheel_text,wheel_image;
    List<MenuItemData> lstText;
    List<ImageData>    lstImage;
    TextView txt,c_kisi;
    String kisiler[];
    String cavirecek_kisi,cv_kisi2;
    Vibrator vib;
    MediaPlayer med;
    Switch tgbtn;
    int device_width, device_height, section;
    RelativeLayout text_container, button_container, null_layout;


//<activity android:name=".dialog" />

   /*<activity android:name=".cumleler" />*/


    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cevir);
        int device_width = getWindowManager().getDefaultDisplay().getWidth();
        int device_height = getWindowManager().getDefaultDisplay().getHeight();
        text_container = findViewById(R.id.text_container);
        button_container = findViewById(R.id.button_container);
        null_layout = findViewById(R.id.null_layout);
        section = device_height / 33;
        med=MediaPlayer.create(this,R.raw.sleep);
        fragmentpenceresi fpencere=new fragmentpenceresi();
        fpencere.show(getFragmentManager(),"My Dialog");
        txt=findViewById(R.id.text);
        tgbtn=(Switch) findViewById(R.id.tgbtn);
        c_kisi=(TextView)findViewById(R.id.kisi);
        vib=(Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        String al=getString(R.string.begining_turn);
        c_kisi.setText(al);
        initViews();
        ViewGroup.LayoutParams image_param = wheel_text.getLayoutParams();
        image_param.height = section * 12;
        image_param.width = section * 12;
        wheel_text.setLayoutParams(image_param);
        wheel_image.setLayoutParams(image_param);
        ViewGroup.LayoutParams container_param = text_container.getLayoutParams();
        container_param.height = section*4;
        ViewGroup.LayoutParams null_param = null_layout.getLayoutParams();
        null_param.height = section;
        text_container.setLayoutParams(container_param);
        button_container.setLayoutParams(container_param);
        null_layout.setLayoutParams(null_param);
        loadData();
        fragmentpenceresi f=new fragmentpenceresi();
        //ara1.setOnMenuSelectedListener(this);
        wheel_text.setOnMenuSelectedListener(this);
        wheel_image.setOnMenuSelectedListener(this);
        kisiler=new String[9];
        tgbtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                   // med=MediaPlayer.create(cevir.this,R.raw.sleep);
                    med.start();


                }else{
                    med.pause();
                }
            }
        });
        //ara1 = (CursorWheelLayout)findViewById(R.id.wheel_image);

    }

    private void loadData() {
        lstText=new ArrayList<>();
        lstText.add(new MenuItemData("OFF"));
        for(int i=0;i<8;i++)
            lstText.add(new MenuItemData(" "+i));
        WheelTextAdapter adapter =  new WheelTextAdapter(getBaseContext(), lstText );
        wheel_text.setAdapter(adapter);


            String c=getString(R.string.DARE);
            String d=getString(R.string.TRUE);
            String p=getString(R.string.PAS);
            lstImage=new ArrayList<>();
            lstImage.add(new ImageData(R.mipmap.p,p));
            lstImage.add(new ImageData(R.mipmap.c,c));
            lstImage.add(new ImageData(R.mipmap.d,d));


            WheelImageAdapter imgAdapter =  new WheelImageAdapter(getBaseContext(), lstImage );
            wheel_image.setAdapter(imgAdapter);

        }



    private void initViews() {
        wheel_image = (CursorWheelLayout)findViewById(R.id.wheel_image);
        wheel_text = (CursorWheelLayout)findViewById(R.id.wheel_text);

    }

    @Override
    public void onItemSelected(CursorWheelLayout parent, View view, int pos) {



            String turn=getString(R.string.whos_turn);
        if(parent.getId()==R.id.wheel_text ) {
            Toast.makeText(getBaseContext(),  lstText.get(pos).mTitle +" "+turn, Toast.LENGTH_SHORT).show();
            if(lstText.get(pos).equals("OFF")){
                String al=getString(R.string.begining_turn);
                c_kisi.setText(al);
                cv_kisi2="OFF";
            }
            else if(!(lstText.get(pos).mTitle.equals("OFF"))){
                cv_kisi2=lstText.get(pos).mTitle;
                String al=getString(R.string.first_turn_then);
                vib.vibrate(250);
                c_kisi.setText(cv_kisi2+" "+al);
                cavirecek_kisi=cv_kisi2;
            }


        }


         if( parent.getId()==R.id.wheel_image){
             Toast.makeText(getBaseContext(),""+lstImage.get(pos).imageDescription+"",Toast.LENGTH_SHORT).show();
                if(!lstImage.get(pos).imageDescription.equals("PASS") && !lstImage.get(pos).imageDescription.equals("Pas") ) // PAS NEW LANGUAGE
                { c.show(getFragmentManager(),"cumleler");
                    String s=lstImage.get(pos).imageDescription;
                    EventBus.getDefault().postSticky(new DataEvent(s,cavirecek_kisi));
                }

            //initViews();
           // loadData();

        }
          // lstImage.get(pos).imageDescription="";

 }



    public void ShowDialog(View view) {
        fragmentpenceresi fpencere=new fragmentpenceresi();
        fpencere.show(getFragmentManager(),"My Dialog");

    }

    @Override
    public void DialogVerisiniGonder(String[] kisiler) {
       // wheel_text.setAdapter(null);

        lstText=new ArrayList<>();
        int y=0;
        for(int i=0;i<8;i++){
            if(kisiler[i].equals(""))y++;}

            if(y==1){lstText.add(new MenuItemData("OFF"));}


        for (int i=0;i<8;i++){
             if(kisiler[i].equals(""))
                //lstText1.add(new MenuItemData(" "+i));
                continue;
           else
            lstText.add(new MenuItemData(kisiler[i]));
        }



        WheelTextAdapter adapter =  new WheelTextAdapter(getBaseContext(), lstText );
        wheel_text.setAdapter(adapter);




    }
    @Subscribe (sticky = true)
    public void onooyle(ooyle event){
        String al=getString(R.string.turn_first_wheel);
                c_kisi.setText(event.getH()+" "+al);

    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    public int dpToPx(int dp) {
        float density = getResources()
                .getDisplayMetrics()
                .density;
        return Math.round((float) dp * density);
    }

}
