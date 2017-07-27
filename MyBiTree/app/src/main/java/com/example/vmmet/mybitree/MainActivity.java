package com.example.vmmet.mybitree;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.vmmet.mybitree.bean.Node;
import com.example.vmmet.mybitree.util.ListViewHelper;
import com.example.vmmet.mybitree.util.TreeHelper;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView lv_left, lv_right;
    private TreeAdapter adapter_left, adapter_right;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }
    private void initView() {
        lv_left = (ListView) findViewById(R.id.lv_left);
        lv_right = (ListView) findViewById(R.id.lv_right);
        List<Node> list = TreeHelper.getAllNodes();
        adapter_left = new TreeAdapter(lv_left, this, list);
        adapter_right = new TreeAdapter(lv_right, this, list);
        /**设置左右的adapter同时刷新*/
        adapter_left.setFollowAdapter(adapter_right);
        adapter_right.setFollowAdapter(adapter_left);
        /**设置左边的listview的宽度为手机屏幕宽度的一半*/
        int mWidth = getResources().getDisplayMetrics().widthPixels;
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(mWidth / 2, LinearLayout.LayoutParams.MATCH_PARENT);
        lv_left.setLayoutParams(params);
        lv_left.setAdapter(adapter_left);
        lv_right.setAdapter(adapter_right);
        /**设置左右的listview同时滑动*/
        ListViewHelper.setListViewScrollListener(lv_left, lv_right);
    }
}
