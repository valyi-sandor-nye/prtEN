package org.example2;

enum Genre2 { FAMILYHOUSE, CONDOMINIUM,
    FARM
}


public class RealEstate2 implements PropertyInterface2 {
    private String city;
    private double price;
    private int sqm;
    private double numberOfRooms;
    private Genre2 genre;

    public RealEstate2(String city, double price, int sqm, double numberOfRooms, Genre2 genre) {
        this.city = city;
        this.price = price;
        this.sqm = sqm;
        this.numberOfRooms = numberOfRooms;
        this.genre = genre;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getSqm() {
        return sqm;
    }

    public void setSqm(int sqm) {
        this.sqm = sqm;
    }

    public double getNumberOfRooms() {
        return numberOfRooms;
    }

    public void setNumberOfRooms(double numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }

    public Genre2 getGenre() {
        return genre;
    }

    public void setGenre(Genre2 genre) {
        this.genre = genre;
    }

    @Override
    public void discount(int percent) {
        price = price-price*(percent/100.0);
    }

    @Override
    public int totalPrice() {
        int i = (int) (price * sqm);
        return i;
    }

    @Override //HOME WORK
    public double average() {
        return 0;
    }
}
