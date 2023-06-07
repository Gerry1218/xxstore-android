package com.wanris.business.http;

import android.util.Log;

import com.wanris.business.common.utils.DeviceHelper;
import com.wanris.business.common.utils.MD5Helper;
import com.wanris.business.common.utils.RandomHelper;
import com.wanris.business.common.utils.TimeHelper;
import com.wanris.business.constant.Constant;
import com.wanris.business.constant.HttpConfigConstant;
import com.wanris.business.constant.URLs;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.TimeUnit;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class HttpService {
    public static HttpService httpService;
    private OkHttpClient okHttpClient;
    private ServiceApi serviceApi;

    public static HttpService getInstance() {
        if (httpService == null) {
            httpService = new HttpService();
        }
        return httpService;
    }

    public ServiceApi getServiceApi() {
        if (serviceApi == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(URLs.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(createOkHttpClient())
                    .build();
            serviceApi = retrofit.create(ServiceApi.class);
        }
        return serviceApi;
    }

    private OkHttpClient createOkHttpClient() {
        if (okHttpClient != null) {
            return okHttpClient;
        }
        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        builder.connectTimeout(HttpConfigConstant.CONNECT_TIME_OUT, TimeUnit.SECONDS);
        builder.readTimeout(HttpConfigConstant.READ_TIME_OUT, TimeUnit.SECONDS);
        builder.writeTimeout(HttpConfigConstant.WRITE_TIME_OUT, TimeUnit.SECONDS);

        builder.sslSocketFactory(HttpsTrustManager.createSSLSocketFactory());
        builder.hostnameVerifier(new HttpsTrustManager.TrustAllHostnameVerifier());

        Interceptor paramsInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                String token = ""; //UserUtil.getToken(BaseApplication.getContext());
//                String userId = UserUtil.getUserId(BaseApplication.getContext());

                String timeStamp = String.valueOf(TimeHelper.getCurrentTime());
                String deviceId = "";//DeviceHelper.getDeviceId();
                String nonceStr = RandomHelper.getSmallLetter(6);

                String signature = "";
                String salt = "xxShop";

                Request oldRequest = chain.request();
                HttpUrl.Builder httpBuilder = oldRequest
                        .url()
                        .newBuilder();

                HttpUrl.Builder newBuilder = httpBuilder
                        .addQueryParameter("version", Constant.VERSION_NAME)
                        .addQueryParameter("devType", Constant.DEV_TYPE);


                HttpUrl newUrl = newBuilder.build();
                if ("POST".equals(oldRequest.method())) {
                    RequestBody body = oldRequest.body();

                    Buffer buffer = new Buffer();
                    if (body != null) {
                        body.writeTo(buffer);
                    }

                    Charset charset = Charset.forName("UTF-8");
                    MediaType contentType = body.contentType();
                    if (contentType != null) {
                        charset = contentType.charset(charset);
                    }

                    String data = buffer.readString(charset);
                    String url = oldRequest.url().encodedPath();
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("url=").append(url)
                            .append("&timeStamp=").append(timeStamp)
                            .append("&deviceId=").append(deviceId)
                            .append("&nonceStr=").append(nonceStr)
                            .append("&platform=").append(Constant.PLATFORM)
                            .append("&data=").append(data);
                    signature = MD5Helper.getMD5(MD5Helper.getMD5(stringBuilder.toString()) + salt);
                    Log.d("HTTP POST: " + "签名字串：" + stringBuilder.toString() + "\n签名md5:", signature);
                } else if ("GET".equals(oldRequest.method())) {
                    HttpUrl httpUrl = newUrl;
                    Set<String> params = httpUrl.queryParameterNames();
                    String data = "";
                    if (params.size() > 0) {
                        params = new TreeSet<>(params);
                        StringBuilder sb = new StringBuilder();
                        for (String key : params) {
                            sb.append(key).append("=").append(httpUrl.queryParameter(key)).append("&");
                        }
                        data = sb.substring(0, sb.length() - 1);
                    }
                    String url = httpUrl.encodedPath();
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("url=").append(url)
                            .append("&timeStamp=").append(timeStamp)
                            .append("&deviceId=").append(deviceId)
                            .append("&nonceStr=").append(nonceStr)
                            .append("&platform=").append(Constant.PLATFORM)
                            .append("&data=").append(data);
                    signature = MD5Helper.getMD5(MD5Helper.getMD5(stringBuilder.toString()) + salt);
                    Log.d("HTTP GET: " + "签名字串：" + stringBuilder.toString() + "\n签名md5:", signature);
                }

                Request newRequest = oldRequest.newBuilder()
                        .url(newUrl)
                        .addHeader("access-token", token)
                        .addHeader("versionNumber", String.valueOf(Constant.VERSION_CODE))
                        .addHeader("Carry-Over", "")
                        .addHeader("channel", BuildConfig.FLAVOR)
                        .addHeader("nonceStr", nonceStr)
                        .addHeader("deviceId", deviceId)
                        .addHeader("timeStamp", timeStamp)
                        .addHeader("platform", Constant.PLATFORM)
                        .build();

                return chain.proceed(newRequest);
            }
        };
        builder.addInterceptor(paramsInterceptor);
        return okHttpClient = builder.build();
    }

}
