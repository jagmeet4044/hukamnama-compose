
package com.jagmeet.android.gurbaani.data.hukamnama.hukamnamaData;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class TodayHukamnama {

    @SerializedName("date")
    @Expose
    private Date date;
    @SerializedName("hukamnamainfo")
    @Expose
    private Hukamnamainfo hukamnamainfo;
    @SerializedName("hukamnama")
    @Expose
    private List<Hukamnama> hukamnama = null;
    @SerializedName("error")
    @Expose
    private Boolean error;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Hukamnamainfo getHukamnamainfo() {
        return hukamnamainfo;
    }

    public void setHukamnamainfo(Hukamnamainfo hukamnamainfo) {
        this.hukamnamainfo = hukamnamainfo;
    }

    public List<Hukamnama> getHukamnama() {
        return hukamnama;
    }

    public void setHukamnama(List<Hukamnama> hukamnama) {
        this.hukamnama = hukamnama;
    }

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

}
