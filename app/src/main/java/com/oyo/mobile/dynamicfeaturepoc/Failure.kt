package com.oyo.mobile.dynamicfeaturepoc

sealed class Failure {
    class FeatureFailure(val throwable: Throwable): Failure()
}