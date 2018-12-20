package com.example.pzl.wanandroid.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.pzl.wanandroid.R;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

public class BaseFragment extends Fragment{

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }


//    public void showLoading() {
//        mLoading.show();
//    }
//
//    public void dismissLoading() {
//        mLoading.dismiss();
//    }

    public void onNetComplete(int api) {
//        dismissLoading();
        Log.e(getString(R.string.net_complete), getString(R.string.net_code) + api);
    }

    public void onErrorLog(Throwable e, int api) {
        Log.e(getString(R.string.net_error), getString(R.string.net_code) + api + e.toString());
    }

    public void initRecycleView(XRecyclerView recyclerView) {
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(manager);
        recyclerView.setRefreshProgressStyle(ProgressStyle.LineSpinFadeLoader);
        recyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallPulse);
        recyclerView.getDefaultFootView().setLoadingHint(getString(R.string.loading));
        recyclerView.getDefaultFootView().setNoMoreHint(getString(R.string.no_more_data));
        recyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                refresh();
            }

            @Override
            public void onLoadMore() {
                loadMore();
            }
        });
    }

    public void refresh() {

    }

    public void loadMore() {
    }

    public void showToast(Context context, String content) {
        Toast.makeText(context, content, Toast.LENGTH_SHORT).show();
    }
}
