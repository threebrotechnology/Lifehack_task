package com.lifeindarkness.lifehacktask

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class AppCore : Application(), HasAndroidInjector {

    @Inject
    lateinit var dispatchingActivityInjector: DispatchingAndroidInjector<Any>

    val appCoreComponent: AppCoreComponent by lazy {
        DaggerAppCoreComponent.builder()
            .context(applicationContext)
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        appCoreComponent.inject(this)
    }

    override fun androidInjector(): AndroidInjector<Any> {
        return dispatchingActivityInjector
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }
}