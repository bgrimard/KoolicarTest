package com.bgrimard.koolicartest.ui.placeDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bgrimard.koolicartest.R
import com.bgrimard.koolicartest.model.Venue
import com.google.gson.Gson
import dagger.android.support.DaggerFragment
import io.reactivex.Observable
import kotlinx.android.synthetic.main.place_detail.view.*

class PlaceDetailFragment : DaggerFragment() {

    private var item: Venue? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            if (it.containsKey(ARG_ITEM)) {
                // Load the dummy content specified by the fragment
                // arguments. In a real-world scenario, use a Loader
                // to load content from a content provider.
                //item = DummyContent.ITEM_MAP[it.getString(ARG_ITEM_ID)]
                val json = it.getString(ARG_ITEM)
                item = Gson().fromJson(json, Venue::class.java)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.place_detail, container, false)

        // Show the dummy content as text in a TextView.
        item?.let {
            rootView.place_title.text = it.name
            rootView.place_address.text = it.location.formattedAddress.joinToString("\n", "", "", -1)

            Observable
                .fromIterable(it.categories)
                .map { category -> category.name }
                .toList()
                .subscribe { categories -> rootView.place_categories.text = categories.joinToString(",", "", "", -1)}

        }

        return rootView
    }

    companion object {
        /**
         * The fragment argument representing the item ID that this fragment
         * represents.
         */
        const val ARG_ITEM = "item"
    }
}
