package com.example.xyzreader.remote;

import android.util.Log;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class Config {
    public static final URL BASE_URL;
    private static String TAG = Config.class.toString();

    static {
        URL url = null;
        try {
            //File myFile=new File("/XYZReader/src/main/java/com/example/xyzreader/remote/initial_data.txt");
            url = new URL("https://go.udacity.com/xyz-reader-json" );
            //url = myFile.toURI().toURL();
        } catch (MalformedURLException ignored) {
            // TODO: throw a real error
            Log.e(TAG, "Please check your internet connection.");
        }

        BASE_URL = url;
    }
}
