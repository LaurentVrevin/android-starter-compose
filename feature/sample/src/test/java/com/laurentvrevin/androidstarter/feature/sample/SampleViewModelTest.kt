package com.laurentvrevin.androidstarter.feature.sample

import com.laurentvrevin.androidstarter.core.model.SampleItem
import com.laurentvrevin.androidstarter.data.network.NetworkResult
import com.laurentvrevin.androidstarter.data.repository.SampleRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class SampleViewModelTest {

    private val testDispatcher = StandardTestDispatcher()
    private lateinit var viewModel: SampleViewModel
    private lateinit var fakeRepository: FakeSampleRepository

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        fakeRepository = FakeSampleRepository()
        viewModel = SampleViewModel(fakeRepository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `initial state has items from repository`() = runTest {
        val items = listOf(SampleItem(1, "Title", "Desc"))
        fakeRepository.emit(items)
        
        // Advance until idle to process the flow emission
        advanceUntilIdle()
        
        val state = viewModel.state.value
        assertEquals(items, state.items)
    }

    @Test
    fun `refresh updates refreshing state and handles success`() = runTest {
        // Trigger refresh
        viewModel.refresh()
        
        // Use runCurrent() to allow the launch and the first update to run
        runCurrent()
        
        // Check refreshing state is true
        assertTrue(viewModel.state.value.isRefreshing)
        
        // Finish the refresh call (delay in FakeSampleRepository)
        advanceUntilIdle()
        
        // Check refreshing state is false
        assertTrue(!viewModel.state.value.isRefreshing)
    }
}

class FakeSampleRepository : SampleRepository {
    private val _samples = MutableStateFlow<List<SampleItem>>(emptyList())
    
    override fun getSamples(): Flow<List<SampleItem>> = _samples

    override suspend fun refreshSamples(): NetworkResult<Unit> {
        kotlinx.coroutines.delay(100)
        return NetworkResult.Success(Unit)
    }

    fun emit(items: List<SampleItem>) {
        _samples.value = items
    }
}
