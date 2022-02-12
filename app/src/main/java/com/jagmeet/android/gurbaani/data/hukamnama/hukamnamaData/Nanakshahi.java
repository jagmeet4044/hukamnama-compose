
package com.jagmeet.android.gurbaani.data.hukamnama.hukamnamaData;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Nanakshahi {

    @SerializedName("english")
    @Expose
    private English english;
    @SerializedName("punjabi")
    @Expose
    private Punjabi punjabi;

    public English getEnglish() {
        return english;
    }

    public void setEnglish(English english) {
        this.english = english;
    }

    public Punjabi getPunjabi() {
        return punjabi;
    }

    public void setPunjabi(Punjabi punjabi) {
        this.punjabi = punjabi;
    }

}
