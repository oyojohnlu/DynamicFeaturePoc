package com.oyo.mobile.dynamicfeaturepoc

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.play.core.splitinstall.SplitInstallManager
import com.google.android.play.core.splitinstall.SplitInstallRequest
import com.google.android.play.core.splitinstall.SplitInstallStateUpdatedListener

class FeatureLoadViewModel(
    private val context: Context,
    private val manager: SplitInstallManager
) : ViewModel() {

    private val _featureLoadingStatus = MutableLiveData<Int>()
    val loadingStatus: LiveData<Int> = _featureLoadingStatus

    private val _onSuccessfulLoad = MutableLiveData<Boolean>()
    val onSuccessfulLoad: LiveData<Boolean> = _onSuccessfulLoad

    private val _onFailedLoad = MutableLiveData<Failure>()
    val onFailedLoad: LiveData<Failure> = _onFailedLoad

    private val listener = SplitInstallStateUpdatedListener { state ->
        _featureLoadingStatus.value = state.status()
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

        requestInstall(name)
    }

    private fun requestInstall(name: String) {
        val request =
            SplitInstallRequest
                .newBuilder()
                .addModule(name)
                .build()

        manager
            .startInstall(request)
            .addOnSuccessListener {
                _onSuccessfulLoad.value = true
            }
            .addOnFailureListener { exception ->
                _onFailedLoad.value = Failure.FeatureFailure(exception)
            }
    }

    override fun onCleared() {
        super.onCleared()
        manager.unregisterListener(listener)
    }


    object LoadingStatus {
        const val UNKNOWN = 0
        const val PENDING = 1
        const val DOWNLOADING = 2
        const val DOWNLOADED = 3
        const val INSTALLING = 4
        const val INSTALLED = 5
        const val FAILED = 6
        const val CANCELED = 7
        const val REQUIRES_USER_CONFIRMATION = 8
        const val CANCELING = 9
    }

}