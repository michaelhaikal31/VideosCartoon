package com.pmui.apps.api.callback

interface ApiCallback<T> {
    fun onSuccess(t: T)

    fun onFailed(throwable: Throwable)
}