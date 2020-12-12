package com.pmui.apps.api.callback


import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers

object ApiCallHelper {

    fun <T> call(observable: Observable<T>?, rxApiCallback: ApiCallback<T>?): Disposable {

        if (observable == null) {
            throw IllegalArgumentException("Observable must not be null.")
        }

        if (rxApiCallback == null) {
            throw IllegalArgumentException("Callback must not be null.")
        }

        return observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(Consumer { t ->
                    // success response
                    rxApiCallback.onSuccess(t)
                }, object : Consumer<Throwable> {
                    @Throws(Exception::class)
                    override fun accept(t: Throwable) {
                        // failure response
                        if (t != null) {
                            rxApiCallback.onFailed(t)
                        } else {
                            rxApiCallback.onFailed(Throwable("Something went wrong"))
                        }
                    }
                })
    }
}
