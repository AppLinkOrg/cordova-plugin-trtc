# Cordova Plugin for QCloud TRTC
This cordova plugin is use to have a video call based on Tentent RTC(TRTC).
TRTC Library Document is https://cloud.tencent.com/document/product/647/42045

# Prepare
When your start to use this plugin, please read and know how TRTC set up in QCloud. TRTC is different way to call with WebRTC. 
https://cloud.tencent.com/document/product/647/16788 

# Install
Latest stable version from npm:
```sh
$ cordova plugin add cordova-plugin-trtc
```
Bleeding edge version from Github:
```sh
$ cordova plugin add https://github.com/AppLinkOrg/cordova-plugin-trtc.git
```

# Using the plugin
Step 1, if your are in android, please try to get the permission about CAMERA,MODIFY_AUDIO_SETTINGS,RECORD_AUDIO you can try to use cordova-open-native-settings to get the permission. If you are iOS, please ignore this step.
```sh
//for example to get CAMERA
cordova.plugins.permissions.requestPermission(cordova.plugins.permissions.CAMERA, successcallback, failcallback);
//for example to get MODIFY_AUDIO_SETTINGS
cordova.plugins.permissions.requestPermission(cordova.plugins.permissions.MODIFY_AUDIO_SETTINGS, successcallback, failcallback);
//for example to get RECORD_AUDIO
cordova.plugins.permissions.requestPermission(cordova.plugins.permissions.RECORD_AUDIO, successcallback, failcallback);
```

Step 2, Generate UserSig
```sh
if you want to get the UserSig for test, please try to use this page to generate https://console.cloud.tencent.com/trtc/usersigtool
if you want to know how to generate UserSig, please try to check the document:  https://cloud.tencent.com/document/product/647/17275  We always recommandate you generate this in Server because it is more easy to control which sdkappid and not save the secret in APK. 
```

Step 3, Methods
```sh
// enterRoom and start video communication with people you want, when 2 application also call this function to enter the calling room, it will start to have video communication.
// get sdkappid in QCloud TRTC application page, it is INT
// userid is my account, it is string
// usersig is use userid to genrate, it is string
// roomid is the room number, it is INT
// remoteUserId is who you want to talk, it is string
TRTC.enterRoom(sdkappid,userid,usersig,roomid,remoteUserId,success,fail);
```
# Future Features
LIve Show will start ASAP
There are bug here, we will try to fix asap

# License
Cordova Plugin for QCloud TRTC is licensed under the MIT. For more information, see the LICENSE file in this repository.
