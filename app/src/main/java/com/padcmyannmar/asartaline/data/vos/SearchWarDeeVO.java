package com.padcmyannmar.asartaline.data.vos;

import com.google.gson.annotations.SerializedName;

import java.util.List;
//------------------------------------------------------------------------------
/*
 This VOs component the response json objects to save with object format for ASarTaLine App.
 */
public class SearchWarDeeVO {

    /* Key name and attribute name are different.
     * Manually link to get same key and attribute. */

    /*
     * This field indicates War Dee Id of Search War Dee endpoint.
     */
    @SerializedName("warDeeId")
    private String warDeeId;

    /*
     * This field indicates name of Search War Dee endpoint.
     */
    @SerializedName("name")
    private String name;

    /*
     * This field indicates images of Search War Dee endpoint.
     */
    @SerializedName("images")
    private List<String> images;

    @SerializedName("generalTaste")
    private List<GeneralTasteVO> generalTastes;

    @SerializedName("suitedFor")
    private List<SuitedForVO> suitedFors;

    @SerializedName("priceRangeMin")
    private int priceRangeMin;

    @SerializedName("priceRangeMax")
    private int priceRangeMax;

    @SerializedName("matchWarDeeList")
    private List<MatchWarDeeListVO> matchWarDeeLists;

    @SerializedName("shopByDistance")
    private List<shopByDistanceVO> shopByDistances;

    @SerializedName("shopByPopularity")
    private List<ShopByPopularityVO> shopByPopularitys;

    public String getWarDeeId() {
        return warDeeId;
    }

    public String getName() {
        return name;
    }

    public List<String> getImages() {
        return images;
    }

    public List<GeneralTasteVO> getGeneralTastes() {
        return generalTastes;
    }

    public List<SuitedForVO> getSuitedFors() {
        return suitedFors;
    }

    public int getPriceRangeMin() {
        return priceRangeMin;
    }

    public int getPriceRangeMax() {
        return priceRangeMax;
    }

    public List<MatchWarDeeListVO> getMatchWarDeeLists() {
        return matchWarDeeLists;
    }

    public List<shopByDistanceVO> getShopByDistances() {
        return shopByDistances;
    }

    public List<ShopByPopularityVO> getShopByPopularitys() {
        return shopByPopularitys;
    }
}
