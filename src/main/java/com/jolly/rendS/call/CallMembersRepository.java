package com.jolly.rendS.call;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CallMembersRepository extends JpaRepository<CallMember, Long> {
    List<CallMember> getAllByCall(Call call);
}
