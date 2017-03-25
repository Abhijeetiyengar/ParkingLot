package com.main.poc;

import com.main.poc.CLI.CliVisualizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

/**
 * Created by abhijeetiyengar on 3/25/17.
 */

@SpringBootApplication
public class ParkingLotInitiator {


    @Autowired
    CliVisualizer visualizer;

    @PostConstruct
    public void start()
    {
        visualizer.createInitMessage();
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(ParkingLotInitiator.class, args);
    }


}
