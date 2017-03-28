package com.test.poc;

import com.main.poc.ParkingLotInitiator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

/**
 * Created by abhijeetiyengar on 3/25/17.
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.main.poc",
        excludeFilters = {
                @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,
                        value = {
                                ParkingLotInitiator.class,

                        })
        })
public class ParkingLotInitiatorTest {

    public static void main(String[] args) {
        SpringApplication.run(ParkingLotInitiatorTest.class, args);
    }
}
