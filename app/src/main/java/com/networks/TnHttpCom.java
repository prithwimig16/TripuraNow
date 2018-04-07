package com.networks;

import android.content.Context;
import android.util.Base64;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.utils.Config;
import com.utils.Utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Prithwi on 03/04/18.
 */

public class TnHttpCom {

    private TnHttpCom(){}

    public static final int NEWS_SERVICE=1000;
     int currentSeq=0;
    private Context context;
    private TnHtttpComCallBack callback;
    private int tag;

    private JSONObject values;
    private JSONArray values1;
    private String finalUrl;

    public static final String GENERIC_ERROR_MESSAGE = "There was some problem in connecting to our server, " +
            "please try again!\nIf problem persist please " +
            "contact your Relationship Manager.";

    public TnHttpCom(Context context) {
        this.context = context;
        this.callback = (TnHtttpComCallBack) context;
        this.finalUrl = "https://tripura-now.firebaseio.com/data.json?auth=h6BKXmFvAqyrVcELkT2iaH01xjV1ZLNc9fUTlzyK";
        this.values = new JSONObject();

    }

    public TnHttpCom(Context context, TnHtttpComCallBack fragment) {
        this.context = context;
        this.callback = fragment;
        this.finalUrl = "https://tripura-now.firebaseio.com/data.json?auth=h6BKXmFvAqyrVcELkT2iaH01xjV1ZLNc9fUTlzyK";
        this.values = new JSONObject();
    }

    public static TnHttpCom getNewInstance(Context context)
    {
        return new TnHttpCom(context);
    }

    public static TnHttpCom getNewInstance(Context context, TnHtttpComCallBack fragment)
    {
        return new TnHttpCom(context,fragment);
    }

    private void processConnection() {
        Utils.consoleLog(TnHttpCom.class,"Post Data:"+this.values.toString());
        final JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.GET, this.finalUrl, this.values, new Response.Listener<JSONObject>()
        {
            @Override
            public void onResponse(JSONObject response) {

                Utils.consoleLog(getClass(), response.toString());
                try {
                    //boolean status=false;
                    boolean status=response.getBoolean("status");







                    if(callback!=null) {
                        callback.onSuccess(status, tag, response,currentSeq);
                        callback = null;
                    }
                } catch (JSONException e) {
                    Utils.consoleLog(getClass(),e.getLocalizedMessage());
                    if(callback!=null) {
                        callback.onFailure(null, tag);
                        callback = null;
                    }
                }


            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO Auto-generated method stub

                Utils.consoleLog(getClass(), error.toString());

                JSONObject rootObj = new JSONObject();
                JSONObject errorObj = new JSONObject();
                try {
                    errorObj.put("error_code", "400");
                    errorObj.put("error_msg", error.toString());
                    rootObj.put("error",errorObj);
                } catch (JSONException e) {
                    Utils.consoleLog(getClass(),e.getLocalizedMessage());
                    if(callback!=null) {
                        callback.onFailure(null, tag);
                        callback = null;
                    }
                }
                if(error.networkResponse!=null) {
                    if (callback != null) {

                        callback.onFailure(rootObj, tag);
                        callback = null;
                    }
                }
                else {
                    if (callback != null) {

                        callback.onFailure(null, tag);
                        callback = null;
                    }
                }
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
//                if(tag!=STARTUP_SERVICE) {
//
//                    String auth = "Basic " + Base64.encodeToString(Config.getSharedInstance().B_AUTH.getBytes(), Base64.NO_WRAP);
//                    headers.put("Authorization", auth);
//                }

//                headers.put("Content-Type", "application/json");
//                headers.put("Host","appdev.clamhub.com");

                return headers;
            }
        };

        int socketTimeout = 30000;//30 seconds - change to what you want
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        jsObjRequest.setRetryPolicy(policy);
        Utils.consoleLog(TnHttpCom.class,jsObjRequest.toString());
        try{
            Utils.volleyRequestQueue.add(jsObjRequest);
        }catch (Exception e){
            if(callback!=null) {
                callback.onFailure(null, tag);
                callback = null;
            }
        }
    }


    public void callNewsService(){


            this.tag=NEWS_SERVICE;

            this.processConnection();



        }

}
