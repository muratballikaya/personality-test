package com.mb.personality.personalitytest.controller;

import com.mb.personality.personalitytest.model.Answer;
import com.mb.personality.personalitytest.service.CategoryService;
import com.mb.personality.personalitytest.service.PersonalTestService;
import com.mb.personality.personalitytest.context.SessionContext;
import com.mb.personality.personalitytest.exception.NoCategoryFoundException;
import com.mb.personality.personalitytest.exception.NoTestFoundException;
import com.mb.personality.personalitytest.exception.ThereIsNoMoreQuestionException;
import com.mb.personality.personalitytest.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;


@Controller
public class PersonalTestController {

    @Autowired
    PersonalTestService personalTestService;

    @Autowired
    CategoryService categoryService;

    @GetMapping("/personalTest")
    public String personelTest(Model model, HttpSession session) {
        try {
            Object o = session.getAttribute("sessionContext");
            if (o == null) {
                throw new NoTestFoundException();
            }
            SessionContext context = (SessionContext) o;
            Question question = context.getQuestion();
            if (question == null)
                question = personalTestService.getNextQuestion(context);
            model.addAttribute("thereIsError", false);
            model.addAttribute("question", question);
            model.addAttribute("answer", new Answer());
        } catch (NoTestFoundException | NoCategoryFoundException e) {
            model.addAttribute("exception", e.getMessage());
            model.addAttribute("thereIsError", true);
        } catch (ThereIsNoMoreQuestionException e) {
            return "redirect:/result";
        } catch (Exception e) {
            model.addAttribute("exception", e.getMessage());
            model.addAttribute("thereIsError", true);

        }
        return "personalTest";
    }


    @PostMapping("/personalTest")
    public String personelTestSubmit(@ModelAttribute Answer answer, HttpSession session, Model model) {
        SessionContext context = null;
        try {
            Object o = session.getAttribute("sessionContext");
            if (o == null) {
                throw new NoTestFoundException();
            }
            context = (SessionContext) o;
            if(answer.getFrom()!=0 && answer.getTo()!=0){
                answer.setAnswer(answer.getFrom() + " - " + answer.getTo());
            }
            if(answer.getTo() <answer.getFrom()){
                model.addAttribute("exception", "To can not be smaller then from ");
                model.addAttribute("thereIsError", true);
                model.addAttribute("question", context.getQuestion());
                return "personalTest";
            }
            personalTestService.saveAnswer(answer, context);
            Question question = personalTestService.getNextQuestion(context);
            model.addAttribute("thereIsError", false);
            model.addAttribute("question", question);
            model.addAttribute("answer", new Answer());
        } catch (ThereIsNoMoreQuestionException e) {
            return "redirect:/result";
        } catch (Exception e) {
            model.addAttribute("exception", "Exception occured while saving answer.." + e.getMessage());
            model.addAttribute("thereIsError", true);
            model.addAttribute("question", context.getQuestion());
        }
        return "personalTest";
    }
}
