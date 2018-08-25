package com.padcmyannmar.asartaline.events;

import com.padcmyannmar.asartaline.data.vos.WarDeeVO;

import java.util.List;

public class SuccessGetWarDeeEvent {
    // start define event broadcast
    public List<WarDeeVO> warDeesList;

    public SuccessGetWarDeeEvent(List<WarDeeVO> warDeesList) {
        this.warDeesList = warDeesList;
    }

    public List<WarDeeVO> getWarDeesList() {
        return warDeesList;
    }

    // end define event broadcast

}
