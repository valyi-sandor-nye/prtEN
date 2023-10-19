package realestate;

import static org.junit.jupiter.api.Assertions.*;

class RealEstateTest {

    private RealEstate underTest = new RealEstate("Debrecen",600000.0,51,2,Genre.CONDOMINIUM);

    @org.junit.jupiter.api.Test
    void discountTest() {
        underTest.discount(20);
        double actual = underTest.getPrice();
        double expected = 480000.0;
        assertEquals(actual,expected);
    }

    @org.junit.jupiter.api.Test
    void totalPriceTest() {
        double actual = underTest.totalPrice();
        double expected = 600000.0*51*1.2;
        assertTrue(expected == actual);

    }

    @org.junit.jupiter.api.Test
    void averageTest() {
        double actual = underTest.average();
        double expected = 51/2.0;
        assertEquals(expected,actual);

    }
}