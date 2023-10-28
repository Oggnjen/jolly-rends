package com.jolly.rendS.call;

import com.jolly.rendS.utils.UserUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CallServiceImpl implements CallService {

    private final CallRepository callRepository;
    private final CallMembersRepository callMembersRepository;
    private final CallIdentifierGenerator identifierGenerator;
    private final SimpMessagingTemplate simpMessagingTemplate;
    private final UserUtils userUtils;

    @Override
    public String initiateCall(String sdp) {
        String identifier = identifierGenerator.generate();
        var user = userUtils.getLoggedUser();
        var call = Call.builder()
                .initiator(user)
                .isActive(true)
                .uniqueCode(identifier)
                .build();
        callRepository.save(call);

        CallMember callMember = CallMember.builder()
                .call(call)
                .sdp(sdp)
                .user(user)
                .build();
        callMembersRepository.save(callMember);

        return identifier;
    }

    @Override
    public void provideSdp() {
        System.out.println("usao");
        simpMessagingTemplate.convertAndSendToUser("john123", "/queue/private", "Hello, John!");;
    }
}
