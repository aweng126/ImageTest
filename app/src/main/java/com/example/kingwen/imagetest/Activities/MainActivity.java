package com.example.kingwen.imagetest.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.kingwen.imagetest.Adapters.ItemAdapter;
import com.example.kingwen.imagetest.Beans.Myitem;
import com.example.kingwen.imagetest.R;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    //用于显示图片的listview
    private ListView mylistview;

    private ItemAdapter mAdapter;

    //数据的链表
    private List<Myitem> itemList=new ArrayList<Myitem>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //初始化fresco
        Fresco.initialize(getApplicationContext());

        setContentView(R.layout.activity_main);
        //初始化image'loader'的参数
        ImageLoaderConfiguration config=new ImageLoaderConfiguration.Builder(this)
                .build();
        ImageLoader.getInstance().init(config);

        //初始化view
        initView();
        //初始化数据
        initData();

        //初始化适配器
        initAdapter();

        //绑定适配器
        mylistview.setAdapter(mAdapter);

    }

    private void initAdapter() {
        mAdapter=new ItemAdapter(MainActivity.this,R.layout.item_layout,itemList);
    }

    private void initData() {
        //后期会选择不同的图片，所以暂时不能用for循环来进行更新
        Myitem item1=new Myitem(R.drawable.img_adam);
        Myitem item2=new Myitem(R.drawable.img_adam);
        Myitem item3=new Myitem(R.drawable.img_adam);

        itemList.add(item1);
        itemList.add(item2);
        itemList.add(item3);
    }

    private void initView() {
        mylistview= (ListView) findViewById(R.id.mylistview);
    }
}
