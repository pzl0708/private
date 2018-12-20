package com.example.pzl.wanandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.pzl.wanandroid.ui.activity.SearchActivity;
import com.example.pzl.wanandroid.ui.activity.UsageActivity;
import com.example.pzl.wanandroid.ui.adapter.BottomFragmentAdapter;
import com.example.pzl.wanandroid.ui.fragment.KnowledgeHierarchyFragment;
import com.example.pzl.wanandroid.ui.fragment.MainPagerFragment;
import com.example.pzl.wanandroid.ui.fragment.NavigationFragment;
import com.example.pzl.wanandroid.ui.fragment.ProjectFragment;
import com.example.pzl.wanandroid.ui.fragment.drawerfragment.CollectFragment;
import com.example.pzl.wanandroid.ui.fragment.drawerfragment.SettingFragment;
import com.example.pzl.wanandroid.utils.BottomNavigationViewHelper;
import com.example.pzl.wanandroid.utils.NoScrollViewPager;
import com.example.pzl.wanandroid.utils.StatusBarUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, ViewPager.OnPageChangeListener {

    @BindView(R.id.common_toolbar_title_tv)
    TextView titleTv;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.nav_view)
    NavigationView navView;
    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.bottom_navigation_view)
    BottomNavigationView bottomNavigationView;
    @BindView(R.id.viewpager)
    NoScrollViewPager viewpager;
    private List<Fragment> mList = new ArrayList<>();
    private BottomFragmentAdapter mAdapter;
    private MainPagerFragment mMainPagerFragment;
    private KnowledgeHierarchyFragment mKnowledgeHierarchyFragment;
    private NavigationFragment mNavigationFragment;
    private ProjectFragment mProjectFragment;
    private int position = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initToolbar();
        initViewPager();
        initBottomNavigationView();
        initNavigationView();

        bottomNavigationView.setSelectedItemId(R.id.tab_main_pager);


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                //获取mDrawerLayout中的第一个子布局，也就是布局中的RelativeLayout
                //获取抽屉的view
                View mContent = mDrawerLayout.getChildAt(0);
                float scale = 1 - slideOffset;
                float endScale = 0.8f + scale * 0.2f;
                float startScale = 1 - 0.3f * scale;

                //设置左边菜单滑动后的占据屏幕大小
                drawerView.setScaleX(startScale);
                drawerView.setScaleY(startScale);
                //设置菜单透明度
                drawerView.setAlpha(0.6f + 0.4f * (1 - scale));

                //设置内容界面水平和垂直方向偏转量
                //在滑动时内容界面的宽度为 屏幕宽度减去菜单界面所占宽度
                mContent.setTranslationX(drawerView.getMeasuredWidth() * (1 - scale));
                //设置内容界面操作无效（比如有button就会点击无效）
                mContent.invalidate();
                //设置右边菜单滑动后的占据屏幕大小
                mContent.setScaleX(endScale);
                mContent.setScaleY(endScale);
            }
        };
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        fab.setOnClickListener(this);
        viewpager.addOnPageChangeListener(this);
    }

    private void initNavigationView() {
        final FragmentManager manager = getSupportFragmentManager();
        final FragmentTransaction transaction = manager.beginTransaction();
        final CollectFragment collectFragment = CollectFragment.newInstance(null, null);
        navView.getMenu().findItem(R.id.nav_item_wan_android)
                .setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        return true;
                    }
                });
        navView.getMenu().findItem(R.id.nav_item_my_collect)
                .setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        return true;
                    }
                });
        navView.getMenu().findItem(R.id.nav_item_about_us)
                .setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        return true;
                    }
                });
        navView.getMenu().findItem(R.id.nav_item_logout)
                .setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        return true;
                    }
                });
        navView.getMenu().findItem(R.id.nav_item_setting)
                .setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        return true;
                    }
                });
    }

    private void initToolbar() {
        setSupportActionBar(toolbar);  //支持toolbar
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayShowTitleEnabled(false);
        titleTv.setText(getString(R.string.home_pager));   //
        StatusBarUtil.setStatusColor(getWindow(), ContextCompat.getColor(this, R.color.main_status_bar_blue), 1f);
    }

    private void initViewPager() {
        mMainPagerFragment = new MainPagerFragment();
        mKnowledgeHierarchyFragment = new KnowledgeHierarchyFragment();
        mNavigationFragment = new NavigationFragment();
        mProjectFragment = new ProjectFragment();
        mList.add(mMainPagerFragment);
        mList.add(mKnowledgeHierarchyFragment);
        mList.add(mNavigationFragment);
        mList.add(mProjectFragment);
        mAdapter = new BottomFragmentAdapter(getSupportFragmentManager(), mList);
        viewpager.setAdapter(mAdapter);
        viewpager.setNoScroll(false);
    }

    private void initBottomNavigationView() {
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.tab_main_pager:
                        viewpager.setCurrentItem(0);
                        titleTv.setText(getString(R.string.home_pager));
                        return true;
                    case R.id.tab_knowledge_hierarchy:
                        viewpager.setCurrentItem(1);
                        titleTv.setText(getString(R.string.knowledge_hierarchy));
                        return true;
                    case R.id.tab_navigation:
                        viewpager.setCurrentItem(2);
                        titleTv.setText(getString(R.string.navigation));
                        return true;
                    case R.id.tab_project:
                        viewpager.setCurrentItem(3);
                        titleTv.setText(getString(R.string.project));
                        return true;
                }
                return false;
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_usage:
                startActivity(new Intent(this, UsageActivity.class));
                break;
            case R.id.action_search:
                startActivity(new Intent(this, SearchActivity.class));
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab:
                jumpToTheTop();
                break;
        }
    }

    private void jumpToTheTop() {
        switch (position) {
            case 0:
                mMainPagerFragment.jumpToTheTop();
                break;
            case 1:
                mKnowledgeHierarchyFragment.jumpToTheTop();
                break;
            case 2:
                mNavigationFragment.jumpToTheTop();
                break;
            case 3:
                mProjectFragment.jumpToTheTop();
                break;
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        this.position = position;
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
