package com.main.poc.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by abhijeetiyengar on 3/25/17.
 */

@Component
@ConfigurationProperties("details")
public class VehicleInfoProperties {

    private List<VehicleInfo> vehicleInfo = new ArrayList();

    public List<VehicleInfo> getVehicleInfo() {
        return vehicleInfo;
    }

    public void setVehicleInfo(List<VehicleInfo> vehicleInfo) {
        this.vehicleInfo = vehicleInfo;
    }

    public static class VehicleInfo {
        private String type;
        private int price = -1;
        private int size = -1;
        private List<String> errorList;
        private float pricePerTimeUnit;

        public VehicleInfo(String type, String price, String size) {
            this.type = type;
            setPrice(price);
            setSize(size);
        }

        public VehicleInfo() {
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(String price) {
            try {
                this.price = Integer.parseInt(price);

            } catch (NumberFormatException e) {
                addError("price of an vehicle should always be a number");
            }
        }

        public int getSize() {
            return size;
        }

        public void setSize(String size) {
            try {
                this.size = Integer.parseInt(size);
            } catch (NumberFormatException e) {
                addError("size take by vehicle should be a number");
            }
        }

        public float getPricePerTimeUnit() {
            return pricePerTimeUnit;
        }

        public void setPricePerTimeUnit(float pricePerTimeUnit) {
            this.pricePerTimeUnit = pricePerTimeUnit;
        }

        private void addError(String errorString) {
            if (errorList == null)
                errorList = new ArrayList<>();

            errorList.add(errorString);
        }

        public List<String> getErrorList() {
            return errorList;
        }

        public boolean verify() {
            if (type == null || price <= 0 || size <= 0)
                return false;
            else
                return true;

        }

        @Override
        public String toString() {
            return type + " are parked at price of " + price;
        }
    }

}
