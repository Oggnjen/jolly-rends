package com.jolly.rendS.users;

import com.jolly.rendS.rabbitmq.RabbitMQProducer;
import com.jolly.rendS.rabbitmq.Receiver;
import com.jolly.rendS.users.dtos.RegisterUserDto;
import com.jolly.rendS.users.dtos.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/registration")
public class RegistrationController {

    private final UserService userService;
//
//    private final RabbitTemplate rabbitTemplate;
//    private final Receiver receiver;

    @PostMapping
    public UserDto register(@RequestBody RegisterUserDto userDto) {
//        rabbitTemplate.convertAndSend("myexchange", "jolly.key", "Hello from RabbitMQ!");
        return userService.register(userDto);
    }


}
