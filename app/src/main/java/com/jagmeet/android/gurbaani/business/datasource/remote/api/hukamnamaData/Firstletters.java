
package com.jagmeet.android.gurbaani.business.datasource.remote.api.hukamnamaData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Firstletters {

    @SerializedName("asciichar")
    @Expose
    private String asciichar;
    @SerializedName("akhar")
    @Expose
    private String akhar;
    @SerializedName("unicode")
    @Expose
    private String unicode;

    public String getAsciichar() {
        return asciichar;
    }

    public void setAsciichar(String asciichar) {
        this.asciichar = asciichar;
    }

    public String getAkhar() {
        return akhar;
    }

    public void setAkhar(String akhar) {
        this.akhar = akhar;
    }

    public String getUnicode() {
        return unicode;
    }

    public void setUnicode(String unicode) {
        this.unicode = unicode;
    }

}
