package com.oyo.mobile.dynamicfeaturepoc

interface Feature {
    fun getFeatureName(): String

    interface Provider {
        operator fun invoke(params: Params): Feature
    }

    interface Params
    class NoParams: Params
}