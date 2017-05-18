package cn.org.nercita.agriculturalconsultant.main.me.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import java.util.ArrayList;

import butterknife.Bind;
import cn.org.nercita.agriculturalconsultant.R;
import cn.org.nercita.agriculturalconsultant.base.BaseActivity;
import cn.org.nercita.agriculturalconsultant.main.me.fragment.ExpertQuestionFragment;
import cn.org.nercita.agriculturalconsultant.main.me.fragment.TecQuestionFragment;
import cn.org.nercita.agriculturalconsultant.view.TitleBar;

/**
 * Created by 范博文 on 2017/4/11.
 * 我的问答
 */

public class MyQuestionActivity extends BaseActivity {
    @Bind(R.id.tab)
    TabLayout tab;
    @Bind(R.id.vp)
    ViewPager vp;
    @Bind(R.id.title)
    TitleBar title;
    ArrayList<String> tabTitle = new ArrayList<>();//tab 标题
    ArrayList<Fragment> vpFragment = new ArrayList<>();//viewpager fragment
    private TecQuestionFragment tecQuestionFragment;
    private ExpertQuestionFragment expertQuestionFragment;

    @Override
    protected int getContentView() {
        return R.layout.activity_myquestion;
    }

    @Override
    protected void initData() {
        super.initData();
        title.setBackListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        expertQuestionFragment = new ExpertQuestionFragment();
        tabTitle.add("农技问答");
        tabTitle.add("专家回答");
        if (tecQuestionFragment == null) {
            tecQuestionFragment = new TecQuestionFragment();
        }
        if (expertQuestionFragment==null){
            expertQuestionFragment = new ExpertQuestionFragment();
        }
        vpFragment.add(tecQuestionFragment);
        vpFragment.add(expertQuestionFragment);
        tab.setTabMode(TabLayout.MODE_FIXED);//设置tab模式，当前为系统默认模式
        tab.addTab(tab.newTab().setText(tabTitle.get(0)));//添加tab选项卡
        tab.addTab(tab.newTab().setText(tabTitle.get(1)));
        MyPagerAdapter mAdapter = new MyPagerAdapter(getSupportFragmentManager());
        vp.setAdapter(mAdapter);//给ViewPager设置适配器
        tab.setupWithViewPager(vp);//将TabLayout和ViewPager关联起来。
        tab.setTabsFromPagerAdapter(mAdapter);//给Tabs设置适配器
    }

    private class MyPagerAdapter extends FragmentPagerAdapter {


        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return vpFragment.get(position);
        }

        @Override
        public int getCount() {
            return vpFragment.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabTitle.get(position);
        }
    }
}
