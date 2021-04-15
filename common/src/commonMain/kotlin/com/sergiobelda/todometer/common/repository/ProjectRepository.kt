/*
 * Copyright 2021 Sergio Belda
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.sergiobelda.todometer.common.repository

import com.sergiobelda.todometer.common.database.dao.IProjectDao
import com.sergiobelda.todometer.common.database.mapper.ProjectMapper.toDomain
import com.sergiobelda.todometer.common.database.mapper.ProjectMapper.toEntity
import com.sergiobelda.todometer.common.model.Project
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ProjectRepository(
    private val projectDao: IProjectDao
) : IProjectRepository {

    override fun getProject(id: Long): Flow<Project?> =
        projectDao.getProject(id).map { it?.toDomain() }

    override fun getProjects(): Flow<List<Project>> =
        projectDao.getProjects().map { list ->
            list.map { it.toDomain() }
        }

    override suspend fun insertProject(project: Project) =
        projectDao.insertProject(project.toEntity())
}
