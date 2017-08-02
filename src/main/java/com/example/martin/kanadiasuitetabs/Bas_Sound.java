package com.example.martin.kanadiasuitetabs;

import android.media.AudioManager;
import android.media.ToneGenerator;

/**
 * Created by Martin on 10.04.2017.
 */

public class Bas_Sound {

    BA_get_Var BgV = new BA_get_Var();
    Bas_gettextview Bgtv = new Bas_gettextview();
    Tab_2_Config_S Tab2 = new Tab_2_Config_S();
    static ToneGenerator warning;


    public Bas_Sound(){
        warning = new ToneGenerator(AudioManager.STREAM_NOTIFICATION,(int)(ToneGenerator.MAX_VOLUME * 0.8));
    }

    public void tone_warning(){



        if(BgV.get_altitude() < string_to_int_tsl(Tab2.getDataaltmin(),1) || BgV.get_speed() > string_to_int_tsl(Tab2.getDataaltmax(),2))
        {
            warning.startTone(ToneGenerator.TONE_CDMA_ABBR_ALERT,20);

        }
        else
        {
            warning.stopTone();
        }

        if(BgV.get_speed() < string_to_int_tsl(Tab2.getDataspeedmin(),1) || BgV.get_speed() > string_to_int_tsl(Tab2.getDataspeedmax(),2))
        {
            warning.startTone(ToneGenerator.TONE_CDMA_ABBR_INTERCEPT,20);
        }
        else
        {
            warning.stopTone();
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
