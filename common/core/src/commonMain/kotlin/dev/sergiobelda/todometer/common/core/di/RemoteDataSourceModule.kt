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

package dev.sergiobelda.todometer.common.core.di

import dev.sergiobelda.todometer.common.data.remotedatasource.ITaskListRemoteDataSource
import dev.sergiobelda.todometer.common.data.remotedatasource.ITaskRemoteDataSource
import dev.sergiobelda.todometer.common.data.remotedatasource.TaskListRemoteDataSource
import dev.sergiobelda.todometer.common.data.remotedatasource.TaskRemoteDataSource
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

internal val remoteDataSourceModule = module {
    singleOf(::TaskListRemoteDataSource) bind ITaskListRemoteDataSource::class
    singleOf(::TaskRemoteDataSource) bind ITaskRemoteDataSource::class
}
