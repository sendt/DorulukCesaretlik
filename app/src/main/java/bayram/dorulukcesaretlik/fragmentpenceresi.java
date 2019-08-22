package bayram.dorulukcesaretlik;

import android.app.Dialog;
import android.app.DialogFragment;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.List;

import bayram.dorulukcesaretlik.Data.MenuItemData;

/**
 * Created by Bayram on 12/26/2017.
 */

public class fragmentpenceresi extends DialogFragment  implements  View.OnClickListener{
    Boolean degr=true;
        Button bitir;
        String kisi[];
        EditText t1,t2,t3,t4,t5,t6,t7,t8;
        TextView tv;
        //MediaPlayer music;
       // RadioButton opn,cls;
    public  View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        //music=MediaPlayer.create(fragmentpenceresi.this,R.raw.sleep);

        kisi=new String[9];
        View view=inflater.inflate(R.layout.my_dialog,null);
        tv=(TextView)view.findViewById(R.id.textvi);
        bitir=(Button)view.findViewById(R.id.btnm);
        bitir.setOnClickListener(this);
        //opn=view.findViewById(R.id.open);
       // cls=view.findViewById(R.id.close);
        //bitir.performClick();
        t1=(EditText) view.findViewById(R.id.txt1);
        t2=(EditText) view.findViewById(R.id.txt2);
        t3=(EditText) view.findViewById(R.id.txt3);
        t4=(EditText) view.findViewById(R.id.txt4);
        t5=(EditText) view.findViewById(R.id.txt5);
        t6=(EditText) view.findViewById(R.id.txt6);
        t7=(EditText) view.findViewById(R.id.txt7);
        t8=(EditText) view.findViewById(R.id.txt8);

        //setCancelable(false);
        return  view;

}

    @Override
    public void onClick(View v) {


        MyListener mylistener= (MyListener) getActivity();

        int y=0;
        if(v.getId()==R.id.btnm){



            if(t1.getText().toString().equals("") && t2.getText().toString().equals("") && t3.getText().toString().equals("") &&
                    t4.getText().toString().equals("") && t5.getText().toString().equals("") && t6.getText().toString().equals("") &&
                    t7.getText().toString().equals("")){
                String cek=getString(R.string.why_no_body_plays);
                Toast.makeText(getActivity(),cek,Toast.LENGTH_SHORT).show();
            }else {
                //kisi[0]="OFF";
                kisi[0]=t1.getText().toString();
                kisi[1]=t2.getText().toString();
                kisi[2]=t3.getText().toString();
                kisi[3]=t4.getText().toString();
                kisi[4]=t5.getText().toString();
                kisi[5]=t6.getText().toString();
                kisi[6]=t7.getText().toString();
                kisi[7]=t8.getText().toString();
                mylistener.DialogVerisiniGonder(kisi);
                String ce=getString(R.string.are_u_ready);
                Toast.makeText(getActivity(),ce,Toast.LENGTH_SHORT).show();

                dismiss();


            }
            //

/*
            for (int i=0;i<kisi.length;i++){
                if(!(kisi[i].equals(""))) degr=false; break;
            }

            if(degr){
                    tv.setText("Oyuncu adını giriniz!");

            }else{*/



            //}

        }
       /* else{
            mylistener.DialogVerisiniGonder(kisi);
            Toast.makeText(getActivity(),"bilgiler alınmadı",Toast.LENGTH_SHORT).show();
            dismiss();

        }*/
    }
}
