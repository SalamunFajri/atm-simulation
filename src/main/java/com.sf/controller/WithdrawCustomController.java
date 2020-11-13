package com.sf.controller;

import com.sf.dao.IBank;
import com.sf.service.ITransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import static com.sf.controller.Helper.getLoggedInUserName;

@Controller
public class WithdrawCustomController {

    @Autowired
    private ITransactionService transactionService;
    @Autowired
    private IBank bank;

    @GetMapping("/withdrawCustom")
    public String withdrawCustom(ModelMap model) {
        return "withdrawCustom";
    }

    @RequestMapping(value = "/withdrawCustom", method = RequestMethod.POST)
    public String withdrawCustomPost(@RequestParam Long amount, ModelMap model) {
        return "withdrawSummary?amount=" + amount.toString();
    }

}
