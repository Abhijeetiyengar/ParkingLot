package com.main.poc.Manager.vo;

import com.main.poc.Helper.VehicleInfoProperties;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by abhijeetiyengar on 3/25/17.
 */
public class ParkingLotVO {

    int totalNumberOfParkingLot;

    int currentSlots;

    double current_cost=0;

    Map<String,Status>  statusMap=new HashMap<>();




    public  ParkingLotVO(List<VehicleInfoProperties.VehicleInfo> vehcilesList)
    {
        vehcilesList.stream().forEach(
                t->{
                    statusMap.put(t.getType(),new Status(t.getType(),0L,0L));
                }
        );
    }


    public void setTotalNumberOfParkingLot(int totalNumberOfParkingLot)
    {
        this.totalNumberOfParkingLot=totalNumberOfParkingLot;
        this.currentSlots=totalNumberOfParkingLot;
    }

    /*
        This method need to be synchronized or we need to use make currentSlots Atomic in case we are dealing with
         multi thread applications specially in case of UI . But be care full blocking algorithms will hurt the performance.

     */

    public  boolean entry(VehicleInfoProperties.VehicleInfo info)
    {
        if(currentSlots<info.getSize()) {
            System.out.println("Apologize , but we don't have enough space for your vehicle");
            return false;
        }

        statusMap.get(info.getType()).addIncoming();

        currentSlots-=info.getSize();

        return true;
    }

    public boolean exit(VehicleInfoProperties.VehicleInfo info, float numberOfHours)
    {
        if(statusMap.get(info.getType()).incoming-statusMap.get(info.getType()).outgoing<=0) {
            System.out.println("Apologize , but not possible to exit as there aren't enough vehicles of your type standing");
            return false;
        }
        currentSlots+=info.getSize();
        current_cost+=numberOfHours*info.getPricePerTimeUnit();
        statusMap.get(info.getType()).addOutGoing();
        return  true;
    }

    public void report()
    {

        statusMap.entrySet().stream().forEach(
                t->
                    System.out.println (String.format("%s Entered %d , Exited %d , currently parking %d",
                            t.getValue().type,t.getValue().incoming,t.getValue().outgoing,
                            t.getValue().incoming-t.getValue().outgoing))

        );

        System.out.println(String.format("Space Available %d ",currentSlots));

        System.out.println(String.format("Total earnings %4.2f ",current_cost));


    }

    public int getAvailableSlots()
    {
        return currentSlots;
    }

    public double getCurrent_cost()
    {
        return  current_cost;
    }

    class Status
    {
        String type;

        long incoming;

        long outgoing;

        public Status(String type, long incoming, long outgoing) {
            this.type = type;
            this.incoming = incoming;
            this.outgoing = outgoing;
        }

        public void addIncoming()
        {
            incoming++;
        }

        public void  addOutGoing()
        {
            outgoing++;
        }
    }
}
