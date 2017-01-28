package com.tyyar.tyyarfooddelivery.utils;

import android.app.Activity;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.tyyar.tyyarfooddelivery.R;

/**
 * User: YourPc
 * Date: 1/21/2017
 */

public class UiUtils {
    private static final String TAG = UiUtils.class.getSimpleName();

    public static Drawer showDrawer(Activity activity, Toolbar toolbar) {
        AccountHeader headerResult = new AccountHeaderBuilder()
                .withActivity(activity)
                .withHeaderBackground(R.color.material_drawer_background)
                .addProfiles(
                        new ProfileDrawerItem()
                                .withName("Mike Penz")
                                .withEmail("mikepenz@gmail.com")
                                .withIcon(ContextCompat.getDrawable(activity, R.mipmap.ic_launcher))
                )
                .withOnAccountHeaderListener((view, profile, currentProfile) -> false)
                .build();

        Drawer result = new DrawerBuilder()
                .withActivity(activity)
                .withToolbar(toolbar)
                .withAccountHeader(headerResult)
                .addDrawerItems(
                        new PrimaryDrawerItem().withIdentifier(1).withName("Home").withIcon(GoogleMaterial.Icon.gmd_home),
                        new PrimaryDrawerItem().withIdentifier(2).withName("Donate").withIcon(GoogleMaterial.Icon.gmd_sentiment_very_satisfied),
                        new PrimaryDrawerItem().withIdentifier(3).withName("Search for donner").withIcon(GoogleMaterial.Icon.gmd_search)
                )
                .addStickyDrawerItems(
                        new PrimaryDrawerItem().withIdentifier(4).withName("Settings").withIcon(GoogleMaterial.Icon.gmd_settings),
                        new PrimaryDrawerItem().withIdentifier(5).withName("Help").withIcon(GoogleMaterial.Icon.gmd_help_outline),
                        new PrimaryDrawerItem().withIdentifier(6).withName("About").withIcon(GoogleMaterial.Icon.gmd_info_outline))
                .withOnDrawerItemClickListener((view, position, drawerItem) -> {
                    Log.d(TAG, "showDrawer " + drawerItem.getIdentifier());
//                    switch ((int) drawerItem.getIdentifier()) {
//                        case 1:
//                            if (!(activity instanceof MainActivity))
//                                activity.startActivity(new Intent(activity, MainActivity.class));
//                            break;
//                        case 2:
//                            if (!(activity instanceof DonateActivity))
//                                activity.startActivity(new Intent(activity, DonateActivity.class));
//                            break;
//                        case 3:
//                            if (!(activity instanceof SearchForDonnerActivity))
//                                activity.startActivity(new Intent(activity, SearchForDonnerActivity.class));
//                            break;
//                        case 4:
//                            if (!(activity instanceof SearchForDonnerActivity))
//                                activity.startActivity(new Intent(activity, SearchForDonnerActivity.class));
//                            break;
//                        case 5:
//                            if (!(activity instanceof HelpActivity))
//                                activity.startActivity(new Intent(activity, HelpActivity.class));
//                            break;
//                        case 6:
//                            if (!(activity instanceof AboutActivity))
//                                activity.startActivity(new Intent(activity, AboutActivity.class));
//                            break;
//                    }
                    return false;
                }).build();
        return result;
    }

}
