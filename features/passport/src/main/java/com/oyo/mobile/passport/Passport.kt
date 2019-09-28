package com.oyo.mobile.passport

import com.oyo.mobile.dynamicfeaturepoc.Feature

class Passport : Feature {

    override fun getFeatureName() = "Passport"

    companion object Provider : Feature.Provider {
        override fun invoke(params: Feature.Params) = Passport()
    }
}