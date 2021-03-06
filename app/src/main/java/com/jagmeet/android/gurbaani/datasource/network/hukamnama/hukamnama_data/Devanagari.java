
package com.jagmeet.android.gurbaani.datasource.network.hukamnama.hukamnama_data;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Devanagari {

    @SerializedName("text")
    @Expose
    private String text;
    @SerializedName("larivaar")
    @Expose
    private String larivaar;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getLarivaar() {
        return larivaar;
    }

    public void setLarivaar(String larivaar) {
        this.larivaar = larivaar;
    }

}
