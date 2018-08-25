/*------------------------------------------------------------------------------

This source is part of the assignment of the PADC Fun5 class.

Modification History


Date		Version		Author			Description
----------	-----------	--------------- ----------------------------------------
14 07 2018	1.0			Nwe Ni Aung		Initial Version.
------------------------------------------------------------------------------*/
package com.padcmyannmar.asartaline.network.responses;

//------------------------------------------------------------------------------

import com.google.gson.annotations.SerializedName;
import com.padcmyannmar.asartaline.data.vos.WarDeeVO;

import java.util.List;

//------------------------------------------------------------------------------
/*
 This VOs component the response json objects to save with object format for AsarTaLine App.
 */
public class SearchWarDeeResponse {

    /* API specific data key name and attribute name are different.
     * Manually link to get same key and attribute. */

    /*
     * This field indicates code.
     */
    @SerializedName("code")
    private int code;

    /*
     * This field indicates message.
     */
    @SerializedName("message")
    private String message;

    /*
     * This field indicates API Version.
     */
    @SerializedName("apiVersion")
    private String apiVersion;

    /*
     * This field indicates tasteType.
     */
    @SerializedName("tasteType")
    private String tasteType;

    /*
     * This field indicates suited.
     */
    @SerializedName("suited")
    private String suited;

    /*
     * This field indicates minPrice.
     */
    @SerializedName("minPrice")
    private int minPrice;

    /*
     * This field indicates maxPrice.
     */
    @SerializedName("maxPrice")
    private int maxPrice;

    /*
     * This field indicates isNearBy.
     */
    @SerializedName("isNearBy")
    private String isNearBy;

    /*
     * This field indicates currentTownship.
     */
    @SerializedName("currentTownship")
    private String currentTownship;

    /*
     * This field indicates currentTLat.
     */
    @SerializedName("currentTLat")
    private String currentTLat;

    /*
     * This field indicates currentLng.
     */
    @SerializedName("currentLng")
    private String currentLng;

    /*
     * This field indicates a reference to  the objects of WarDeeVO
     * which stores overview details of all tag information.
     */
    @SerializedName("searchResults")
    private List<WarDeeVO> searchResults;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getApiVersion() {
        return apiVersion;
    }

    public String getTasteType() {
        return tasteType;
    }

    public String getSuited() {
        return suited;
    }

    public int getMinPrice() {
        return minPrice;
    }

    public int getMaxPrice() {
        return maxPrice;
    }

    public String getIsNearBy() {
        return isNearBy;
    }

    public String getCurrentTownship() {
        return currentTownship;
    }

    public String getCurrentTLat() {
        return currentTLat;
    }

    public String getCurrentLng() {
        return currentLng;
    }

    public List<WarDeeVO> getSearchResults() {
        return searchResults;
    }

    /* Check Response is Ok or not */
    public boolean isResponseOK() {
        //expression
        return (code == 200 && searchResults != null);
    }
}
