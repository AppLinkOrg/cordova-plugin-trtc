package com.helpfooter.trtc;

public class TRTC extends CordovaPlugin {
    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        

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