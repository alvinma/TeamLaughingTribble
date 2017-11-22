package edu.sjsu.thelaughingtribble.parkhere.models.viewModels;

import android.app.Activity;
import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import edu.sjsu.thelaughingtribble.parkhere.R;
import edu.sjsu.thelaughingtribble.parkhere.controllers.AddAPlace;

/**
 * Created by jennifernghinguyen on 11/2/17.
 */

public class MyPlacesActivityViewModel extends ActivityCommonViewModel {
    private RecyclerView placeList;
    private FloatingActionButton addPlaceButton;

    public MyPlacesActivityViewModel(Context context) {
        super(context);
        placeList = (RecyclerView) ((Activity) context).findViewById(R.id.places_list);
        addPlaceButton = (FloatingActionButton) ((Activity) context).findViewById(R.id.place_add_button);
        setAddButtonListener();
    }

    public RecyclerView getPlaceList() {
        return placeList;
    }

    public void setPlaceList(RecyclerView placeList) {
        this.placeList = placeList;
    }

    public FloatingActionButton getAddPlaceButton() {
        return addPlaceButton;
    }

    public void setAddPlaceButton(FloatingActionButton addPlaceButton) {
        this.addPlaceButton = addPlaceButton;
    }

    private void setAddButtonListener() {
        addPlaceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddAPlace.startIntent(getContext(), getUser());
            }
        });
    }
}
