package com.myf.dnd.dataClass;


public enum landformType {

    RIVER("河流",1,true, false, false),
    FOREST("森林", 2, true, true, false),
    MOUNTAIN("山脉", 3, true, false, true),
    ACCESSIBLE("平地", 1, true, false, false),
    HILL("丘陵", 2, true, true, true),
    INACCESSIBLE("不可进入区域", 0, false, false, false);

    private final String landformName;
    //消耗行动力
    private final int cost;
    //是否可进区域
    private final boolean ifMovable;
    //是否影响进入AC
    private final boolean ifDefensive;
    //是否影响周围AC
    private final boolean ifInfluential;

    private landformType(String landformName, int cost, boolean ifMovable, boolean ifDefensive, boolean ifInfluential){
        this.landformName = landformName;
        this.cost = cost;
        this.ifMovable = ifMovable;
        this.ifDefensive = ifDefensive;
        this.ifInfluential = ifInfluential;
    }

    public String getLandformName() {
        return landformName;
    }

    public int getCost() {
        return cost;
    }

    public boolean isIfMovable() {
        return ifMovable;
    }

    public boolean isIfDefensive() {
        return ifDefensive;
    }

    public boolean isIfInfluential() {
        return ifInfluential;
    }

    @Override
    public String toString() {
        return "landformType{" +
                "landformName='" + landformName + '\'' +
                ", cost=" + cost +
                ", ifMovable=" + ifMovable +
                ", ifDefensive=" + ifDefensive +
                ", ifInfluential=" + ifInfluential +
                '}';
    }
}
