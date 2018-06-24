package androidcrew.bti7251_android.background;

import android.app.Activity;
import android.widget.TextView;

import androidcrew.bti7251_android.R;

public class DoStuffRunnable implements Runnable {
    Activity activity;
    TextView textView;

    public DoStuffRunnable(Activity activity) {
        this.activity = activity;
        textView = activity.findViewById(R.id.output);
    }

    @Override
    public void run() {
        try {
            for (int i = 1; i < 6; i++) {
                Thread.sleep(1000);
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //this.textView.setText(Float.toString( Float.valueOf(i * 20)));
                    }
                });

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
