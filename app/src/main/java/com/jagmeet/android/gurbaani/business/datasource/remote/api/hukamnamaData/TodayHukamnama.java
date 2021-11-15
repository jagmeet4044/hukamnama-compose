
package com.jagmeet.android.gurbaani.business.datasource.remote.api.hukamnamaData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

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
    @SerializedName("license")
    @Expose
    private String license;

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

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

}
