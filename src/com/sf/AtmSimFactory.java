package com.sf;

import com.sf.service.IScreenService;

public class AtmSimFactory {
    public static void create(IScreenService screenService) {
        screenService.Run();
    }
}
