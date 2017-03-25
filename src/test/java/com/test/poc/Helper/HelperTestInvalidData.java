package com.test.poc.Helper;

import com.main.poc.Helper.PropertiesHelper;
import com.main.poc.Helper.VehicleInfoProperties;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by abhijeetiyengar on 3/25/17.
 */
@RunWith(SpringRunner.class)
public class HelperTestInvalidData {





    @Test(expected = RuntimeException.class)
    public void testPropertieswithInvalidMultipleHours() throws  Exception
    {
        PropertiesHelper helper=new PropertiesHelper();
        helper.setPrice_in_multiple_of_hours("abc");
        helper.postConstruct();
    }

    @Test(expected = RuntimeException.class)
    public void testPropertieswithInvalidSize() throws  Exception
    {
        PropertiesHelper helper=new PropertiesHelper();
        VehicleInfoProperties infoProperties=new VehicleInfoProperties();
        infoProperties.getVehicleInfo().add(new VehicleInfoProperties.VehicleInfo("car","2","abc"));
        helper.postConstruct();
    }

    @Test(expected = RuntimeException.class)
    public void testPropertieswithInvalidPrice() throws  Exception
    {
        PropertiesHelper helper=new PropertiesHelper();
        VehicleInfoProperties infoProperties=new VehicleInfoProperties();
        infoProperties.getVehicleInfo().add(new VehicleInfoProperties.VehicleInfo("car","def","2"));
        helper.postConstruct();
    }



}
