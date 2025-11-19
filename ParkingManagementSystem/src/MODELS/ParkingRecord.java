/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODELS;

import java.time.LocalDateTime;

/**
 *
 * @author Fabrice
 */
public class ParkingRecord {
    private int recordId;
    private int vehicleId;
    private int slotId;
    private LocalDateTime timeIn;
    private LocalDateTime timeOut;
    private double fee;
    private String status;

    public ParkingRecord() {
    }

    public ParkingRecord(int recordId, int vehicleId, int slotId, LocalDateTime timeIn, LocalDateTime timeOut, double fee, String status) {
        this.recordId = recordId;
        this.vehicleId = vehicleId;
        this.slotId = slotId;
        this.timeIn = timeIn;
        this.timeOut = timeOut;
        this.fee = fee;
        this.status = status;
    }

    public int getRecordId() {
        return recordId;
    }

    public void setRecordId(int recordId) {
        this.recordId = recordId;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public int getSlotId() {
        return slotId;
    }

    public void setSlotId(int slotId) {
        this.slotId = slotId;
    }

    public LocalDateTime getTimeIn() {
        return timeIn;
    }

    public void setTimeIn(LocalDateTime timeIn) {
        this.timeIn = timeIn;
    }

    public LocalDateTime getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(LocalDateTime timeOut) {
        this.timeOut = timeOut;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    
}
