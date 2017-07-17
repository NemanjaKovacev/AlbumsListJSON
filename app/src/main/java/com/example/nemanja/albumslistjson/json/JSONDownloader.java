package com.example.nemanja.albumslistjson.json;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.nemanja.albumslistjson.R;
import com.example.nemanja.albumslistjson.utils.Connector;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

public class JSONDownloader extends AsyncTask<Void, Void, String> {

    private final Context context;
    private final String jsonURL;
    private final RecyclerView recyclerView;

    private ProgressDialog progressDialog;

    public JSONDownloader(Context context, String jsonURL, RecyclerView recyclerView) {
        this.context = context;
        this.jsonURL = jsonURL;
        this.recyclerView = recyclerView;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        progressDialog = new ProgressDialog(context);
        progressDialog.setTitle(context.getString(R.string.download));
        progressDialog.setMessage(context.getString(R.string.downloading));
        progressDialog.show();
    }

    @Override
    protected String doInBackground(Void... voids) {
        return download();
    }

    @Override
    protected void onPostExecute(@NonNull String jsonData) {
        super.onPostExecute(jsonData);

        progressDialog.dismiss();
        if (jsonData.startsWith(context.getString(R.string.error))) {
            String error = jsonData;
            Toast.makeText(context, error, Toast.LENGTH_SHORT).show();
        } else {
            new JSONParser(context, jsonData, recyclerView).execute();
        }
    }

    private String download() {
        Object connection = Connector.connect(jsonURL);
        if (connection.toString().startsWith((context.getString(R.string.error)))) {
            return connection.toString();
        }

        try {
            HttpURLConnection con = (HttpURLConnection) connection;
            if (con.getResponseCode() == HttpURLConnection.HTTP_OK) {

                InputStream inputStream = new BufferedInputStream(con.getInputStream());
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                StringBuilder jsonData = new StringBuilder();

                while ((line = bufferedReader.readLine()) != null) {
                    jsonData.append(line).append("\n");
                }

                bufferedReader.close();
                inputStream.close();

                return jsonData.toString();

            } else {
                return (context.getString(R.string.error)) + con.getResponseMessage();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return (context.getString(R.string.error)) + e.getMessage();
        }
    }
}


















