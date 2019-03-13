package demo.luojun.com.demo.activity.viewactivity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import demo.luojun.com.demo.R;
import demo.luojun.com.demo.utils.LogPrint;
import demo.luojun.com.demo.widget.custom.CounterView;

public class WidgetActivity extends AppCompatActivity {
   private CounterView counterView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_widget);
        counterView = (CounterView) findViewById(R.id.counter_view);
        LogPrint.debugError(counterView.getMeasuredHeight()+"-------*****---------------"+counterView.getMeasuredWidth());

        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View view = layoutInflater.inflate(R.layout.activity_main,null,false);

        LayoutInflater flater1 = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    protected void onStart() {
        super.onStart();

        LogPrint.debugError(counterView.getMeasuredHeight()+"-------*****---ONSTATT------------"+counterView.getMeasuredWidth());

    }

    @Override
    protected void onResume() {
        super.onResume();
        LogPrint.debugError(counterView.getMeasuredHeight()+"-------*****---ONRESUME------------"+counterView.getMeasuredWidth());
    }
}
