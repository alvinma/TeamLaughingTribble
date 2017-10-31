package edu.sjsu.thelaughingtribble.parkhere;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import edu.sjsu.thelaughingtribble.parkhere.Controllers.MainActivity;


public class ParkingSpotList extends AppCompatActivity{

    ArrayList<String> tempList = new ArrayList<String>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.parking_spot_list);
        MainActivity temp = new MainActivity();

        ListView listView = (ListView) findViewById(R.id.ParkingList);

        /*for(int i = 0; i < 10; i++){
            tempList.add(" list number is positioned at :: " + Integer.toString(i));
        }*/

        for(int i = 0; i < temp.tempDB.size(); i++){
            tempList.add(temp.tempObjectDB.get(i).getAddressLocation());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, tempList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                Intent intent = new Intent(ParkingSpotList.this, ViewObjectInfo.class);
                intent.putExtra("position", position);


                //Toast.makeText(getApplicationContext(), "Show me position: " + position + " and show me value: " + tempList.get(position), Toast.LENGTH_LONG).show();

                ParkingSpotList.this.startActivity(intent);

            }
        });

    }
}
