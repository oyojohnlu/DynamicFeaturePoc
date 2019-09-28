package com.oyo.mobile.dynamicfeaturepoc

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.reflect.full.companionObjectInstance

private const val FEATURE_OYOPASSPORT = "OYO Passport"
private const val OYOPASSPORT_ACTIVITY = "com.oyo.mobile.passport.PassportActivity"
private const val OYOPASSPORT_UTILITY = "com.oyo.mobile.passport.Passport"

class MainActivity : AppCompatActivity() {

    private val viewModel: FeatureLoadViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
        initViewModel()

        viewModel.loadFeature(FEATURE_OYOPASSPORT)
    }

    private fun initViews() {
        featureBtn.setOnClickListener {
            launchActivity(OYOPASSPORT_ACTIVITY)
        }
    }

    private fun initViewModel() = with(viewModel) {
            observe(loadingStatus) {
                updateStatusLabel(it)
            }
            observe(onSuccessfulLoad) {
                it?.apply {
                    statusLabel.text =
                        "Done: ${getFeature(OYOPASSPORT_UTILITY, Feature.NoParams()).getFeatureName()}"
                    featureBtn.visibility = View.VISIBLE
                }
            }

            failure(onFailedLoad) {
                // Showing error dialog
            }
        }

    private fun launchActivity(className: String) {
        val intent = Intent().setClassName(BuildConfig.APPLICATION_ID, className)
        startActivity(intent)
    }

    private fun updateStatusLabel(status: Int?) = status?.let {
        when (status) {
            FeatureLoadViewModel.LoadingStatus.DOWNLOADING,
            FeatureLoadViewModel.LoadingStatus.INSTALLING -> {
                statusLabel.text = "Loading"
                updateProgress(true)
            }
            FeatureLoadViewModel.LoadingStatus.INSTALLED -> {
                statusLabel.text =
                    "Done: ${getFeature(OYOPASSPORT_UTILITY, Feature.NoParams()).getFeatureName()}"
                updateProgress(false)
            }
            FeatureLoadViewModel.LoadingStatus.FAILED -> {
                updateProgress(false)
            }
        }
    }

    private fun updateProgress(enable: Boolean) {
        progress.visibility = if (enable) View.VISIBLE else View.GONE
    }

    private fun getFeature(className: String, params: Feature.Params): Feature {
        val provider = Class.forName(className).kotlin.companionObjectInstance as Feature.Provider
        return provider(params)
    }
}
