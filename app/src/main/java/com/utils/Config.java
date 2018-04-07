package com.utils;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by prithwi on 5/2/17.
 */

public class Config {



    //public String STARTUP_API ="http://52.39.67.134/oopdev/CricketStartup/master";//dev or for test

    public String STARTUP_API ="https://tripura-now.firebaseio.com/data.json?auth=h6BKXmFvAqyrVcELkT2iaH01xjV1ZLNc9fUTlzyK";//live Or Production


    public String TRANSACTION_API="";
    public int selectedTab;

   public String TRANSACTION_ACCESS_KEY="";

    public String CONTROLLER_METHOD="";

    public String ASSET_BASE_URL="";



    public String THUMBNAIL_FOLDER = "";

    public String MAIN_FOLDER = "";

    public String REACHABILITY_URL="";

    public int MAX_COUNT = 20;

    //public boolean debugMode = true;

      public boolean debugMode = false;

   // public boolean showNotification = true;

    public static boolean IS_SKIP_LOGIN = false;

    public static boolean IS_LOGIN_DONE = false;



    public static String STR_PUSH_TOKEN="";

    public static String TOKEN = "";
    public boolean suggestUpdate = false;

    public boolean forceUpdate = false;

    public String UPDATE_MESSAGE="New Version of Tripura Now is available, please update now!";

    public boolean maintenanceMode = false;

    public String MAINTENANCE_MESSAGE = "";

    public String B_AUTH="";

    public String PLAYSTORE_URL="";



    public String APP_ID="";

    public Context applicationContext=null;



    public boolean allowLandscape;

    public boolean allowNotification;
    public boolean allowNotificationSound;
    public boolean allowNotificationBadge;
    public boolean allowNotificationAlert;

    public HashMap<String,JSONArray> globalOptions;

    public static Typeface OpenSans_Bold;
    public static Typeface OpenSans_Regular;
    public static Typeface OpenSans_Light;

    public boolean isInternetAvailable=false;

    public boolean isIsSkipLogin=false;

    private static Config _instance = null;

    public static Config getSharedInstance()
    {
        if(_instance==null)
        {
            _instance = new Config();
            _instance.isInternetAvailable = true;




            _instance.globalOptions = new HashMap<String,JSONArray>();



        }
        return _instance;
    }

    public void setMasterData(JSONObject object)
    {
        if(object!=null)
        {
            this.TRANSACTION_API = object.optString("api");
            this.TRANSACTION_ACCESS_KEY = object.optString("api_key");
            this.CONTROLLER_METHOD = object.optString("main_method");
            this.ASSET_BASE_URL = object.optString("asset_base_url");
            this.MAX_COUNT = object.optInt("page_max_count",10);
            this.REACHABILITY_URL = object.optString("reachability_url");
            this.B_AUTH = object.optString("b_auth");
            this.UPDATE_MESSAGE = object.optString("update_message");
            this.MAINTENANCE_MESSAGE = object.optString("maintenance_message");
            this.THUMBNAIL_FOLDER = object.optString("thumbnail_folder");
            this.MAIN_FOLDER = object.optString("main_folder");
            if(object.optString("suggest_update").equalsIgnoreCase("1"))
                this.suggestUpdate = true;
            if(object.optString("force_update").equalsIgnoreCase("1"))
                this.forceUpdate = true;
            if(object.optString("maintenance_mode").equalsIgnoreCase("1"))
                this.maintenanceMode = true;
            this.PLAYSTORE_URL = object.optString("appstore_url");
            this.APP_ID = object.optString("appstore_id");
        }
    }


}
