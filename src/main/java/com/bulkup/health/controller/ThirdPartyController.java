package com.bulkup.health.controller;

import com.bulkup.health.dto.GymListByNameDto;
import com.bulkup.health.util.GymDataUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/gymSearch")
public class ThirdPartyController {

    @GetMapping("/{gymName}")
    public GymListByNameDto getGymListByNameMapping(@PathVariable String gymName) {
        return GymDataUtil.parseGymListWithName(gymName);
    }

}
