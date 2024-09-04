package homework.hw4.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;

import java.sql.Date;
import java.util.ArrayList;

@Entity
public class Guest extends Person{
    @Column(name = "start_date")
    private Date startDate;
    @Column(name = "end_date")
    private Date endDate;

    public Guest() {}

    public Guest(String firstName, String lastName, String phone, String email, Date startDate, Date endDate) {
        super(firstName, lastName, phone, email);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Guest(int id, String firstName, String lastName, String phone, String email, Date startDate, Date endDate) {
        super(id, firstName, lastName, phone, email);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Date getStartDate() {
        return startDate;
    }
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    public Date getEndDate() {
        return endDate;
    }
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    @ManyToMany
    private ArrayList<Room> rooms = new ArrayList<>();

    public ArrayList<Room> getRooms() {
        return rooms;
    }
    public void setRooms(ArrayList<Room> rooms) {
        this.rooms = rooms;
    }
    public void addRoom(Room room) {
        this.rooms.add(room);
    }

    @Override
    public String toString() {
        return super.toString() + startDate.toString() + " " + endDate.toString();
    }
}