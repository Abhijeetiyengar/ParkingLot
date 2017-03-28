package com.main.poc.manager;

import com.main.poc.helper.ConversionHelper;
import com.main.poc.helper.PropertiesHelper;
import com.main.poc.helper.InputOutputHelper;
import com.main.poc.properties.VehicleInfoProperties;
import com.main.poc.manager.dataStore.DataStore;
import com.main.poc.manager.dataStore.InMemoryDataStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Created by abhijeetiyengar on 3/25/17.
 */

@Component
public class ParkingLotManager {

    DataStore store;

    @Autowired
    PropertiesHelper helper;

    @Value("${storageType:inMemory}")
    private String name;

    @PostConstruct
    public void postConstruct() {
        if (name != null && name.equalsIgnoreCase("inMemory")) {
            store = new InMemoryDataStore(helper.getHelper().getVehicleInfoProperties().getVehicleInfo());
        } else {
            throw new RuntimeException("Invalid Storage Type Provided");
        }
    }

    public boolean preStart() {
        if (helper.getVechileTypeToInfoMap().size() == 0) {
            InputOutputHelper.printOutput("No Specific types of Vehicles are provided , sorry can't park");

            throw new RuntimeException("No type of vehcile present . So closing the shop !!");

        }

        if (store.isParkingSpaceToBeAsked()) {
            int numberOfParking = InputOutputHelper.<Integer>getInput("Please provide number of parking lots available",
                    t -> {
                        try {
                            Integer i = ConversionHelper.convertStringToInt(t);

                            if (i.intValue() <= 0) {
                                InputOutputHelper.printOutput("Number of Parking lots cant be less than or equal to 0");
                                return null;
                            }
                            return i;

                        } catch (Exception e) {
                            return null;
                        }
                    });

            store.setParkingSpace(numberOfParking);
        }

        InputOutputHelper.printOutput("Lets start parking");

        help();

        return true;

    }

    public void help() {

        InputOutputHelper.printOutput("-----------------Parking Help--------------------------\n");
        helper.getVechileTypeToInfoMap().entrySet().stream().forEach(
                t -> InputOutputHelper.printOutput("               " + t.getValue())
        );
        InputOutputHelper.printOutput(String.format("\nPrices are per %d hour", helper.getHelper().getPrice_in_multiple_of_hours()));
        InputOutputHelper.printOutput("To Park type ENTER <vehicle type> for e.g ENTER CAR");
        InputOutputHelper.printOutput("To Un-Park type EXIT <vehicle type> <number of hours> for e.g EXIT CAR 2");
        InputOutputHelper.printOutput("To get report type REPORT");
        InputOutputHelper.printOutput("To get help type HELP");
        InputOutputHelper.printOutput("To get history of activity type ACTIVITY");
        InputOutputHelper.printOutput("To stop type SHUTDOWN");
        InputOutputHelper.printOutput("\n**COMMANDS ARE NOT CASE SENSITIVE");

    }

    public VehicleInfoProperties.VehicleInfo getVehicleInfo(String type) {
        VehicleInfoProperties.VehicleInfo info = helper.getVechileTypeToInfoMap().get(type);

        if (info == null) {
            System.out.print("Invalid Vehicle Type provided , type help to find information. \n");
        }

        return info;
    }

    public void enter(VehicleInfoProperties.VehicleInfo vehicleInfo) {
        store.entry(vehicleInfo);
    }

    public void exit(VehicleInfoProperties.VehicleInfo vehicleInfo, int numberOfHours) {
        store.exit(vehicleInfo, numberOfHours);
    }

    public void report() {
        store.report();
    }

    public void activity() {
        store.showActivity();
    }

}
