package id.ajiguna.newsapp.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import id.ajiguna.newsapp.R
import id.ajiguna.newsapp.databinding.CustomToolbarBinding
import id.ajiguna.newsapp.databinding.FragmentHomeBinding
import id.ajiguna.newsapp.source.news.ArticleModel
import id.ajiguna.newsapp.source.news.CategoryModel
import id.ajiguna.newsapp.ui.adapter.CategoryAdapter
import id.ajiguna.newsapp.ui.adapter.HomeAdapter
import id.ajiguna.newsapp.ui.detail.DetailActivity
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.dsl.module

val homeModule = module {
    factory { HomeFragment() }
}
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var bindingToolbar: CustomToolbarBinding
    private val viewModel: HomeViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =  FragmentHomeBinding.inflate(inflater, container, false)
        bindingToolbar = binding.toolbar
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        bindingToolbar.textTitle.text = viewModel.title
        bindingToolbar.container.inflateMenu(R.menu.menu_search)
        val menu = binding.toolbar.container.menu
        val search = menu.findItem(R.id.action_search)
        val searchView = search.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                firstLoad()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let { viewModel.query = it }
                return true
            }
        })

        binding.listCategory.adapter = categoryAdapter
        binding.listNews.adapter = homeAdapter
        viewModel.category.observe(viewLifecycleOwner) {
            firstLoad()
        }

        viewModel.news.observe(viewLifecycleOwner) {
            if (viewModel.page == 1)
            homeAdapter.setArticle(it.articles)
        }

        viewModel.message.observe(viewLifecycleOwner) {
            it?.let {
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
            }
        }

        binding.scroll.setOnScrollChangeListener {
                v: NestedScrollView, _, scrollY, _, _ ->
            if (scrollY == v?.getChildAt(0)!!.measuredHeight - v?.measuredHeight as Int){
                if (viewModel.page < viewModel.total && viewModel.loadMore.value == false){
                    viewModel.loadMore
                    binding.progressBottom.visibility = View.GONE
                } else {
                    binding.progressBottom.visibility = View.VISIBLE
                }
            }
        }
    }
    private fun firstLoad(){
        binding.scroll.scrollTo(0,0)
        viewModel.page = 1
        viewModel.total = 1
        viewModel.fetch()
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

    private val categoryAdapter by lazy {
        CategoryAdapter(viewModel.categories, object : CategoryAdapter.OnAdapterListener {
            override fun onClick(category: CategoryModel) {
                viewModel.category.postValue(category.id)
            }

        })
    }

}