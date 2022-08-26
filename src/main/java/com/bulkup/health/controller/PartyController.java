package com.bulkup.health.controller;

import com.bulkup.health.config.CurrentUserParameter;
import com.bulkup.health.dto.PartyDto;
import com.bulkup.health.entity.account.Account;
import com.bulkup.health.service.PartyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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

    @GetMapping("/{partyId}/join")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void joinParty(@CurrentUserParameter Account account, @PathVariable Long partyId) {
        partyService.joinParty(account, partyId);
    }

    @DeleteMapping("/{partyId}")
    @ResponseStatus(value = org.springframework.http.HttpStatus.NO_CONTENT)
    public void deletePartyMapping(@CurrentUserParameter Account account, @PathVariable Long partyId) {
        partyService.deleteParty(account, partyId);
    }

    @GetMapping("crew")
    @ResponseStatus(code = HttpStatus.OK)
    public List<PartyDto.Response.PartyInfo> searchPartyMapping(@CurrentUserParameter Account account, PartyDto.Request.SearchParty request) {
        return partyService.searchPartyCrew(account, request);
    }

    @GetMapping("/my/in")
    public PartyDto.Response.GetMyPartyList getMyPartyInMapping(@CurrentUserParameter Account account) {
        return partyService.getMyPartyIn(account);
    }

    @PutMapping("/{partyId}/trainer")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void updatePartyTrainerMapping(@CurrentUserParameter Account account, @PathVariable Long partyId) {
        partyService.registerPartyTrainer(account, partyId);
    }

    @GetMapping("/find")
    public List<PartyDto.Response.PartyInfo> findTrainerMapping(@CurrentUserParameter Account account) {
        return partyService.findPartyForTrainer(account);
    }

}
