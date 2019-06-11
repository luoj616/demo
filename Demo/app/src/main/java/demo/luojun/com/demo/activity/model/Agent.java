package demo.luojun.com.demo.activity.model;

import demo.luojun.com.demo.interfaceimg.GuTianLe;
import demo.luojun.com.demo.interfaces.Star;
import demo.luojun.com.demo.utils.LogPrint;

/**  代理模式：静态代理
 *   代理对象：经纪人
 * Created by Administrator on 2019/6/10/010.
 */

public class Agent implements Star {
    private GuTianLe guTianLe;
    public  Agent(GuTianLe guTianLe){
        this.guTianLe=guTianLe;

    }
    @Override
    public void paly(String name) {
        goPaly(name);
        guTianLe.paly(name);
    }

    /**
     * 经纪人接戏
     */
    public void goPaly(String name){
        LogPrint.debugError("----经纪人接戏---"+name);
    }
}
