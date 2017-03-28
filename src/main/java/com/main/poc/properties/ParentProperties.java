package com.main.poc.properties;

import com.main.poc.properties.VehicleInfoProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by abhijeetiyengar on 3/25/17.
 */

@Component
@ConfigurationProperties
public class ParentProperties {

    private int price_in_multiple_of_hours;
    private String price_in_multiple_of_hours_str;
    private String storageType;

    @Autowired
    private VehicleInfoProperties vehicleInfoProperties;

    public String getStorageType() {
        return storageType;
    }

    public void setStorageType(String storageType) {
        this.storageType = storageType;
    }

    public int getPrice_in_multiple_of_hours() {
        return price_in_multiple_of_hours;
    }

    public void setPrice_in_multiple_of_hours(String price_in_multiple_of_hours) throws Exception {
        this.price_in_multiple_of_hours_str = price_in_multiple_of_hours;
    }

    public VehicleInfoProperties getVehicleInfoProperties() {
        return vehicleInfoProperties;
    }

    public void setVehicleInfoProperties(VehicleInfoProperties vehicleInfoProperties) {
        this.vehicleInfoProperties = vehicleInfoProperties;
    }

    @PostConstruct
    public void postConstruct() {

        try {
            price_in_multiple_of_hours = Integer.parseInt(price_in_multiple_of_hours_str);
        } catch (NumberFormatException e) {
            throw new RuntimeException("price_in_multiple_of_hours should be numeric");
        }

        if (price_in_multiple_of_hours == 0) {
            throw new RuntimeException("price_in_multiple_of_hours shouldn't be 0");
        }

        StringBuilder vehicleInfoBuilder = new StringBuilder();
        final AtomicInteger counter = new AtomicInteger(0);

        vehicleInfoProperties.getVehicleInfo().stream().forEach(
                vehicleInfo -> {
                    if (vehicleInfo.getErrorList() != null)
                        throw new RuntimeException(vehicleInfo.getErrorList().toString());
                    if (!vehicleInfo.verify())
                        throw new RuntimeException("Invalid data provided for type " + vehicleInfo.getType());
                    vehicleInfo.setPricePerTimeUnit((float) vehicleInfo.getPrice() / price_in_multiple_of_hours);
                    vehicleInfoBuilder.append(counter.addAndGet(1)).append(" for ").append(vehicleInfo.getType()).append("\n");
                }
        );

    }
}
