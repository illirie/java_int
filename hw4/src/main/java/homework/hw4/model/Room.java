package homework.hw4.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import org.springframework.data.relational.core.mapping.Table;

@Entity
@Table(name = "rooms")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private Rate rate;
    @ManyToMany
    private ArrayList<Guest> guests;

    public Room() {

    }

    public Room(Rate rate) {
        this.rate = rate;
        this.guests = new ArrayList<>();
    }

    public Room(int id, Rate rate) {
        this.id = id;
        this.rate = rate;
        this.guests = new ArrayList<>();
    }

    public int getId() {
        return id;
    }
    public Rate getRate() {
        return rate;
    }
    public void setRate(Rate rate) {
        this.rate = rate;
    }
    public ArrayList<Guest> getGuests() {
        return guests;
    }
    public void setGuests(ArrayList<Guest> guests) {
        this.guests = guests;
    }

    @Override
    public String toString() {
        return "Room [id=" + id + ", rate=" + rate + "]";
    }
}