package com.sf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

import static com.sf.controller.Helper.getLoggedInUserName;

@Controller
public class MainMenuController {

    @GetMapping({"/", "/mainMenu"})
    public String mainMenu(ModelMap model, HttpSession session) {
        model.put("name", getLoggedInUserName());
        session.setAttribute("destAccount", "");
        session.setAttribute("amount", 0);
        session.setAttribute("refNumber", "");
        return "mainMenu";
    }

}
