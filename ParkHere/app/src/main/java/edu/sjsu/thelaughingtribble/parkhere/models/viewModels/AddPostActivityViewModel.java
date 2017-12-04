package edu.sjsu.thelaughingtribble.parkhere.models.viewModels;

import android.app.Activity;
import android.content.Context;
import android.widget.Button;
import android.widget.EditText;

import edu.sjsu.thelaughingtribble.parkhere.R;

/**
 * Created by jennifernghinguyen on 12/4/17.
 */

public class AddPostActivityViewModel extends ActivityCommonViewModel {
    private EditText title;
    private Button cancel;
    private Button next;
    public AddPostActivityViewModel(Context context) {
        super(context);
        title = (EditText) ((Activity) context).findViewById(R.id.title_edit);
        cancel= (Button) ((Activity) context).findViewById(R.id.cancel);
        next= (Button) ((Activity) context).findViewById(R.id.next);
    }

    public EditText getTitle() {
        return title;
    }

    public void setTitle(EditText title) {
        this.title = title;
    }

    public Button getCancel() {
        return cancel;
    }

    public void setCancel(Button cancel) {
        this.cancel = cancel;
    }

    public Button getNext() {
        return next;
    }

    public void setNext(Button next) {
        this.next = next;
    }
}
