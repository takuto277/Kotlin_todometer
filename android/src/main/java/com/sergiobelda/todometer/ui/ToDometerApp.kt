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

package com.sergiobelda.todometer.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.sergiobelda.todometer.ui.Destinations.AddProject
import com.sergiobelda.todometer.ui.Destinations.AddTask
import com.sergiobelda.todometer.ui.Destinations.EditTask
import com.sergiobelda.todometer.ui.Destinations.Home
import com.sergiobelda.todometer.ui.Destinations.TaskDetail
import com.sergiobelda.todometer.ui.Destinations.TaskDetailArgs.TaskId
import com.sergiobelda.todometer.ui.addproject.AddProjectScreen
import com.sergiobelda.todometer.ui.addtask.AddTaskScreen
import com.sergiobelda.todometer.ui.edittask.EditTaskScreen
import com.sergiobelda.todometer.ui.home.HomeScreen
import com.sergiobelda.todometer.ui.taskdetail.TaskDetailScreen

@Composable
fun ToDometerApp() {
    val navController = rememberNavController()
    val actions = remember(navController) { Actions(navController) }
    NavHost(navController, startDestination = Home) {
        composable(Home) {
            HomeScreen(actions.addProject, actions.addTask, actions.openTask)
        }
        composable(
            "$TaskDetail/{$TaskId}",
            arguments = listOf(navArgument(TaskId) { type = NavType.StringType })
        ) { navBackStackEntry ->
            TaskDetailScreen(
                taskId = navBackStackEntry.arguments?.getString(TaskId) ?: "",
                actions.editTask,
                actions.navigateUp
            )
        }
        composable(AddProject) {
            AddProjectScreen(actions.navigateUp)
        }
        composable(AddTask) {
            AddTaskScreen(actions.navigateUp)
        }
        composable(
            "$EditTask/{$TaskId}",
            arguments = listOf(navArgument(TaskId) { type = NavType.StringType })
        ) { backStackEntry ->
            EditTaskScreen(
                taskId = backStackEntry.arguments?.getString(TaskId) ?: "",
                actions.navigateUp
            )
        }
    }
}
