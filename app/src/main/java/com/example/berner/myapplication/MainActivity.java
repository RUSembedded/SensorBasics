package com.example.berner.myapplication;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    TextView txtAccel;
    private SensorManager mySensorManager;
    private Sensor myAccelerometer, myGyroscope;

    private double[] f_b_ib = new double[3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // connect to xml layout
        txtAccel = (TextView)findViewById(R.id.txtAccel);

        // create SensorManager and get default sensors
        mySensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        myAccelerometer = mySensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        myGyroscope = mySensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);

        // put sth in the textView
        txtAccel.setText("Hallo");
    }

    @Override
    protected void onResume(){
        super.onResume();
        //register Sensor Listener on startup
        mySensorManager.registerListener(this, myAccelerometer,SensorManager.SENSOR_DELAY_NORMAL);
        mySensorManager.registerListener(this, myGyroscope, SensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    protected void onPause(){
        super.onPause();
        // unregister listener saves energy
        mySensorManager.unregisterListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        // this method is called on every change of ACCELEROMETER AND GYROSCOPE data
        // first
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER){
            //do sth with accelerometer data
            f_b_ib[0] = event.values[0];
            f_b_ib[1] = event.values[1];
            f_b_ib[2] = event.values[2];

            txtAccel.setText("[" + String.format("%.02f",f_b_ib[0]) +" " + String.format("%.02f",f_b_ib[1]) +" " + String.format("%.02f",f_b_ib[2]) +" " + "]");
        }

        if (event.sensor.getType() == Sensor.TYPE_GYROSCOPE){
            //do sth with gyro data

        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
