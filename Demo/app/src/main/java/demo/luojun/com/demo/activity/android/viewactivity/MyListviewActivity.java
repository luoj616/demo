package demo.luojun.com.demo.activity.android.viewactivity;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import demo.luojun.com.demo.R;
import demo.luojun.com.demo.activity.adapter.MyListViewAdapter;
import demo.luojun.com.demo.context.BaseActivity;
import demo.luojun.com.demo.widget.custom.MyListView;

public class MyListviewActivity extends BaseActivity {
    @BindView(R.id.my_list_view)
    MyListView myListView;
    private List<String> list = new ArrayList<>();
    private MyListViewAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_listview);
        ButterKnife.bind(this);
        list.add("1111");
        list.add("1111");
        list.add("1111");  list.add("1111");  list.add("1111");
        list.add("1111");  list.add("1111");
        list.add("1111");
        list.add("1111");
        list.add("1111");
        list.add("1111");  list.add("1111");  list.add("1111");
        list.add("1111");  list.add("1111");
        list.add("1111");
        list.add("1111");  list.add("1111");  list.add("1111");  list.add("1111");  list.add("1111");




        myListView.setOnDeleteListener(new MyListView.OnDeleteListener() {
            @Override
            public void onDelete(int index) {
                list.remove(index);
                adapter.notifyDataSetChanged();
            }
        });
        adapter = new MyListViewAdapter(list,this);
        myListView.setAdapter(adapter);
    }
}
