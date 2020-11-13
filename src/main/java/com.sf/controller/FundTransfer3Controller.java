package com.sf.controller;

import com.sf.model.FundTrfProcess;
import com.sf.util.UtilCls;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpSession;

@Controller
public class FundTransfer3Controller {

    @GetMapping("/fundTransfer3")
    public String fundTransfer3(ModelMap model, HttpSession session) {
        int refNumb = UtilCls.random6Digits();
        Integer refNumbInt = new Integer(refNumb);
        model.put("refNumber", refNumbInt);
        session.setAttribute("refNumber", refNumbInt);
        return "fundTransfer3";
    }

}
