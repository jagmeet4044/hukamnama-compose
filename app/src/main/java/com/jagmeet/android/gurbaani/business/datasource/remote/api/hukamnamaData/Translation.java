
package com.jagmeet.android.gurbaani.business.datasource.remote.api.hukamnamaData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Translation {

    @SerializedName("english")
    @Expose
    private English_ english;
    @SerializedName("punjabi")
    @Expose
    private Punjabi_ punjabi;
    @SerializedName("spanish")
    @Expose
    private String spanish;

    public English_ getEnglish() {
        return english;
    }

    public void setEnglish(English_ english) {
        this.english = english;
    }

    public Punjabi_ getPunjabi() {
        return punjabi;
    }

    public void setPunjabi(Punjabi_ punjabi) {
        this.punjabi = punjabi;
    }

    public String getSpanish() {
        return spanish;
    }

    public void setSpanish(String spanish) {
        this.spanish = spanish;
    }

}
