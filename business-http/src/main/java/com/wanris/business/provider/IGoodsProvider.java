package com.wanris.business.provider;

import com.alibaba.android.arouter.facade.template.IProvider;
import com.wanris.business.response.BaseResponse;
import com.wanris.business.response.XXGoodsListData;
import com.wanris.business.provider.bean.XXGoodsListRequest;

import io.reactivex.Flowable;

public interface IGoodsProvider extends IProvider {
    public Flowable<BaseResponse<XXGoodsListData>> getGoodsList(XXGoodsListRequest request);

}
