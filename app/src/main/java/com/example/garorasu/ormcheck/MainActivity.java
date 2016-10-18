package com.example.garorasu.ormcheck;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private DatabaseHandler db;
    private final int fare = 40;
    //private long now;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new DatabaseHandler(this);

    }
    public void enterCar(String vid){
        String now = new SimpleDateFormat("ddHHmm").format(Calendar.getInstance().getTime());
        System.out.println("Now value "+now);
        Long wow = Long.parseLong(now)*10000;
        //System.out.println("Now value "+wow);
        //wow = wow + fare;
        //System.out.println("Now value "+wow);
        //wow = wow*10000;
        System.out.println("Now value "+wow);
        long id = wow+Long.parseLong(vid);
        System.out.println("Unique code : "+id);
        Car c = new Car();
        c.CarIn(id,vid,now,false,fare);
        db.addCar(c);
    }
    public void logListAll(View v){

        // Inserting Cars
        Log.d("Insert: ", "Inserting ..");

        // Reading all cars
        Log.d("Reading: ", "Reading all cars..");
        List<Car> cars = db.getAllCars();

        for (Car cn : cars) {
            String log = "Id: "+cn.get_id()+" ,Vid : " + cn.get_vid() + " InTime: " + cn.get_inTime()+" Occupancy : "+cn.get_ocp();
            // Writing Cars to log
            Log.d("Name: ", log);
        }
    }
    public void submitCarNumber(View v){
        EditText vehicleRegistrationNo = (EditText)findViewById(R.id.vehicleRegistrationNo);
        String s = vehicleRegistrationNo.getText().toString();
        enterCar(s);
    }
}
