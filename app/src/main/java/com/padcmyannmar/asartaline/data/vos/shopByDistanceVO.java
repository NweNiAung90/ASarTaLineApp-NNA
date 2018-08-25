package com.padcmyannmar.asartaline.data.vos;

import com.google.gson.annotations.SerializedName;

//------------------------------------------------------------------------------
/*
 This VOs component the response json objects to save with object format for ASarTaLine App.
 */
public class shopByDistanceVO {
    /* Key name and attribute name are different.
     * Manually link to get same key and attribute. */

    /*
     * This field indicates shopByDistance Id of Get War Dee endpoint.
     */
    @SerializedName("shopByDistanceId")
    private String shopByDistanceId;

    @SerializedName("mealShop")
    private MealShopVO mealShops;

    @SerializedName("distanceInFeet")
    private double distanceInFeet;

    public String getShopByDistanceId() {
        return shopByDistanceId;
    }

    public MealShopVO getMealShops() {
        return mealShops;
    }

    public double getDistanceInFeet() {
        return distanceInFeet;
    }
}
