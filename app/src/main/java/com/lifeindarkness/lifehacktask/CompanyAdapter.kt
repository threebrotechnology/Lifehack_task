package com.lifeindarkness.lifehacktask

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lifeindarkness.lifehacktask.data.Company
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.company_row.view.*

class CompanyAdapter(
    context: Context,
    var items: List<Company>,
    var onClickItem: (index: Int) -> Unit
) : RecyclerView.Adapter<CompanyAdapter.ViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = inflater.inflate(R.layout.company_row, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.view.companyContainer.setOnClickListener {
            onClickItem(position)
        }
        holder.view.tvName.text = item.name
        Picasso.get()
            .load(Constants.baseUrl + item.img)
            .into(holder.view.ivIcon)
    }

    fun updateItems(items: List<Company>) {
        this.items = items
        notifyDataSetChanged()
    }
}