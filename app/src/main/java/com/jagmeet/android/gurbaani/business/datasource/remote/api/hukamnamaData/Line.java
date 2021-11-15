
package com.jagmeet.android.gurbaani.business.datasource.remote.api.hukamnamaData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Line {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("gurmukhi")
    @Expose
    private Gurmukhi gurmukhi;
    @SerializedName("larivaar")
    @Expose
    private Larivaar larivaar;
    @SerializedName("translation")
    @Expose
    private Translation translation;
    @SerializedName("transliteration")
    @Expose
    private Transliteration transliteration;
    @SerializedName("linenum")
    @Expose
    private Integer linenum;
    @SerializedName("firstletters")
    @Expose
    private Firstletters firstletters;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Gurmukhi getGurmukhi() {
        return gurmukhi;
    }

    public void setGurmukhi(Gurmukhi gurmukhi) {
        this.gurmukhi = gurmukhi;
    }

    public Larivaar getLarivaar() {
        return larivaar;
    }

    public void setLarivaar(Larivaar larivaar) {
        this.larivaar = larivaar;
    }

    public Translation getTranslation() {
        return translation;
    }

    public void setTranslation(Translation translation) {
        this.translation = translation;
    }

    public Transliteration getTransliteration() {
        return transliteration;
    }

    public void setTransliteration(Transliteration transliteration) {
        this.transliteration = transliteration;
    }

    public Integer getLinenum() {
        return linenum;
    }

    public void setLinenum(Integer linenum) {
        this.linenum = linenum;
    }

    public Firstletters getFirstletters() {
        return firstletters;
    }

    public void setFirstletters(Firstletters firstletters) {
        this.firstletters = firstletters;
    }

}
