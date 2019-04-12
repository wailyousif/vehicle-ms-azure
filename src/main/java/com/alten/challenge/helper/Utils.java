package com.alten.challenge.helper;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by wailm.yousif on 4/6/19.
 */

public class Utils {

    public static boolean vehicleIsConnected(Date vehicleLastPing) {
        boolean vehicleIsConnected = false;

        if (vehicleLastPing != null) {
            if (vehicleLastPing.compareTo(getCompareTime()) > 0) {
                vehicleIsConnected = true;
            }
        }

        return vehicleIsConnected;
    }

    public static Date getCompareTime() {
        return Utils.addSeconds(new Date(), -Constants.maxPingGracePeriod);
    }

    public static Date addSeconds(Date date, int seconds) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.SECOND, seconds);
        return calendar.getTime();
    }
}
