package com.wzh.emptyviewtest02.project

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.tabs.TabLayoutMediator
import com.wzh.core.util.getStatusBarHeight
import com.wzh.core.view.custom.FragmentAdapter
import com.wzh.emptyviewtest02.databinding.FragmentProjectBinding
import com.wzh.emptyviewtest02.project.list.ProjectListFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProjectFragment : BaseTabFragment() {

    private val viewModel by viewModels<ProjectViewModel>()

    private lateinit var adapter: FragmentAdapter
    private var binding: FragmentProjectBinding? = null

    override fun getLayoutView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToRoot: Boolean
    ): View {
        binding = FragmentProjectBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun initView() {
        adapter = FragmentAdapter(requireActivity().supportFragmentManager, lifecycle)
        binding?.apply {
            projectViewPager2.adapter = adapter
            projectTabLayout.addOnTabSelectedListener(this@ProjectFragment)
            TabLayoutMediator(projectTabLayout, projectViewPager2) { tab, position ->
                tab.text = adapter.title(position)
            }.attach()
            projectTabLayout.setPadding(0, context.getStatusBarHeight(), 0, 0)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun initData() {
        startLoading()
        setDataStatus(viewModel.dataLiveData) {
            val nameList = mutableListOf<String>()
            val viewList = mutableListOf<Fragment>()
            it.forEach { project ->
                nameList.add(project.name)
                viewList.add(ProjectListFragment.newInstance(project.id))
            }
            adapter.apply {
                reset(nameList.toTypedArray())
                reset(viewList)
                notifyDataSetChanged()
            }
            binding?.projectViewPager2?.currentItem = viewModel.position
        }
    }

    override fun onTabPageSelected(position: Int) {
        viewModel.position = position
    }

    companion object {
        @JvmStatic
        fun newInstance() = ProjectFragment()
    }

}
