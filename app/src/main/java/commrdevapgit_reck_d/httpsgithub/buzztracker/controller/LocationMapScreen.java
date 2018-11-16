package commrdevapgit_reck_d.httpsgithub.buzztracker.controller;

import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

import commrdevapgit_reck_d.httpsgithub.buzztracker.R;

/**
 * The type Location map screen.
 */
public class LocationMapScreen extends AppCompatActivity implements OnMapReadyCallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_map_screen);

        FragmentManager mFragment = getSupportFragmentManager();
        SupportMapFragment mapFragment = (SupportMapFragment) mFragment.findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }
    }

    @Override
    public void onMapReady(final GoogleMap map) {
        FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
        DatabaseReference mReference1 = mDatabase.getReference();
        DatabaseReference mReference = mReference1.child("Location");

        mReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot l: dataSnapshot.getChildren()) {
                    DataSnapshot lat = l.child("latitude");
                    Double latitude = (Double) lat.getValue();
                    DataSnapshot lon = l.child("longitude");
                    Double longitude = (Double) lon.getValue();
                    DataSnapshot lName = l.child("name");
                    Object lValue = lName.getValue();
                    Object obj = Objects.requireNonNull(lValue);
                    String name = obj.toString();
                    DataSnapshot lAddress = l.child("address");
                    Object val = lAddress.getValue();
                    Object lObj = Objects.requireNonNull(val);
                    String address = lObj.toString();
                    DataSnapshot lPhone = l.child("phoneNumber");
                    Object lVal = lPhone.getValue();
                    Object o = Objects.requireNonNull(lVal);
                    String phoneNumber = o.toString();
                    MarkerOptions mOptions = new MarkerOptions();
                    LatLng ll = new LatLng(latitude, longitude);
                    MarkerOptions markerPos = mOptions.position(ll);
                    MarkerOptions markerTitle = markerPos.title(name);
                    MarkerOptions markerSnippet = markerTitle.snippet(address + ":" + phoneNumber);
                    map.addMarker(markerSnippet);
                    //map.addMarker(new MarkerOptions()
                    //        .position(new LatLng(latitude, longitude))
                    //        .title(name)
                    //        .snippet(address + ":" + phoneNumber));

                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

}
