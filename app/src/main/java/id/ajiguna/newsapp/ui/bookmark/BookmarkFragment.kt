package id.ajiguna.newsapp.ui.bookmark

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import id.ajiguna.newsapp.databinding.CustomToolbarBinding
import id.ajiguna.newsapp.databinding.FragmentBookmarkBinding
import id.ajiguna.newsapp.source.news.ArticleModel
import id.ajiguna.newsapp.ui.adapter.HomeAdapter
import id.ajiguna.newsapp.ui.detail.DetailActivity
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.dsl.module

val bookmarkModule = module {
    factory { BookmarkFragment() }
}
class BookmarkFragment : Fragment() {

    private lateinit var binding: FragmentBookmarkBinding
    private lateinit var bindingToolbar: CustomToolbarBinding
    private val viewModel: BookmarkViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =  FragmentBookmarkBinding.inflate(inflater, container, false)
        bindingToolbar = binding.toolbar
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        bindingToolbar.title = viewModel.title

        binding.listBookmark.adapter = homeAdapter
        viewModel.articles.observe(viewLifecycleOwner) {
            homeAdapter.setArticle(it)

        }
    }

    private val homeAdapter by lazy {
        HomeAdapter(arrayListOf(), object : HomeAdapter.OnAdapterListener {
            override fun onClick(articleModel: ArticleModel) {
                startActivity(
                    Intent(requireActivity(), DetailActivity::class.java)
                        .putExtra("detail", articleModel)
                )
            }
        })
    }
}