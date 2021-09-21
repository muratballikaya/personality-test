package com.mb.personality.personalitytest.controller;

import com.mb.personality.personalitytest.exception.NoTestFoundException;
import com.mb.personality.personalitytest.context.SessionContext;
import com.mb.personality.personalitytest.model.QuestionAnswerModel;
import com.mb.personality.personalitytest.service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;


/**
 * @author Murat Ballikaya
 */
@Controller()
@RequestMapping(value = "/result")
public class ResultController {

    @Autowired
    ResultService resultService;

    @GetMapping
    public String results(Model model, HttpSession httpSession) {

        Object o = httpSession.getAttribute("sessionContext");
        try {
            if (o == null) {
                throw new NoTestFoundException();
            }
            SessionContext sessionContext = (SessionContext) o;
            List<QuestionAnswerModel> qaList = resultService.generateQAList(sessionContext.getTestId());
            model.addAttribute("qaList", qaList);
            model.addAttribute("thereIsError", false);
        } catch (Exception e) {
            model.addAttribute("thereIsError", false);
            model.addAttribute("exception", e.getMessage());
        }

        return "result";
    }

}
