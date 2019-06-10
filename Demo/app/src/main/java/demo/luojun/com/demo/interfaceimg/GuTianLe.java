package demo.luojun.com.demo.interfaceimg;

import demo.luojun.com.demo.interfaces.Star;
import demo.luojun.com.demo.utils.LogPrint;

/**
 * 代理设计模式
 * 实现明星接口
 * Created by Administrator on 2019/6/10/010.
 */

public class GuTianLe implements Star {
    @Override
    public void paly(String name) {
        LogPrint.debugError("古天乐拍的戏=="+name);
    }
}
