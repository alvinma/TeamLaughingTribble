package edu.sjsu.thelaughingtribble.parkhere;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class ParkingSpotList extends AppCompatActivity{

    ArrayList<String> tempList = new ArrayList<String>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.parking_spot_list);

        ListView listView = (ListView) findViewById(R.id.ParkingList);

        for(int i = 0; i < 10; i++){
            tempList.add(" list number is positioned at :: " + Integer.toString(i));
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, tempList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), "Show me position: " + position + " and show me value: " + tempList.get(position), Toast.LENGTH_LONG).show();
            }
        });

    }
}
