package xyz.belvi.zerofinder

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gdsahub.trainer.exts.observe
import com.gdsahub.trainer.exts.withViewModel
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.widget.Autocomplete
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode
import kotlinx.android.synthetic.main.activity_maps.*
import kotlinx.android.synthetic.main.atm_details_bottom_sheet.*
import pub.devrel.easypermissions.EasyPermissions
import xyz.belvi.domain.states.DataResponseError
import xyz.belvi.domain.states.DataStates
import xyz.belvi.domain.states.resultSuccessful
import xyz.belvi.domain.states.searchFailed
import xyz.belvi.zerofinder.adapter.AtmAdapter
import xyz.belvi.zerofinder.vm.MainVM
import xyz.belvi.zerofinder.vm.MainVMFactory
import java.util.*

class MapsActivity : AppCompatActivity(), OnMapReadyCallback, EasyPermissions.PermissionCallbacks {
    val RC_LOCATION = 1
    private lateinit var mMap: GoogleMap
    private lateinit var mainVM: MainVM
    lateinit var atmAdapter: AtmAdapter

    private fun deviceLocationQuery() {
        LocationServices.getFusedLocationProviderClient(this).lastLocation.addOnSuccessListener {
            mainVM.nearByATM(it.latitude, it.longitude)
            moveCamToLocation(it.latitude, it.longitude)
        }
    }

    private fun displayMessage(message: String) {
        AlertDialog.Builder(this)
            .setMessage(message)
            .show()
    }

    private fun addMarker(lat: Double, lng: Double) {
        val marker = mMap.addMarker(
            MarkerOptions().position(LatLng(lat, lng))
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_location_on_black_24dp))
        )
        marker.isDraggable = true

    }


    private fun moveCamToLocation(lat: Double, lng: Double) {
        val center =
            CameraUpdateFactory.newLatLng(LatLng(lat, lng))
        val zoom = CameraUpdateFactory.zoomTo(15F)
        mMap.moveCamera(center)
        mMap.animateCamera(zoom)
    }


    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {

    }

    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        mainVM = withViewModel(MainVMFactory) {
            observe(searchResults, ::showResult)
        }

        search_field.setOnClickListener {
            Places.initialize(this, getString(R.string.google_maps_key), Locale.US)

            val fields = Arrays.asList(Place.Field.ID, Place.Field.NAME, Place.Field.LAT_LNG, Place.Field.ADDRESS)

            val intent = Autocomplete.IntentBuilder(
                AutocompleteActivityMode.FULLSCREEN, fields
            )
                .build(this)
            startActivityForResult(intent, requestCode)
        }

        location_btn.setOnClickListener {
            deviceLocationQuery()
        }
        atmAdapter = AtmAdapter(mutableListOf()){
            val intent = Intent(
                android.content.Intent.ACTION_VIEW,
                Uri.parse("geo:0,0?q=${it.geometry.location.lat},${it.geometry.location.lng}"))
            startActivity(intent)
        }

        atm_recycler.adapter = atmAdapter
        atm_recycler.layoutManager = LinearLayoutManager(this,RecyclerView.VERTICAL,false)
        atm_recycler.addItemDecoration(
            DividerItemDecoration(
                this,
                DividerItemDecoration.VERTICAL
            )
        )
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        EasyPermissions.requestPermissions(
            this, getString(R.string.rationale_location),
            RC_LOCATION, android.Manifest.permission.ACCESS_FINE_LOCATION
        )

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.


    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        deviceLocationQuery()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }

    private fun showResult(dataStates: DataStates?) {
        dataStates?.let {
            when (it) {
                is resultSuccessful -> {
                    atmAdapter.refreshList(it.results)
                    mMap.clear()
                    it.results.forEach {
                        addMarker(it.geometry.location.lat, it.geometry.location.lng)
                    }
                }
                is searchFailed -> displayMessage(it.message)
                is DataResponseError -> displayMessage(it.cause?.message?:"")
            }
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            data?.let {
                val place = Autocomplete.getPlaceFromIntent(data)
                place.latLng?.let {
                    mainVM.nearByATM(it.latitude, it.longitude)
                    moveCamToLocation(it.latitude, it.longitude)
                }

            }


        }
    }
}
