package com.helpfooter.trtc;

import android.app.Activity;
import android.content.Context;

import com.tencent.trtc.TRTCCloud;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaWebView;
import org.json.JSONArray;
import org.json.JSONException;

public class TRTC extends CordovaPlugin {

    Activity mActivity;

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if ("enterRoom".equals(action)) {
            int sdkappid = args.getInt(0);
            String UserId = args.getString(1).trim();
            String UserSig = args.getString(2).trim();
            int RoomId = args.getInt(3);
            String RemoteUser = args.getString(4).trim();

            Context context = this.cordova.getActivity().getApplicationContext();
            mActivity = this.cordova.getActivity();

            TRTCService trtcService = new TRTCService(context, callbackContext, mActivity, sdkappid);
            trtcService.exitRoom();
            trtcService.enterRoom(UserId, UserSig, RoomId,RemoteUser);
        }
        return true;
    }

    @Override
    public void onRequestPermissionResult(int requestCode, String[] permissions, int[] grantResults) throws JSONException {

    }

    @Override
    public void onPause(boolean p) {
        super.onPause(p);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}