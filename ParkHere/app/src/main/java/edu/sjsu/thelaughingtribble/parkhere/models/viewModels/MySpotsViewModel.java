package edu.sjsu.thelaughingtribble.parkhere.models.viewModels;

import android.app.Activity;
import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;

import edu.sjsu.thelaughingtribble.parkhere.R;

/**
 * Created by jennifernghinguyen on 11/5/17.
 */

public class MySpotsViewModel extends ActivityCommonViewModel {
    private RecyclerView spotList;
    private FloatingActionButton addSpotButton;
    public MySpotsViewModel(Context context) {
        super(context);
        spotList = (RecyclerView) ((Activity) context).findViewById(R.id.spots_list);
        addSpotButton = (FloatingActionButton) ((Activity) context).findViewById(R.id.spot_add_button);
    }

    public RecyclerView getSpotList() {
        return spotList;
    }

    public void setSpotList(RecyclerView spotList) {
        this.spotList = spotList;
    }

    public FloatingActionButton getAddSpotButton() {
        return addSpotButton;
    }

    public void setAddSpotButton(FloatingActionButton addSpotButton) {
        this.addSpotButton = addSpotButton;
    }
}
