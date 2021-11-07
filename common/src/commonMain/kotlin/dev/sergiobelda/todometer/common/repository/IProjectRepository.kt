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

package dev.sergiobelda.todometer.common.repository

import dev.sergiobelda.todometer.common.data.Result
import dev.sergiobelda.todometer.common.model.Project
import kotlinx.coroutines.flow.Flow

interface IProjectRepository {

    /**
     * Get a project given its [id].
     */
    fun getProject(id: String): Flow<Result<Project>>

    /**
     * Get the list of all [Project].
     */
    fun getProjects(): Flow<Result<List<Project>>>

    suspend fun refreshProject(id: String)

    /**
     * Inserts a [Project] given a [name].
     */
    suspend fun insertProject(name: String): Result<String>

    /**
     * Updates a [Project] given a [project] object.
     */
    suspend fun updateProject(project: Project)

    suspend fun refreshProjects()

    /**
     * Deletes a [Project] given its [id].
     */
    suspend fun deleteProject(id: String)
    // suspend fun deleteProject(id: String): Result<String>
}