package com.bichotitas.votacionesmvc.repositories;

import com.bichotitas.votacionesmvc.controllers.VotesController;
import com.bichotitas.votacionesmvc.utils.FileReader;
import com.bichotitas.votacionesmvc.utils.Logger;

import java.util.List;

public class FileProductsRepository implements ProductsRepository {
    final String productsFilePath;

    public FileProductsRepository(String productsFilePath) {
        this.productsFilePath = productsFilePath;
    }

    @Override
    public List<String> getAllProducts() {
        Logger.log(this.getClass().getSimpleName(), "Getting the name of all products");

        FileReader fileReader = new FileReader(productsFilePath);
        return fileReader.getFileContent();
    }
}
