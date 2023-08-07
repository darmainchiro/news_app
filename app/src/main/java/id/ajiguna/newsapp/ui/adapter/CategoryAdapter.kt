package id.ajiguna.newsapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import id.ajiguna.newsapp.R
import id.ajiguna.newsapp.databinding.ItemCategoryBinding
import id.ajiguna.newsapp.source.news.CategoryModel
import java.util.*

class CategoryAdapter(
    val categories: List<CategoryModel>,
    val listener: OnAdapterListener
): RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    private val items = arrayListOf<TextView>()
    class ViewHolder(val  binding: ItemCategoryBinding): RecyclerView.ViewHolder(binding.root)

    interface OnAdapterListener{
        fun onClick(category: CategoryModel)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ItemCategoryBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    )

    override fun onBindViewHolder(holder: CategoryAdapter.ViewHolder, position: Int) {
        val category = categories[position]
        holder.binding.category.text = category.name
        items.add(holder.binding.category)
        holder.itemView.setOnClickListener{
            listener.onClick(category)
            setColor(holder.binding.category)
        }
        setColor(items[0]) //first load
    }

    override fun getItemCount() = categories.size

    private fun setColor(textView: TextView) {
        items.forEach { it.setBackgroundResource(R.color.white) }
        textView.setBackgroundResource(android.R.color.darker_gray)

    }
}