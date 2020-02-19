package com.reactnativecommunity.andersonfrfilho.geolocation;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReadableMap;

import org.json.JSONObject;

public class RNCGeolocationModule extends ReactContextBaseJavaModule implements LocationListener {
    private static final String[] LOCATION_PERMS = {
            Manifest.permission.ACCESS_FINE_LOCATION
    };
    private final ReactApplicationContext reactContext;

    // flag for GPS status
    public boolean isGPSEnabled = false;

    // flag for network status
    boolean isNetworkEnabled = false;

    // flag for GPS status
    boolean canGetLocation = false;

    Location location; // location
    double latitude; // latitude
    double longitude; // longitude
    double accuracy;

    // The minimum distance to change Updates in meters
    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 1; // 10 meters

    // The minimum time between updates in milliseconds
    private static final long MIN_TIME_BW_UPDATES = 1; // 1 minute

    // Declaring a Location Manager
    protected LocationManager locationManager;

    public RNCGeolocationModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactContext = reactContext;
    }

    @Override
    public String getName() {
        return "Geolocation";
    }

    @ReactMethod
    public void sampleMethod(String stringArgument, int numberArgument, Callback callback) {
        // TODO: Implement some actually useful functionality
        callback.invoke("Received numberArgument: " + numberArgument + " stringArgument: " + stringArgument);
    }
    @ReactMethod
    public boolean requestPermissions(int numberArgument) {
        int MyVersion = Build.VERSION.SDK_INT;
        if (MyVersion > Build.VERSION_CODES.LOLLIPOP_MR1) {
            final Activity activity = getCurrentActivity();
            if (ContextCompat.checkSelfPermission(this.reactContext,
                    Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(activity,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, numberArgument);
                if (ActivityCompat.shouldShowRequestPermissionRationale(activity,
                        Manifest.permission.ACCESS_FINE_LOCATION)) {

                    return true;
                } else {
                    ActivityCompat.requestPermissions(activity,
                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                            numberArgument);

                    return true;
                    // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                    // app-defined int constant. The callback method gets the
                    // result of the request.
                }
            } else {
                ActivityCompat.requestPermissions(activity,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, numberArgument);
                return false;
            }
        }
        return false;

    }
    @ReactMethod
    public void getCurrentPosition(ReadableMap options, final Promise promise) {
        try {
            locationManager = (LocationManager) this.reactContext.getSystemService(Context.LOCATION_SERVICE);
            isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
            isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
            if (isGPSEnabled == false && isNetworkEnabled == false) {
                throw new Exception("error 01 (hardware not work) - Services gps and networking not work");
            } else {
                if (ActivityCompat.checkSelfPermission(this.reactContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this.reactContext, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    this.requestPermissions(121);
                    JSONObject geolocation = new JSONObject();
                    geolocation.put("time", null);
                    geolocation.put("speed",null);
                    geolocation.put("provider",null);
                    geolocation.put("longitude",null);
                    geolocation.put("latitude",null);
                    geolocation.put("bearing",null);
                    geolocation.put("altitude",null);
                    geolocation.put("accuracy",null);
                    promise.resolve(geolocation.toString());
                    return;
                }
                this.canGetLocation = true;
                if (options.getString("provider").toLowerCase().equals("gps")) {
                    if (isGPSEnabled) {
                        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, MIN_TIME_BW_UPDATES, MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
                        if(locationManager!=null){
                            location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                            if(location!=null){
                                JSONObject geolocation = new JSONObject();
                                geolocation.put("time", location.getTime());
                                geolocation.put("speed",location.getSpeed());
                                geolocation.put("provider",location.getProvider());
                                geolocation.put("longitude",location.getLongitude());
                                geolocation.put("latitude",location.getLatitude());
                                geolocation.put("bearing",location.getBearing());
                                geolocation.put("altitude",location.getAltitude());
                                geolocation.put("accuracy",location.getAccuracy());
                                promise.resolve(geolocation.toString());
                                return;
                            }else{
                                throw new Exception("error 02 - location not get");
                            }
                        }else{

                            throw new Exception("error 03 - location manager not work");
                        }

                    }else{

                        throw new Exception("error 04 - provider gps not able");
                    }
                }else if(options.getString("provider").toLowerCase().equals("network")){
                    if (isNetworkEnabled) {
                        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, MIN_TIME_BW_UPDATES, MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
                        if(locationManager!=null){
                            location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                            if(location!=null){
                                JSONObject geolocation = new JSONObject();
                                geolocation.put("time", location.getTime());
                                geolocation.put("speed",location.getSpeed());
                                geolocation.put("provider",location.getProvider());
                                geolocation.put("longitude",location.getLongitude());
                                geolocation.put("latitude",location.getLatitude());
                                geolocation.put("bearing",location.getBearing());
                                geolocation.put("altitude",location.getAltitude());
                                geolocation.put("accuracy",location.getAccuracy());
                                promise.resolve(geolocation.toString());
                                return;
                            }else{
                                throw new Exception("error 02 - location not get");
                            }
                        }else{

                            throw new Exception("error 03 - location manager not work");
                        }

                    }else{

                        throw new Exception("error 04 - provider network not able");
                    }

                }else{
                    throw new Exception("error 05 - provider undefined");
                }
            }

        }catch (Exception error){
            promise.reject("error 01 (hardware error)",new Throwable("Services gps and networking this hardware not work"));
        }
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }
}
