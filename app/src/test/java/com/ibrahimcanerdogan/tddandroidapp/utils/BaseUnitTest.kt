package com.ibrahimcanerdogan.tddandroidapp.utils

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.ibrahimcanerdogan.tddandroidapp.utils.MainCoroutineScopeRule
import org.junit.Rule

open class BaseUnitTest {
    @get:Rule
    var coroutinesTestRule = MainCoroutineScopeRule()

    @get: Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()
}