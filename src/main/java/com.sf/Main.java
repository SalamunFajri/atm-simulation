package com.sf;

import com.sf.dao.impl.SqlDbBank;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;


@SpringBootApplication(scanBasePackages = "com.sf")
public class Main  extends SpringBootServletInitializer {

    @Autowired
    private SqlDbBank sqlDbBank;

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Main.class);
    }

    public static void main(String[] args)  {
        SpringApplication.run(Main.class, args);
    }

    @Bean
    InitializingBean sendDatabase() {
        return () -> {
            sqlDbBank.addDefaultAccount();
        };
    }

}


//Console Application
/*
@SpringBootApplication
public class Main implements CommandLineRunner{

    @Autowired
    private SqlDbBank sqlDbBank;

    public static void main(String[] args) throws Exception {
        SpringApplication app = new SpringApplication(Main.class);
        app.run(args);
    }

    @Override
    public void run(String... args) throws Exception {
        AtmSimulation
                .createConsole(args.length>=1?args[0]:null)
                .run();
    }
}
*/
