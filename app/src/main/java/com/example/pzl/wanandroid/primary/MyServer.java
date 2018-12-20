package com.example.pzl.wanandroid.primary;

import com.example.pzl.wanandroid.bean.BannerBean;
import com.example.pzl.wanandroid.bean.HomeArticleBean;
import com.example.pzl.wanandroid.bean.KnowledgeDetailsBean;
import com.example.pzl.wanandroid.bean.KnowledgeHierachBean;
import com.example.pzl.wanandroid.bean.NavigationBean;
import com.example.pzl.wanandroid.bean.ProjectDetailsBean;
import com.example.pzl.wanandroid.bean.ProjectTabBean;
import com.example.pzl.wanandroid.bean.SearchBean;
import com.example.pzl.wanandroid.bean.UsageBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MyServer {
    //http://www.wanandroid.com/banner/json
    @GET("banner/json")
    Observable<BannerBean> getBannerImage();

//    http://www.wanandroid.com/article/list/0/json
    @GET("article/list/{page}/json")
    Observable<HomeArticleBean> getArticleList(@Path("page") int page);

    //http://www.wanandroid.com/tree/json
    @GET("tree/json")
    Observable<KnowledgeHierachBean> getKnowledgeList();

    //http://www.wanandroid.com/article/list/0/json?cid=169
    @GET("article/list/{page}/json?")
    Observable<KnowledgeDetailsBean> getKnowledgeDetails(@Path("page") int page,@Query("cid") int cid);

    //http://www.wanandroid.com/project/tree/json
    @GET("project/tree/json")
    Observable<ProjectTabBean> getProjectTitle();

    //http://www.wanandroid.com/project/list/0/json?cid=293
    @GET("article/list/{page}/json?")
    Observable<ProjectDetailsBean> getProjectDetails(@Path("page") int page, @Query("cid") int cid);

    //http://www.wanandroid.com/navi/json
    @GET("navi/json")
    Observable<NavigationBean> getNavigation();

    //http://www.wanandroid.com/friend/json
    @GET("friend/json")
    Observable<UsageBean> getUsageList();

    //http://www.wanandroid.com//hotkey/json
    @GET("hotkey/json")
    Observable<SearchBean> getSearchList();
}
