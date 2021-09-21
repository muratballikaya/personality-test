package com.mb.personality.personalitytest.service;

import com.mb.personality.personalitytest.client.BackendClient;
;
import com.mb.personality.personalitytest.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
