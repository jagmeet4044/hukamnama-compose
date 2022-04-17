
package com.jagmeet.android.gurbaani.datasource.network.hukamnama.hukamnama_data;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Transliteration {

    @SerializedName("english")
    @Expose
    private English__2 english;
    @SerializedName("devanagari")
    @Expose
    private Devanagari devanagari;

    public English__2 getEnglish() {
        return english;
    }

    public void setEnglish(English__2 english) {
        this.english = english;
    }

    public Devanagari getDevanagari() {
        return devanagari;
    }

    public void setDevanagari(Devanagari devanagari) {
        this.devanagari = devanagari;
    }

}
