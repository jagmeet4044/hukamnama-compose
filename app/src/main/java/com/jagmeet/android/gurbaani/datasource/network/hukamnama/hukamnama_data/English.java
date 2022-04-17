
package com.jagmeet.android.gurbaani.datasource.network.hukamnama.hukamnama_data;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class English {

    @SerializedName("month")
    @Expose
    private String month;
    @SerializedName("monthno")
    @Expose
    private Integer monthno;
    @SerializedName("date")
    @Expose
    private Integer date;
    @SerializedName("year")
    @Expose
    private Integer year;
    @SerializedName("day")
    @Expose
    private String day;

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public Integer getMonthno() {
        return monthno;
    }

    public void setMonthno(Integer monthno) {
        this.monthno = monthno;
    }

    public Integer getDate() {
        return date;
    }

    public void setDate(Integer date) {
        this.date = date;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

}
