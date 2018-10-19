package com.lzq.sprout.model.http;


import com.lzq.sprout.model.bean.BaseBean;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class RxHelper {

    /**
     * 处理http请求返回的结果，result_code,当返回成功的时候将data剥离出来，返回给subscriber
     * @param <T>
     * @return
     */
    public static <T> Observable.Transformer<BaseBean<T>, T> handleResult() {
        return new Observable.Transformer<BaseBean<T>, T>() {
            @Override
            public Observable<T> call(Observable<BaseBean<T>> baseBeanObservable) {
                return baseBeanObservable.flatMap(new Func1<BaseBean<T>, Observable<T>>() {
                    @Override
                    public Observable<T> call(BaseBean<T> tBaseBean) {
                        if ("1".equals(tBaseBean.getResult())) {
                            //返回成功
                            return addData(tBaseBean.getData());
                        } else {
                            //返回失败
                            return Observable.error(new Exception(tBaseBean.getMsg()));
                        }
                    }
                }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    /**
     * 将服务端返回的数据加入subscriber
     *
     * @param data
     * @param <T>
     * @return
     */
    private static <T> Observable<T> addData(final T data) {
        return Observable.create(new Observable.OnSubscribe<T>() {
            @Override
            public void call(Subscriber<? super T> subscriber) {
                try {
                    subscriber.onNext(data);
                    subscriber.onCompleted();
                } catch (Exception e) {
                    subscriber.onError(e);
                }
            }
        });
    }

    /**
     * rxJava线程转换，在io线程中发起请求，回调给主线程
     *
     * @param <T>
     * @return
     */
    public static <T> Observable.Transformer<T, T> schedulersThread() {
        return new Observable.Transformer<T, T>() {
            @Override
            public Observable<T> call(Observable<T> tObservable) {
                return tObservable
                        .subscribeOn(Schedulers.io())
                        .unsubscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }
}
