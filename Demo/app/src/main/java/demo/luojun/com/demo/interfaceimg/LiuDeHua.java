package demo.luojun.com.demo.interfaceimg;

import demo.luojun.com.demo.utils.LogPrint;

/**
 * 非接口的动态代理 必须添加cglib-3.2.6.jar
 * Created by luo.j on 2019/6/11.
 */

public class LiuDeHua {
    public void song(String name){
        LogPrint.debugError("---华仔唱歌很好----"+name);
    }
    public void play(String name){
        LogPrint.debugError("--------华仔演戏也很好----------------"+name);
    }
}
