var exec = require('cordova/exec');
var TRTC = {};

TRTC.enterRoom = function (sdkAppId, userId, userSig, roomId,remoteUserId, success, failure) {
    // fire
    exec(
        success,
        failure,
        'TRTC',
        'enterRoom',
        [sdkAppId, userId, userSig, roomId,remoteUserId]
    );
};


module.exports = TRTC;