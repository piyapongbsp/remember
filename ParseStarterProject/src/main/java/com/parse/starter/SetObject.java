package com.parse.starter;

/**
 * Created by TIW on 9/9/2015.
 */
public class SetObject {

    String _name,_thai,_japan,_english;

    public SetObject(String name, String eng, String jap, String th) {
        this._name = name;
        this._english = eng;
        this._thai = th;
        this._japan = jap;
    }

//    public void setName(String strName){this._name = strName;}
//    public void setThai(String strThai){this._thai = strThai;}
//    public void setJapan(String strJapan){this._japan = strJapan;}
//    public void setEnglish(String strEnglish){this._english = strEnglish;}

    public String getName(){return this._name;}
    public String getThai(){return  this._thai;}
    public String getJapan(){return this._japan;}
    public String getEnglish(){return this._english;}

}
