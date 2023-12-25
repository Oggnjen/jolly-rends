package com.jolly.rendS.call;

import com.jolly.rendS.call.dtos.CreatedCallDto;
import com.jolly.rendS.call.dtos.JoinedCallDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/call")
public class CallController {

    private final CallService callService;

    @PostMapping
    public CreatedCallDto initiateCall() {
        return callService.initiateCall();
    }

    @PostMapping(path = "/{callId}")
    public JoinedCallDto initiateCall(@PathVariable String callId) {
        return callService.joinCall(callId);
    }

}
