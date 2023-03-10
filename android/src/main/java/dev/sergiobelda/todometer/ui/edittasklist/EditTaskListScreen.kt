/*
 * Copyright 2022 Sergio Belda
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

package dev.sergiobelda.todometer.ui.edittasklist

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import dev.sergiobelda.todometer.R
import dev.sergiobelda.todometer.common.compose.ui.designsystem.components.TitledTextField
import dev.sergiobelda.todometer.common.compose.ui.designsystem.theme.ToDometerTheme
import dev.sergiobelda.todometer.glance.ToDometerWidgetReceiver
import dev.sergiobelda.todometer.ui.components.ToDometerContentLoadingProgress
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun EditTaskListScreen(
    navigateBack: () -> Unit,
    editTaskListViewModel: EditTaskListViewModel = getViewModel()
) {
    var taskListName by rememberSaveable { mutableStateOf("") }
    var taskListNameInputError by remember { mutableStateOf(false) }

    val editTaskListUiState = editTaskListViewModel.editTaskListUiState
    editTaskListUiState.taskList?.let { taskList ->
        taskListName = taskList.name
    }
    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = navigateBack) {
                        Icon(
                            Icons.Rounded.ArrowBack,
                            contentDescription = "Back",
                            tint = ToDometerTheme.toDometerColors.onSurfaceMediumEmphasis
                        )
                    }
                },
                title = { Text(stringResource(id = R.string.edit_task_list)) },
                actions = {
                    if (!editTaskListUiState.isLoading && editTaskListUiState.taskList != null) {
                        IconButton(
                            onClick = {
                                if (taskListName.isBlank()) {
                                    taskListNameInputError = true
                                } else {
                                    editTaskListViewModel.updateTaskList(
                                        taskListName
                                    )
                                    ToDometerWidgetReceiver().updateData()
                                    navigateBack()
                                }
                            }
                        ) {
                            Icon(
                                Icons.Rounded.Check,
                                contentDescription = "Save",
                                tint = MaterialTheme.colorScheme.primary
                            )
                        }
                    }
                }
            )
        },
        content = { paddingValues ->
            if (editTaskListUiState.isLoading) {
                ToDometerContentLoadingProgress()
            } else {
                Column(modifier = Modifier.padding(paddingValues)) {
                    TitledTextField(
                        title = stringResource(id = R.string.name),
                        value = taskListName,
                        onValueChange = {
                            taskListName = it
                            taskListNameInputError = false
                        },
                        placeholder = { Text(stringResource(id = R.string.enter_task_list_name)) },
                        singleLine = true,
                        isError = taskListNameInputError,
                        errorMessage = stringResource(id = R.string.field_not_empty),
                        keyboardOptions = KeyboardOptions(
                            capitalization = KeyboardCapitalization.Sentences,
                            imeAction = ImeAction.Done
                        ),
                        modifier = Modifier.padding(
                            start = 16.dp,
                            end = 16.dp,
                            top = 8.dp,
                            bottom = 8.dp
                        )
                    )
                }
            }
        }
    )
}
