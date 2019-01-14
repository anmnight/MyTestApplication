package com.example.testapp;

import android.content.Context;

import com.anmnight.commlibrary.unit.PathManager;
import com.example.testapp.andserver.manager.ImageManager;
import com.example.testapp.andserver.test.TestCardManager;

import androidx.test.InstrumentationRegistry;
import androidx.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

//        assertEquals("com.example.anxiao.testapp", appContext.getPackageName());


        TestCardManager testCardManager =
                new TestCardManager(appContext);


//        testCardManager.readInfo();

        testCardManager.readFace();

    }
}
