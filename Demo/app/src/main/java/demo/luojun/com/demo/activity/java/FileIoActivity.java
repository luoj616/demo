package demo.luojun.com.demo.activity.java;

import android.os.Bundle;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import butterknife.ButterKnife;
import demo.luojun.com.demo.R;
import demo.luojun.com.demo.context.BaseActivity;
import demo.luojun.com.demo.utils.FileUtils;

public class FileIoActivity extends BaseActivity {
    public static final String FILE_DIR = "TEST";
    public static final String FILE_NAME = "1.text";
    public static final String FILE_NAME2 = "2.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_io);
        ButterKnife.bind(this);
    }

    public void fileInputStreamOnClick() {
        File file = new File(FILE_DIR, FILE_NAME);
        if (!file.exists())
            return;

        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(file);
            byte[] bytes = new byte[1024];
            int n = 0;
            while ((n = fileInputStream.read(bytes)) != -1) {
                String s = new String(bytes, 0, n);
                err("fileio==" + s);
            }
            int size = fileInputStream.available();
            byte[] bytes1 = new byte[size];
            fileInputStream.read(bytes1);
            err("err---" + (new String(bytes1)));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fileInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void fileReadWriteStreamOnClick(){
        FileInputStream fileInputStream=null;
        FileOutputStream fileOutputStream = null;
        try {
            fileInputStream=new FileInputStream(new File(FILE_DIR,FILE_NAME));
            File writeFile = new File(FILE_DIR,FILE_NAME2);
            if(!writeFile.exists()){
                FileUtils.createFile(FILE_DIR,FILE_NAME2);
            }
            fileOutputStream =new FileOutputStream(writeFile);
            byte[] bytes =new byte[1024];
            int n;
            while ((n=fileInputStream.read(bytes))!=-1){
                fileOutputStream.write(bytes,0,n);
            }
            fileOutputStream.write("最后添加的".getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }finally {

            try {
                fileOutputStream.close();
                fileInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public void fileOutPutStreamOnClick() {
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(FileUtils.createFile(FILE_DIR, FILE_NAME));
            String str1 = "hhawoshi";
            String str2 = "women";
            fileOutputStream.write(str1.getBytes());
            fileOutputStream.write(str2.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
