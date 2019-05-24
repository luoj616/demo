package demo.luojun.com.demo.utils;

import android.os.Environment;
import android.text.TextUtils;

import java.io.File;

/**
 * https://www.jianshu.com/p/8dc08476261a
 *
 * https://blog.csdn.net/qq_15059163/article/details/80723556
 *
 * https://www.cnblogs.com/zhaoyanjun/p/6292384.html
 * Created by luo.j on 2019/5/24.
 */

public class FileUtils {
    /**
     * 创建文件
     * @param fileDir
     * @param fileName
     * @return
     */
    public static File createFile(String fileDir,String fileName) throws Exception{
        LogPrint.debugError("filePath=="+fileDir+",fileName=="+fileName);
        if(TextUtils.isEmpty(fileDir)||TextUtils.isEmpty(fileName))
            return null;

        String filePath=Environment.getExternalStorageDirectory()+fileDir;
        LogPrint.debugError(filePath);

            //判断目录是否存在，如果不存在，递归创建目录
            File dir = new File(Environment.getExternalStorageDirectory() + filePath);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            //组织文件路径
            StringBuilder sbFile= new StringBuilder(filePath);
            if(!filePath.endsWith("/")){
                sbFile.append("/");
            }
            sbFile.append(fileName);
             LogPrint.debugError(sbFile.toString());
            //创建文件并返回文件对象
            File file = new File(sbFile.toString());
            if(!file.exists()){
                file.createNewFile();
            }
            return file;


    }
}
