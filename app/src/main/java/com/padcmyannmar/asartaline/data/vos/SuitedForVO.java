package com.padcmyannmar.asartaline.data.vos;

import com.google.gson.annotations.SerializedName;

//------------------------------------------------------------------------------
/*
 This VOs component the response json objects to save with object format for ASarTaLine App.
 */
class SuitedForVO {

    /* Key name and attribute name are different.
     * Manually link to get same key and attribute. */

    /*
     * This field indicates suitedFor Id of Get War Dee endpoint.
     */
    @SerializedName("suitedForId")
    private String suitedForId;

    @SerializedName("suitedFor")
    private String suitedFor;

    @SerializedName("suitedForDesc")
    private String suitedForDesc;

    public String getSuitedForId() {
        return suitedForId;
    }

    public String getSuitedFor() {
        return suitedFor;
    }

    public String getSuitedForDesc() {
        return suitedForDesc;
    }
}
