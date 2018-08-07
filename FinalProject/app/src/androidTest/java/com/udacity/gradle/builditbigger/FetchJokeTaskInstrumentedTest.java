package com.udacity.gradle.builditbigger;


import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;
import android.util.Pair;

import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;


import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@RunWith(AndroidJUnit4.class)
public class FetchJokeTaskInstrumentedTest {

    @Test
    public void assertJokeIsAvailable() {
        final CountDownLatch signal = new CountDownLatch(1);
        FetchJokeTask jokeTask = new FetchJokeTask(){
            @Override
            protected void onPostExecute(Pair<String, Throwable> pair) {
                Log.i("assertJokeIsAvailable", "Got joke: "+ pair.first);
                assertNotEquals("Joke = " + pair.first, null,  pair.first);
                assertEquals("No exception occurred", null, pair.second);
                signal.countDown();
            }
        };
        jokeTask.execute(InstrumentationRegistry.getContext());
        try {
            signal.await(2000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            fail("Interruped waiting for task to complete");
        }

    }
}
