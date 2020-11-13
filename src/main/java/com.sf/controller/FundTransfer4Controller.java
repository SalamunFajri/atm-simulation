package com.sf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import javax.servlet.http.HttpSession;

@Controller
public class FundTransfer4Controller {

    @GetMapping("/fundTransfer4")
    public String fundTransfer4(ModelMap model, HttpSession session) {
        model.put("destAccount", session.getAttribute("destAccount"));
        model.put("amount", session.getAttribute("amount"));
        model.put("refNumber", session.getAttribute("refNumber"));
        return "fundTransfer4";
    }

}
