package realestate;

public class RealEstate implements PropertyInterface, Comparable <RealEstate>{
    private String city;
    private double price;
    private int sqm;
    private double numberOfRooms;
    private Genre genre;

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

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public RealEstate(String city, double price, int sqm, double numberOfRooms, Genre genre) {
        this.city = city;
        this.price = price;
        this.sqm = sqm;
        this.numberOfRooms = numberOfRooms;
        this.genre = genre;
    }

    public void discount(int percentage) {
      setPrice(price-=(price*percentage/100.0));
    }

    public int totalPrice() {
        int basePrice = (int)(price*sqm);
        switch (city) {
            case "Budapest": return (int) (basePrice*1.3);
            case "Debrecen": return (int) (basePrice*1.2);
            case "Nyiregyhaza": return (int) (basePrice*1.15);
            default: return basePrice;
        }
    }

    public double average() {
        return sqm/numberOfRooms;
    }


    @Override
    public String toString() {
        return "RealEstate{" +
                "city='" + city + '\'' +
                ", price=" + price +
                ", sqm=" + sqm +
                ", numberOfRooms=" + numberOfRooms +
                ", genre=" + genre +
                ", total price=" + totalPrice() +
                ", average=" + average()+
                '}';
    }

    @Override
    public int compareTo(RealEstate o) {
        return Integer.compare(totalPrice(),o.totalPrice());
    }
}
