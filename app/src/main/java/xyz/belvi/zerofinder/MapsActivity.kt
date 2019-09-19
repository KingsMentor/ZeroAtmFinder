package xyz.belvi.zerofinder

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.gdsahub.trainer.exts.observe
import com.gdsahub.trainer.exts.withViewModel
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import pub.devrel.easypermissions.EasyPermissions
import xyz.belvi.domain.states.DataStates
import xyz.belvi.domain.states.resultSuccessful
import xyz.belvi.zerofinder.vm.MainVM
import xyz.belvi.zerofinder.vm.MainVMFactory

class MapsActivity : AppCompatActivity(), OnMapReadyCallback, EasyPermissions.PermissionCallbacks {
    val RC_LOCATION = 1
    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {

    }

    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        mainVM = withViewModel(MainVMFactory) {
            observe(searchResults, ::showResult)
        }
    }

    private fun addMarker(lat: Double, lng: Double) {
        val marker = mMap.addMarker(
            MarkerOptions().position(LatLng(lat, lng))
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_location_on_black_24dp))
        )
        marker.isDraggable = true

    }

    private lateinit var mMap: GoogleMap
    private lateinit var mainVM: MainVM

    private fun moveCamToLocation(lat: Double, lng: Double) {
        val center =
            CameraUpdateFactory.newLatLng(LatLng(lat, lng))
        val zoom = CameraUpdateFactory.zoomTo(15F)
        mMap.moveCamera(center)
        mMap.animateCamera(zoom)
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

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        LocationServices.getFusedLocationProviderClient(this).lastLocation.addOnSuccessListener {
            mainVM.nearByATM(it.latitude, it.longitude)
            mMap.isMyLocationEnabled = true
            moveCamToLocation(it.latitude, it.longitude)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }

    private fun showResult(dataStates: DataStates?) {
        dataStates?.let {
            if (it is resultSuccessful) {
                mMap.clear()
                it.results.forEach {
                    addMarker(it.geometry.location.lat, it.geometry.location.lng)
                }
            }
        }
    }
}
