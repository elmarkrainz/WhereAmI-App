package org.mobdev.whereami;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.mobdev.whereami.helper.FileHelper;

public class LastPositionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_last_position);

        TextView lastPos = (TextView) findViewById(R.id.txtLastPosition);

        TextView lastAddress = (TextView) findViewById(R.id.lastAddress);


        FileHelper fileHelper = new FileHelper(this);
        lastPos.setText(fileHelper.getFromFile("coords.txt"));

        lastAddress.setText(fileHelper.getFromFile("address.txt"));


    }
}
