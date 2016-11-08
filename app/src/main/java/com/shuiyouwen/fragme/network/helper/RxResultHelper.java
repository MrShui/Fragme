package com.shuiyouwen.fragme.network.helper;

import android.text.TextUtils;

import com.shuiyouwen.fragme.network.bean.ResultModel;
import com.shuiyouwen.fragme.network.exception.ServerException;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

/**
 * 将网络请求结果分类处理
 * Created by Administrator on 2016/11/8 0008.
 */

public class RxResultHelper {
    public static <T> Observable.Transformer<ResultModel<T>, T> handleResult() {
        return new Observable.Transformer<ResultModel<T>, T>() {
            @Override
            public Observable<T> call(Observable<ResultModel<T>> resultModelObservable) {
                return resultModelObservable.flatMap(new Func1<ResultModel<T>, Observable<T>>() {
                    @Override
                    public Observable<T> call(ResultModel<T> tResultModel) {
                        if (tResultModel == null) {
                            return Observable.error(new ServerException("服务器异常"));
                        }

                        if (TextUtils.equals(tResultModel.getErrcode(), ResultModel.REQUEST_SUCCESS)) {
                            return createData(tResultModel.getData());
                        } else {
                            return Observable.error(new ServerException(tResultModel.getErrmsg()));
                        }
                    }
                });
            }
        };
    }

    private static <T> Observable<T> createData(final T data) {
        return Observable.create(new Observable.OnSubscribe<T>() {
            @Override
            public void call(Subscriber<? super T> subscriber) {
                subscriber.onNext(data);
                subscriber.onCompleted();
            }
        });
    }
}
