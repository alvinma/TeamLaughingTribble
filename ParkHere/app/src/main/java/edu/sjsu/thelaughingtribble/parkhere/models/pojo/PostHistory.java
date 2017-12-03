package edu.sjsu.thelaughingtribble.parkhere.models.pojo;

import com.google.firebase.database.Exclude;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by CROOOME on 12/1/17.
 */


//TODO: may not need this class
public class PostHistory {

    private Post post;
    private ArrayList<DateFormat> history;

    public void PostHistory(){}

    public void addBookedTime(DateFormat start, DateFormat end){
//        if(history.contains(date)){
//
//        }
//        history.add(date);
    }

    public Boolean isAvaliable(DateFormat start, DateFormat end){

        for(DateFormat booking: history){
            if(true){
                return true;
            }
        }

        return false;
    }

    public Boolean endDate(){
        return false;
    }



    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("post", post);
        result.put("history", history);

        return result;
    }
}
