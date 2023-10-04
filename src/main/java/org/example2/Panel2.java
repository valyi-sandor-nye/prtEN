package org.example2;

public class Panel2 extends RealEstate2 {
    private int floor;
    private boolean isInsulated;

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public boolean isInsulated() {
        return isInsulated;
    }

    public void setInsulated(boolean insulated) {
        isInsulated = insulated;
    }

    public Panel2(String city, double price, int sqm, double numberOfRooms, Genre2 genre, int floor, boolean isInsulated) {
        super(city, price, sqm, numberOfRooms, genre);
        this.floor = floor;
        this.isInsulated = isInsulated;
    }

}
