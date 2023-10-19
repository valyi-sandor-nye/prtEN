package realestate;

public class Panel extends RealEstate implements PanelInterface{
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

    public Panel(String city, double price, int sqm, double numberOfRooms, Genre genre, int floor, boolean isInsulated) {
        super(city, price, sqm, numberOfRooms, genre);
        this.floor = floor;
        this.isInsulated = isInsulated;
    }

    @Override
    public int totalPrice() {
        int price = super.totalPrice();
        if (0  <= floor && floor  <=2) price += (int) (0.05 * price);
        if (10  == floor) price -= (int) (0.05 * price);
        if (isInsulated()) price += (int) (0.05 * price);
        return price;
    }

    @Override
    public boolean hasSameAmount(RealEstate realEstate) {
                    return totalPrice() == realEstate.totalPrice();

    }

    @Override
    public int roomPrice() {
        return (int) (super.totalPrice()/getNumberOfRooms());
    }
}
