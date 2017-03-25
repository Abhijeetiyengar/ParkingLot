package com.test.poc.Helper;

import com.main.poc.Helper.GenericHelper;
import com.test.poc.ParkingLotInitiatorTest;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by abhijeetiyengar on 3/25/17.
 */
@RunWith(SpringRunner.class)
/*@SpringBootTest(classes = ParkingLotInitiatorTest.class )*/
@ActiveProfiles("test")
@SpringBootTest
//@ContextConfiguration(classes = ConfigurationClass.class)
@TestPropertySource("classpath:\\test_properties_no_data.properties")
public class HelperTestNoData {

    @Autowired
    GenericHelper helper;

    @Test

    public void  testPropertiesWithValidData()
    {

        Assert.assertEquals(helper.getHelper().getPrice_in_multiple_of_hours(),5);


    }
}
