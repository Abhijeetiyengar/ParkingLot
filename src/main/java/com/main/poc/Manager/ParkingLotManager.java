package com.main.poc.Manager;

import com.main.poc.Helper.GenericHelper;
import com.main.poc.Helper.Utils;
import com.main.poc.Helper.VehicleInfoProperties;
import com.main.poc.Manager.DataStore.DataStore;
import com.main.poc.Manager.DataStore.InMemoryDataStore;
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
    GenericHelper helper;

    @Value("${storageType:inMemory}")
    private String name;

    @PostConstruct
    public void postConstruct()
    {
        if(name!=null && name.equalsIgnoreCase("inMemory"))
        {
            store=new InMemoryDataStore(helper.getHelper().getVehicleInfoProperties().getVehicleInfo());
        }
        else
        {
            throw new RuntimeException("Invalid Storage Type Provided");
        }
    }

    public boolean preStart()
    {
        if(helper.getVechileTypeToInfoMap().size()==0)
        {
            System.out.println("No Specific types of Vehicles are provided , sorry can't park");

            throw new RuntimeException("No type of vehcile present . So closing the shop !!");

        }

        if(store.isParkingSpaceToBeAsked())
        {
            int numberOfParking= Utils. <Integer>  getInput("Please provide number of parking lots available",
                    t->{
                        try {
                           Integer i= Utils.convertStringToInt(t);

                           if(i.intValue()<=0)
                           {
                                System.out.println("Number of Parking lots cant be less then or equal to 0");
                                return null;
                           }
                           return  i;

                        } catch (Exception e) {
                            return null;
                        }
                    });

            store.setParkingSpace(numberOfParking);
        }

        System.out.println("Lets start parking");

        help();


        return true;

    }

    public void help()
    {

        System.out.println("-----------------Parking Help--------------------------\n");
        helper.getVechileTypeToInfoMap().entrySet().stream().forEach(
            t->  System.out.println( "               "+t.getValue())
        );
        System.out.println(String.format("\nPrices are per %d hour",helper.getHelper().getPrice_in_multiple_of_hours()));
        System.out.println("To Park type ENTER <vehicle type> for e.g ENTER CAR");
        System.out.println("To Park type EXIT <vehicle type> <number of hours> for e.g EXIT CAR 2");
        System.out.println("To get report type REPORT");
        System.out.println("To get help type HELP");
        System.out.println("To get history of activity type ACTIVITY");
        System.out.println("To stop type SHUTDOWN");
        System.out.println("\n**COMMANDS ARE NOT CASE SENSITIVE");


    }

    public VehicleInfoProperties.VehicleInfo getVehicleInfo(String type)
    {
        VehicleInfoProperties.VehicleInfo info= helper.getVechileTypeToInfoMap().get(type);

        if(info==null)
        {
            System.out.print("Invalid Vehicle Type provided , type help to find information");
        }

        return  info;
    }


    public void enter(VehicleInfoProperties.VehicleInfo vehicleInfo)
    {
        store.entry(vehicleInfo);
    }

    public void exit(VehicleInfoProperties.VehicleInfo vehicleInfo,int numberOfHours)
    {
        store.exit(vehicleInfo,numberOfHours);
    }

    public void report()
    {
        store.report();
    }

    public void activity() {store.showActivity();}







}
