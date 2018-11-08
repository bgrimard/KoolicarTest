package com.bgrimard.koolicartest.ui.placeList

import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.bgrimard.koolicartest.R
import com.bgrimard.koolicartest.model.Venue
import com.bgrimard.koolicartest.ui.placeDetail.PlaceDetailActivity
import com.bgrimard.koolicartest.ui.placeDetail.PlaceDetailFragment
import com.bgrimard.koolicartest.util.nonNull
import com.bgrimard.koolicartest.util.observe
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_place_list.*
import kotlinx.android.synthetic.main.place_list.*
import kotlinx.android.synthetic.main.place_list_content.view.*
import javax.inject.Inject

/**
 * An activity representing a list of Pings. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a [PlaceDetailActivity] representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
class PlaceListActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var placeListViewModelFactory: PlaceListViewModelFactory
    private lateinit var placeListViewModel: PlaceListViewModel
    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private var twoPane: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_place_list)

        setSupportActionBar(toolbar)
        toolbar.title = title

        if (place_detail_container != null) {
            twoPane = true
        }

        placeListViewModel = ViewModelProviders.of(this, placeListViewModelFactory).get(PlaceListViewModel::class.java)
        placeListViewModel.loadData()

        placeListViewModel.getLoading()
            .nonNull()
            .observe(this) { loading -> if (loading) venueListLoading.show() else venueListLoading.hide() }
        placeListViewModel.getError()
            .nonNull()
            .observe(this) { error -> Toast.makeText(this, error, Toast.LENGTH_SHORT).show() }
        placeListViewModel.getData()
            .nonNull()
            .observe(this) { venues -> place_list.adapter = SimpleItemRecyclerViewAdapter(this, venues, twoPane) }
    }


    class SimpleItemRecyclerViewAdapter(
        private val parentActivity: PlaceListActivity,
        private val values: List<Venue>,
        private val twoPane: Boolean
    ) :
        RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder>() {

        private val onClickListener: View.OnClickListener

        init {
            onClickListener = View.OnClickListener { v ->
                val item = v.tag as Venue
                if (twoPane) {
                    val fragment = PlaceDetailFragment().apply {
                        arguments = Bundle().apply {
                            putString(PlaceDetailFragment.ARG_ITEM, item.toJSON())
                        }
                    }
                    parentActivity.supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.place_detail_container, fragment)
                        .commit()
                } else {
                    val intent = Intent(v.context, PlaceDetailActivity::class.java).apply {
                        putExtra(PlaceDetailFragment.ARG_ITEM, item.toJSON())
                    }
                    v.context.startActivity(intent)
                }
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.place_list_content, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val item = values[position]
            holder.placeName.text = item.name
            holder.placeAddress.text = item.location.formattedAddress.joinToString("\n", "", "", -1)
            holder.placeDistance.text = item.location.distance.toString()

            with(holder.itemView) {
                tag = item
                setOnClickListener(onClickListener)
            }
        }

        override fun getItemCount() = values.size

        inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val placeName: TextView = view.place_name
            val placeAddress: TextView = view.place_address
            val placeDistance: TextView = view.place_distance
        }
    }
}
