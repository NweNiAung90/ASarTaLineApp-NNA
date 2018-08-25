package com.padcmyannmar.asartaline.data.vos;

import com.google.gson.annotations.SerializedName;

//------------------------------------------------------------------------------
/*
 This VOs component the response json objects to save with object format for ASarTaLine App.
 */
public class MatchWarDeeListVO {
    /* Key name and attribute name are different.
     * Manually link to get same key and attribute. */

    /*
     * This field indicates War Dee Id of Get War Dee endpoint.
     */
    @SerializedName("warDeeId")
    private String warDeeId;

    public String getWarDeeId() {
        return warDeeId;
    }
}
