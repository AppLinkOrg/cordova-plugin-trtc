var exec = require('cordova/exec');
var TRTC = {};

TRTC.enterRoom = function (sdkAppId, userId, userSig, roomId, success, failure) {
    // fire
    exec(
        success,
        failure,
        'TRTC',
        'enterRoom',
        [sdkAppId, userId, userSig, roomId]
    );
};


module.exports = TRTC;