package com.bichotitas.votacionesmvc.repositories;

import com.bichotitas.votacionesmvc.models.Vote;

import java.util.HashMap;
import java.util.List;

public interface ResultsRepository {
    HashMap<String, List<Vote>> getAllResults();

    List<Vote> getResultsByProductName(String productName);

    Vote save(Vote entity);
}
