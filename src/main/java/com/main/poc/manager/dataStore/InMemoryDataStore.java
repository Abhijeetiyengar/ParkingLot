package com.main.poc.manager.dataStore;

import com.main.poc.helper.InputOutputHelper;
import com.main.poc.properties.VehicleInfoProperties;
import com.main.poc.manager.vo.ParkingLotVO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by abhijeetiyengar on 3/25/17.
 * <p>
 * An in-memory implementation for keeping track of parking lot
 */
public class InMemoryDataStore implements DataStore {

    final ParkingLotVO parkingLotVO;

    final List<String> activityList = new ArrayList<>();

    public InMemoryDataStore(List<VehicleInfoProperties.VehicleInfo> vehicleInfoList) {
        parkingLotVO = new ParkingLotVO(vehicleInfoList);
    }

    @Override
    public void entry(VehicleInfoProperties.VehicleInfo vehicleInfo) {

        if (parkingLotVO.entry(vehicleInfo))
            activityList.add(String.format("%s entered at %s", vehicleInfo.getType(), LocalDateTime.now().toString()));

    }

    @Override
    public void exit(VehicleInfoProperties.VehicleInfo vehicleInfo, int numberOfHours) {

        if (parkingLotVO.exit(vehicleInfo, numberOfHours))
            activityList.add(String.format("%s exited at %s with hours %d", vehicleInfo.getType(), LocalDateTime.now().toString(), numberOfHours));

    }

    @Override
    public void report() {
        parkingLotVO.report();
    }

    @Override
    public boolean isParkingSpaceToBeAsked() {
        return true;

    }

    @Override
    public void setParkingSpace(int numberOfParkingSpace) {
        parkingLotVO.setTotalNumberOfParkingLot(numberOfParkingSpace);
    }

    @Override
    public void showActivity() {
        InputOutputHelper.printOutput("\n");

        if (activityList.size() != 0)
            activityList.stream().forEach(
                    t -> InputOutputHelper.printOutput(t)
            );
        else
            InputOutputHelper.printOutput("No Activity has been performed");
    }

}
