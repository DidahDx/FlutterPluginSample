package com.example.pluign_sample

import android.app.Activity
import android.content.Context
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.embedding.engine.plugins.activity.ActivityAware
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result
import java.lang.ref.WeakReference
import java.security.NoSuchAlgorithmException
import android.content.pm.Signature


import android.os.Build
import android.util.Log
import java.security.MessageDigest
import java.util.Base64



/** PluignSamplePlugin */
class PluignSamplePlugin : FlutterPlugin, MethodCallHandler, ActivityAware {
    // The MethodChannel that will the communication between Flutter and native Android
    //
    // This local reference serves to register the plugin with the Flutter Engine and unregister it
    // when the Flutter Engine is detached from the Activity
    private lateinit var channel: MethodChannel
    private lateinit var context: Context
    private lateinit var activity: Activity
    private var activityReference = WeakReference<Activity>(null)
    private var contextReference = WeakReference<Context>(null)

    override fun onAttachedToEngine(flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
        channel = MethodChannel(flutterPluginBinding.binaryMessenger, "pluign_sample")
        channel.setMethodCallHandler(this)
        context = flutterPluginBinding.applicationContext
    }

    override fun onDetachedFromActivityForConfigChanges() {
        activityReference.clear()
    }

    override fun onReattachedToActivityForConfigChanges(binding: ActivityPluginBinding) {
        activityReference = WeakReference(binding.activity)
    }

    override fun onDetachedFromActivity() {
        activityReference.clear()
    }

    override fun onAttachedToActivity(binding: ActivityPluginBinding) {
        activity = binding.activity;
    }

    override fun onMethodCall(
        call: MethodCall, result: Result
    ) {
        if (call.method == "getPlatformVersion") {
            result.success("Android ${android.os.Build.VERSION.RELEASE}")
        } else if (call.method == "getFaceBookKeyHash") {

            try {
                val packageInfo: PackageInfo = activity.getPackageManager()
                    .getPackageInfo(activity.getPackageName(), PackageManager.GET_SIGNATURES)
                if (packageInfo != null && packageInfo.signatures != null && (packageInfo.signatures?.size
                        ?: 0) > 0
                ) {

                    // Process each signature if needed
                    val signatures = packageInfo.signatures
                    // Process each signature if needed
                    val firstSignature: Signature? = signatures?.getOrNull(0) // Often, checking the first one is sufficient for basic verification

                    if (firstSignature != null) {
                        try {
                            val md: MessageDigest = MessageDigest.getInstance("SHA") // Or "SHA-256" for stronger hashing
                            md.update(firstSignature.toByteArray())
                            val currentSignatureHash: String =
                                Base64.getEncoder().encodeToString(md.digest())
                            result.success(currentSignatureHash)
                        } catch (e: NoSuchAlgorithmException) {
                            result.notImplemented()
                        }
                    }else{
                        result.notImplemented()
                    }

                }else{
                    result.notImplemented()
                }
            } catch (e: NoSuchAlgorithmException) {
                result.notImplemented()

            } catch (e: Exception) {
                result.notImplemented()

            }


        } else {
            result.notImplemented()
        }
    }

    override fun onDetachedFromEngine(binding: FlutterPlugin.FlutterPluginBinding) {
        channel.setMethodCallHandler(null)
    }
}
