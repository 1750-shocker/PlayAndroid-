package com.wzh.emptyviewtest02.base

import android.content.res.Configuration
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.wzh.emptyviewtest02.article.ArticleAdapter
import com.wzh.emptyviewtest02.databinding.FragmentBaseListBinding
import com.wzh.emptyviewtest02.home.ArticleCollectBaseFragment

/**
 * 版权：Zhujiang 个人版权
 *
 * @author zhujiang
 * 创建日期：2020/10/20
 * 描述：PlayAndroid
 *
 */
abstract class BaseListFragment : ArticleCollectBaseFragment() {

    protected var binding: FragmentBaseListBinding? = null

    protected lateinit var articleAdapter: ArticleAdapter
    protected var page = 1

    override fun getLayoutView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToRoot: Boolean
    ): View {
        binding = FragmentBaseListBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun initView() {
        binding?.apply {
            baseFragmentToTop.setRecyclerViewLayoutManager(resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT)
            baseFragmentToTop.setAdapter(articleAdapter)
            baseFragmentToTop.onRefreshListener({
                page = 1
                refreshData()
            }, {
                page++
                refreshData()
            })
        }
    }

}
