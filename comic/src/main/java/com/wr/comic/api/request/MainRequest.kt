package com.wr.comic.api.request

import com.wr.comic.api.UrlTencentComic
import com.wr.comic.api.jsoup.TencentComicData
import com.wr.comic.bean.MainBanner
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.ObservableOnSubscribe
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.jsoup.Jsoup

/**
 * Created by wangru
 * Date: 2018/6/12  16:12
 * mail: 1902065822@qq.com
 * describe:
 */
class MainRequest {
    companion object {
        public fun getBanner(observer: Observer<List<MainBanner>>) {
            var observable = Observable.create(ObservableOnSubscribe { e: ObservableEmitter<List<MainBanner>> ->
                run {
                    val doc = Jsoup.connect(UrlTencentComic.Banner).get()
                    val mainBannerList = TencentComicData.transToBanner(doc)
                    e.onNext(mainBannerList)
                }
            })
            observable.subscribeOn(Schedulers.io())
                    .unsubscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread()).subscribe(observer)
        }
    }

}