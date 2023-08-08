package id.ajiguna.newsapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import id.ajiguna.newsapp.R
import id.ajiguna.newsapp.databinding.ItemNewsBinding
import id.ajiguna.newsapp.source.news.ArticleModel
import id.ajiguna.newsapp.utils.DateUtil

class HomeAdapter(
    val articles: ArrayList<ArticleModel>,
    val listener: OnAdapterListener
): RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {

    fun setArticle(article: List<ArticleModel>) {
        if (article == null) return
        this.articles.clear()
        this.articles.addAll(article)
        notifyDataSetChanged()
    }

    interface OnAdapterListener{
        fun onClick(category: ArticleModel)
    }

    inner class HomeViewHolder(private val  binding: ItemNewsBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(article: ArticleModel){
            with(binding){
                title.text = article.title
                publishedAt.text = DateUtil().dateFormat(article.publishedAt)
                Glide.with(itemView.context)
                    .load(article.urlToImage)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_error)
                    )
                    .into(image)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val itemNewsBinding =
            ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeViewHolder(itemNewsBinding)
    }

    override fun getItemCount(): Int = articles.size

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val article = articles[position]
        holder.bind(article)
        holder.itemView.setOnClickListener{
            listener.onClick(article)
        }
    }
}