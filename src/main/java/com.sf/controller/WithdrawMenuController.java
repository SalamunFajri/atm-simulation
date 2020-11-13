package com.sf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WithdrawMenuController {

    @GetMapping("/withdrawMenu")
    public String mainMenu(ModelMap model) {
        return "withdrawMenu";
    }



}
