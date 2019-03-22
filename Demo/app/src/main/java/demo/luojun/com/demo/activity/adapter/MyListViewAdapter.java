package demo.luojun.com.demo.activity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

import demo.luojun.com.demo.R;

/**
 * Created by luo.j on 2019/3/21.
 */

public class MyListViewAdapter extends BaseAdapter {
    private List<String> list;
    private Context context;
    public MyListViewAdapter(List<String> list, Context context){
        this.list = list;
        this.context = context;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        if(convertView ==null){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_mylistview,null);
        }else {
            view = convertView;
        }

        return view;
    }
}
