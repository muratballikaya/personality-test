package com.softtech.personality.personalitytest.service;

import com.softtech.personality.personalitytest.client.BackendClient;
import com.softtech.personality.personalitytest.exception.NoCategoryFoundException;
;
import com.softtech.personality.personalitytest.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class CategoryService {

    @Autowired
    BackendClient backendClient;

    public Category getCategoryById(long id){
        return new Category(id,"c1");
    }

    public List<String> getCategoryList(String testId){
       return backendClient.fetchCategoryList(testId);

    }
}
