package com.jolly.rendS.call.dtos;


import java.util.List;

public record JoinedCallDto(String callId, String myId, List<MemberDto> members) {
}
