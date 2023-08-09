package com.wanris.business.provider;

import android.content.Context;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.wanris.business.http.HttpService;
import com.wanris.business.response.BaseResponse;
import com.wanris.business.response.XXGoodsListData;
import com.wanris.business.provider.bean.XXGoodsListRequest;

import io.reactivex.Flowable;

@Route(path = "/http/goods", name = "商品模块")
public class GoodsProviderImpl implements IGoodsProvider{
    @Override
    public void init(Context context) {

    }

    @Override
    public Flowable<BaseResponse<XXGoodsListData>> getGoodsList(XXGoodsListRequest request) {
        return HttpService.getInstance()
                .getServiceApi()
                .getGoods("goods",
                        "getGoodsPage",
                        request.getPageNo(),
                        request.getPageSize(),
                        request.getCategoryId(),
                        request.getOrderBy(),
                        request.getAsc(),
                        request.getTitle(),
                        request.getStatus());
    }
}
