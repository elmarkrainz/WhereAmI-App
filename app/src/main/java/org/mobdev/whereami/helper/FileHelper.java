package org.mobdev.whereami.helper;

import android.app.Activity;
import android.content.Context;
import android.location.LocationManager;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * Created by EKrainz on 17/05/17.
 */

public class FileHelper {

    private final Activity activity;

    public FileHelper(Activity activity) {
        this.activity = activity;
    }


    public void saveToFile(String filename, String data) {

        try {
            FileOutputStream fos = activity.openFileOutput(filename, Context.MODE_PRIVATE);
            OutputStreamWriter osw = new OutputStreamWriter(fos);
            osw.write(data);
            osw.flush();
            osw.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public String getFromFile(String filename) {

        String result = null;

        try {
            FileInputStream fin = activity.openFileInput(filename);
            InputStreamReader inr = new InputStreamReader(fin);

            BufferedReader bufferedReader = new BufferedReader(inr);
            String receiveString = "";
            StringBuilder stringBuilder = new StringBuilder();

            while ((receiveString = bufferedReader.readLine()) != null) {
                stringBuilder.append(receiveString);
            }

            fin.close();
            inr.close();
            result = stringBuilder.toString();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

}