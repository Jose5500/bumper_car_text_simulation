package com.example.company;

import java.util.ArrayList;
import java.util.Scanner;

public class GameController {
    public static final int MAXIMUM_AMOUNT_OF_CARS = 36;
    Grid grid;
    Scanner scanner = new Scanner(System.in);
    String[][] carLocationCheckerArray = new String[6][6];
    ArrayList<BumperCar> bumperCarList = new ArrayList<>();

    public GameController(Grid grid) {
        this.grid = grid;
        initializeCarLocationCheckerArray();
        getBumperCars();
        startGame();
    }

    public void startGame() {
        while(true){
            waitFewMilliseconds();
            for (BumperCar bumperCar : bumperCarList) {
                updateCarAndBoard(bumperCar);
                avoidGoingPastBorder(bumperCar);
                avoidHittingOtherBumperCars(bumperCarList);
            }
        }
    }
    public void waitFewMilliseconds(){
        Thread thread = new Thread();
        try {
            thread.sleep(1000);
        }catch (Exception e){
            System.out.println(e);
        }
    }
    public void getBumperCars() {
        System.out.println("Hello and welcome to this low-budget bumper car simulation\n" +
                "The field is a 6x6 grid\n" +
                "Enter each bumper car individually\n" +
                "Enter (x,y) and direction it's facing(N,S,E,W)");
        int bumperCarCount = 1;
        while (true) {
            System.out.println("Bumper car #" + bumperCarCount);

            System.out.println("Enter the x coordinate:");
            int xCoordinate = getCoordinateFromUserInput();
            scanner.nextLine();

            System.out.println("Enter the y coordinate:");
            int yCoordinate = getCoordinateFromUserInput();
            scanner.nextLine();

            if(pointIsAvailable(xCoordinate,yCoordinate)) {
                carLocationCheckerArray[yCoordinate][xCoordinate] = "taken";

                System.out.println("Enter the direction it's facing:(N,E,S,W)");
                String direction = getDirectionInputFromUser();
                Location location = new Location(xCoordinate, yCoordinate);
                BumperCar bumperCar = new BumperCar(location, direction);
                bumperCarList.add(bumperCar);
                bumperCarCount++;
            }else{
                System.out.println("Point is already taken. Try again");
            }
            System.out.println("Would you like to add another bumper car?(yes/no)");
            if (getYesNoInputFromUser().equals("no")) {
                break;
            }else if(bumperCarCount == MAXIMUM_AMOUNT_OF_CARS){
                break;
            }
        }
    }
    public void initializeCarLocationCheckerArray(){
        for(int i = 0; i < carLocationCheckerArray.length; i++){
            for(int j = 0; j < carLocationCheckerArray[0].length;j++){
                carLocationCheckerArray[i][j] = "free";
            }
        }
    }
    public int getCoordinateFromUserInput() {
        int userInput = 0;
        while (true) {
            try {
                int input = scanner.nextInt();
                if (input >= 0 && input < 6) {
                    userInput = input;
                    break;
                } else {
                    System.out.println("Number must be 0-6");
                }
            } catch (Exception e) {
                System.out.println("Not a correct input, try again");
                scanner.nextLine();
            }
        }
        return userInput;
    }

    public boolean pointIsAvailable(int xCoordinate, int yCoordinate){
        return !carLocationCheckerArray[yCoordinate][xCoordinate].equals("taken");
    }
    public String getDirectionInputFromUser() {
        while (true) {
            try {
                String stringInput = scanner.nextLine();
                if (stringInput.toUpperCase().equals("N") || stringInput.toUpperCase().equals("E") || stringInput.toUpperCase().equals("S") || stringInput.toUpperCase().equals("W")) {
                    return stringInput;
                } else {
                    System.out.println("Incorrect input. Try again");
                }
            } catch (Exception e) {
                System.out.println("Incorrect input. Try again");
            }
        }
    }

    public String getYesNoInputFromUser() {
        while (true) {
            try {
                String stringInput = scanner.nextLine();
                if (stringInput.toLowerCase().equals("yes") || stringInput.toLowerCase().equals("no")) {
                    return stringInput;
                } else {
                    System.out.println("Incorrect input. Try again");
                }
            } catch (Exception e) {
                System.out.println("Incorrect input. Try again");
            }
        }
    }

    public void updateCarAndBoard(BumperCar bumperCar){
        Location oldLocation = bumperCar.getLocation();
        bumperCar.setLocation(correctCarMovingLocation(bumperCar));
        grid.removeBumperCar(oldLocation);
        grid.printBumperCar(bumperCar);
        grid.printGrid();
    }

    public Location correctCarMovingLocation(BumperCar bumperCar) {
        if(!isAtBorder(bumperCar)) {
            String direction = bumperCar.getDirection().toUpperCase();
            switch (direction) {
                case "N":
                    return new Location(bumperCar.getLocation().getX(), bumperCar.getLocation().getY() + -1);
                case "E":
                    return new Location(bumperCar.getLocation().getX() + 1, bumperCar.getLocation().getY());
                case "S":
                    return new Location(bumperCar.getLocation().getX(), bumperCar.getLocation().getY() + 1);

                case "W":
                    return new Location(bumperCar.getLocation().getX() - 1, bumperCar.getLocation().getY());
                default:
                    return new Location(0, 0);
            }
        }
        return bumperCar.getLocation();
    }
    public void avoidHittingOtherBumperCars(ArrayList<BumperCar> bumperCarList){
        for(int i = 0; i < bumperCarList.size(); i++){
            for(int j = 0; j < bumperCarList.size(); j++){
                if(carsAreGoingToHit(bumperCarList.get(i),bumperCarList.get(j))){
                    bumperCarList.get(i).setDirection(bumperCarList.get(i).getOppositeDirection(bumperCarList.get(i).getDirection()));
                    bumperCarList.get(j).setDirection(bumperCarList.get(j).getOppositeDirection(bumperCarList.get(j).getDirection()));
                    waitFewMilliseconds();
                    grid.printBumperCar(bumperCarList.get(i));
                    grid.printBumperCar(bumperCarList.get(j));
                    grid.printGrid();
                }
            }
        }
    }
    public boolean carsAreGoingToHit(BumperCar firstCar, BumperCar secondCar){
        int firstCarX = firstCar.getLocation().getX();
        int firstCarY = firstCar.getLocation().getY();
        String firstCarDirection = firstCar.getDirection();
        int secondCarX = secondCar.getLocation().getX();
        int secondCarY = secondCar.getLocation().getY();
        String secondCarDirection = secondCar.getDirection();
        if(Math.abs(firstCarX-secondCarX) == 1 && firstCar.getOppositeDirection(firstCarDirection).equals(secondCarDirection) ||
                Math.abs(firstCarY-secondCarY) == 1 && firstCar.getOppositeDirection(firstCarDirection).equals(secondCarDirection)){
            return true;
        }
        return false;
    }
    public void avoidGoingPastBorder(BumperCar bumperCar) {
        if (isAtBorder(bumperCar)) {
            bumperCar.setDirection(bumperCar.getOppositeDirection(bumperCar.getDirection()));
            waitFewMilliseconds();
            grid.printBumperCar(bumperCar);
            grid.printGrid();
        }
    }

    public boolean isAtBorder(BumperCar bumperCar) {
        if (bumperCar.getLocation().getY() == 5 && bumperCar.getDirection().equals("S")||
                bumperCar.getLocation().getY() == 0 && bumperCar.getDirection().equals("N") ||
                bumperCar.getLocation().getX() == 5 && bumperCar.getDirection().equals("E")||
                bumperCar.getLocation().getX() == 0 && bumperCar.getDirection().equals("W")) {
            return true;
        }
        return false;
    }
}
