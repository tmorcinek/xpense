package com.morcinek.xpense.project

import com.morcinek.xpense.create.Validator
import com.morcinek.xpense.data.project.Project
import com.morcinek.xpense.data.project.ProjectManager

/**
 * Copyright 2016 Tomasz Morcinek. All rights reserved.
 */
class ProjectValidator(private val projectManager: ProjectManager) : Validator<Project> {

    override fun isValid(item: Project): Boolean = item.name.isNotBlank()
            && item.currency.isNotBlank()
            && projectManager.getProjects().none { it.name.equals(item.name, ignoreCase = true) }
}