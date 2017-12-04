package com.cztek.concept.cargps.third;

import android.content.res.Resources;
import android.graphics.BitmapFactory;

import com.amap.api.maps2d.model.BitmapDescriptorFactory;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.MarkerOptions;
import com.cztek.concept.cargps.R;

/**
 * Created by HH
 * Date: 2017/12/4
 */

public class MapViewMarkOPtions {
    public static MarkerOptions typeMarkerOptions(double lat, double lng, String title, String message, Resources res, int imageId){
        return new MarkerOptions()
                .position(new LatLng(lat,lng))
                .title(title)
                .snippet(message)
                .icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(res, R.mipmap.icon_red_car)));
    }
}
