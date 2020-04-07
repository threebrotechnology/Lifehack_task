package com.lifeindarkness.lifehacktask

import android.content.Context
import com.lifeindarkness.lifehacktask.base.BaseActivity
import com.lifeindarkness.lifehacktask.utils.ApiModule
import com.lifeindarkness.lifehacktask.utils.ResourceUtil
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidInjectionModule::class, ApiModule::class, ResourceUtil::class])
interface AppCoreComponent {

    fun inject(baseActivity: BaseActivity)

    fun inject(appCore: AppCore)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun context(context: Context): Builder

        fun build(): AppCoreComponent
    }
}