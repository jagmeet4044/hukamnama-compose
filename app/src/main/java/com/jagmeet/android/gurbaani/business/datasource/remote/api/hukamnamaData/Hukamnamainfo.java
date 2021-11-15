
package com.jagmeet.android.gurbaani.business.datasource.remote.api.hukamnamaData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Hukamnamainfo {

    @SerializedName("shabadid")
    @Expose
    private List<Integer> shabadid = null;
    @SerializedName("pageno")
    @Expose
    private Integer pageno;
    @SerializedName("source")
    @Expose
    private Source source;
    @SerializedName("writer")
    @Expose
    private Writer writer;
    @SerializedName("raag")
    @Expose
    private Raag raag;

    public List<Integer> getShabadid() {
        return shabadid;
    }

    public void setShabadid(List<Integer> shabadid) {
        this.shabadid = shabadid;
    }

    public Integer getPageno() {
        return pageno;
    }

    public void setPageno(Integer pageno) {
        this.pageno = pageno;
    }

    public Source getSource() {
        return source;
    }

    public void setSource(Source source) {
        this.source = source;
    }

    public Writer getWriter() {
        return writer;
    }

    public void setWriter(Writer writer) {
        this.writer = writer;
    }

    public Raag getRaag() {
        return raag;
    }

    public void setRaag(Raag raag) {
        this.raag = raag;
    }

}