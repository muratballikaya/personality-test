package com.mb.personality.personalitytest.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mb.personality.personalitytest.model.setup.SetupDto;
import com.mb.personality.personalitytest.client.BackendClient;
import com.mb.personality.personalitytest.exception.AtLeastOneFieldShouldBeTypedException;
import com.mb.personality.personalitytest.model.SetupModel;
import com.mb.personality.personalitytest.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import java.io.IOException;

@Service
public class SetupService {

    @Autowired
    BackendClient backendClient;

    private static final String DEFAULT_JSON_URI = "src/main/resources/files/personal.json";

    public String setup(SetupModel setup) throws IOException {
        if (setup.isDefaultJson()) {
            String content = FileUtil.readFromInputStream(DEFAULT_JSON_URI);
            SetupDto setupdto = new ObjectMapper().readValue(content, SetupDto.class);
            return backendClient.saveTest(setupdto);
        } else if (!StringUtils.isEmpty(setup.getJsonData())) {
            SetupDto setupdto = new ObjectMapper().readValue(setup.getJsonData(), SetupDto.class);
            return backendClient.saveTest(setupdto);
        } else if (!StringUtils.isEmpty(setup.getTestId())) {
            return setup.getTestId();
        } else {
            throw new AtLeastOneFieldShouldBeTypedException();
        }
    }

}
