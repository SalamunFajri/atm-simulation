package com.sf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;

@Controller
public class FundTransfer1Controller {

    @GetMapping("/fundTransfer1")
    public String fundTransfer1() {
        return "fundTransfer1";
    }

    @RequestMapping(value = "/fundTransfer1", method = RequestMethod.POST)
    public String fundTransfer1(@RequestParam String destAccount, HttpSession session) {
        session.setAttribute("destAccount", destAccount);
        return "redirect:/fundTransfer2";
    }

}
