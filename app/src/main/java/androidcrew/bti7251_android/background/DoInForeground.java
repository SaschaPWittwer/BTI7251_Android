package androidcrew.bti7251_android.background;

import android.app.Activity;
import android.widget.TextView;

import androidcrew.bti7251_android.R;

public class DoInForeground implements Runnable {
    Activity activity;
    int value;
    TextView textView;
    public DoInForeground(Activity activity, int value) {
        this.activity = activity;
        this.value = value;
        textView = activity.findViewById(R.id.output);
    }

    @Override
    public void run() {

        textView.setText(Float.toString( Float.valueOf(value * 20)));

    }
}
