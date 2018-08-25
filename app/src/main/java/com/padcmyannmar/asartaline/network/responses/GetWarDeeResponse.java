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
public class GetWarDeeResponse {
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
     * This field indicates a reference to  the objects of TalksVO
     * which stores overview details of all tag information.
     */
    @SerializedName("astlWarDee")
    private List<WarDeeVO> warDees;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getApiVersion() {
        return apiVersion;
    }

    public List<WarDeeVO> getWarDees() {
        return warDees;
    }

    /* Check Response is Ok or not */
    public boolean isResponseOK() {
        //expression
        return (code == 200 && warDees != null);
    }

//--------------------------------------------------------------------------

}
//------------------------------------------------------------------------------
//
//		End Of File
//
//------------------------------------------------------------------------------