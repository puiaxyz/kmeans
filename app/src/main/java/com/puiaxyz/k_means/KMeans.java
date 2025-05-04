package com.puiaxyz.k_means;

import java.util.*;

public class KMeans {
    private int k;
    private List<Point> points;
    private List<Point> centroids = new ArrayList<>();

    public KMeans(List<Point> points, int k) {
        this.points = points;
        this.k = k;
    }

    public void run() {
        //centroid randomizer
        centroids.clear();
        Collections.shuffle(points);
        for (int i = 0; i < k; i++) {
            centroids.add(new Point(points.get(i).x, points.get(i).y));
        }

        boolean changed;
        do {
            changed = false;
            // assign cluster
            for (Point p : points) {
                int nearest = 0;
                float minDist = p.distanceTo(centroids.get(0));
                for (int i = 1; i < k; i++) {
                    float dist = p.distanceTo(centroids.get(i));
                    if (dist < minDist) {
                        minDist = dist;
                        nearest = i;
                    }
                }
                if (p.cluster != nearest) {
                    p.cluster = nearest;
                    changed = true;
                }
            }

            for (int i = 0; i < k; i++) {
                float sumX = 0, sumY = 0;
                int count = 0;
                for (Point p : points) {
                    if (p.cluster == i) {
                        sumX += p.x;
                        sumY += p.y;
                        count++;
                    }
                }
                if (count > 0) {
                    centroids.set(i, new Point(sumX / count, sumY / count));
                }
            }
        } while (changed);
    }
}
