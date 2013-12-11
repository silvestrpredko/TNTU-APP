package ua.edu.tntu;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.Menu;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapActivity extends FragmentActivity {

    public static final LatLng TNTU = new LatLng(49.551126, 25.601095);

    private GoogleMap map;

    final int RQS_GooglePlayServices = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.tntuMap);
        SupportMapFragment supportMapFragment = (SupportMapFragment) fragment;

        map = supportMapFragment.getMap();
    }

    @Override
    protected void onResume() {
        super.onResume();

        int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getApplicationContext());

        if (resultCode == ConnectionResult.SUCCESS) {
            setupMap();
        } else {
            GooglePlayServicesUtil.getErrorDialog(resultCode, this, RQS_GooglePlayServices);
        }

    }

    private void setupMap() {

        map.addMarker(new MarkerOptions().position(TNTU).title("ТНТУ, корпус №1")
                .snippet("Кафедра ПІ - перший поверх, аудиторія 104"));
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(TNTU, 15));
        map.animateCamera(CameraUpdateFactory.zoomTo(18), 2000, null);
        map.setMyLocationEnabled(true);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}
