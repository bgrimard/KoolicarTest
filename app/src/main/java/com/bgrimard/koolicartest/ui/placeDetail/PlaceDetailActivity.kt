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

        if (savedInstanceState == null) {
            val fragment = PlaceDetailFragment().apply {
                arguments = Bundle().apply {
                    putString(
                        PlaceDetailFragment.ARG_ITEM_ID,
                        intent.getStringExtra(PlaceDetailFragment.ARG_ITEM_ID)
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
