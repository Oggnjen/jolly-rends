package com.jolly.rendS.call.dtos;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberDto {
    String email;
    String name;
    String surname;
    String memberId;
}
