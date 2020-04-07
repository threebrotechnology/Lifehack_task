package com.lifeindarkness.lifehacktask.base

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.lifeindarkness.lifehacktask.AppCore
import com.lifeindarkness.lifehacktask.db.AppDatabase
import com.lifeindarkness.lifehacktask.utils.ApiModule
import com.lifeindarkness.lifehacktask.utils.ResourceUtil
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import java.lang.Exception
import javax.inject.Inject

abstract class BaseActivity(
    private val layoutId: Int
) : AppCompatActivity(), HasAndroidInjector, BaseActivityLifecycle {

    @Inject
    lateinit var dispatchingActivityInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<Any> {
        return dispatchingActivityInjector
    }

    @Inject
    lateinit var apiModule: ApiModule

    @Inject
    lateinit var resourceUtil: ResourceUtil

    override fun onCreate(savedInstanceState: Bundle?) {
        (applicationContext as AppCore).appCoreComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(layoutId)

        onCreate()
    }
}