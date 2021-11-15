
package com.jagmeet.android.gurbaani.business.datasource.remote.api.hukamnamaData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Transliteration {

    @SerializedName("english")
    @Expose
    private English__ english;
    @SerializedName("devanagari")
    @Expose
    private Devanagari devanagari;

    public English__ getEnglish() {
        return english;
    }

    public void setEnglish(English__ english) {
        this.english = english;
    }

    public Devanagari getDevanagari() {
        return devanagari;
    }

    public void setDevanagari(Devanagari devanagari) {
        this.devanagari = devanagari;
    }

}
