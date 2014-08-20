package com.pga.project1.DataModel;

import com.pga.project1.Intefaces.PathMapObject;

/**
 * Created by aliparsa on 8/19/2014.
 */
public class PathObject implements PathMapObject {

    private String name;

    public PathObject(String name) {

        this.name = name;
    }

    @Override
    public int getId() {
        return 0;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getType() {
        return 0;
    }

    @Override
    public Object getSelf() {
        return this;
    }
}
