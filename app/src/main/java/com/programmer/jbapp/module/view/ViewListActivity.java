package com.programmer.jbapp.module.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.lib.base.utils.ClassUtils;
import com.programmer.jbapp.R;
import com.programmer.jbapp.common.bean.Technique;
import com.programmer.jbapp.framework.AbsBaseActivity;
import com.programmer.jbapp.framework.ItemInfo;
import com.programmer.jbapp.module.view.adapter.ViewListAdapter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * zft
 * 2016/11/22.
 */
public class ViewListActivity extends AbsBaseActivity implements AdapterView.OnItemClickListener {
    private ListView techniquelv;

    private List<Technique> mList = new ArrayList<Technique>();
    private ViewListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.techniquelist_activity);
        initUI();
        initData();
        initEvent();

    }

    private void initEvent() {
        techniquelv.setOnItemClickListener(this);
    }

    private void initData() {
        mAdapter = new ViewListAdapter(this, mList);
        techniquelv.setAdapter(mAdapter);
        refreshData();
    }

    private void refreshData() {
//        Technique technique = new Technique();
//        technique.setTitle("侧滑菜单控件");
//        technique.setClassName("com.programmer.jbapp.module.view.item.Widget_DragLayoutActivity");
//
//        Technique technique2 = new Technique();
//        technique2.setTitle("录制短视频");
//        technique2.setClassName("com.programmer.jbapp.module.view.item.Widget_DragLayoutActivity");
        Set<String> className = ClassUtils.getClassName(this, "com.programmer.jbapp.module.view.item");
        Iterator<String> it = className.iterator();
        while (it.hasNext()) {
            String str = it.next();
            try {
                Class<?> aClass = Class.forName(str);
                if(Activity.class.isAssignableFrom(aClass)) {
                    Technique technique = new Technique();
                    ItemInfo itemInfo = (ItemInfo) (Class.forName(str).newInstance());
                    technique.setTitle(itemInfo.getItemName());
                    technique.setDec(itemInfo.getItemDec());
                    technique.setClassName(str);
                    System.out.println(str);
                    mList.add(technique);
                }
            }  catch (Exception e) {
                e.printStackTrace();
            }

        }
        mAdapter.notifyDataSetChanged();
    }

    private void initUI() {
        initBar(this);
        this.techniquelv = (ListView) findViewById(R.id.technique_lv);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Technique technique = mList.get(i);
        try {
            startActivity(new Intent(this, Class.forName(technique.getClassName())).putExtra("title",technique.getTitle()));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


}
