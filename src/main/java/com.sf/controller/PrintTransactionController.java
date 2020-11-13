package com.sf.controller;

import com.sf.model.Transaction;
import com.sf.service.ITransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import javax.servlet.http.HttpSession;
import java.util.List;
import static com.sf.controller.Helper.getLoggedInUserName;

@Controller
public class PrintTransactionController {

    @Autowired
    private ITransactionService transactionService;

    @GetMapping("/printTransaction")
    public String printTransaction(ModelMap model) {
        List<Transaction> list = transactionService.getMutation().getLastNTransaction(getLoggedInUserName(), 10);
        model.put("transactions",list);
        return "printTransaction";
    }

}
