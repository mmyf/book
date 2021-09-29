package com.myf.dnd.dataClass;


import com.myf.dnd.dataClass.landformType;

public class landform {

    private final landformType landformTemple;
    //影响最终命中
    private final int height;

    public landform(landformType landformTemple, int height){
        this.landformTemple = landformTemple;
        this.height = height;
    }

    public landformType getLandformTemple() {
        return landformTemple;
    }

    public int getHeight() {
        return height;
    }

    @Override
    public String toString() {
        return "landform{" +
                "landformTemple=" + landformTemple +
                ", height=" + height +
                '}';
    }
}
