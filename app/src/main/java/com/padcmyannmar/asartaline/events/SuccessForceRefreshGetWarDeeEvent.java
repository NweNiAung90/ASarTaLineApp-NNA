package com.padcmyannmar.asartaline.events;

import com.padcmyannmar.asartaline.data.vos.WarDeeVO;

import java.util.List;

public class SuccessForceRefreshGetWarDeeEvent extends SuccessGetWarDeeEvent {

    public SuccessForceRefreshGetWarDeeEvent(List<WarDeeVO> warDeesList) {
        super(warDeesList);
    }
}
