package com.puiaxyz.k_means;

import android.content.Context;
import android.graphics.*;
import android.util.AttributeSet;
import android.view.View;

import java.util.*;

public class ClusterView extends View {
    private List<Point> points = new ArrayList<>();
    private List<Point> centroids = new ArrayList<>();
    private int[] colors = { Color.RED, Color.BLUE, Color.GREEN, Color.MAGENTA, Color.CYAN, Color.YELLOW };

    public ClusterView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setPoints(List<Point> points) {
        this.points = points;
        invalidate();
    }

    public void setCentroids(List<Point> centroids) {
        this.centroids = centroids;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();


        for (Point p : points) {
            paint.setColor(colors[p.cluster % colors.length]);
            canvas.drawCircle(p.x, p.y, 20, paint);
        }

        // draw centroids as "X"
        for (int i = 0; i < centroids.size(); i++) {
            Point c = centroids.get(i);
            paint.setColor(colors[i % colors.length]);
            paint.setStrokeWidth(5);

            float size = 20; // half-length of the "X" arms
            canvas.drawLine(c.x - size, c.y - size, c.x + size, c.y + size, paint);
            canvas.drawLine(c.x - size, c.y + size, c.x + size, c.y - size, paint);
        }

    }
}
