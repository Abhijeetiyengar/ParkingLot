package com.main.poc.helper;

import com.main.poc.properties.ParentProperties;
import com.main.poc.properties.VehicleInfoProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by abhijeetiyengar on 3/25/17.
 */

@Component
public class PropertiesHelper {

    @Autowired
    ParentProperties helper;

    private Map<String, VehicleInfoProperties.VehicleInfo> vechileTypeToInfoMap = new HashMap<>();

    public ParentProperties getHelper() {
        return helper;
    }

    public Map<String, VehicleInfoProperties.VehicleInfo> getVechileTypeToInfoMap() {
        return vechileTypeToInfoMap;
    }

    @PostConstruct
    public void postConstruct() {

        helper.getVehicleInfoProperties().getVehicleInfo().stream().forEach(
                t -> vechileTypeToInfoMap.put(t.getType(), t)
        );

    }

}
