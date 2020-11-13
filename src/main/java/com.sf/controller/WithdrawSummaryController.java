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
public class WithdrawSummaryController {
    @Autowired
    private ITransactionService transactionService;
    @Autowired
    private IBank bank;

    @GetMapping("/withdrawSummary")
    public String withdrawSummaryGet(@RequestParam long amount, ModelMap model) {
        String date = new SimpleDateFormat("yyyy-MM-dd hh:mm a").format(Calendar.getInstance().getTime());
        model.addAttribute("date", date);
        model.addAttribute("amount", amount);
        transactionService.withdrawFrom(bank.getAccountByAccountNumber(getLoggedInUserName()), amount);
        model.addAttribute("balance", bank.getAccountByAccountNumber(getLoggedInUserName()).getBalance());
        return "withdrawSummary";
    }

}
