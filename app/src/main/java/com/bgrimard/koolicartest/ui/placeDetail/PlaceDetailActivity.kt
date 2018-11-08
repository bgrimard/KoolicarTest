package com.bgrimard.koolicartest.ui.placeDetail

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import com.bgrimard.koolicartest.R
import com.bgrimard.koolicartest.ui.placeList.PlaceListActivity
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_place_detail.*

/**
 * An activity representing a single Place detail screen. This
 * activity is only used on narrow width devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a [PlaceListActivity].
 */
class PlaceDetailActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_place_detail)
        setSupportActionBar(detail_toolbar)

        // Show the Up button in the action bar.
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // savedInstanceState is non-null when there is fragment state
        // saved from previous configurations of this activity
        // (e.g. when rotating the screen from portrait to landscape).
        // In this case, the fragment will automatically be re-added
        // to its container so we don't need to manually add it.
        // For more information, see the Fragments API guide at:
        //
        // http://developer.android.com/guide/components/fragments.html
        //
        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.
            val fragment = PlaceDetailFragment().apply {
                arguments = Bundle().apply {
                    putString(
                        PlaceDetailFragment.ARG_ITEM,
                        intent.getStringExtra(PlaceDetailFragment.ARG_ITEM)
                    )
                }
            }

            supportFragmentManager.beginTransaction()
                .add(R.id.place_detail_container, fragment)
                .commit()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem) =
        when (item.itemId) {
            android.R.id.home -> {

                navigateUpTo(Intent(this, PlaceListActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
}
