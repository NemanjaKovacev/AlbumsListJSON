package com.example.nemanja.albumslistjson.json;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.nemanja.albumslistjson.R;
import com.example.nemanja.albumslistjson.adapter.RecyclerAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

class JSONParser extends AsyncTask<Void, Void, Boolean> {

    private final Context context;
    private final String jsonData;
    private final RecyclerView recyclerView;

    private ProgressDialog progressDialog;
    @NonNull
    private final
    ArrayList<String> albums = new ArrayList<>();

    public JSONParser(Context context, String jsonData, RecyclerView recyclerView) {
        this.context = context;
        this.jsonData = jsonData;
        this.recyclerView = recyclerView;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = new ProgressDialog(context);
        progressDialog.setTitle(context.getString(R.string.parse));
        progressDialog.setMessage(context.getString(R.string.parsing));
        progressDialog.show();
    }

    @NonNull
    @Override
    protected Boolean doInBackground(Void... voids) {
        return parse();
    }

    @Override
    protected void onPostExecute(Boolean isParsed) {
        super.onPostExecute(isParsed);

        progressDialog.dismiss();
        if (isParsed) {
            RecyclerAdapter adapter = new RecyclerAdapter(context, albums);
            recyclerView.setAdapter(adapter);

        } else {
            Toast.makeText(context, R.string.parse_unable, Toast.LENGTH_SHORT).show();
        }
    }

    private Boolean parse() {
        try {
            JSONArray jsonArray = new JSONArray(jsonData);
            JSONObject jsonObject;

            albums.clear();

            for (int i = 0; i < jsonArray.length(); i++) {
                jsonObject = jsonArray.getJSONObject(i);

                String name = jsonObject.getString("title");

                albums.add(name);
            }
            return true;

        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
    }
}
