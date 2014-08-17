package com.pga.project1.Viewes;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.pga.project1.Intefaces.PathMapObject;
import com.pga.project1.R;

import java.util.Stack;

/**
 * Created by ashkan on 8/13/2014.
 */
public class PathMapManager extends LinearLayout {

    private static Stack<PathMapObject> stack = new Stack<PathMapObject>();

    public static void push(PathMapObject object) {

        stack.push(object);
    }

    public static PathMapObject pop(Activity activity) {
        if (!stack.empty()) {

            return stack.pop();
        }

        return null;
    }

    public static PathMapObject popByObject(PathMapObject object, Activity activity) {


        if (stack.contains(object)) {

            PathMapObject popped = stack.pop();

            while (object != popped) {

                popped = stack.pop();
            }

            return popped;

        } else {
            return null;
        }
    }



    LinearLayout mainLayout;
    private Context context;
    LayoutInflater inflater;

    public PathMapManager(Context context) {
        super(context);
        this.context = context;

        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.view_pathmap, this, true);

        mainLayout = (LinearLayout) this.findViewById(R.id.ll_viewPathMap_main);

        refresh();

    }

    public PathMapManager(Context context, AttributeSet attrs) {
        super(context);
        this.context = context;
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ViewNameValue);
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.view_pathmap, this, true);

        a.recycle();

        mainLayout = (LinearLayout) this.findViewById(R.id.ll_viewPathMap_main);

        refresh();

    }

    @Override
    public void onScreenStateChanged(int screenState) {
        super.onScreenStateChanged(screenState);

        refresh();
    }

    public void refresh(){

        mainLayout.removeAllViews();

        boolean isFirst = true;
       //if()


        Button btn = null;

        for (int i = stack.size() - 1; i >= 0 ; i--) {

            PathMapObject object = stack.get(i);

            String name = object.getName();




           /* View view = new View(context);
            view.setBackgroundResource(R.drawable.previous);
            view.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT));
*/

     /*       btn = (Button) inflater.inflate(R.layout.path_map_button, null);
            btn.setText("");
            btn.setBackgroundResource(R.drawable.previous);*/

            ImageView img = new ImageView(this.getContext());
            img.setScaleType(ImageView.ScaleType.FIT_XY);
            img.setLayoutParams(new LayoutParams(30, LayoutParams.MATCH_PARENT));
            img.setImageResource(R.drawable.previous);//TODO add path map divider

            if(!isFirst) {
                mainLayout.addView(img);
            }




           // LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
           // LinearLayout view = (LinearLayout)LayoutInflater.from(this).inflate(R.layout.my_button, null);
            btn = (Button) inflater.inflate(R.layout.path_map_button, null);
            /*
            btn =  new Button(this.getContext());
            btn.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, 25));
            btn.setPadding(5,5,5,5);
            btn.setTextSize(10);*/
            if (!isFirst) {
                //    btn.setBackgroundResource(R.drawable.selector_btn_pathmap);
            } else {
                //    btn.setBackgroundResource(R.drawable.selector_btn_pathmap_current);
            }
            btn.setText(name);
            btn.setTag(object.getSelf());

            mainLayout.addView(btn);


            isFirst = false;

        }


        invalidate();
        requestLayout();
    }



}
