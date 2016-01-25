package com.morcinek.xpense.data.project

import java.util.*

/**
 * Copyright 2016 Tomasz Morcinek. All rights reserved.
 */
class ProjectManager {

    private val projects: MutableList<Project> = ArrayList()

    fun getCurrentProject(): Project? {
        return null;
    }

    fun getProjects(): List<Project> = projects

    fun addProject(project: Project) {
        projects.add(project)
    }
}