
package com.jagmeet.android.gurbaani.datasource.network.hukamnama.hukamnama_data;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Raag {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("akhar")
    @Expose
    private String akhar;
    @SerializedName("unicode")
    @Expose
    private String unicode;
    @SerializedName("english")
    @Expose
    private String english;
    @SerializedName("startang")
    @Expose
    private Integer startang;
    @SerializedName("endang")
    @Expose
    private Integer endang;
    @SerializedName("raagwithpage")
    @Expose
    private String raagwithpage;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getEnglish() {
        return english;
    }

    public void setEnglish(String english) {
        this.english = english;
    }

    public Integer getStartang() {
        return startang;
    }

    public void setStartang(Integer startang) {
        this.startang = startang;
    }

    public Integer getEndang() {
        return endang;
    }

    public void setEndang(Integer endang) {
        this.endang = endang;
    }

    public String getRaagwithpage() {
        return raagwithpage;
    }

    public void setRaagwithpage(String raagwithpage) {
        this.raagwithpage = raagwithpage;
    }

}
