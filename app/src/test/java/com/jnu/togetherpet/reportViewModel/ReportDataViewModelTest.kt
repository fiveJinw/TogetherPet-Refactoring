package com.jnu.togetherpet.reportViewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.jnu.searching.enums.ButtonType
import com.jnu.searching.ReportDataViewModel
import io.mockk.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue

/*초기값 확인*/
@OptIn(ExperimentalCoroutinesApi::class)
class ReportDataViewModelTest {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: ReportDataViewModel
    private val mockReportRepository: com.jnu.data.repo.ReportRepository = mockk(relaxed = true)
    private val mockMissingRepository: com.jnu.data.repo.MissingRepository = mockk(relaxed = true)

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        viewModel = ReportDataViewModel(mockReportRepository, mockMissingRepository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `selectedBtn 초기값 확인`() = runTest {
        assertEquals(com.jnu.searching.enums.ButtonType.MISSING, viewModel.selectedButton.value)
    }

    @Test
    fun `MISSING 버튼 타입 업데이트 확인`() = runTest {
        viewModel.updateSelectedBtn(com.jnu.searching.enums.ButtonType.MISSING)
        assertEquals(com.jnu.searching.enums.ButtonType.MISSING, viewModel.selectedButton.value)
    }

    @Test
    fun `REPORT 버튼 타입 업데이트 확인`() = runTest {
        viewModel.updateSelectedBtn(com.jnu.searching.enums.ButtonType.REPORT)
        assertEquals(com.jnu.searching.enums.ButtonType.REPORT, viewModel.selectedButton.value)
    }

    @Test
    fun `MuPet 버튼 타입 업데이트 확인`() = runTest {
        viewModel.updateSelectedBtn(com.jnu.searching.enums.ButtonType.MyPET)
        assertEquals(com.jnu.searching.enums.ButtonType.MyPET, viewModel.selectedButton.value)
    }

    @Test
    fun `StateFlow 초기값 확인`() = runTest {
        assertTrue(viewModel.missingReports.first().isEmpty())
        assertTrue(viewModel.myPetReports.first().isEmpty())
        assertTrue(viewModel.nearbySuspectedReports.first().isEmpty())

        assertNull(viewModel.missingDetail.first())
        assertNull(viewModel.suspectedDetail.first())
    }
}