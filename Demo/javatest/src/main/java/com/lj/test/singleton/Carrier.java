package com.lj.test.singleton;

/**
 * Created by Administrator on 2019/7/9/009.
 * 单例模式 ，搬货工人
 */

public class Carrier {
    public StoreHouse storeHouse;
    public Carrier(StoreHouse storeHouse){
        this.storeHouse = storeHouse;
    }
    //搬货进仓库
    public void moveIn(int quantity){
        this.storeHouse.setQuantity(this.storeHouse.getQuantity()+quantity);
    }
    //搬货出仓库
    public void MoveOut(int quantity){
        this.storeHouse.setQuantity(this.storeHouse.getQuantity()-quantity);

    }
}
