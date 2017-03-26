package com.test.poc.VisualizerTest;

import com.main.poc.CLI.CliVisualizer;
//import com.test.poc.ParkingLotInitiatorTest;
import com.test.poc.ParkingLotInitiatorTest;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by abhijeetiyengar on 3/25/17.
 */


@RunWith(SpringRunner.class)
@SpringBootTest(classes = ParkingLotInitiatorTest.class )
@ActiveProfiles("test")


public class VisualizerTest {

    @Autowired
    CliVisualizer visualizer;


    @Test
    public void testValidateStart()
    {
          CliVisualizer.ParsedStatusVO statusVO=visualizer.validateStart("entEr car");

          Assert.assertEquals(statusVO.getStatusEum(),CliVisualizer.OperationStatusEum.ENTER);
            Assert.assertEquals(statusVO.getVehicleInfo().getType(),"car");

         statusVO=visualizer.validateStart("entEr truck");

        Assert.assertEquals(statusVO.getStatusEum(),CliVisualizer.OperationStatusEum.ENTER);
        Assert.assertEquals(statusVO.getVehicleInfo().getType(),"truck");

         statusVO=visualizer.validateStart("exit car   2");

        Assert.assertEquals(statusVO.getStatusEum(),CliVisualizer.OperationStatusEum.EXIT);
        Assert.assertEquals(statusVO.getVehicleInfo().getType(),"car");

        statusVO=visualizer.validateStart("exit car  asd");
        Assert.assertEquals(statusVO,null);

        statusVO=visualizer.validateStart("asds car  asd");
        Assert.assertEquals(statusVO,null);

        statusVO=visualizer.validateStart("enter car1  asd");
        Assert.assertEquals(statusVO,null);

        statusVO=visualizer.validateStart("exit car -1");
        Assert.assertEquals(statusVO,null);

        statusVO=visualizer.validateStart("exit car 0");
        Assert.assertEquals(statusVO,null);



    }

}
