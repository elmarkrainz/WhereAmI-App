package org.mobdev.whereami;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button btnLastPos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btnLastPos = (Button) findViewById(R.id.btnLastPos);

        btnLastPos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LastPositionActivity.class);
                startActivity(intent);
            }
        });
    }

    public void whereAmI(View v) {

        TextView txtCoords, txtAddress;

        txtAddress = (TextView) findViewById(R.id.txtAddress);
        txtCoords = (TextView) findViewById(R.id.txtCoords);


        LocationHelper locationHelper = new LocationHelper(this);
        locationHelper.startRequestingLocation();


        // todo
        txtAddress.setText("HS Bremen, Flughafenallee 10, 28199 Bremen");
        txtCoords.setText("53.055138, 8.783160");



    }


}
