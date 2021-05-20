var exec = require('cordova/exec');
var TRTC = {};

TRTC.start = function(uri, success, failure) {
    // fire
    exec(
        success,
        failure,
        'VideoStream',
        'streamRTSP',
        [uri]
    );
};