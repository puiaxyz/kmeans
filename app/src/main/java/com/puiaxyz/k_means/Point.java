package com.puiaxyz.k_means;


public class Point {
    public float x, y;
    public int cluster;

    public Point(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float distanceTo(Point other) {
        return (float) Math.sqrt(Math.pow(this.x - other.x, 2) + Math.pow(this.y - other.y, 2));
}
}

