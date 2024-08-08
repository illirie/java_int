package com.example.model;

import java.sql.Date;

public class Booking {
    private int id;
    private int guestId;
    private int roomId;
    private Date startTime;
    private Date endTime;

    public Booking() {}

    public Booking(int id, int guestId, int roomId, Date startTime, Date endTime) {
        this.id = id;
        this.guestId = guestId;
        this.roomId = roomId;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public int getId() {
        return id;
    }
    public int getGuestId() {
        return guestId;
    }
    public void setGuestId(int guestId) {
        this.guestId = guestId;
    }
    public int getRoomId() {
        return roomId;
    }
    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }
    public Date getStartTime() {
        return startTime;
    }
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }
    public Date getEndTime() {
        return endTime;
    }
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "Booking [id=" + id + ", guestId=" + guestId + ", roomId="
                + roomId + ", startTime=" + startTime + ", endTime=" + endTime + "]";
    }

}
