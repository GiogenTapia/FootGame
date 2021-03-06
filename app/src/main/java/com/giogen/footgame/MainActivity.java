package com.giogen.footgame;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Point;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    private SensorManager sensorManager;
    private Sensor mSensor;
    int vx, vy, aceleracion= 10,px,py;
    float  inicialY, inicialX;
    TextView lbBaloon;
    Display display ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        display = getWindowManager().getDefaultDisplay();
        sensorManager = (SensorManager) getSystemService(this.SENSOR_SERVICE);
        mSensor = sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY);
        lbBaloon = findViewById(R.id.lbBalon);
        Point size = new Point();
        display.getSize(size);
        px = size.x;
        py = size.y;
        inicialX = lbBaloon.getX();
        inicialY = lbBaloon.getY();



    }

    @Override
    public void onSensorChanged(SensorEvent event) {



        if (event.values[0]<0.1 & lbBaloon.getX()<px -lbBaloon.getWidth()){
           vx+=5;
            lbBaloon.setTranslationX(vx*aceleracion);
        }else if(event.values[0]>0.1 & lbBaloon.getX()>0){

            vx-=5;
            lbBaloon.setTranslationX(vx*aceleracion);
        }

        if (event.values[1]>0.1 & lbBaloon.getY()<py-400){
            vy+=5;
            lbBaloon.setTranslationY(vy*aceleracion);
        }else if(event.values[1]<0.1 & lbBaloon.getY()> 50){

            vy-=5;
            lbBaloon.setTranslationY(vy*aceleracion);
        }



        Log.d("CALIS","Y:"+lbBaloon.getY());
        Log.d("CALIS","X:"+lbBaloon.getX());


    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, mSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }
}