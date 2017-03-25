package com.main.poc.Manager.DataStore;

import com.main.poc.Helper.VehicleInfoProperties;
import com.main.poc.Manager.vo.ParkingLotVO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by abhijeetiyengar on 3/25/17.
 */
public class InMemoryDataStore implements  DataStore {



    final ParkingLotVO parkingLotVO;

    final  List<String> activityList=new ArrayList<>();

    public  InMemoryDataStore(List<VehicleInfoProperties.VehicleInfo> vehicleInfoList)
    {
        parkingLotVO=new ParkingLotVO(vehicleInfoList);
    }


    @Override
    public void entry(VehicleInfoProperties.VehicleInfo vehicleInfo) {

        parkingLotVO.entry(vehicleInfo);

        activityList.add(String.format("%s entered at %s",vehicleInfo.getType(), LocalDateTime.now().toString()));

    }

    @Override
    public void exit(VehicleInfoProperties.VehicleInfo vehicleInfo,int numberOfHours) {

        parkingLotVO.exit(vehicleInfo,numberOfHours);

        activityList.add(String.format("%s exited at %s",vehicleInfo.getType(), LocalDateTime.now().toString()));

    }

    @Override
    public void report() {
        parkingLotVO.report();
    }

    @Override
    public boolean isParkingSpaceToBeAsked() {
        return  true;

    }

    @Override
    public void setParkingSpace(int numberOfParkingSpace) {
        parkingLotVO.setTotalNumberOfParkingLot(numberOfParkingSpace);
    }




}
