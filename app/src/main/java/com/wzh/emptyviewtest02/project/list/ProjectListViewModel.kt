package com.wzh.emptyviewtest02.project.list

import androidx.lifecycle.LiveData
import com.wzh.core.view.base.BaseAndroidViewModel
import com.wzh.model.pojo.QueryArticle
import com.wzh.model.room.entity.Article
import com.wzh.emptyviewtest02.project.ProjectRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * 版权：Zhujiang 个人版权
 * @author zhujiang
 * 版本：1.5
 * 创建日期：2020/5/18
 * 描述：PlayAndroid
 *
 */
@HiltViewModel
class ProjectListViewModel @Inject constructor(
    private val projectRepository: ProjectRepository
) : BaseAndroidViewModel<List<Article>, Article, QueryArticle>() {

    override fun getData(page: QueryArticle): LiveData<Result<List<Article>>> {
        return projectRepository.getProject(page)
    }

}