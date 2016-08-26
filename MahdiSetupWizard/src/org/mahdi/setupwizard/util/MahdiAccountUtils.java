/*
 * Copyright (C) 2013 The MoKee OpenSource Project
 * Modifications Copyright (C) 2014 The NamelessRom Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.mahdi.setupwizard.util;

import org.mahdi.setupwizard.R;
import org.mahdi.setupwizard.MahdiSetupWizard;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;

public class MahdiAccountUtils {
    public static boolean isNetworkConnected(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }

    public static void tryEnablingWifi(Context context) {
        WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        if (!wifiManager.isWifiEnabled()) {
            wifiManager.setWifiEnabled(true);
        }
    }

    private static Intent getWifiSetupIntent(Context context) {
        Intent intent = new Intent(MahdiSetupWizard.ACTION_SETUP_WIFI);
        intent.putExtra(MahdiSetupWizard.EXTRA_FIRST_RUN, true);
        intent.putExtra(MahdiSetupWizard.EXTRA_ALLOW_SKIP, true);
        intent.putExtra(MahdiSetupWizard.EXTRA_SHOW_BUTTON_BAR, true);
        intent.putExtra(MahdiSetupWizard.EXTRA_ONLY_ACCESS_POINTS, true);
        intent.putExtra(MahdiSetupWizard.EXTRA_SHOW_SKIP, true);
        intent.putExtra(MahdiSetupWizard.EXTRA_AUTO_FINISH, true);
        intent.putExtra(MahdiSetupWizard.EXTRA_PREF_BACK_TEXT, context.getString(R.string.skip));
        return intent;
    }

    public static void launchWifiSetup(Activity context) {
        MahdiAccountUtils.tryEnablingWifi(context);
        Intent intent = getWifiSetupIntent(context);
        context.startActivityForResult(intent, MahdiSetupWizard.REQUEST_CODE_SETUP_WIFI);
    }

    public static void launchWifiSetup(Fragment fragment) {
        final Context context = fragment.getActivity();
        MahdiAccountUtils.tryEnablingWifi(context);
        Intent intent = getWifiSetupIntent(context);
        fragment.startActivityForResult(intent, MahdiSetupWizard.REQUEST_CODE_SETUP_WIFI);
    }
}
