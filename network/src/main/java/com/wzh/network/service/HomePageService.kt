package com.wzh.network.service

import com.wzh.model.model.ArticleList
import com.wzh.model.model.BaseModel
import com.wzh.model.room.entity.Article
import com.wzh.model.room.entity.BannerBean
import com.wzh.model.room.entity.HotKey
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * 版权：Zhujiang 个人版权
 * @author zhujiang
 * 版本：1.5
 * 创建日期：2020/4/30
 * 描述：SunnyWeather
 *
 */
interface HomePageService {

    @GET("banner/json")
    suspend fun getBanner(): BaseModel<List<BannerBean>>

    @GET("article/top/json")
    suspend fun getTopArticle(): BaseModel<List<Article>>

    @GET("article/list/{a}/json")
    suspend fun getArticle(@Path("a") a: Int): BaseModel<ArticleList>

    @GET("hotkey/json")
    suspend fun getHotKey(): BaseModel<List<HotKey>>

    @POST("article/query/{page}/json")
    suspend fun getQueryArticleList(@Path("page") page: Int, @Query("k") k: String): BaseModel<ArticleList>

}