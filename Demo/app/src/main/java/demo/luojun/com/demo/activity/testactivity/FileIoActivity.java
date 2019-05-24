package demo.luojun.com.demo.activity.testactivity;

import android.os.Bundle;

import java.io.FileOutputStream;
import java.io.IOException;

import butterknife.ButterKnife;
import demo.luojun.com.demo.R;
import demo.luojun.com.demo.context.BaseActivity;
import demo.luojun.com.demo.utils.FileUtils;

public class FileIoActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_io);
        ButterKnife.bind(this);
    }

    public void fileInputStreamOnClick(){

    }

    public void fileOutPutStreamOnClick(){
        FileOutputStream fileOutputStream=null;
        try {
            fileOutputStream = new FileOutputStream(FileUtils.createFile("test","1.txt"));
            String str1="hhawoshi";
            String str2="women";
            fileOutputStream.write(str1.getBytes());
            fileOutputStream.write(str2.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
