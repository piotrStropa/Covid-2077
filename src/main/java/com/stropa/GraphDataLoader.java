package com.stropa;

public class GraphDataLoader {
    private static GraphDataLoader instance;

    public static GraphDataLoader getInstance() {
        if(instance == null) instance = new GraphDataLoader();
        return instance;
    }


}
