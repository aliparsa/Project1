package com.pga.project1.Viewes;

import android.app.FragmentManager;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;

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

    public static PathMapObject pop() {
        if (!stack.empty())
            return stack.pop();

        return null;
    }

    public static PathMapObject popByObject(PathMapObject object) {


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

    public PathMapManager(Context context) {
        super(context);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.view_pathmap, this, true);

        mainLayout = (LinearLayout) this.findViewById(R.id.ll_viewPathMap_main);

        refresh();

    }

    public PathMapManager(Context context, AttributeSet attrs) {
        super(context);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ViewNameValue);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.view_pathmap, this, true);

        a.recycle();

        mainLayout = (LinearLayout) this.findViewById(R.id.ll_viewPathMap_main);

        refresh();

    }



    public void refresh(){

        mainLayout.removeAllViews();



        for (PathMapObject object : PathMapManager.stack){

            String name = object.getName();

            Button btn = new Button(this.getContext());
            btn.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
            btn.setText(name);
            btn.setTag(object.getSelf());

            mainLayout.addView(btn);

            ImageView img = new ImageView(this.getContext());
            img.setScaleType(ImageView.ScaleType.MATRIX);
            img.setImageResource(R.drawable.ic_drawer);//TODO add path map divider

            mainLayout.addView(img);
        }

        invalidate();
        requestLayout();
    }

    public static class BackStackChanged implements FragmentManager.OnBackStackChangedListener {

        @Override
        public void onBackStackChanged() {

        }
    }

}
