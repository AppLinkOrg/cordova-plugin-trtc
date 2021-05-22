package com.helpfooter.trtc;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.tencent.trtc.TRTCCloud;
import com.tencent.trtc.TRTCCloudDef;
import com.tencent.trtc.TRTCCloudListener;

import static com.tencent.trtc.TRTCCloudDef.TRTC_APP_SCENE_VIDEOCALL;

public class TRTCService {

    TRTCCloud mTRTCCloud=null;
    int sdkappid=0;

    public TRTCService(Context ctx,int sdkappid){
        this.sdkappid=sdkappid;
        this.mTRTCCloud = TRTCCloud.sharedInstance(ctx);
        this.mTRTCCloud.setListener(new TRTCCloudListener(){
            @Override
            public void onError(int errCode, String errMsg, Bundle extraInfo) {
                Log.d("TRTCService Error","["+String.valueOf(errCode)+"]"+errMsg );
            }

            @Override
            public void onEnterRoom(long result) {
                Log.d("TRTCService onEnterRoom","["+String.valueOf(result)+"]" );
                if (result > 0) {

                } else {

                }
            }
        });
    }

    public void enterRoom(String userid,String usersig,int roomId) {
        TRTCCloudDef.TRTCParams trtcParams = new TRTCCloudDef.TRTCParams();
        trtcParams.sdkAppId = this.sdkappid;
        trtcParams.userId = userid;
        trtcParams.roomId = roomId;
        trtcParams.userSig = usersig;
        mTRTCCloud.enterRoom(trtcParams, TRTC_APP_SCENE_VIDEOCALL);
    }


}
