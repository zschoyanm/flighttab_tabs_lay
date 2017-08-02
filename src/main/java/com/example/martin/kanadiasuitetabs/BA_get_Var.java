package com.example.martin.kanadiasuitetabs;


import android.util.Log;

/**
 * Created by Martin on 01.03.2017.
 */


public class BA_get_Var {
    static float body_pitch_rate, body_roll_rate, body_roll_angle, speed, altitude, heading, pos_Lat, pos_Long, gps_speed;


    public void update_data() throws NullPointerException{
        //long startTime = System.nanoTime();
        body_pitch_rate = BCK.get_var_data_f("Body_Pitch_Rate");
        //long stopTime = System.nanoTime();
        //long elapsedTime = stopTime - startTime;
        body_roll_rate = BCK.get_var_data_f("Body_Roll_Rate");
        body_roll_angle = BCK.get_var_data_f("Body_Roll_Angle");
        speed = BCK.get_var_data_f("Speed");
        altitude = BCK.get_var_data_f("Altitude");
        heading = BCK.get_var_data_f("Heading");
        pos_Lat = BCK.get_var_data_f("Pos_Lat");
        pos_Long = BCK.get_var_data_f("Pos_Long");
        gps_speed = BCK.get_var_data_f("GPS_Speed");





    }

    Bas_Can_Kanadia BCK = new Bas_Can_Kanadia();
    public float get_body_pitch_rate() throws NullPointerException{
        return body_pitch_rate;
    }
    public float get_body_roll_rate() throws NullPointerException{
        return body_roll_rate;
    }
    public float get_body_roll_angle() throws NullPointerException{
        return body_roll_angle;
    }
    public float get_speed() throws NullPointerException{
        return speed;
    }
    public float get_altitude() throws NullPointerException{
        return altitude;
    }
    public float get_Heading() throws NullPointerException{
        return heading;
    }
    public float get_pos_Lat() throws NullPointerException{
        return pos_Lat;
    }
    public float get_pos_Long() throws NullPointerException{
        return pos_Long;
    }
    public float get_gps_speed() throws NullPointerException{
        return gps_speed;
    }


}
