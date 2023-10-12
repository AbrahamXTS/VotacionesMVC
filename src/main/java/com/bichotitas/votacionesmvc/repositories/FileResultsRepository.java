package com.bichotitas.votacionesmvc.repositories;

import com.bichotitas.votacionesmvc.models.Vote;
import com.bichotitas.votacionesmvc.utils.FileReader;
import com.bichotitas.votacionesmvc.utils.Logger;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FileResultsRepository implements ResultsRepository {
    final String resultsFilePath;

    public FileResultsRepository(String resultsFilePath) {
        this.resultsFilePath = resultsFilePath;
    }

    private HashMap<String, List<Vote>> getAllResults() {
        FileReader fileReader = new FileReader(resultsFilePath);
        List<String> fileContent = fileReader.getFileContent();

        HashMap<String, List<Vote>> results = new HashMap<>();

        fileContent.forEach(line -> {
            String[] lineContent = line.split(" - ");

            Vote vote = Vote.builder()
                    .productName(lineContent[0])
                    .date(lineContent[1])
                    .time(lineContent[2])
                    .build();

            if (results.containsKey(vote.getProductName())) {
                results.get(vote.getProductName()).add(vote);
            } else {
                List<Vote> tempVotes = new ArrayList<>();
                tempVotes.add(vote);

                results.put(vote.getProductName(), tempVotes);
            }
        });

        return results;
    }

    @Override
    public List<Vote> getResultsByProductName(String productName) {
        Logger.log(this.getClass().getSimpleName(), "Getting votes for " + productName);

        return getAllResults()
                .getOrDefault(productName, new ArrayList<>());
    }

    @Override
    public Vote save(Vote vote) {
        Logger.log(this.getClass().getSimpleName(), "Saving vote for " + vote.getProductName());

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(resultsFilePath, true))) {
            writer.write(vote.toString() + "\n");
        } catch (IOException error) {
            Logger.log(this.getClass().getSimpleName(), "Error registered while saving the vote" + error.getMessage());
        }

        return vote;
    }
}
