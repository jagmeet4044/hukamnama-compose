
package com.jagmeet.android.gurbaani.data.hukamnama.hukamnamaData;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Translation {

    @SerializedName("english")
    @Expose
    private English__1 english;
    @SerializedName("punjabi")
    @Expose
    private Punjabi__1 punjabi;
    @SerializedName("spanish")
    @Expose
    private String spanish;

    public English__1 getEnglish() {
        return english;
    }

    public void setEnglish(English__1 english) {
        this.english = english;
    }

    public Punjabi__1 getPunjabi() {
        return punjabi;
    }

    public void setPunjabi(Punjabi__1 punjabi) {
        this.punjabi = punjabi;
    }

    public String getSpanish() {
        return spanish;
    }

    public void setSpanish(String spanish) {
        this.spanish = spanish;
    }

}
