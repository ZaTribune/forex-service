package com.zatribune.forex;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
public class ForexServiceApplication {
    public static void main(String[] args) {
        System.setProperty("com.oracle.coherence.hibernate.cache.lock_lease_duration","5000");
        System.setProperty("coherence.ttl", "0");
        System.setProperty("java.net.preferIPv4Stack", "true");
        SpringApplication.run(ForexServiceApplication.class, args);
    }

}
