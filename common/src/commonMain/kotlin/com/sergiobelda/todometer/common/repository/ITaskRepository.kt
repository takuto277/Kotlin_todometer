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

import com.sergiobelda.todometer.common.datasource.Result
import com.sergiobelda.todometer.common.model.Task
import com.sergiobelda.todometer.common.model.TaskState
import com.sergiobelda.todometer.common.model.TaskTag
import kotlinx.coroutines.flow.Flow

interface ITaskRepository {

    fun getTask(id: String): Flow<Result<TaskTag?>>

    fun getTasks(): Flow<Result<List<TaskTag>>>

    suspend fun refreshTasksByProjectId(id: String)

    suspend fun insertTask(title: String, description: String?, projectId: String, tagId: String?)

    suspend fun updateTask(task: Task)

    suspend fun updateTaskState(id: String, state: TaskState)

    suspend fun deleteTask(id: String)
}