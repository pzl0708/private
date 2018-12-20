package com.example.pzl.wanandroid.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.pzl.wanandroid.R;
import com.example.pzl.wanandroid.base.BaseMvpFragment;
import com.example.pzl.wanandroid.bean.BannerBean;
import com.example.pzl.wanandroid.bean.HomeArticleBean;
import com.example.pzl.wanandroid.model.MainFragmentModel;
import com.example.pzl.wanandroid.primary.CommonPresenter;
import com.example.pzl.wanandroid.primary.ICommonView;
import com.example.pzl.wanandroid.primary.config.IntentConfig;
import com.example.pzl.wanandroid.ui.activity.DetailsActivity;
import com.example.pzl.wanandroid.ui.adapter.HomeArticleAdapter;
import com.example.pzl.wanandroid.utils.GlideImageLoader;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MainPagerFragment extends BaseMvpFragment<CommonPresenter, MainFragmentModel> implements ICommonView, HomeArticleAdapter.OnLongClick {

    @BindView(R.id.recycler)
    XRecyclerView recycler;
    private Banner mBanner;
    private List<String> mImageList = new ArrayList<>();
    private List<String> mBannerTitleList = new ArrayList<>();
    private List<String> mBannerUrlList = new ArrayList<>();
    private HomeArticleAdapter mAdapter;
    private List<HomeArticleBean.DataBean.DatasBean> mList = new ArrayList<>();

    @Override
    protected int initLayout() {
        return R.layout.fragment_main_pager;
    }

    @Override
    protected void initView() {
        initRecycleView(recycler);
        recycler.setFocusable(false);
        recycler.setFocusableInTouchMode(false);
        mAdapter = new HomeArticleAdapter(getContext(),mList);
        recycler.setHasFixedSize(true);
        LinearLayout mHeaderGroup = ((LinearLayout) LayoutInflater.from(getContext()).inflate(R.layout.head_banner, null));
        mBanner = mHeaderGroup.findViewById(R.id.head_banner);
        mHeaderGroup.removeView(mBanner);
        recycler.addHeaderView(mBanner);
        recycler.setAdapter(mAdapter);

        mAdapter.setOnLongClick(this);
    }

    @Override
    protected void initData() {
        mPresenter.getData(IntentConfig.COMMON);
        mPresenter.getData(IntentConfig.NO_LOAD);
    }

    @Override
    protected CommonPresenter getPresenter() {
        return new CommonPresenter();
    }

    @Override
    protected MainFragmentModel getModel() {
        return new MainFragmentModel();
    }

    @Override
    public void onResponse(Object o, int intent) {
        if (intent == IntentConfig.COMMON){
            BannerBean bean = (BannerBean) o;
            List<BannerBean.DataBean> data = bean.getData();
            for (BannerBean.DataBean datum : data) {
                if (!mImageList.contains(datum.getImagePath())) {
                    mImageList.add(datum.getImagePath());
                    mBannerTitleList.add(datum.getTitle());
                    mBannerUrlList.add(datum.getUrl());
                }
            }
            //设置banner样式
            mBanner.setBannerStyle(BannerConfig.NUM_INDICATOR_TITLE);
            //设置图片加载器
            mBanner.setImageLoader(new GlideImageLoader());
            //设置图片集合
            mBanner.setImages(mImageList);
            //设置banner动画效果
            mBanner.setBannerAnimation(Transformer.DepthPage);
            //设置标题集合（当banner样式有显示title时）
            mBanner.setBannerTitles(mBannerTitleList);
            //设置自动轮播，默认为true
            mBanner.isAutoPlay(true);
            //设置轮播时间
            mBanner.setDelayTime(data.size() * 400);
            //设置指示器位置（当banner模式中有指示器时）
            mBanner.setIndicatorGravity(BannerConfig.CENTER);

//            mBanner.setOnBannerListener(i -> JudgeUtils.startArticleDetailActivity(_mActivity, null,
//                    0, mBannerTitleList.get(i), mBannerUrlList.get(i),
//                    false, false, true));
            //banner设置方法全部调用完毕时最后调用
            mBanner.setOnBannerListener(new OnBannerListener() {
                @Override
                public void OnBannerClick(int position) {
                    Intent intent = new Intent(getActivity(), DetailsActivity.class);
                    intent.putExtra("title",mBannerTitleList.get(position));
                    intent.putExtra("url",mBannerUrlList.get(position));
                    startActivity(intent);
                }
            });
            mBanner.start();
        }
        if (intent == IntentConfig.NO_LOAD || intent == IntentConfig.REFRESH){
            mList.clear();
            HomeArticleBean bean = (HomeArticleBean) o;
            mList.addAll(bean.getData().getDatas());
            mAdapter.notifyDataSetChanged();
            recycler.refreshComplete();
        }
        if (intent == IntentConfig.LOAD_MORE){
            HomeArticleBean bean = (HomeArticleBean) o;
//            int pageCount = bean.getData().getPageCount();
//            int curPage = bean.getData().getCurPage();
            mList.addAll(bean.getData().getDatas());
            mAdapter.notifyDataSetChanged();
            recycler.loadMoreComplete();
        }
    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void refresh() {
        super.refresh();
        new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    Thread.sleep(1000);
                    mPresenter.getData(IntentConfig.REFRESH);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    @Override
    public void loadMore() {
        super.loadMore();
        new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    Thread.sleep(1000);
                    mPresenter.getData(IntentConfig.LOAD_MORE);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    @Override
    public void onLong(int position) {
        HomeArticleBean.DataBean.DatasBean bean = mList.get(position);
        Intent intent = new Intent(getActivity(), DetailsActivity.class);
        intent.putExtra("title",bean.getTitle());
        intent.putExtra("url",bean.getLink());
        startActivity(intent);
    }

    public void jumpToTheTop() {
        if (recycler != null) {
            recycler.smoothScrollToPosition(0);
        }
    }
}
