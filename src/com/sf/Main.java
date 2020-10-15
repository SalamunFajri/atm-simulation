package com.sf;

import com.sf.model.Bank;
import com.sf.service.ScreenService;

public class Main {

    public static void main(String[] args){
        ScreenService screenService = new ScreenService(new Bank());
        screenService.Run();
    }
}
