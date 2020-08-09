package com.sky.maskpage.entity;

public class MaskInfo {
    private int[] locations;
    private int width;
    private int height;
    private int centreX;
    private int centreY;
    public boolean exist = true;

    public MaskInfo(boolean exist) {
        this.exist = exist;
    }

    public MaskInfo(int[] locations, int width, int height) {
        this.locations = locations;
        this.width = width;
        this.height = height;
        if (this.width == 0 || this.height == 0 || locations == null || locations.length == 0) {
            exist = false;
        }
    }

    public int[] getLocations() {
        return locations == null || locations.length == 0 ? new int[2] : locations;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getCentreX() {
        if (centreX == 0) {
            centreX = getLocations()[0] + (width / 2);
        }
        return centreX;
    }

    public int getCentreY() {
        if (centreY == 0) {
            centreY = getLocations()[1] + (height / 2);
        }
        return centreY;
    }
}
