package edu.sjsu.thelaughingtribble.parkhere.models.pojo;

import com.google.firebase.database.Exclude;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by CROOOME on 12/1/17.
 */

public class PostHistory implements Serializable{

    private Spot spot;
    private ArrayList<Renting> history;

    public void PostHistory(){}

    public void setHistory(ArrayList<Renting> history){
        this.history = history;
    }

    public void PostHistory(Spot spot, ArrayList<Renting> history){
        this.spot = spot;
        this.history = history;
    }

    public void setSpot(Spot spot){
        this.spot = spot;
    }

    public Spot getSpot(){
        return this.spot;
    }

    public void addToHistory(Renting booking){
        if(!this.history.contains(booking)){
            this.history.add(booking);
        }
    }

    public void removeFromHistory(Renting booking){
        if(this.history.contains(booking)){
            this.history.remove(booking);
        }
    }

    public ArrayList<Renting> getHistory(){
        return this.history;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("spot", spot);
        result.put("history", history);

        return result;
    }
}
