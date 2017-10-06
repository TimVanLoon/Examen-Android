package be.ap.edu.examenandroid;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.osmdroid.util.GeoPoint;

import java.io.Serializable;

public class DescriptionActivity extends AppCompatActivity {

    final Context currentContext = this;

    private static final String LOCATION = "LOCATION";
    private static final String BESCHRIJVING = "BESCHRIJVING";

    private TextView descrTxtView;
    private EditText beschrijving;
    private Button postDescrButton;

    private MapSQLiteHelper helper;

    final String TABLE_NAME = "locations";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);

        descrTxtView = (TextView) findViewById(R.id.descrTxtView);
        beschrijving = (EditText) findViewById(R.id.descrTxtField);
        postDescrButton = (Button) findViewById(R.id.postDescrButton);

        helper = new MapSQLiteHelper(this);

        postDescrButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                try{

                    final GeoPoint location = (GeoPoint) getIntent().getSerializableExtra("LOCATION");
                    Double lat = location.getLatitude();
                    Double lon = location.getLongitude();
                    String mBeschrijving = beschrijving.getText().toString();
                    helper.addLocation(lat, lon, mBeschrijving);
                    Toast.makeText(getApplicationContext(),
                            "Location saved!",
                            Toast.LENGTH_SHORT)
                            .show();
                }
                catch(Exception ex){
                    Log.e("SQLITE", ex.getMessage());
                }

                Intent mainScreenIntent = new Intent(currentContext, MainActivity.class);
                mainScreenIntent.putExtra(LOCATION, (Serializable) (GeoPoint) getIntent().getSerializableExtra("LOCATION"));
                mainScreenIntent.putExtra(BESCHRIJVING, beschrijving.getText().toString());
                startActivity(mainScreenIntent);
            }
        });
    }
}
