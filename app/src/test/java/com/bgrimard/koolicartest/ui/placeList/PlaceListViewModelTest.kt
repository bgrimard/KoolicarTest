package com.bgrimard.koolicartest.ui.placeList

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.lifecycle.Observer
import com.bgrimard.koolicartest.RxImmediateSchedulerRule
import com.bgrimard.koolicartest.model.Location
import com.bgrimard.koolicartest.model.Venue
import com.bgrimard.koolicartest.repository.VenuesRepository
import io.reactivex.Observable
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.any
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class PlaceListViewModelTest {

    @Rule @JvmField var testSchedulerRule = RxImmediateSchedulerRule()

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Mock
    lateinit var venuesRepository: VenuesRepository

    @Mock
    lateinit var dataObserver: Observer<List<Venue>>
    @Mock
    lateinit var errorObserver: Observer<String>

    lateinit var venues : List<Venue>

    @Before
    fun setup() {
        venues = listOf(
            Venue(listOf(), false, "1", Location("123", "", "", "", 50, listOf("1,2,3"), listOf(), 40.0, 50.0, ""),"first", "referral"),
            Venue(listOf(), false, "2", Location("123", "", "", "", 50, listOf("1,2,3"), listOf(), 40.0, 50.0, ""),"first", "referral"))
    }

    @Test
    fun loadSuccessData() {
        val placeListViewModel = PlaceListViewModel(venuesRepository)

        `when`(venuesRepository.getVenues()).thenReturn(Observable.just(venues))
        placeListViewModel.getData().observeForever(dataObserver)
        placeListViewModel.getError().observeForever(errorObserver)

        placeListViewModel.loadData()

        verify(dataObserver).onChanged(venues)
        verify(errorObserver, never()).onChanged(any())
    }

    @Test
    fun loadFailureData() {
        val placeListViewModel = PlaceListViewModel(venuesRepository)

        `when`(venuesRepository.getVenues()).thenReturn(Observable.error(RuntimeException("Error")))
        placeListViewModel.getData().observeForever(dataObserver)
        placeListViewModel.getError().observeForever(errorObserver)

        placeListViewModel.loadData()

        verify(dataObserver).onChanged(listOf())
        verify(errorObserver).onChanged("Fail to load data: Error")
    }
}