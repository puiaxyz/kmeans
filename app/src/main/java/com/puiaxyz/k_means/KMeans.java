package com.puiaxyz.k_means;

import java.util.*;

public class KMeans {
    private int k;
    private List<Point> points;
    private List<Point> centroids = new ArrayList<>();
    private boolean changed = true;

    public interface IterationListener {
        void onIteration(List<Point> points, List<Point> centroids, boolean isFinished);
    }

    public KMeans(List<Point> points, int k) {
        this.points = points;
        this.k = k;
    }

    public void initializeCentroids() {
        centroids.clear();
        Collections.shuffle(points);
        for (int i = 0; i < k; i++) {
            centroids.add(new Point(points.get(i).x, points.get(i).y));
        }
    }

    public void stepIteration(IterationListener listener) {
        if (!changed) {
            listener.onIteration(points, centroids, true); // Done
            return;
        }

        changed = false;

        // assign clusters
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

        // update centroids
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

        listener.onIteration(points, centroids, !changed);
    }
}
