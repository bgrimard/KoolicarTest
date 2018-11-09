package com.bgrimard.koolicartest.ui.placeDetail

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.bgrimard.koolicartest.R
import com.bgrimard.koolicartest.model.VenuesDetailVenue
import com.bgrimard.koolicartest.util.nonNull
import com.bgrimard.koolicartest.util.observe
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.place_detail.*
import javax.inject.Inject

class PlaceDetailFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: PlaceDetailViewModelFactory
    private lateinit var placeDetailViewModel: PlaceDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            if (it.containsKey(ARG_ITEM_ID)) {
                // Load the dummy content specified by the fragment
                // arguments. In a real-world scenario, use a Loader
                // to load content from a content provider.
                //item = DummyContent.ITEM_MAP[it.getString(ARG_ITEM_ID)]
                val itemId = it.getString(ARG_ITEM_ID)

                if(itemId != null) {
                    placeDetailViewModel =
                            ViewModelProviders.of(this, viewModelFactory).get(PlaceDetailViewModel::class.java)
                    placeDetailViewModel.loadData(itemId)

                    placeDetailViewModel.getLoading()
                        .nonNull()
                        .observe(this) { loading -> if (loading) venue_detail_loading.show() else venue_detail_loading.hide() }
                    placeDetailViewModel.getError()
                        .nonNull()
                        .observe(this) { error -> Toast.makeText(context, error, Toast.LENGTH_SHORT).show() }
                    placeDetailViewModel.getData()
                        .nonNull()
                        .observe(this) { venue ->
                            displayVenue(venue)
                        }
                }
            }
        }
    }

    private fun displayVenue(venue: VenuesDetailVenue) {
        place_title.text = venue.name
        place_address.text = venue.location.formattedAddress.joinToString("\n", "", "", -1)
        place_categories.text = venue.categories.firstOrNull()?.name ?: "No category"
        place_price.text = venue.price?.message ?: "Unspecified"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.place_detail, container, false)
    }

    companion object {
        /**
         * The fragment argument representing the item ID that this fragment
         * represents.
         */
        const val ARG_ITEM_ID = "item_id"
    }
}
