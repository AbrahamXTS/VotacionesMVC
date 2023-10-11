package com.bichotitas.votacionesmvc.controllers;

import com.bichotitas.votacionesmvc.models.Vote;
import com.bichotitas.votacionesmvc.repositories.ResultsRepository;

import java.util.Calendar;

public class VotesController {
    private final ResultsRepository resultsRepository;

    public VotesController(ResultsRepository resultsRepository) {
        this.resultsRepository = resultsRepository;
    }

    public void addVote(String productName) {
        Calendar calendar = Calendar.getInstance();

        String date = calendar.get(Calendar.DAY_OF_MONTH) + "-" + calendar.get(Calendar.MONTH) + "-" + calendar.get(Calendar.YEAR);
        String time = calendar.get(Calendar.HOUR_OF_DAY) + ":" + calendar.get(Calendar.MINUTE) + ":" + calendar.get(Calendar.SECOND);

        this.resultsRepository.save(
                Vote.builder()
                        .productName(productName)
                        .date(date)
                        .time(time)
                        .build()
        );
    }
}
