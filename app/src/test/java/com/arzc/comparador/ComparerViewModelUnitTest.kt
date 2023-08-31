package com.arzc.comparador

import org.junit.Test
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Before
import org.junit.After
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.*

@OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
class ComparerViewModelUnitTest {
    private lateinit var viewModel: ComparerViewModel

    @get:Rule
    val instantTaskRule = InstantTaskExecutorRule()
    private val dispatcher = StandardTestDispatcher()

    @Before fun before() {
        Dispatchers.setMain(dispatcher)
        viewModel = ComparerViewModel()
    }

    @After fun after() {
        Dispatchers.resetMain()
    }

    @Test fun comparerViewModel_InitialValue() = runTest {
        assertEquals(true, viewModel.comparer.value?.equal)
    }

    @Test fun comparerViewModel_CompareEquals() = runTest {
        launch {
            viewModel.compareStrings("Foo", "Foo")
        }
        advanceUntilIdle()
        assertEquals(true, viewModel.comparer.value?.equal)
    }
    @Test fun comparerViewModel_CompareNonEquals() = runTest {
        launch {
            viewModel.compareStrings("Bar", "bar")
        }
        advanceUntilIdle()
        assertEquals(false, viewModel.comparer.value?.equal)
    }
}