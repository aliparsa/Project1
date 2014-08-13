package com.pga.project1.Utilities;

import android.app.FragmentManager;
import android.content.Context;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Stack;

/**
 * Created by ashkan on 8/13/2014.
 */
public class PathMapManager extends View {

    public static Stack<Object> stack = new Stack<Object>();

    public PathMapManager(Context context) {
        super(context);
    }

    public static void push(Object object) {

        stack.push(object);
    }

    public static Object pop() {
        if (!stack.empty())
            return stack.pop();

        return null;
    }

    public static Object popById(Object object) {

        if (stack.contains(object)) {

            Object popped = stack.pop();

            while (object != popped) {

                popped = stack.pop();
            }

            return popped;

        } else {
            return null;
        }
    }



    public static class BackStackChanged implements FragmentManager.OnBackStackChangedListener {

        @Override
        public void onBackStackChanged() {

        }
    }

}
