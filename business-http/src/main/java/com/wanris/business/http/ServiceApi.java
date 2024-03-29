package com.wanris.business.http;


import com.wanris.business.constant.URLs;
import com.wanris.business.response.BaseResponse;
import com.wanris.business.response.XXGoodsDetailData;
import com.wanris.business.response.XXGoodsListData;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ServiceApi {

    // 商品列表
    // --url /m.api?_gp=goods&_mt=getGoodsPage&pageNo=1&pageSize=20&categoryId=&orderBy=&isAsc=&title=&status=
    // --header access_token=undefined
    @GET(URLs.BASE_URL + "/m.api")
    Flowable<BaseResponse<XXGoodsListData>> getGoods(
            @Query("_gp") String _gp,
            @Query("_mt") String _mt,
            @Query("pageNo") Integer pageNo,
            @Query("pageSize") Integer pageSize,
            @Query("categoryId") Long categoryId,
            @Query("orderBy") String orderBy,
            @Query("isAsc") Boolean isAsc,
            @Query("title") String title,
            @Query("status") Integer status);

    // 商品详情
    // --url /m.api?_gp=goods&_mt=getGoods&spuId=1236771&userId=
    @GET(URLs.BASE_URL + "/m.api")
    Flowable<BaseResponse<XXGoodsDetailData>> getGoodsDetail(
            @Query("_gp") String _gp,
            @Query("_mt") String _mt,
            @Query("spuId") String spuId
    );

}
