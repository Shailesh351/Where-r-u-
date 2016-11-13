package com.example.deepanshu.whereru.other;

import android.content.Context;
import android.preference.PreferenceManager;

/**
 * Created by tanishka on 27/8/16.
 */
public class MobileNumberPreferences {
private static final String MOBILE_NO="Mobile_NO";

    public static void setMobileNo(String mobileNo,Context context){
        PreferenceManager.getDefaultSharedPreferences(context).edit().putString(MOBILE_NO,mobileNo).apply();
    }

   public static String getMobileNo(Context context){
       return PreferenceManager.getDefaultSharedPreferences(context).getString(MOBILE_NO,null);
   }
}
