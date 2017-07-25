package com.example.martin.kanadiasuitetabs;

import android.app.Activity;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Martin on 17.05.2017.
 */

public class Bas_gettextview extends Activity {
    static Activity activity;
    public Bas_gettextview (){

    }
    public Bas_gettextview (Activity myact){
        this.activity = myact;
    }

    public TextView get_textview_alt(){

        return  (TextView) this.activity.findViewById(R.id.textaltitude);
    }

    public TextView get_textview_speed(){

        return  (TextView) this.activity.findViewById(R.id.textspeed);
    }

    public TextView get_textview_poslat(){

        return  (TextView) this.activity.findViewById(R.id.textposlat);
    }

    public TextView get_textview_poslong(){

        return  (TextView) this.activity.findViewById(R.id.textposlong);
    }

    public TextView get_textview_heading(){

        return  (TextView) this.activity.findViewById(R.id.textheading);
    }

    public TextView get_textview_gpsspeed(){

        return  (TextView) this.activity.findViewById(R.id.textgpsspeed);
    }

    public TextView get_textview_bodypitchr(){

        return  (TextView) this.activity.findViewById(R.id.textbodypitchrate);
    }

    public TextView get_textview_bodyrollr(){

        return  (TextView) this.activity.findViewById(R.id.textbodyrollrate);
    }

    public TextView get_textview_bodyrolla(){

        return  (TextView) this.activity.findViewById(R.id.textbodyrollangle);
    }

    public EditText get_edittext_s_altitude_min(){

        return (EditText) this.activity.findViewById(R.id.editminalt);
    }

    public EditText get_edittext_s_altitude_max(){

        return (EditText) this.activity.findViewById(R.id.editmaxalt);
    }

    public EditText get_edittext_s_speed_min(){

        return (EditText) this.activity.findViewById(R.id.editminspeed);
    }

    public EditText get_edittext_s_speed_max(){

        return (EditText) this.activity.findViewById(R.id.editmaxspeed);
    }

    public EditText get_edittext_t_altitude_min(){

        return (EditText) this.activity.findViewById(R.id.editminalt_t);
    }

    public EditText get_edittext_t_altitude_max(){

        return (EditText) this.activity.findViewById(R.id.editmaxalttest);
    }

    public EditText get_edittext_t_speed_min(){

        return (EditText) this.activity.findViewById(R.id.editminspeed_t);

    }

    public EditText get_edittext_t_speed_max(){

        return (EditText) this.activity.findViewById(R.id.editmaxspeed_t);
    }
}
