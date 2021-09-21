package com.mb.personality.personalitytest.controller;

import com.mb.personality.personalitytest.context.SessionContext;
import com.mb.personality.personalitytest.model.SetupModel;
import com.mb.personality.personalitytest.service.SetupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;


/**
 * @author Murat Ballikaya
 */
@Controller()
@RequestMapping(value = "/setup")
public class SetupController {

    @Autowired
    SetupService setupService;

    @GetMapping
    public String setup(Model model) {
        model.addAttribute("setup", new SetupModel());
        model.addAttribute("thereIsError", false);
        return "setup";
    }

    @PostMapping
    public String setupSubmit(@ModelAttribute SetupModel setup , HttpSession httpSession, Model model) {
        try{
           String result = setupService.setup(setup);
           httpSession.setAttribute("sessionContext",SessionContext.builder().testId(result).build());
           return "redirect:/personalTest";
        }catch (Exception e){
            model.addAttribute("thereIsError", true);
            model.addAttribute("exception", e.getMessage());
            model.addAttribute("setup", new SetupModel());
            return "setup";
        }
    }

}
