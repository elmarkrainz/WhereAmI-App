package org.mobdev.whereami.helper;

import android.app.Activity;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import org.mobdev.whereami.helper.FileHelper;
import org.mobdev.whereami.helper.GeoCodingHelper;

/**
 * Created by krajn on 16/05/17.
 */

public class LocationHelper implements LocationListener {


    private final Activity activity;
    private LocationManager locationManager;

    // add a element to Update e.g Textview


    public LocationHelper(Activity activity) {
        this.activity = activity;
        locationManager = (LocationManager) activity.getSystemService(activity.LOCATION_SERVICE);
    }


    public void startRequestingLocation() throws SecurityException {


        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1, 1, this);

    }

    public void stopRequesting() {
        locationManager.removeUpdates(this);
    }

    @Override
    public void onLocationChanged(Location location) {


        //  Update element e.g Textview

        FileHelper fileHelper = new FileHelper(activity);
        fileHelper.saveToFile("coords.txt", location.getLatitude()+ ", "+ location.getLongitude() );

        GeoCodingHelper geoCodingHelper = new GeoCodingHelper(activity, location.getLatitude(),location.getLongitude());



    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

}
