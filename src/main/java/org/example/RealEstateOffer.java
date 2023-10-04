package org.example;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.TreeSet;

public class RealEstateOffer {
    static int NUMBER_OF_REAL_ESTATE_DATA_MEMBERS = 6;
    static int NUMBER_OF_EXTRA_PANEL_DATA_MEMBERS = 2;

    static TreeSet<RealEstate> stock = new TreeSet<>();

    public RealEstateOffer() {
        try {
            Scanner scnr = new Scanner(new File("properties.txt"));
            int lineNumber = 1;
            while (scnr.hasNextLine()) {
                String line = scnr.nextLine();
                try {
                    String[] pieces = line.split("#");
                    if (pieces.length == NUMBER_OF_REAL_ESTATE_DATA_MEMBERS) {
                        String city = pieces[1];
                        double price = Double.parseDouble(pieces[2]);
                        int sqm = Integer.parseInt(pieces[3]);
                        double numberOfRooms = Double.parseDouble(pieces[4]);
                        Genre genre = Genre.valueOf(pieces[5]);
                        if (!pieces[0].equals("REALESTATE")) {
                            throw new BadInputFile();
                        }
                        stock.add(new RealEstate(city, price, sqm, numberOfRooms, genre));
                    }


                    if (pieces.length == NUMBER_OF_REAL_ESTATE_DATA_MEMBERS +
                            NUMBER_OF_EXTRA_PANEL_DATA_MEMBERS) {
                        String city = pieces[1];
                        double price = Double.parseDouble(pieces[2]);
                        int sqm = Integer.parseInt(pieces[3]);
                        double numberOfRooms = Double.parseDouble(pieces[4]);
                        Genre genre = Genre.valueOf(pieces[5]);
                        int floor = Integer.parseInt(pieces[6]);
                        boolean isInsulated = pieces[7].equalsIgnoreCase("true");  //TODO error danger getBoolean
                        if (!pieces[0].equals("PANEL")) {
                            throw new BadInputFile();
                        }
                            stock.add(new Panel(city, price, sqm, numberOfRooms, genre, floor, isInsulated));

                    }
                } catch (BadInputFile bif) {
                    System.err.println("bad format");
                }


                lineNumber++;
            }
            ;


        } catch (FileNotFoundException e) {
            System.err.println("File reading error");
        }

    }

    public static void main(String[] args) {
        StringBuffer answer = new StringBuffer();
        answer.append(" The average square meter price of real estate: ");
        double collectSqm = 0.0; int collectPrice = 0;
        for (RealEstate r: stock) {
            collectSqm += r.getSqm();
            collectPrice += r.totalPrice();}
        answer.append(collectPrice/collectSqm);
        answer.append('\n');
        answer.append(" The price of the cheapest property: ");
        int minimumFound= 0;
        for (RealEstate r: stock) {
            int newPrice = r.totalPrice();

            if (minimumFound > newPrice) {
                minimumFound = newPrice;
            }
        }
        answer.append(minimumFound);
        answer.append('\n');


    }

}
