package com.kmangutov.happycouple.services;

import de.greenrobot.event.EventBus;

/**
 * Created by kmangutov on 1/7/15.
 */
public class EventBusProvider
{
    private static EventBus mBus;

    public static EventBus getEventBus() {

        if(mBus == null)
            mBus = new EventBus();

        return mBus;
    }
}
