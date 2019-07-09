package com.lj.test.singleton;

/**
 * Created by Administrator on 2019/7/9/009.
 * 单利模式
 * 厂库
 */

public class StoreHouse {
    //商品数量
    private int quantity = 100;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    //单例模式
    private static StoreHouse storeHouse = new StoreHouse();
    private StoreHouse(){}
    public static StoreHouse getInstance(){
        return storeHouse;
    }
}
