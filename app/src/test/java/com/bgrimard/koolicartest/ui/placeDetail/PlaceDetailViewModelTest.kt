package com.bgrimard.koolicartest.ui.placeDetail

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.lifecycle.Observer
import com.bgrimard.koolicartest.RxImmediateSchedulerRule
import com.bgrimard.koolicartest.model.VenuesDetailLocation
import com.bgrimard.koolicartest.model.VenuesDetailPrice
import com.bgrimard.koolicartest.model.VenuesDetailVenue
import com.bgrimard.koolicartest.repository.VenueDetailRepository
import io.reactivex.Observable
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers
import org.mockito.ArgumentMatchers.any
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.never
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class PlaceDetailViewModelTest {
    @Rule
    @JvmField var testSchedulerRule = RxImmediateSchedulerRule()

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Mock
    lateinit var venueDetailRepository: VenueDetailRepository

    @Mock
    lateinit var dataObserver: Observer<VenuesDetailVenue>
    @Mock
    lateinit var errorObserver: Observer<String>

    lateinit var venue : VenuesDetailVenue

    @Before
    fun setup() {
        venue = VenuesDetailVenue(listOf(),
            "id1",
            VenuesDetailLocation("", "ca", listOf("address"), listOf(),45.0, -50.0, "qc"),
            "name",
            VenuesDetailPrice("$", "message", 1)
        )
    }

    @Test
    fun loadSuccessData() {
        val placeListViewModel = PlaceDetailViewModel(venueDetailRepository)

        Mockito.`when`(venueDetailRepository.getVenueDetail("1")).thenReturn(Observable.just(venue))
        placeListViewModel.getData().observeForever(dataObserver)
        placeListViewModel.getError().observeForever(errorObserver)

        placeListViewModel.loadData("1")

        Mockito.verify(dataObserver).onChanged(venue)
        Mockito.verify(errorObserver, Mockito.never()).onChanged(ArgumentMatchers.any())
    }

    @Test
    fun loadFailureData() {
        val placeListViewModel = PlaceDetailViewModel(venueDetailRepository)

        Mockito.`when`(venueDetailRepository.getVenueDetail(anyString())).thenReturn(Observable.error(RuntimeException("Error")))
        placeListViewModel.getData().observeForever(dataObserver)
        placeListViewModel.getError().observeForever(errorObserver)

        placeListViewModel.loadData("52")

        Mockito.verify(dataObserver, never()).onChanged(any())
        Mockito.verify(errorObserver).onChanged("Fail to load data: Error")
    }
}