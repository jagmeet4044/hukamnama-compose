
package com.jagmeet.android.gurbaani.business.datasource.remote.api.hukamnamaData;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Punjabi {

    @SerializedName("month")
    @Expose
    private String month;
    @SerializedName("monthno")
    @Expose
    private String monthno;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("year")
    @Expose
    private String year;
    @SerializedName("day")
    @Expose
    private String day;

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getMonthno() {
        return monthno;
    }

    public void setMonthno(String monthno) {
        this.monthno = monthno;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

}
