package com.nitin.cleanarch.feature_one.utils

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher

class TestDispatcherProvider @OptIn(ExperimentalCoroutinesApi::class) constructor(
    val testDispatcher: TestDispatcher = UnconfinedTestDispatcher()
) : DispatcherProvider {
    @OptIn(ExperimentalCoroutinesApi::class)
    override val io: CoroutineDispatcher
        get() = testDispatcher
    override val main: CoroutineDispatcher
        get() = testDispatcher
    override val default: CoroutineDispatcher
        get() = testDispatcher
}