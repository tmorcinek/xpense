package com.morcinek.xpense.data.project

import com.morcinek.xpense.common.helpers.PreferencesHelper
import com.orm.SugarRecord

/**
 * Copyright 2016 Tomasz Morcinek. All rights reserved.
 */
class ProjectManager(private val preferencesHelper: PreferencesHelper) {

    private val projects: MutableList<Project> = SugarRecord.listAll(Project::class.java)

    var currentProject: Project? = null
        set(value) {
            field = value
            if (value != null) {
                preferencesHelper.putCurrentProjectId(value.id)
            }
        }

    init {
        currentProject = SugarRecord.findById(Project::class.java, preferencesHelper.getCurrentProjectId())
    }

    fun getProjects(): List<Project> = projects

    fun addProject(project: Project) {
        project.save()
        currentProject = project
        projects.add(project)
    }
}