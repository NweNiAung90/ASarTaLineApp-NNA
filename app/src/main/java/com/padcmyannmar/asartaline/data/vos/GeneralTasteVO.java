package com.padcmyannmar.asartaline.data.vos;

import com.google.gson.annotations.SerializedName;

//------------------------------------------------------------------------------
/*
 This VOs component the response json objects to save with object format for ASarTaLine App.
 */
public class GeneralTasteVO {

    /* Key name and attribute name are different.
     * Manually link to get same key and attribute. */

    /*
     * This field indicates War Dee Id of Get War Dee endpoint.
     */
    @SerializedName("tasteId")
    private String tasteId;

    @SerializedName("taste")
    private String taste;

    @SerializedName("tasteDesc")
    private String tasteDesc;

    public String getTasteId() {
        return tasteId;
    }

    public String getTaste() {
        return taste;
    }

    public String getTasteDesc() {
        return tasteDesc;
    }
}
