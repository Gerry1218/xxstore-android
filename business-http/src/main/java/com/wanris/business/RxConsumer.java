package com.wanris.business;


import com.wanris.business.response.BaseResponse;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

/**
 * Description: 统一订阅封装
 *
 * @Author: Gerry
 * @Date: 2020/12/12
 */
public abstract class RxConsumer<T> implements Consumer<BaseResponse<T>> {

    @Override
    public void accept(@NonNull BaseResponse<T> baseResponse) throws Exception {
        //正常
        if (baseResponse.getErrno() == 200) {
            _accept(baseResponse.getData());
        }
//        //token异常
//        else if (HttpResponseConstant.TOKEN_EXCEPTION.equals(baseResponse.getSub_code())) {
////            _handleMsg(baseResponse.getMessage(), baseResponse.getSub_code(), baseResponse.getSub_message());
//            EventBus.getDefault().post(new LoginOutEvent(false));
//        }
//        //密码异常
//        else if (HttpResponseConstant.PASSWORD_EXCEPTION.equals(baseResponse.getSub_code())) {
////            _handleMsg(baseResponse.getMessage(), baseResponse.getSub_code(), baseResponse.getSub_message());
//            EventBus.getDefault().post(new LoginOutEvent(false));
//        }
//        //token无效
//        else if (HttpResponseConstant.TOKEN_AUTH_EXCEPTION.equals(baseResponse.getSub_code())) {
//            EventBus.getDefault().post(new LoginOutEvent(false));
//        } else if (HttpResponseConstant.SYSTEM_EXCEPTION.equals(baseResponse.getSub_code())) {
//            _handleMsg(baseResponse.getMessage(), baseResponse.getSub_code(), baseResponse.getSub_message());
//            EventBus.getDefault().post(new SystemEvent());
//        } else {
//            _handleMsg(baseResponse.getMessage(), baseResponse.getSub_code(), baseResponse.getSub_message());
//        }
    }

    public abstract void _accept(T t);

    public abstract void _handleMsg(String message, String subCode, String subMessage);
}
