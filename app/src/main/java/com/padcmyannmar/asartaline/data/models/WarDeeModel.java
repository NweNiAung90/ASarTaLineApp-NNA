/*------------------------------------------------------------------------------

This source is part of the assignment of the PADC Fun5 class.

Modification History


Date		Version		Author			Description
----------	-----------	--------------- ----------------------------------------
14 07 2018	1.0			Nwe Ni Aung		Initial Version.
------------------------------------------------------------------------------*/
package com.padcmyannmar.asartaline.data.models;
//------------------------------------------------------------------------------
import com.padcmyannmar.asartaline.data.vos.WarDeeVO;
import com.padcmyannmar.asartaline.events.SuccessForceRefreshGetWarDeeEvent;
import com.padcmyannmar.asartaline.events.SuccessGetWarDeeEvent;
import com.padcmyannmar.asartaline.network.RetrofitDataAgentImpl;
import com.padcmyannmar.asartaline.network.WarDeeDataAgent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
//------------------------------------------------------------------------------

public class WarDeeModel {

    private static final String DUMMY_ACCESS_TOKEN = "b002c7e1a528b7cb460933fc2875e916";

    /* Singleton Design Pattern */
    //2. static class type attribute
    private static WarDeeModel objInstance;

    private WarDeeDataAgent mDataAgent;

    private Map<String, WarDeeVO> mWarDeeMap;

    private int mPage;

    //1. private constructor
    private WarDeeModel() {
        //mDataAgent = HttpUrlConnectionDataAgentImpl.getInstance();
        //mDataAgent = OkHttpDataAgentImpl.getInstance();
        mDataAgent = RetrofitDataAgentImpl.getInstance();
        /*empty collection type for mull safety
          attribute is Map , object initialize is HashMap
        */
        mWarDeeMap = new HashMap<>();
        mPage = 1;

        /* 2 steps for listen  broadcast event from EventBus
           1. register event
           2. define Event listen method
        * */
        EventBus.getDefault().register(this);
    }

    //3. getter method -Factory Logic
    public static WarDeeModel getObjInstance() {
        /*Factory Logic
         * only one obj for this class type
         */
        if (objInstance == null) {
            objInstance = new WarDeeModel();
        }
        return objInstance;
    }

    /*
     * loaWarDeeList - This method is called load War Dee List from network call
     *
     * @param accessToken
     * @param page
     * @return void         listen response asynchronously
     */
    public void loaWarDeeList() {
        mDataAgent.loadWarDeeList(DUMMY_ACCESS_TOKEN,false);

    }

    public void forcedRefreshWarDeeList() {
        //for mPage++ issue ,set page 1 for don't miss pages
        mPage = 1;
        //for pull to refresh check news data comes or not
        mDataAgent.loadWarDeeList(DUMMY_ACCESS_TOKEN, true);

    }

    /*Send Id to details activity
      Retrieve data from data Repository
    */
    public WarDeeVO getWarDeeById(String warDeeId) {
        //return null; //TODO remove this after testing empty view layout in news details screen.
        return mWarDeeMap.get(warDeeId);
    }

    //composite
    /* Define Event Listen Method */
    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onSuccessGetWarDee(SuccessGetWarDeeEvent event) {
        //Reach data into data repository
        //cannot directly add list into Map
        setDataIntoRepository(event.getWarDeesList());
        mPage++;
    }

    //composite
    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onSuccessForceRefreshGetWarDee(SuccessForceRefreshGetWarDeeEvent event) {
        setDataIntoRepository(event.getWarDeesList());
    }

    //helper method
    private void setDataIntoRepository(List<WarDeeVO> warDeeList) {
        for (WarDeeVO warDees : warDeeList) {
            mWarDeeMap.put(warDees.getWarDeeId(), warDees);
        }
    }
//--------------------------------------------------------------------------

}
//------------------------------------------------------------------------------
//
//		End Of File
//
//------------------------------------------------------------------------------
