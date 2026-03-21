package ru.yandex.practicum.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.yandex.practicum.controller.requestResponse.membership.CreateMembershipRequest;
import ru.yandex.practicum.controller.requestResponse.membership.CreateMembershipResponse;
import ru.yandex.practicum.mapper.membership.MembershipRequestMapper;
import ru.yandex.practicum.mapper.membership.MembershipResponseMapper;
import ru.yandex.practicum.servise.Dto.MembershipDto;
import ru.yandex.practicum.servise.MembershipService;

@RestController
@RequestMapping("/memberships")
@RequiredArgsConstructor
public class MembershipController {
    private final MembershipService membershipService;

    @PostMapping("/{clientId}")
    public CreateMembershipResponse createMembershipToClient(@PathVariable long clientId,
                                                             @Valid @RequestBody CreateMembershipRequest createMembershipRequest) {
        MembershipDto membershipDto = MembershipRequestMapper.toMembershipDto(createMembershipRequest);
        membershipDto.setClientId(clientId);
        return MembershipResponseMapper.toMembershipResponse(membershipService.createMembershipToClient(membershipDto));
    }
}
