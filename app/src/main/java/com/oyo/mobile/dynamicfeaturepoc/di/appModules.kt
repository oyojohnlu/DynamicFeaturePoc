package com.oyo.mobile.dynamicfeaturepoc.di

import com.google.android.play.core.splitinstall.SplitInstallManagerFactory
import com.oyo.mobile.dynamicfeaturepoc.MainViewModule
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val featureModules = module {
    single { SplitInstallManagerFactory.create(androidContext()) }
    viewModel { MainViewModule(androidContext(), get()) }
}