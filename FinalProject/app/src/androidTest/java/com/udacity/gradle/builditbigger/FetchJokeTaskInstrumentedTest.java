package com.udacity.gradle.builditbigger;


import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class FetchJokeTaskInstrumentedTest {

    @Test
    public void assertJokeIsAvailable() {
        FetchJokeTask jokeTask = new FetchJokeTask(){
            @Override
            protected void onPostExecute(String result) {
                Log.i("assertJokeIsAvailable", "Got joke: "+ result);
                assert(result != null);
            }
        };
        jokeTask.execute(InstrumentationRegistry.getContext());
    }
}
