package com.cztek.concept.cargps.third;

import android.content.Context;
import android.view.Gravity;
import android.view.View;

import q.rorbin.badgeview.Badge;
import q.rorbin.badgeview.QBadgeView;

/**
 * Created by HH
 * Date: 2017/12/1
 */

public class BadgeView {

    public static void newBadgeView(Context context,final View targetView,int number){
        Badge badge = new QBadgeView(context)
                .bindTarget(targetView)
                .setBadgeNumber(number)
                .setExactMode(true)
                .setBadgeGravity(Gravity.END | Gravity.TOP);
    }
}
