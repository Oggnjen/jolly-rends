package com.jolly.rendS.call;

import com.jolly.rendS.users.User;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "call_members")
public class CallMember {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Call call;

    @ManyToOne
    private User user;

    @Column(length = 5000)
    private String sdp;
}
