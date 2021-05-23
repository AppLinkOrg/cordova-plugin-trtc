package com.helpfooter.trtc;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.tencent.rtmp.ui.TXCloudVideoView;

import org.apache.cordova.CordovaActivity;

import static com.tencent.trtc.TRTCCloudDef.TRTC_VIDEO_RENDER_MODE_FILL;
import static com.tencent.trtc.TRTCCloudDef.TRTC_VIDEO_RENDER_MODE_FIT;

public class VideoRoomActivity extends CordovaActivity {
    SurfaceView mSurfaceView;
    TXCloudVideoView mLocalView;


    SurfaceView mRemoteSurfaceView;
    TXCloudVideoView mRemoteView;

    boolean isMuteVoice=false;
    boolean isMuteVideo=false;
    boolean isFrontCamera=true;

    ImageView mCameraChange,mVoiceChange,mVideoChange,mExit;

    public static VideoRoomActivity RoomActivity;

    public VideoRoomActivity() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //VideoRoomActivity.RoomActivity = this.cordovaInterface.getActivity();
        VideoRoomActivity.RoomActivity = this;
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(_getResource("trtc_videocall_main", "layout"));

        _UIListener();
        TRTCService.sTRTCCloud.setLocalViewFillMode(TRTC_VIDEO_RENDER_MODE_FILL);
        TRTCService.sTRTCCloud.startLocalPreview(isFrontCamera, mLocalView);
        TRTCService.sTRTCCloud.startLocalAudio();

        Intent intent = getIntent();
        String remoteUserId = intent.getStringExtra("remoteuser");
        TRTCService.sTRTCCloud.startRemoteView(remoteUserId, mRemoteView);
        TRTCService.sTRTCCloud.setRemoteViewFillMode(remoteUserId, TRTC_VIDEO_RENDER_MODE_FILL);

        mCameraChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TRTCService.sTRTCCloud.switchCamera();
                isFrontCamera=!isFrontCamera;
            }
        });


        mVoiceChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isMuteVoice=!isMuteVoice;
                if(isMuteVoice==true){
                    TRTCService.sTRTCCloud.stopLocalAudio();
                    int voiceoffimg=_getResource("trtc_voiceon","mipmap");
                    mVoiceChange.setImageResource(voiceoffimg);
                }else{
                    TRTCService.sTRTCCloud.startLocalAudio();
                    mVoiceChange.setImageResource(_getResource("trtc_voiceoff","mipmap"));
                }
            }
        });


        mVideoChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isMuteVideo=!isMuteVideo;
                if(isMuteVideo==true){
                    TRTCService.sTRTCCloud.stopLocalPreview();
                    int voiceoffimg=_getResource("trtc_videoon","mipmap");
                    mVideoChange.setImageResource(voiceoffimg);
                }else{
                    TRTCService.sTRTCCloud.startLocalPreview(isFrontCamera, mLocalView);
                    mVideoChange.setImageResource(_getResource("trtc_videooff","mipmap"));
                }
            }
        });


        mExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TRTCService.sTRTCCloud.stopAllRemoteView();
                TRTCService.sTRTCCloud.stopLocalPreview();
                TRTCService.sTRTCCloud.exitRoom();
                VideoRoomActivity.this.finish();
            }
        });
    }


    private int _getResource(String name, String type) {
        String package_name = getApplication().getPackageName();
        Resources resources = getApplication().getResources();
        return resources.getIdentifier(name, type, package_name);
    }

    private void _UIListener() {
        mSurfaceView = findViewById(_getResource("local_surfaceView", "id"));
        mLocalView = new TXCloudVideoView(mSurfaceView);
        mRemoteSurfaceView = findViewById(_getResource("remote_surfaceView", "id"));
        mRemoteView = new TXCloudVideoView(mRemoteSurfaceView);

        mCameraChange=findViewById(_getResource("changecamera", "id"));
        mVoiceChange=findViewById(_getResource("voicechange", "id"));
        mVideoChange=findViewById(_getResource("videochange", "id"));
        mExit=findViewById(_getResource("exit", "id"));

    }

}
