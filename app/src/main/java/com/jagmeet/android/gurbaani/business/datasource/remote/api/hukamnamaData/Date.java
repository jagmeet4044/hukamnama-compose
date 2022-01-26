
package com.jagmeet.android.gurbaani.business.datasource.remote.api.hukamnamaData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Date {

    @SerializedName("gregorian")
    @Expose
    private Gregorian gregorian;
    @SerializedName("nanakshahi")
    @Expose
    private Nanakshahi nanakshahi;

    public Gregorian getGregorian() {
        return gregorian;
    }

    public void setGregorian(Gregorian gregorian) {
        this.gregorian = gregorian;
    }

    public Nanakshahi getNanakshahi() {
        return nanakshahi;
    }

    public void setNanakshahi(Nanakshahi nanakshahi) {
        this.nanakshahi = nanakshahi;
    }

}
