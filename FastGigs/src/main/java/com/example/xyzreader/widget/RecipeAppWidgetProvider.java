package com.example.xyzreader.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.TextView;

import com.example.xyzreader.R;
import com.example.xyzreader.model.Result;
import com.example.xyzreader.util.Constants;
import com.google.gson.Gson;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Implementation of App Widget functionality.
 */
public class RecipeAppWidgetProvider extends AppWidgetProvider {
    /*private static String taskdesc;
    public static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId, String taskdes) {
        taskdesc = taskdes;
        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.app_widget);
        views.setTextViewText(R.id.recipe_name_text_view, taskdesc);
        // Construct an Intent object includes web adresss.
        Intent intent = new Intent(context, RecipeAppWidgetProvider.class);
        intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
        // In widget we are not allowing to use intents as usually. We have to use PendingIntent instead of 'startActivity'
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
        // Here the basic operations the remote view can do.
        views.setOnClickPendingIntent(R.id.recipe_name_text_view, pendingIntent);
        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    public static void updateAppWidgets(Context context, AppWidgetManager appWidgetManager, int appWidgetIds , String taskdes) {
        //for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetIds, taskdes);
        //}
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId, taskdesc);
        }

        *//*RemoteViews remoteV = new RemoteViews(context.getPackageName(), R.layout.app_widget);

        Intent intentSync = new Intent(context, RecipeAppWidgetProvider.class);
        intentSync.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE); //You need to specify the action for the intent. Right now that intent is doing nothing for there is no action to be broadcasted.
        PendingIntent pendingSync = PendingIntent.getBroadcast(context,0, intentSync, PendingIntent.FLAG_UPDATE_CURRENT); //You need to specify a proper flag for the intent. Or else the intent will become deleted.
        remoteV.setOnClickPendingIntent(R.id.recipe_name_text_view,pendingSync);

        appWidgetManager.updateAppWidget(appWidgetIds, remoteV);*//*

    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);

        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.app_widget);
        views.setTextViewText(R.id.recipe_name_text_view, taskdesc);
        // This time we dont have widgetId. Reaching our widget with that way.
        ComponentName appWidget = new ComponentName(context, RecipeAppWidgetProvider.class);
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidget, views);
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }*/

    // Name of shared preferences file & key
    private static final String SHARED_PREF_FILE =
            "com.example.xyzreader";
    private static final String COUNT_KEY = "count";
    private static String strTaskDesc = "";

    /**
     * Update a single app widget.  This is a helper method for the standard
     * onUpdate() callback that handles one widget update at a time.
     *
     * @param context          The application context.
     * @param appWidgetManager The app widget manager.
     * @param appWidgetId      The current app widget id.
     */
    private static void updateAppWidget(Context context,
                                 AppWidgetManager appWidgetManager,
                                 int appWidgetId, String strTaskDes) {

        // Get the count from prefs.
        SharedPreferences prefs =
                context.getSharedPreferences(SHARED_PREF_FILE, 0);
        int count = prefs.getInt(COUNT_KEY + appWidgetId, 0);
        count++;

        // Get the current time.
        String dateString =
                DateFormat.getTimeInstance(DateFormat.SHORT).format(new Date());

        // Construct the RemoteViews object.
        RemoteViews views = new RemoteViews(context.getPackageName(),
                R.layout.new_app_widget);
        views.setTextViewText(R.id.appwidget_id,
                String.valueOf(appWidgetId));
//        views.setTextViewText(R.id.appwidget_update,
//                context.getResources().getString(
//                        R.string.date_count_format, count, dateString));
        views.setTextViewText(R.id.appwidget_update, strTaskDesc);

        // Save count back to prefs.
        SharedPreferences.Editor prefEditor = prefs.edit();
        prefEditor.putInt(COUNT_KEY + appWidgetId, count);
        prefEditor.apply();

        // Setup update button to send an update request as a pending intent.
        Intent intentUpdate = new Intent(context, RecipeAppWidgetProvider.class);

        // The intent action must be an app widget update.
        intentUpdate.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);

        // Include the widget ID to be updated as an intent extra.
        int[] idArray = new int[]{appWidgetId};
        intentUpdate.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, idArray);

        // Wrap it all in a pending intent to send a broadcast.
        // Use the app widget ID as the request code (third argument) so that
        // each intent is unique.
        PendingIntent pendingUpdate = PendingIntent.getBroadcast(context,
                appWidgetId, intentUpdate, PendingIntent.FLAG_UPDATE_CURRENT);

        // Assign the pending intent to the button onClick handler
        views.setOnClickPendingIntent(R.id.button_update, pendingUpdate);

        // Instruct the widget manager to update the widget.
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    public static void updateTsak(String strTaskDes){
        strTaskDesc = strTaskDes;
    }
    /**
     * Override for onUpdate() method, to handle all widget update requests.
     *
     * @param context          The application context.
     * @param appWidgetManager The app widget manager.
     * @param appWidgetIds     An array of the app widget IDs.
     */
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager,
                         int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them.
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId, strTaskDesc);
        }
    }
}

