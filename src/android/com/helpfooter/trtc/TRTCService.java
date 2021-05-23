package com.helpfooter.trtc;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;

import com.tencent.rtmp.ui.TXCloudVideoView;
import com.tencent.trtc.TRTCCloud;
import com.tencent.trtc.TRTCCloudDef;
import com.tencent.trtc.TRTCCloudListener;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.PluginResult;
import org.json.JSONException;
import org.json.JSONObject;

import static com.tencent.trtc.TRTCCloudDef.TRTC_APP_SCENE_VIDEOCALL;
import static com.tencent.trtc.TRTCCloudDef.TRTC_VIDEO_RENDER_MODE_FIT;

public class TRTCService {

    public static TRTCCloud sTRTCCloud=null;
    TRTCCloud mTRTCCloud=null;
    int sdkappid=0;
    Context ctx=null;
    CallbackContext callbackContext=null;
    Activity mActivity=null;
    String remoteUserId;

    public TRTCService(Context ctx, CallbackContext callbackContext, Activity mActivity, int sdkappid){
        this.ctx=ctx;
        this.callbackContext=callbackContext;
        this.sdkappid=sdkappid;
        this.mActivity=mActivity;
        this.mTRTCCloud = TRTCCloud.sharedInstance(ctx);

        this.mTRTCCloud.setListener(new TRTCCloudListener(){

            @Override
            public void onError(int errCode, String errMsg, Bundle extraInfo) {
                Log.d("TRTCService Error","["+String.valueOf(errCode)+"]"+errMsg );
                JSONObject jresult=new JSONObject();
                try {
                    jresult.put("type","onError");
                    jresult.put("errCode",errCode);
                    jresult.put("errMsg",errMsg);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                PluginResult errResult=new PluginResult(PluginResult.Status.ERROR, jresult);
                errResult.setKeepCallback(true);
                callbackContext.sendPluginResult(errResult);
            }

            @Override
            public void onEnterRoom(long result) {
                Log.d("TRTCService onEnterRoom","["+String.valueOf(result)+"]" );
                JSONObject jresult=new JSONObject();
                if (result > 0) {
                    try {
                        jresult.put("type","enterRoom");
                        jresult.put("entertime",result);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    TRTCService.sTRTCCloud=mTRTCCloud;
                    try {
                        Intent intent = new Intent(mActivity, VideoRoomActivity.class);
                        intent.putExtra("remoteuser", remoteUserId);
                        mActivity.startActivity(intent);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }




                    PluginResult succResult=new PluginResult(PluginResult.Status.OK, jresult);
                    succResult.setKeepCallback(true);
                    callbackContext.sendPluginResult(succResult);
                } else {
                    try {
                        jresult.put("type","enterRoom");
                        jresult.put("errorCOde",result);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    PluginResult errResult=new PluginResult(PluginResult.Status.ERROR, jresult);
                    errResult.setKeepCallback(true);
                    callbackContext.sendPluginResult(errResult);
                }
            }

            @Override
            public void onExitRoom(int result) {
                Log.d("TRTCService onExitRoom","["+String.valueOf(result)+"]" );
                super.onExitRoom(result);
            }

            @Override
            public void onUserVideoAvailable(String userId, boolean available) {
                Log.d("TRTCService onUserVideo","["+String.valueOf(userId)+"]" );

                JSONObject jresult=new JSONObject();
                try {
                    jresult.put("type","onUserVideoAvailable");
                    jresult.put("userid",userId);
                    jresult.put("available",available);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


                //
                if (available) {

                    PluginResult errResult=new PluginResult(PluginResult.Status.OK, jresult);
                    errResult.setKeepCallback(true);
                    callbackContext.sendPluginResult(errResult);


                } else {

                    PluginResult errResult=new PluginResult(PluginResult.Status.OK, jresult);
                    errResult.setKeepCallback(true);
                    callbackContext.sendPluginResult(errResult);

//                    mTRTCCloud.stopRemoteView(userId);
                }



            }
        });
    }

    public void enterRoom(String userid,String usersig,int roomId,String remoteUserId) {
        TRTCCloudDef.TRTCParams trtcParams = new TRTCCloudDef.TRTCParams();
        trtcParams.sdkAppId = this.sdkappid;
        trtcParams.userId = userid;
        trtcParams.roomId = roomId;
        trtcParams.userSig = usersig;
        this.remoteUserId=remoteUserId;
        mTRTCCloud.enterRoom(trtcParams, TRTC_APP_SCENE_VIDEOCALL);
    }

    public void  exitRoom(){
        mTRTCCloud.exitRoom();
    }


}
