package com.lifeindarkness.lifehacktask

import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import com.lifeindarkness.lifehacktask.base.BaseActivity
import com.lifeindarkness.lifehacktask.data.Company
import com.lifeindarkness.lifehacktask.db.AppDatabase
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.jetbrains.anko.startActivity


class MainActivity : BaseActivity(R.layout.activity_main) {

    private lateinit var companyAdapter: CompanyAdapter

    override fun onCreate() {
        Picasso.get()
            .setIndicatorsEnabled(BuildConfig.DEBUG)

        companyAdapter = CompanyAdapter(this, emptyList()) {
            val itemId = companyAdapter.items[it].id
            startActivity<CompanyInfoActivity>("id" to itemId)
        }
        rvItems.layoutManager = GridLayoutManager(this, 2)
        rvItems.adapter = companyAdapter
        GlobalScope.launch {
            val items = apiModule.apiService().companyList()
            GlobalScope.launch {
                updateItemsData(items)
            }
            runOnUiThread {
                companyAdapter.updateItems(items)
            }
        }
    }

    private suspend fun updateItemsData(items: List<Company>) {
        withContext(Dispatchers.IO) {
            val appDatabase = AppDatabase.getInstance(applicationContext)
            items.forEach {
                try {
                    apiModule.apiService().companyInfo(it.id).firstOrNull()?.let {
                        appDatabase.companyDAO().insert(it)
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                    Log.e("Error", e.message ?: "")
                }
            }
        }
    }
}