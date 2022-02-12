
package com.jagmeet.android.gurbaani.data.hukamnama.hukamnamaData;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Hukamnamainfo {

    @SerializedName("shabadid")
    @Expose
    private List<String> shabadid = null;
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
    @SerializedName("count")
    @Expose
    private Integer count;

    public List<String> getShabadid() {
        return shabadid;
    }

    public void setShabadid(List<String> shabadid) {
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

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

}
