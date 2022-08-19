package com.bulkup.health.controller;

import com.bulkup.health.config.CurrentUserParameter;
import com.bulkup.health.dto.PartyDto;
import com.bulkup.health.entity.account.Account;
import com.bulkup.health.service.PartyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/party")
public class PartyController {
    private final PartyService partyService;

    @PostMapping("alone")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void createPartyAloneMapping(@CurrentUserParameter Account account, @Valid PartyDto.Request.CreateParty request) {
        partyService.createParty(account, request, "alone");
    }

    @PostMapping("crew")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void createPartyCrewMapping(@CurrentUserParameter Account account, @Valid PartyDto.Request.CreateParty request) {
        partyService.createParty(account, request, "crew");
    }

    @PutMapping("/{partyId}/")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void updatePartyMapping() {

    }

    @DeleteMapping("/{partyId}")
    @ResponseStatus(value = org.springframework.http.HttpStatus.NO_CONTENT)
    public void deletePartyMapping() {

    }

    @GetMapping("/{partyId}")
    public void searchPartyMapping() {

    }

    @GetMapping("/")
    public void getMyPartyListMapping() {

    }

    @PutMapping("/{partyId}/trainer")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void updatePartyTrainerMapping() {

    }

}
