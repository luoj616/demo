package demo.luojun.com.demo.context;

/**
 * Created by luo.j on 2018/1/26.
 */
public class AppServerAPI {
    public static final String SERVER_DEV = "http://10.0.30.58:8080";//开发
    public static final String SERVER_TEST = "http://10.0.30.94:8080";//测试
    public static final String SERVER_PX = "http://prev-initial.belle.net.cn";//培训
    public static final String SERVER_PRO = "http://initial.belle.net.cn";//生成
    public static final String SERVER = SERVER_PX;

    //地区区号
    public static final String OKHTTP_GET = "http://initial.belle.net.cn/initial-web/countryRegionCodeMapping";
    public static final String OKHTTP_GET_JIANSHU = "http://www.jianshu.com/u/9df45b87cfdf";
   //YOUGOU
    public static final String YG_VERSION = "http://mobile.yougou.com/v_1.8/version";
    public static final String YG_categories4all = "http://mobile.yougou.com/v_1.8/categories4all";
    public static final String YG_commodityDetail = "http://mobile.yougou.com/v_1.8/commodityDetail?productid=99890025";
    public static final String YG_CHANELS = "http://mobile.yougou.com/v_1.8/channels";


}
