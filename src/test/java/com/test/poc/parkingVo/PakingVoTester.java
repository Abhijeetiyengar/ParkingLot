package com.test.poc.parkingVo;

import com.main.poc.helper.PropertiesHelper;
import com.main.poc.properties.VehicleInfoProperties;
import com.main.poc.manager.vo.ParkingLotVO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by abhijeetiyengar on 3/25/17.
 */
@RunWith(SpringRunner.class)
//@SpringBootTest(classes = ParkingLotInitiatorTest.class )
//@ActiveProfiles("test")
@SpringBootTest
@TestPropertySource(locations = "classpath:\\test_properties_correct_data.properties")
public class PakingVoTester {

    @Autowired
    PropertiesHelper helper;

    public ParkingLotVO parkingLotVO;

    @Before
    public void createVO() {
        parkingLotVO = new ParkingLotVO(helper.getHelper().getVehicleInfoProperties().getVehicleInfo());
        parkingLotVO.setTotalNumberOfParkingLot(7);

    }

    @Test
    public void addMoreThenAvaliable() {
        VehicleInfoProperties.VehicleInfo vo = helper.getVechileTypeToInfoMap().get("car");
        boolean val = parkingLotVO.entry(vo);

        Assert.assertEquals(val, true);
        Assert.assertEquals(parkingLotVO.getAvailableSlots(), 5);

        vo = helper.getVechileTypeToInfoMap().get("truck");
        val = parkingLotVO.entry(vo);

        Assert.assertEquals(val, true);
        Assert.assertEquals(parkingLotVO.getAvailableSlots(), 1);

        vo = helper.getVechileTypeToInfoMap().get("car");
        val = parkingLotVO.entry(vo);

        Assert.assertEquals(val, false);
        Assert.assertEquals(parkingLotVO.getAvailableSlots(), 1);

    }

    @Test

    public void removeMoreThenAdded() {
        VehicleInfoProperties.VehicleInfo vo = helper.getVechileTypeToInfoMap().get("car");
        boolean val = parkingLotVO.entry(vo);

        Assert.assertEquals(val, true);
        Assert.assertEquals(parkingLotVO.getAvailableSlots(), 5);
        Assert.assertEquals(parkingLotVO.getCurrent_cost(), 0, 0);

        vo = helper.getVechileTypeToInfoMap().get("car");
        val = parkingLotVO.exit(vo, 1);

        Assert.assertEquals(parkingLotVO.getCurrent_cost(), 1, 0);
        Assert.assertEquals(val, true);
        Assert.assertEquals(parkingLotVO.getAvailableSlots(), 7);

        vo = helper.getVechileTypeToInfoMap().get("car");
        val = parkingLotVO.exit(vo, 1);

        Assert.assertEquals(val, false);
        Assert.assertEquals(parkingLotVO.getAvailableSlots(), 7);
        Assert.assertEquals(parkingLotVO.getCurrent_cost(), 1, 0);

        vo = helper.getVechileTypeToInfoMap().get("truck");
        val = parkingLotVO.entry(vo);

        Assert.assertEquals(val, true);
        Assert.assertEquals(parkingLotVO.getAvailableSlots(), 3);
        Assert.assertEquals(parkingLotVO.getCurrent_cost(), 1, 0);

        vo = helper.getVechileTypeToInfoMap().get("truck");
        val = parkingLotVO.exit(vo, 1);

        Assert.assertEquals(val, true);
        Assert.assertEquals(parkingLotVO.getAvailableSlots(), 7);
        Assert.assertEquals(parkingLotVO.getCurrent_cost(), 2.5, 0);
    }

}
