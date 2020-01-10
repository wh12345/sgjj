package com.hq.modules.tq.utils;

/**
 * Created by Because of you on 2019/12/26.
 */
public class DispatchUtils {
    public static double distanceCheck(double lngA,double latA,double lngB,double latB) {
        double pk = 180/3.14169;
        double a1 = latA /pk;
        double a2 = lngA/pk;
        double b1 = latB /pk;
        double b2 = lngB /pk;
        double t1 = Math.cos(a1)*Math.cos(a2)*Math.cos(b1)*Math.cos(b2);
        double t2 = Math.cos(a1)*Math.sin(a2)*Math.cos(b1)*Math.sin(b2);
        double t3 = Math.sin(a1)*Math.sin(b1);
        double tt = Math.acos(t1+t2+t3);
        return  Math.floor(6366000*tt);
    }
}
