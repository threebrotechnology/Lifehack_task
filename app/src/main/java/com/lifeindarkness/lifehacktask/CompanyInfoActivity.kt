package com.lifeindarkness.lifehacktask

import android.content.Intent
import android.net.Uri
import android.view.View
import androidx.databinding.DataBindingUtil
import com.lifeindarkness.lifehacktask.base.BaseActivity
import com.lifeindarkness.lifehacktask.databinding.ActivityCompanyInfoBinding
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_company_info.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.jetbrains.anko.toast

class CompanyInfoActivity : BaseActivity(R.layout.activity_company_info) {

    lateinit var binding: ActivityCompanyInfoBinding

    override fun onCreate() {
        binding = DataBindingUtil.setContentView(
            this, R.layout.activity_company_info
        )
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }

        val id = intent.getIntExtra("id", -1)
        if (id == -1) {
            toast("Some error")
        } else {
            GlobalScope.launch {
                val item = resourceUtil.getCompanyInfo(id)
                if (item != null) {
                    runOnUiThread {
                        collapsingToolbar.title = item.name
                        title = item.name
                        Picasso.get()
                            .load(Constants.baseUrl + item.img)
                            .into(ivIcon)
                    }
                    binding.info = item
                    if (item.phone.isNullOrEmpty()) {
                        lPhone.post {
                            lPhone.visibility = View.GONE
                        }
                    }
                    ivSite.post {
                        if (item.site.isNullOrEmpty()) {
                            ivSite.visibility = View.GONE
                        } else {
                            ivSite.setOnClickListener {
                                val url = if (item.site?.startsWith("http://") == false
                                    && item.site?.startsWith("https://") == false
                                ) {
                                    "http://" + item.site;
                                } else {
                                    item.site
                                }
                                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                                val chooser = Intent.createChooser(browserIntent, "Open with")
                                startActivity(chooser)
                            }
                        }
                    }
                } else {
                    runOnUiThread {
                        toast("Error while loading information")
                    }
                }
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}