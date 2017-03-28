package com.test.poc.helper;

import com.main.poc.properties.ParentProperties;
import com.main.poc.properties.VehicleInfoProperties;
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
        ParentProperties helper=new ParentProperties();
        helper.setPrice_in_multiple_of_hours("abc");
        helper.postConstruct();
    }

    @Test(expected = RuntimeException.class)
    public void testPropertieswithInvalidSize() throws  Exception
    {
        ParentProperties helper=new ParentProperties();
        VehicleInfoProperties infoProperties=new VehicleInfoProperties();
        infoProperties.getVehicleInfo().add(new VehicleInfoProperties.VehicleInfo("car","2","abc"));
        helper.postConstruct();
    }

    @Test(expected = RuntimeException.class)
    public void testPropertieswithInvalidPrice() throws  Exception
    {
        ParentProperties helper=new ParentProperties();
        VehicleInfoProperties infoProperties=new VehicleInfoProperties();
        infoProperties.getVehicleInfo().add(new VehicleInfoProperties.VehicleInfo("car","def","2"));
        helper.postConstruct();
    }



}
