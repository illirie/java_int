package org.example.tables;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Rate {
    private int id;
    private int max_persons;
    private boolean hasSeeview;
    private boolean hasLiftNearby;
    private boolean hasFridge;

    public Rate() {

    };

    public Rate(int rate_id, int r_max_persons, boolean r_hasSeeview, boolean r_hasLiftNearby, boolean r_hasFridge) {
        id = rate_id;
        max_persons = r_max_persons;
        hasSeeview = r_hasSeeview;
        hasLiftNearby = r_hasLiftNearby;
        hasFridge = r_hasFridge;
    }

    public Rate(ResultSet resultSet) {
        try {
            id = resultSet.getInt("rate_id");
            max_persons = resultSet.getInt("max_persons");
            hasSeeview = resultSet.getBoolean("has_seeview");
            hasLiftNearby = resultSet.getBoolean("has_lift_nearby");
            hasFridge = resultSet.getBoolean("has_fridge");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public int getId() {
        return id;
    }

    public boolean isHasFridge() {
        return hasFridge;
    }

    public boolean isHasLiftNearby() {
        return hasLiftNearby;
    }

    public boolean isHasSeeview() {
        return hasSeeview;
    }

    public int getMax_persons() {
        return max_persons;
    }

    @Override
    public String toString() {
        return "Rate{ id=" + id + ", max persons=" + max_persons + ", " +
                "has seeview=" + hasSeeview + ", has lift nearby=" + hasLiftNearby + "}";
    }
}
