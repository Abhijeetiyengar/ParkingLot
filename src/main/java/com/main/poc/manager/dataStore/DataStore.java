package com.main.poc.manager.dataStore;

import com.main.poc.properties.VehicleInfoProperties;

/**
 * Created by abhijeetiyengar on 3/25/17.
 */
public interface DataStore {

    void entry(VehicleInfoProperties.VehicleInfo vehicleInfo);

    void exit(VehicleInfoProperties.VehicleInfo vehicleInfo, int numberOfHours);

    void report();

    boolean isParkingSpaceToBeAsked();

    void setParkingSpace(int numberOfParkingSpace);

    void showActivity();

}
