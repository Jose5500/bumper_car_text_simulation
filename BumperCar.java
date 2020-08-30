package com.example.company;

public class BumperCar {
    Location location;
    String direction = null;

    public BumperCar(Location location, String direction){
        this.location = location;
        this.direction = direction.toUpperCase();
    }

    public String getDirection() {
        return direction;
    }
    public String getPrintedDirection(){
        switch(direction) {
            case "N":
                return "^";
            case "E":
                return ">";
            case "S":
                return "v";
            case "W":
                return "<";
            default:
                return " ";
        }
    }
    public String getOppositeDirection(String oldLocation){
        switch (oldLocation.toUpperCase()){
            case "N":
                return "S";
            case "E":
                return "W";
            case "S":
                return "N";
            case "W":
                return "E";
            default:
                return " ";
        }
    }
    public void setDirection(String direction){
        this.direction = direction;
    }
    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public String toString() {
       return("Bumper Car is at location " + this.getLocation() + "facing " +this.getDirection());
    }
}
