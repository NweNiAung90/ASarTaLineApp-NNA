package com.padcmyannmar.asartaline.network;

import com.padcmyannmar.asartaline.events.ApiErrorEvent;
import com.padcmyannmar.asartaline.events.SuccessForceRefreshGetWarDeeEvent;
import com.padcmyannmar.asartaline.events.SuccessGetWarDeeEvent;
import com.padcmyannmar.asartaline.network.responses.GetWarDeeResponse;
import com.padcmyannmar.asartaline.utils.WarDeeConstants;

import org.greenrobot.eventbus.EventBus;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitDataAgentImpl implements WarDeeDataAgent {
    private static RetrofitDataAgentImpl sObjInstance;

    private WarDeeApi mTheApi;

    // add required initialization for Retrofit
    private RetrofitDataAgentImpl() {
        //configure okHtttpClient
        final OkHttpClient okHttpClient = new OkHttpClient.Builder() //1.
                .connectTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build();

        /* configure Retrofit Object
        API_BASE = "http://padcmyanmar.com/padc-2/asartaline/api/v1/"
        */
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(WarDeeConstants.API_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        /*pass interface type from retrofit object and take interface object
          TedTalksApi Object Initialization
        */
        mTheApi = retrofit.create(WarDeeApi.class);
    }

    public static RetrofitDataAgentImpl getInstance() {
        /*Factory Logic
         * only one obj for this class type
         */
        if(sObjInstance == null){
            sObjInstance = new RetrofitDataAgentImpl();
        }
        return sObjInstance;
    }

    @Override
    public void loadWarDeeList(String accessToken, final boolean isForceRefresh) {
        /* call Interface Type method with param and get Call object
           Pass GetTedTalksResponse type as Call Object Generic Type */
        Call<GetWarDeeResponse> loadWarDeeCall = mTheApi.loadWarDeeList(accessToken);
        // pass Callback Interface as Anonymous Inner Class
        loadWarDeeCall.enqueue(new Callback<GetWarDeeResponse>() {
            @Override
            public void onResponse(Call<GetWarDeeResponse> call, Response<GetWarDeeResponse> response) {
                //Retrieve Response Body
                GetWarDeeResponse warDeeResponse = response.body();
                //Response is ok.
                if(warDeeResponse != null && warDeeResponse.isResponseOK()){
                    if (isForceRefresh) {
                        SuccessForceRefreshGetWarDeeEvent event = new SuccessForceRefreshGetWarDeeEvent(warDeeResponse.getWarDees());
                        EventBus.getDefault().post(event);
                    } else {
                        SuccessGetWarDeeEvent event = new SuccessGetWarDeeEvent(warDeeResponse.getWarDees());
                        EventBus.getDefault().post(event);
                    }
                }else { //Response is not ok. Error Case. Response Null Case
                    if(warDeeResponse == null){
                        //For 200OK
                        ApiErrorEvent event = new ApiErrorEvent("Empty response in network call.");
                        EventBus.getDefault().post(event);
                    }else {
                        //shows server message
                        ApiErrorEvent event = new ApiErrorEvent(warDeeResponse.getMessage());
                        EventBus.getDefault().post(event);
                    }
                }
            }

            @Override
            public void onFailure(Call<GetWarDeeResponse> call, Throwable t) {
                //cannot get internet connection or  serer down or api crush
                ApiErrorEvent event = new ApiErrorEvent(t.getMessage());
                EventBus.getDefault().post(event);
            }
        });



    }


//--------------------------------------------------------------------------

}
//------------------------------------------------------------------------------
//
//		End Of File
//
//------------------------------------------------------------------------------

