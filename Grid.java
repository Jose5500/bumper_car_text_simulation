package com.example.company;

import java.util.ArrayList;

public class Grid {
    ArrayList<ArrayList<String>> gridIndexes = new ArrayList<ArrayList<String>>();
    public Grid(){
        for(int i = 0; i < 6; i++){
            ArrayList<String> newArray = new ArrayList<>();
            for(int j = 0; j < 6; j++){
                newArray.add(" ");
            }
            gridIndexes.add(newArray);
        }
    }

    public void printBumperCar(BumperCar bumperCar){
        gridIndexes.get(bumperCar.getLocation().getY()).set(bumperCar.getLocation().getX(),bumperCar.getPrintedDirection());
    }
    public void removeBumperCar(Location location){
        gridIndexes.get(location.y).set(location.getX(), " ");
    }
    public void printGrid(){
        System.out.println(" _ _ _ _ _ _\n" +
                "|" + gridIndexes.get(0).get(0) + "|" + gridIndexes.get(0).get(1) + "|" +gridIndexes.get(0).get(2) + "|" + gridIndexes.get(0).get(3) + "|" + gridIndexes.get(0).get(4) +"|" + gridIndexes.get(0).get(5) +"|\n" +
                "|" + gridIndexes.get(1).get(0) + "|" + gridIndexes.get(1).get(1) + "|" +gridIndexes.get(1).get(2) + "|" + gridIndexes.get(1).get(3) + "|" + gridIndexes.get(1).get(4) +"|" + gridIndexes.get(1).get(5) +"|\n" +
                "|" + gridIndexes.get(2).get(0) + "|" + gridIndexes.get(2).get(1) + "|" +gridIndexes.get(2).get(2) + "|" + gridIndexes.get(2).get(3) + "|" + gridIndexes.get(2).get(4) +"|" + gridIndexes.get(2).get(5) +"|\n" +
                "|" + gridIndexes.get(3).get(0) + "|" + gridIndexes.get(3).get(1) + "|" +gridIndexes.get(3).get(2) + "|" + gridIndexes.get(3).get(3) + "|" + gridIndexes.get(3).get(4) +"|" + gridIndexes.get(3).get(5) +"|\n" +
                "|" + gridIndexes.get(4).get(0) + "|" + gridIndexes.get(4).get(1) + "|" +gridIndexes.get(4).get(2) + "|" + gridIndexes.get(4).get(3) + "|" + gridIndexes.get(4).get(4) +"|" + gridIndexes.get(4).get(5) +"|\n" +
                "|" + gridIndexes.get(5).get(0) + "|" + gridIndexes.get(5).get(1) + "|" +gridIndexes.get(5).get(2) + "|" + gridIndexes.get(5).get(3) + "|" + gridIndexes.get(5).get(4) +"|" + gridIndexes.get(5).get(5) +"|\n" +
                " - - - - - -");
    }
}
