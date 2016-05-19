package com.example.kingwen.imagetest.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.kingwen.imagetest.Adapters.ItemAdapter;
import com.example.kingwen.imagetest.Beans.MyItem1;
import com.example.kingwen.imagetest.Beans.MyItem2;
import com.example.kingwen.imagetest.Beans.Myitem;
import com.example.kingwen.imagetest.R;
import com.example.kingwen.imagetest.Utils.FormatUtils;
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


        /**
         * item1 uri 是资源文件，通过我们的格式转换方法进行转化
         */
        Myitem item1=new MyItem2(FormatUtils.resourceIdToUri(getApplicationContext(),R.drawable.img_adam));
        /**
         * item2 uri 是网络文件  幺蛾子头像
         */
        Myitem item2=new MyItem1("https://img3.doubanio.com/icon/u99034774-3.jpg");
        /**
         * item3  是asset文件
         */
        Myitem item3=new MyItem1("assets://img_fei.jpg");
        /**
         * item4  是资源文件，格式是gif
         */
        Myitem item4=new MyItem2(FormatUtils.resourceIdToUri(getApplicationContext(),R.drawable.gif_fatman));



        itemList.add(item1);
        itemList.add(item2);
        itemList.add(item3);
        itemList.add(item4);
    }

    private void initView() {
        mylistview= (ListView) findViewById(R.id.mylistview);
    }
}
