package com.example.nemanja.albumslistjson.utils;

import android.support.annotation.NonNull;

import com.example.nemanja.albumslistjson.R;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Connector {

    @NonNull
    public static Object connect(String jsonURL) {

        try {
            URL url = new URL(jsonURL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");
            connection.setConnectTimeout(15000);
            connection.setReadTimeout(15000);
            connection.setDoInput(true);

            return connection;

        } catch (MalformedURLException e) {
            e.printStackTrace();
            return (R.string.error) + " " + e.getMessage();

        } catch (IOException e) {
            e.printStackTrace();
            return (R.string.error) + " " + e.getMessage();
        }
    }
}
