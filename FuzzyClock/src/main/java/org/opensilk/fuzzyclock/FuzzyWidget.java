/*
 *  Copyright (C) 2013 OpenSilk Productions LLC
 *
 *  This file is part of Fuzzy Clock
 *
 *  Fuzzy Clock is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *  Fuzzy Clock is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with Fuzzy Clock.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.opensilk.fuzzyclock;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import hugo.weaving.DebugLog;

public class FuzzyWidget extends AppWidgetProvider {

    @DebugLog
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        context.startService(new Intent(context, FuzzyWidgetService.class));
    }

    @DebugLog
    @Override
    public void onAppWidgetOptionsChanged(Context context, AppWidgetManager appWidgetManager, int appWidgetId, Bundle newOptions) {
    }

    @DebugLog
    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        Intent i = new Intent(AppWidgetManager.ACTION_APPWIDGET_DELETED, null, context, FuzzyWidgetService.class);
        i.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, appWidgetIds);
        context.startService(i);
    }

    @Override
    @DebugLog
    public void onEnabled(Context context) {
        PackageManager pm = context.getPackageManager();
        if (pm != null) {
            pm.setComponentEnabledSetting(
                    new ComponentName(context, FuzzyWidgetTimeChangeReceiver.class),
                    PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                    PackageManager.DONT_KILL_APP);
        }
    }

    @Override
    @DebugLog
    public void onDisabled(Context context) {
        PackageManager pm = context.getPackageManager();
        if (pm != null) {
            pm.setComponentEnabledSetting(
                    new ComponentName(context, FuzzyWidgetTimeChangeReceiver.class),
                    PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                    PackageManager.DONT_KILL_APP);
        }
    }

}
