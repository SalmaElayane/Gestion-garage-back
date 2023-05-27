package com.cigma.gg;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApplicationRunner implements CommandLineRunner {
    /*
    @Autowired
    IStockService service;
     */

    public static void main(String[] args) {
        SpringApplication.run(ApplicationRunner.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        /*
        StockEntity se = new StockEntity();
        se.setNom("AAA");
        se.setQuantite(2);
        se.setPrix(1000);//hada à changer le type dialo fi la classe par Double
        se.setReference("A123");

        service.save(se);
         */
    }


}
