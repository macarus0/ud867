package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Pair;
import android.widget.Toast;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.backend.jokeApi.JokeApi;;

import java.io.IOException;

public class FetchJokeTask extends AsyncTask <Context, Void,Pair<String, Throwable>> {
    private static JokeApi jokeApi = null;
    private Context context;
    private Throwable throwable = null;

    @Override
    protected Pair<String, Throwable> doInBackground(Context... params){
        if(jokeApi == null) {  // Only do this once
            JokeApi.Builder builder = new JokeApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            // end options for devappserver

            jokeApi = builder.build();
        }

        context = params[0];
        try {
            return new Pair<String, Throwable>(jokeApi.getJoke().execute().getData(), throwable);
        } catch (Throwable t) {
            throwable = t;
            return null;
        }

    }

    @Override
    protected void onPostExecute(Pair<String, Throwable> pair) {
        if(pair.second == null) {
            Toast.makeText(context, pair.first, Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(context, pair.second.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}
