package com.puiaxyz.k_means;


import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import java.util.*;

public class MainActivity extends AppCompatActivity {

    private List<Point> points = new ArrayList<>();
    private ClusterView clusterView;
    private EditText clusterInput;
    private Button runButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        clusterView = findViewById(R.id.clusterView);
        clusterInput = findViewById(R.id.clusterInput);
        runButton = findViewById(R.id.runKMeansButton);


        clusterView.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                points.add(new Point(event.getX(), event.getY()));
                clusterView.setPoints(points);  // update immediately
                return true;
            }
            return false;
        });

        runButton.setOnClickListener(v -> {
            String kValue = clusterInput.getText().toString();
            if (kValue.isEmpty()) {
                Toast.makeText(this, "Enter number of clusters", Toast.LENGTH_SHORT).show();
                return;
            }

            int k = Integer.parseInt(kValue);
            if (k <= 0 || k > points.size() ) {

                Toast.makeText(this, "Invalid number of clusters", Toast.LENGTH_SHORT).show();
                return;
            }

            KMeans kMeans = new KMeans(points, k);
            kMeans.run();
            clusterView.setPoints(points);
        });
    }
}
