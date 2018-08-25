package com.padcmyannmar.asartaline.data.vos;

import com.google.gson.annotations.SerializedName;

//------------------------------------------------------------------------------
/*
 This VOs component the response json objects to save with object format for ASarTaLine App.
 */
class ShopByPopularityVO {

    /* Key name and attribute name are different.
     * Manually link to get same key and attribute. */

    /*
     * This field indicates shopByPopularity Id of Get War Dee endpoint.
     */
    @SerializedName("shopByPopularityId")
    private String shopByPopularityId;

    @SerializedName("mealShop")
    private MealShopVO mealShops;
}
