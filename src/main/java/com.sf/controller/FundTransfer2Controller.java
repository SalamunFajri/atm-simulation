package com.sf.controller;

import com.sf.model.FundTrfProcess;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
public class FundTransfer2Controller {

    @GetMapping("/fundTransfer2")
    public String fundTransfer2() {
        return "fundTransfer2";
    }

    @RequestMapping(value = "/fundTransfer2", method = RequestMethod.POST)
    public String fundTransfer2(@RequestParam long amount, HttpSession session) {
        session.setAttribute("amount", amount);
        return "redirect:/fundTransfer3";
    }

}
