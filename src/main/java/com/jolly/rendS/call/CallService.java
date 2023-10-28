package com.jolly.rendS.call;

public interface CallService {
    String initiateCall(String sdp);
    void provideSdp();
}
