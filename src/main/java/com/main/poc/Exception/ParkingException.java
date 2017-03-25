package com.main.poc.Exception;

/**
 * Created by abhijeetiyengar on 3/25/17.
 */
public class ParkingException extends  Exception {
    public ParkingException(String message) {
        super(message);
    }

    public ParkingException(String message, Throwable cause) {
        super(message, cause);
    }

    public ParkingException(Throwable cause) {
        super(cause);
    }
}
