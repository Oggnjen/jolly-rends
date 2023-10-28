package com.jolly.rendS.call;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/call")
public class CallController {

    private final CallService callService;

    @PostMapping
    public String initiateCall(@RequestBody SdpDto sdp) {
        callService.provideSdp();
        return callService.initiateCall(sdp.sdp());
    }

    @GetMapping
    public void test() {
        callService.provideSdp();
    }
}
