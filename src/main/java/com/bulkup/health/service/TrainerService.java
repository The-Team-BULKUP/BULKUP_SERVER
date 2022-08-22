package com.bulkup.health.service;

import com.bulkup.health.repository.account.TrainerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional()
public class TrainerService {
    private final TrainerRepository trainerRepository;
}
