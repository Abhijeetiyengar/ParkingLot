package com.main.poc.CLI;

import com.main.poc.Helper.Utils;
import com.main.poc.Helper.VehicleInfoProperties;
import com.main.poc.Manager.ParkingLotManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by abhijeetiyengar on 3/25/17.
 */
@Component
public class CliVisualizer {

    @Autowired
    ParkingLotManager manager;





    public void createInitMessage()
    {
        System.out.println("Welcome to Test Parking Lot");

        if(!manager.preStart())
            return ;

        for(;;)
        {

            ParsedStatusVO statusVO= Utils.<ParsedStatusVO>getInput("Enter Command you want to perform ",this::validateStart);

            if(statusVO==null)
                continue;


            switch (statusVO.getStatusEum())
            {
                case ENTER:

                            manager.enter(statusVO.getVehicleInfo());

                            break;

                case EXIT:

                            manager.exit(statusVO.getVehicleInfo(),statusVO.hoursForExit);
                            break;

                case HELP:
                            manager.help();
                            break;

                case REPORT:

                            manager.report();
                            break;


            }

            System.out.println("Operation Performed Successfully ");


        }



    }

    public ParsedStatusVO validateStart(String start)
    {
        if(start==null || (start=start.trim()).isEmpty())
        {
            return  null;
        }

        String[] commandArr=start.toLowerCase().split("\\s+");

        switch (commandArr[0])
        {
            case "enter":
                            if(commandArr.length!=2)
                            {
                                System.out.println("Invalid ENTER Command provided . Provide the right one please for e.g ENTER CAR");
                                return  null;
                            }

                            VehicleInfoProperties.VehicleInfo info=manager.getVehicleInfo(commandArr[1]);

                            return info==null?null: new ParsedStatusVO(OperationStatusEum.ENTER,info,-1);

            case "exit":

                            if(commandArr.length!=3)
                            {
                                System.out.println("Invalid Exit Command provided . Provide the right one please for e.g EXIT CAR 2");
                                return  null;
                            }
                            info=manager.getVehicleInfo(commandArr[1]);

                            int i=-1;

                            try {
                                i=Utils.convertStringToInt(commandArr[2]);
                                if(i<=0)
                                {
                                    System.out.println("Time cannot be less then 0 for Exit command, type help to find information");
                                }
                            } catch (Exception e) {
                                System.out.println("Invalid time provided for Exit command, type help to find information");
                            }

                            return info==null||i<=0?null: new ParsedStatusVO(OperationStatusEum.EXIT,info,i);

            case "report":

                        //manager.report();
                        return  new ParsedStatusVO(OperationStatusEum.REPORT,null,-1);

            case "help":
                            //manager.help();
                        return  new ParsedStatusVO(OperationStatusEum.HELP,null,-1);

            case "shutdown":

                            System.exit(0);

            default:

                            System.out.println("Invalid Command , type help to find information");
                            return  null;




        }



    }

    public class ParsedStatusVO
    {
        OperationStatusEum statusEum;
        VehicleInfoProperties.VehicleInfo vehicleInfo;
        int hoursForExit;

        public ParsedStatusVO(OperationStatusEum statusEum, VehicleInfoProperties.VehicleInfo vehicleInfo, int hoursForExit) {
            this.statusEum = statusEum;
            this.vehicleInfo = vehicleInfo;
            this.hoursForExit = hoursForExit;
        }

        public OperationStatusEum getStatusEum() {
            return statusEum;
        }

        public VehicleInfoProperties.VehicleInfo getVehicleInfo() {
            return vehicleInfo;
        }

        public int getHoursForExit() {
            return hoursForExit;
        }
    }

    public enum OperationStatusEum{ENTER,EXIT,REPORT,HELP};
}
