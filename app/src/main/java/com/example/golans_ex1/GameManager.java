package com.example.golans_ex1;

import java.util.Random;

public class GameManager {

    private int[][] main_IMG_cones;
    private int[] main_IMG_racingCars;
    private int numOfCols;
    private int numOfRows;
    private int numOfCrashes = 0;
    private int lifeCount;
    Random random = new Random();




    public GameManager() {
        this(8, 3, 3);
    }

    public GameManager(int rows, int cols, int lifeCount) {
        this.lifeCount = lifeCount;
        numOfRows = rows;
        numOfCols = cols;
        main_IMG_cones = new int[rows][cols];
        main_IMG_racingCars = new int[cols];
    }

    public void setNumOfCrashes(int numOfCrashes) {
        this.numOfCrashes = numOfCrashes;
    }

    public int getNumOfCrashes() {

        return numOfCrashes;
    }

    public int getLifeCount() {

        return lifeCount;
    }


    public boolean isGameLost() {

        return getLifeCount() == getNumOfCrashes();
    }



}


