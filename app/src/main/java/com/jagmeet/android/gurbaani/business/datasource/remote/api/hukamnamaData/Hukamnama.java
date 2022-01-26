
package com.jagmeet.android.gurbaani.business.datasource.remote.api.hukamnamaData;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Hukamnama {

    @SerializedName("line")
    @Expose
    private Line line;

    public Line getLine() {
        return line;
    }

    public void setLine(Line line) {
        this.line = line;
    }

}
