package com.oyo.mobile.dynamicfeaturepoc

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

private const val FEATURE_OYOPASSPORT = "OYO Passport"
private const val OYOPASSPORT_ACTIVITY = "com.oyo.mobile.passport.PassportActivity"

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModule by viewModel()

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

    private fun initViewModel() {
        with(viewModel) {
            observe(featureLoadingStatus) {
                updateStatusLabel(it)
            }
            observe(onSuccessfulLoad) {
                it?.apply {
                    featureBtn.visibility = View.VISIBLE
                }
            }

            failure(onFailedLoad) {
                // Showing error dialog
            }
        }
    }

    private fun launchActivity(className: String) {
        val intent = Intent().setClassName(BuildConfig.APPLICATION_ID, className)
        startActivity(intent)
    }

    private fun updateStatusLabel(status: MainViewModule.FeatureLoadingStatus?) = status?.let {
        statusLabel.text = it.name
        when (status) {
            MainViewModule.FeatureLoadingStatus.DOWNLOADING,
            MainViewModule.FeatureLoadingStatus.INSTALLING ->
                updateProgress(true)
            MainViewModule.FeatureLoadingStatus.INSTALLED,
            MainViewModule.FeatureLoadingStatus.FAILED ->
                updateProgress(false)
        }
    }

    private fun updateProgress(enable: Boolean) {
        progress.visibility = if (enable) View.VISIBLE else View.GONE
    }
}
