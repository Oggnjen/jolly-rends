package com.jolly.rendS.call;

import com.jolly.rendS.call.dtos.CreatedCallDto;
import com.jolly.rendS.call.dtos.JoinedCallDto;
import com.jolly.rendS.call.dtos.MemberDto;
import com.jolly.rendS.utils.CustomBadRequestException;
import com.jolly.rendS.utils.UserUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CallServiceImpl implements CallService {

    private final CallRepository callRepository;
    private final CallMembersRepository callMembersRepository;
    private final CallIdentifierGenerator identifierGenerator;
    private final UserUtils userUtils;

    @Override
    public CreatedCallDto initiateCall() {
        String callIdentifier = identifierGenerator.generate();
        var user = userUtils.getLoggedUser();
        var call = Call.builder()
                .initiator(user)
                .isActive(true)
                .uniqueCode(callIdentifier)
                .build();
        callRepository.save(call);

        String callerIdentifier = user.getEmail();
        CallMember callMember = CallMember.builder()
                .call(call)
                .user(user)
                .userIdentifier(callerIdentifier)
                .build();
        callMembersRepository.save(callMember);

        return new CreatedCallDto(callIdentifier, callerIdentifier, user.getName(), user.getSurname(), user.getEmail());
    }

    @Override
    public JoinedCallDto joinCall(String callId) {
        Call call = callRepository.findByUniqueCode(callId)
                .orElseThrow(() -> new CustomBadRequestException("Call does not exist"));

        List<CallMember> members = callMembersRepository.getAllByCall(call);
        var user = userUtils.getLoggedUser();
        String memberId = user.getEmail();
        CallMember callMember = CallMember.builder()
                .user(user)
                .call(call)
                .userIdentifier(user.getEmail())
                .build();
        callMembersRepository.save(callMember);
        return new JoinedCallDto(callId, memberId, user.getName(),user.getSurname(),user.getEmail(),members.stream()
                .map((member) -> MemberDto.builder()
                        .email(member.getUser().getEmail())
                        .name(member.getUser().getName())
                        .surname(member.getUser().getSurname())
                        .memberId(member.getUserIdentifier())
                        .build()).collect(Collectors.toList()));
    }
}
