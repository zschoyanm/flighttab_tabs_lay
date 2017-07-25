package com.example.martin.kanadiasuitetabs;
import android.app.Activity;
import android.graphics.Color;

/**
 * Created by Martin on 08.05.2017.
 */

public class Bas_Color  {
    BA_get_Var BgV = new BA_get_Var();
    Bas_gettextview Bgtv = new Bas_gettextview();
    Tab_3_Config_T Tab3 = new Tab_3_Config_T();


    public void change_color(){




        if(BgV.get_altitude() < string_to_int_tsl(Tab3.getDataaltmin(),1) || BgV.get_altitude() > string_to_int_tsl(Tab3.getDataaltmax(),2)){
            Bgtv.get_textview_alt().setTextColor(Color.RED);
        }
        else{
            Bgtv.get_textview_alt().setTextColor(Color.GREEN);
        }

        if(BgV.get_speed() < string_to_int_tsl(Tab3.getDataspeedmin(),1) || BgV.get_speed() > string_to_int_tsl(Tab3.getDataspeedmax(),2)){
            Bgtv.get_textview_speed().setTextColor(Color.RED);
        }
        else{
            Bgtv.get_textview_speed().setTextColor(Color.GREEN);
        }

        if(BgV.get_speed() < string_to_int_tsl(Tab3.getDataspeedmin(),1) || BgV.get_speed() > string_to_int_tsl(Tab3.getDataspeedmax(),2)){
            Bgtv.get_textview_gpsspeed().setTextColor(Color.RED);
        }else{
            Bgtv.get_textview_gpsspeed().setTextColor(Color.GREEN);
        }

    }

    private int string_to_int_tsl(String edittext,int min_max){
        if(edittext.length()>0){
            return Integer.valueOf(edittext);
        }
        else
        {
            if(min_max==1) {
                return 0;
            }
            else if (min_max==2){
                return 65555;
            }
            else{
                return 0;
            }
        }
    }



}
