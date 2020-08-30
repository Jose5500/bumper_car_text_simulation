package com.example.company;

public class Location {
    int x;
    int y;

    public Location(){
      x = 0;
      y = 0;
    }
    public Location(int x, int y){
        this.x = x;
        this.y = y;
    }

    public void flipCoordinates(){
        int temp = this.x;
        this.x = this.y;
        this.y = temp;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return(getX() + "," + getY());
    }
}
