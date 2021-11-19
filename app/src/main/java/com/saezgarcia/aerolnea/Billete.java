package com.saezgarcia.aerolnea;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Billete implements Parcelable { /* Es un objeto parcelable que usamos para
                                                poder mover entre fragmentos los datos con mayor facilidad
                                                y que sea compatible con el bundle. Al ser parcelable, tenemos
                                                que importar ciertos métodos.
                                             */

    //Propiedades del objeto
    private String origin;
    private String destiny;
    private String date;
    private ArrayList<String> checkbox;
    private String[] data;

    //Constructor del objeto
    public Billete(String origin, String destiny, String date, ArrayList<String> checkbox, String[] data){
        this.origin = origin;
        this.destiny = destiny;
        this.date = date;
        this.checkbox = checkbox;
        this.data = data;
    }

    protected Billete(Parcel in) {
        origin = in.readString();
        destiny = in.readString();
        date = in.readString();
        checkbox = in.createStringArrayList();
        data = in.createStringArray();
    }

    public static final Creator<Billete> CREATOR = new Creator<Billete>() {
        @Override
        public Billete createFromParcel(Parcel in) {
            return new Billete(in);
        }

        @Override
        public Billete[] newArray(int size) {
            return new Billete[size];
        }
    };

    //region getters y setters de todos los campos del objeto
    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestiny() {
        return destiny;
    }

    public void setDestiny(String destiny) {
        this.destiny = destiny;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public ArrayList<String> getCheckbox() {
        return checkbox;
    }

    public void setCheckbox(ArrayList<String> checkbox) {
        this.checkbox = checkbox;
    }

    public String[] getData() {
        return data;
    }

    public void setData(String[] data) {
        this.data = data;
    }
    //endregion
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(origin);
        parcel.writeString(destiny);
        parcel.writeString(date);
        parcel.writeStringList(checkbox);
        parcel.writeStringArray(data);
    }
}
