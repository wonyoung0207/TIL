```js
// UnrealWebRTCPlayer - Copyright 2017-2019 Unreal Streaming Technologies
// Version 1.5 - May 10 2019. Added support for overlayed Play button.

export const UnrealWebRTCPlayer = function UnrealWebRTCPlayer(videoID, alias, sid, ipAddress, port, useSecureWebsocket, useSingleWebRTCPort, WebRTCProtocol) {
    "use strict";

    var pc = null;
    var ws = null;
    var state = -1;
    var audioCodec = "";
    var videoCodec = "";
    var latestStopTime = 0;
    var connOK = false;
    // var remoteVideo = document.getElementsByClassName(videoID);
    var remoteVideo = document.getElementById(videoID);
    // console.log("🚀 ~ UnrealWebRTCPlayer ~ videoID ::",videoID)
    var showControls = remoteVideo.controls;

    this.Connection = function Connection() {
        var nowTime = new Date().getTime();

        if ((state == -1) && (nowTime - latestStopTime > 1000)) {
            //remoteVideo.srcObject = null;
            state = 0;
            connOK = false;
            ToggleOverlay(false);
            remoteVideo.setAttribute('style', 'background: black url(resources/img/loader.gif) center no-repeat;');
            showControls = remoteVideo.controls;
            // remoteVideo.controls = true;
            remoteVideo.controls = false;
            DoSignaling();
            // remoteVideo.pause();
            // console.log("🚀 ~ Connection ~ remoteVideo:", remoteVideo)
            // pc.ontrack = gotRemoteStream; // 얘에서 remoteVideo 사용
        }

        if (nowTime - latestStopTime <= 1000)
            remoteVideo.pause();
    }

    this.Play = function Play() {
        // console.log("🚀 ~ Play ~ remoteVideo:", remoteVideo.paused)
        // console.log("🚀 ~ Play ~ remoteVideo:", remoteVideo)

        remoteVideo.play();
    }

    this.Stop = function Stop() {
        showControls = remoteVideo.controls;
        Terminate();
    }
    
    this.Pause = function Pause() {
        remoteVideo.pause();
        // console.log("🚀 ~ Pause ~ remoteVideo:", remoteVideo)
        // console.log("🚀 ~ Pause ~ remoteVideo:", remoteVideo.paused)
    }


    // remoteVideo.onplay = this.Play;
    // remoteVideo.onpause = this.Pause;

    function Terminate() {
        latestStopTime = new Date().getTime();

        state = -1;
        ToggleOverlay(true);
        remoteVideo.setAttribute('style', 'background-color:black');

        if (showControls)
            remoteVideo.controls = true;

        if (pc != null) {
            pc.onconnectionstatechange = null;
            pc.close();
            pc = null;
        }

        if (ws != null) {
            ws.onerror = null;
            ws.close();
            ws = null;
        }

        remoteVideo.pause();
    }

    function onCreateSessionDescriptionError(error) {
        Terminate();
        console.log("Failed to create session description: " + error.toString());
    }

    function onCreateOfferSuccess(desc) {

        var audioRate = 8000;
        if (audioCodec === "opus")
            audioRate = 48000;

        if (audioCodec != "")
            desc.sdp = setCodec(desc.sdp, "audio", audioCodec, audioRate);
        if (videoCodec != "")
            desc.sdp = setCodec(desc.sdp, "video", videoCodec, 90000);

        //Fix for some browsers and/or adapter incorrect behavior
        // chrome 89.0 이상
        desc.sdp = desc.sdp.replace("a=extmap-allow-mixed\r\n", "");
        desc.sdp = desc.sdp.replace("a=extmap-allow-mixed", "");

        desc.sdp = desc.sdp.replace("a=sendrecv", "a=recvonly");
        desc.sdp = desc.sdp.replace("a=sendrecv", "a=recvonly");

        //Signal the SDP to the server
        var msgString = JSON.stringify(desc);
        ws.send(msgString);

        pc.setLocalDescription(desc);
    }

    function onIceCandidate(event) {
        //Do nothing! We only need one endpoint from server; browser is going to connect to it
    }

    function gotRemoteStream(e) {
        remoteVideo.srcObject = e.streams[0];

        if (videoCodec == "")
            remoteVideo.setAttribute('style', 'background-color:black');

        if (showControls)
            remoteVideo.controls = true;
    }

    function onConnStateChange(event) {
        if (pc.connectionState === "failed") {
            Terminate();
            console.log("Connection failed; playback stopped");
        }
    }

    function DoSignaling() {
        var centralWebRTCPort = useSingleWebRTCPort ? "singleport/" : "randomport/";
        var URL = useSecureWebsocket ? "wss://" : "ws://";
        URL += ipAddress + ":" + port + "/webrtc_playnow/" + centralWebRTCPort + WebRTCProtocol + "/" + alias;
        if (sid != "")
            URL += "/sid:" + sid;

        try {
            ws = new WebSocket(URL);
        }
        catch (error) {
            Terminate();
            console.log("Error creating websocket: " + error);
        }

        ws.onmessage = function(evt) {
            var response = evt.data;
            var strArr = response.split("|-|-|");
            connOK = true;

            if (state == 0) { // this.Connection() 에서 state : 0으로 변경 
                state = 1;

                if (strArr.length == 1) {
                    Terminate();
                }
                else {

                    var servers = null;
                    var offerOptions = null;

                    videoCodec = strArr[0];
                    audioCodec = strArr[1];

                    if ((videoCodec != "") && (audioCodec != ""))
                        offerOptions = { offerToReceiveAudio: 1, offerToReceiveVideo: 1 };
                    else if (videoCodec != "")
                        offerOptions = { offerToReceiveAudio: 0, offerToReceiveVideo: 1 };
                    else if (audioCodec != "")
                        offerOptions = { offerToReceiveAudio: 1, offerToReceiveVideo: 0 };

                    pc = new RTCPeerConnection(servers);
                    pc.onicecandidate = onIceCandidate; // 사용 x 
                    pc.onconnectionstatechange = onConnStateChange; // terminate

                    pc.createOffer(offerOptions).then(
                      onCreateOfferSuccess,
                      onCreateSessionDescriptionError // error처리
                    );
                    // test(); 
                    // console.log("🚀pc:", pc)

                    pc.ontrack = gotRemoteStream; // 얘에서 remoteVideo 사용
                    // console.log("🚀 ~ DoSignaling ~ pc:", pc)

                    // remoteVideo.pause();
                }
            }
            else {

                if (strArr.length == 1) {
                    Terminate();
                }
                else {
                    var serverSDP = JSON.parse(strArr[0]);
                    var serverEndpoint = JSON.parse(strArr[1]);

                    //var serverSDP = strArr[0];
                    //var serverEndpoint = strArr[1];

                    serverEndpoint.candidate = EnsureValidCandidate(serverEndpoint.candidate);

                    pc.setRemoteDescription(new RTCSessionDescription(serverSDP));
                    var candidate = new RTCIceCandidate({ sdpMLineIndex: serverEndpoint.sdpMLineIndex, candidate: serverEndpoint.candidate });
                    pc.addIceCandidate(candidate);
                    // pc.ontrack = gotRemoteStream; // 얘에서 remoteVideo 사용


                    ws.close();
                    ws = null;
                }
            }
        }

        ws.onerror = function(evt) {
            if (!connOK) {
                Terminate();
                console.log("Error connecting to Unreal Media Server");
            }
        }
    }

    function EnsureValidCandidate(candidate) {

        if ((candidate.search(ipAddress) !== -1) || !useSingleWebRTCPort || (ipAddress == "127.0.0.1") || !ValidateIPaddress(ipAddress)) {
            return candidate;
        }

        //In case the server is behind the NAT router, replace private IP with public IP in the candidate
        var candLines = candidate.split(" ");
        var ipIndex = 4;
        for (var i = 0; i < candLines.length; i++) {
            if (candLines[i] === "typ") {
                ipIndex = i - 2;
                break;
            }
        }

        candLines[ipIndex] = ipAddress;
        candidate = candLines.join(" ");
        return candidate;
    }

    function ValidateIPaddress(ipaddr) {

        if (/^(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$/.test(ipaddr)) {
            return true;
        }

        return false;
    }

    function setCodec(sdp, type, codec, clockRate) {

        var sdpLines = sdp.split("\r\n");

        for (var i = 0; i < sdpLines.length; i++) {
            if (sdpLines[i].search("m=" + type) !== -1) {
                var mLineIndex = i;
                break;
            }
        }

        if (mLineIndex === null) return sdp;

        var codecPayload = null;
        var re = new RegExp(":(\\d+) " + codec + "\/" + clockRate);

        for (var i = mLineIndex; i < sdpLines.length; i++) {
            if (sdpLines[i].search(codec + "/" + clockRate) !== -1) {
                codecPayload = extractPayloadType(sdpLines[i], re);
                if (codecPayload && (EnsureSupportedProfile(codec, sdpLines, mLineIndex, codecPayload))) {
                    sdpLines[mLineIndex] = setDefaultCodec(sdpLines[mLineIndex], codecPayload);
                    break;
                }
            }
        }

        if (codecPayload === null) return sdp;

        var rtmpmap = "a=rtpmap:";
        var rtcp = "a=rtcp-fb:";
        var fmptp = "a=fmtp:";
        var rtmpmapThis = "a=rtpmap:" + codecPayload;
        var rtcpThis = "a=rtcp-fb:" + codecPayload;
        var fmptpThis = "a=fmtp:" + codecPayload;
        var bAddAll = false;
        var resSDPLines = new Array();

        for (var i = 0; i < sdpLines.length; i++) {
            if (i <= mLineIndex) {
                resSDPLines.push(sdpLines[i]);
            }
            else {
                if (sdpLines[i].search("m=") === 0)
                    bAddAll = true;

                var bNotToAdd = ((sdpLines[i].search(rtmpmap) === 0) && (sdpLines[i].search(rtmpmapThis) !== 0)) || ((sdpLines[i].search(rtcp) === 0) && (sdpLines[i].search(rtcpThis) !== 0)) || ((sdpLines[i].search(fmptp) === 0) && (sdpLines[i].search(fmptpThis) !== 0));

                if (bAddAll || !bNotToAdd)
                    resSDPLines.push(sdpLines[i]);
            }

        }

        sdp = resSDPLines.join("\r\n");
        return sdp;
    };

    function extractPayloadType(sdpLine, pattern) {
        
        var result = sdpLine.match(pattern);
        return (result && result.length == 2) ? result[1] : null;
    };

    function EnsureSupportedProfile(codec, sdpLines, mLineIndex, codecPayload) {

        if (codec != "H264")
            return true;

        //Server can send any profile/level H264, but SDP has to specify supported one
        for (var i = mLineIndex; i < sdpLines.length; i++) {
            if ((sdpLines[i].search("a=fmtp:" + codecPayload) === 0) && (sdpLines[i].search("profile-level-id=42") !== -1))
                return true;
        }

        return false;
    };

    function setDefaultCodec(mLine, payload) {

        var elements = mLine.split(" ");
        var newLine = new Array();
        var index = 0;
        for (var i = 0; i < elements.length; i++) {
            if (index === 3) {
                newLine[index++] = payload;
                break;
            }
            if (elements[i] !== payload) newLine[index++] = elements[i];
        }
        return newLine.join(" ");
    };

    function ToggleOverlay(show) {
        
        var Overlay = document.getElementById(videoID + "_Overlay");
        if (Overlay != null) {
            if(show)
                Overlay.style.display = "inline";
            else
                Overlay.style.display = "none";
        }
    }

};
```

