package com.example.pzl.wanandroid.ui.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

public class KnowledgeFragmentAdapter extends FragmentPagerAdapter {

    private List<Fragment> mList;
    private List<String> mTitle;

    public KnowledgeFragmentAdapter(FragmentManager fm, List<Fragment> mList, List<String> mTitle) {
        super(fm);
        this.mList = mList;
        this.mTitle = mTitle;
    }

    public void setList(List<Fragment> list,List<String> title) {
        mList = list;
        mTitle = title;
    }

    @Override
    public Fragment getItem(int position) {
        return mList.get(position);
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mTitle.get(position);
    }
}
