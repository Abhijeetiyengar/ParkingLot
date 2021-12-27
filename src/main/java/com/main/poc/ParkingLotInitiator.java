package com.main.poc;

import com.main.poc.cli.CliVisualizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

/**
 * Created by abhijeetiyengar on 3/25/2020.
 * <p>
 * Start Application .
 */

@SpringBootApplication
public class ParkingLotInitiator {

    @Autowired
    CliVisualizer visualizer;

    @PostConstruct
    public void start() {
        visualizer.createInitMessage();
    }

    /*
        This is start of main program
     */
    public static void main(String[] args) throws Exception {
        SpringApplication.run(ParkingLotInitiator.class, args);
    }

}
