package android.sportsnews.ui.fragments

import android.sportsnews.data.model.Article
import android.sportsnews.databinding.ItemAuthorBinding
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

//class TopHeadlinesAdapter : RecyclerView.Adapter<TopHeadlinesAdapter>(DiffCallback()) {

class TopHeadlinesAdapter : RecyclerView.Adapter<TopHeadlinesAdapter.ArticleViewHolder>() {

    inner class ArticleViewHolder(val binding: ItemAuthorBinding) : RecyclerView.ViewHolder(binding.root)

    private val differCallback = object : DiffUtil.ItemCallback<Article>() {

        override fun areItemsTheSame(oldItem: Article, newItem: Article) = oldItem.url == newItem.url

        override fun areContentsTheSame(oldItem: Article, newItem: Article) = oldItem == newItem
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val binding = ItemAuthorBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return ArticleViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = differ.currentList[position]

        holder.binding.apply {
            Picasso.get()
                .load(article.urlToImage)
                .into(ivImageArticle)

            tvSource.text = article.source.name
            tvPublishAt.text = article.publishedAt
            tvTitle.text = article.title
            tvDescription.text = article.description

        }
    }

    override fun getItemCount() = differ.currentList.size
}