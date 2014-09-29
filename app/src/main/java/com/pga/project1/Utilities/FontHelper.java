package com.pga.project1.Utilities;

import android.content.Context;
import android.graphics.Typeface;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by ashkan on 9/1/2014.
 */
public class FontHelper {

    public static void SetFont(Context context, Fonts font, TextView view, int typeFace){
        Typeface tf;

        return;

/*        switch (font){
            case MAIN_FONT:
                tf = Typeface.createFromAsset(context.getAssets(),"fonts/BKOODB.TTF");
                view.setTypeface(tf, typeFace);
                break;
            case BKAMRAN:
                tf = Typeface.createFromAsset(context.getAssets(),"fonts/BKAMRAN.TTF");
                view.setTypeface(tf, typeFace);
                break;
            case BKOODAK:
                tf = Typeface.createFromAsset(context.getAssets(),"fonts/BKOODB.TTF");
                view.setTypeface(tf, typeFace);
                break;
        }*/
    }

    public static void SetFont(Context context, Fonts font, Button view, int typeFace) {
        Typeface tf;

        return;
/*        switch (font) {
            case MAIN_FONT:
                tf = Typeface.createFromAsset(context.getAssets(), "fonts/BKOODB.TTF");
                view.setTypeface(tf, typeFace);
                break;
            case BKAMRAN:
                tf = Typeface.createFromAsset(context.getAssets(), "fonts/BKAMRAN.TTF");
                view.setTypeface(tf, typeFace);
                break;
            case BKOODAK:
                tf = Typeface.createFromAsset(context.getAssets(), "fonts/BKOODB.TTF");
                view.setTypeface(tf, typeFace);
                break;
        }*/
    }


}
