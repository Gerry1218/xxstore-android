package com.wanris.business;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

/**
 * Description: 统一异常封装
 *
 * @Author: Gerry
 * @Date: 2020/12/12
 */
public class RxThrowableConsumer implements Consumer<Throwable> {
    @Override
    public void accept(@NonNull Throwable throwable) throws Exception {
        throwable.printStackTrace();
//        LogUtil.e(throwable);
//        UMCrash.generateCustomLog(throwable,"RxThrowableConsumer");
        //网络
//        if (!NetWorkUtil.isNetConnected(BaseApplication.getContext())) {
//            _accept(HttpResponseConstant.NONET, BaseApplication.getContext().getString(R.string.common_network_msg));
//        }
//        //服务器超时
//        else if (throwable instanceof SocketTimeoutException || throwable instanceof ConnectException) {
//            _accept(HttpResponseConstant.NONET, BaseApplication.getContext().getString(R.string.common_network_msg));
//        }
//        //解析异常
//        else if (throwable instanceof JsonParseException || throwable instanceof JSONException || throwable instanceof ParseException) {
//            _accept(HttpResponseConstant.PARSE_ERROR, BaseApplication.getContext().getString(R.string.common_parse_msg));
//        } else if (throwable instanceof HttpException) {
//            Response raw = ((HttpException) throwable).response().raw();
//            Request request = raw.request();
//            LogUtil.e("httpCode " + raw.code() + " url " + request.url().toString());
//            _accept(HttpResponseConstant.UNKNOWN, BaseApplication.getContext().getString(R.string.common_unknown_msg));
//        }
//        //其它
//        else {
//            _accept(HttpResponseConstant.UNKNOWN, BaseApplication.getContext().getString(R.string.common_unknown_msg));
//        }
    }

    public void _accept(int errorCode, String errorMsg) {
//        LogUtil.e(errorMsg);
//        ToastUtil.show(BaseApplication.getContext(), errorMsg);
    }

}
