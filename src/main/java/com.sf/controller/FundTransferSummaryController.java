package com.sf.controller;

import com.sf.dao.IBank;
import com.sf.service.ITransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import javax.servlet.http.HttpSession;

import static com.sf.controller.Helper.getLoggedInUserName;

@Controller
public class FundTransferSummaryController {

    @Autowired
    private ITransactionService transactionService;
    @Autowired
    private IBank bank;

    @GetMapping("/fundTransferSummary")
    public String fundTransferSummary(ModelMap model, HttpSession session) {
        model.put("destAccount", session.getAttribute("destAccount"));
        model.put("amount", session.getAttribute("amount"));
        model.put("refNumber", session.getAttribute("refNumber"));

        transactionService.fundTransfer(bank.getAccountByAccountNumber(getLoggedInUserName()),
                bank.getAccountByAccountNumber((String)session.getAttribute("destAccount")),
                (long)session.getAttribute("amount"));

        model.put("balance", bank.getAccountByAccountNumber(getLoggedInUserName()).getBalance());
        return "fundTransferSummary";
    }

}
