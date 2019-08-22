package bayram.dorulukcesaretlik;

/**
 * Created by Bayram on 12/30/2017.
 */

public class DataEvent {
    private  String durum,kisi_Adi;



    public DataEvent(String durum, String kisi_Adi) {
        this.durum = durum;
        this.kisi_Adi=kisi_Adi;

    }

    public String getDurum() {
        return durum;
    }

    public void setDurum(String durum) {
        this.durum = durum;
    }

    public String getKisi_Adi() {
        return kisi_Adi;
    }

    public void setKisi_Adi(String durum) {
        this.kisi_Adi = kisi_Adi;
    }
}
