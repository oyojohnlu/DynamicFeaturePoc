package com.oyo.mobile.dynamicfeaturepoc

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.play.core.splitinstall.SplitInstallManager
import com.google.android.play.core.splitinstall.SplitInstallRequest
import com.google.android.play.core.splitinstall.SplitInstallStateUpdatedListener
import com.google.android.play.core.splitinstall.model.SplitInstallSessionStatus

class MainViewModule(
    private val context: Context,
    private val manager: SplitInstallManager
) : ViewModel() {

    private val _featureLoadingStatus = MutableLiveData<FeatureLoadingStatus>()
    val featureLoadingStatus: LiveData<FeatureLoadingStatus> = _featureLoadingStatus

    private val _onSuccessfulLoad = MutableLiveData<Boolean>()
    val onSuccessfulLoad: LiveData<Boolean> = _onSuccessfulLoad

    private val _onFailedLoad = MutableLiveData<Failure>()
    val onFailedLoad: LiveData<Failure> = _onFailedLoad

    private val listener = SplitInstallStateUpdatedListener { state ->
        when (state.status()) {
            SplitInstallSessionStatus.FAILED ->
                _featureLoadingStatus.value = FeatureLoadingStatus.FAILED
            SplitInstallSessionStatus.INSTALLING ->
                _featureLoadingStatus.value = FeatureLoadingStatus.INSTALLING
            SplitInstallSessionStatus.INSTALLED ->
                _featureLoadingStatus.value = FeatureLoadingStatus.INSTALLED
            SplitInstallSessionStatus.DOWNLOADING ->
                _featureLoadingStatus.value = FeatureLoadingStatus.DOWNLOADING
        }
    }

    init {
        manager.registerListener(listener)
    }

    fun isFeatureLoaded(name: String) =
        if (BuildConfig.DEBUG) true else manager.installedModules.contains(name)

    fun loadFeature(name: String) {
        if (isFeatureLoaded(name)) {
            _onSuccessfulLoad.value = true
            return
        }

        requestStorageInstall(name)
    }

    private fun requestStorageInstall(name: String) {
        val request =
            SplitInstallRequest
                .newBuilder()
                .addModule(name)
                .build()

        manager
            .startInstall(request)
            .addOnFailureListener { exception ->
                _onFailedLoad.value = Failure.FeatureFailure(exception)
            }
    }

    override fun onCleared() {
        super.onCleared()
        manager.unregisterListener(listener)
    }

    enum class FeatureLoadingStatus {
        DOWNLOADING, INSTALLING, FAILED, INSTALLED
    }

}