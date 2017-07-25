package com.example.martin.kanadiasuitetabs;

/**
 * Created by Martin on 16.06.2017.
 */

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class Tab_2_Config_S extends Fragment{
    static EditText speed_min,speed_max,alt_min,alt_max;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab2_config_s, container, false);

        speed_min = (EditText) rootView.findViewById(R.id.editminspeed);
        speed_max = (EditText) rootView.findViewById(R.id.editmaxspeed);
        alt_min = (EditText) rootView.findViewById(R.id.editminalt);
        alt_max = (EditText) rootView.findViewById(R.id.editmaxalt);


        return rootView;
    }

    public String getDataspeedmin (){
        return speed_min.getText().toString();
    }
    public String getDataspeedmax (){
        return speed_max.getText().toString();
    }
    public String getDataaltmin (){
        return alt_min.getText().toString();
    }
    public String getDataaltmax (){
        return alt_max.getText().toString();
    }
}
