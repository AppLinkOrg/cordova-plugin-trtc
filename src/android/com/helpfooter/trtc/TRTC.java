package com.helpfooter.trtc;

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

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if ("enterRoom".equals(action)) {
            int sdkappid= args.getInt(0);
            String UserId = args.getString(1);
            String UserSig = args.getString(2);
            int RoomId = args.getInt(3);

            Context context=this.cordova.getActivity().getApplicationContext();

            TRTCService trtcService=new TRTCService(context,sdkappid);
            trtcService.enterRoom(UserId,UserSig,RoomId);
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