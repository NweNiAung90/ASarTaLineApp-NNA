package com.padcmyannmar.asartaline.data.vos;

import com.google.gson.annotations.SerializedName;

//------------------------------------------------------------------------------
/*
 This VOs component the response json objects to save with object format for ASarTaLine App.
 */
class MealShopVO {

    /*
     * This field indicates meal Shop Id of Get War Dee endpoint.
     */
    @SerializedName("mealShopId")
    private String mealShopId;

    public String getMealShopId() {
        return mealShopId;
    }
}
