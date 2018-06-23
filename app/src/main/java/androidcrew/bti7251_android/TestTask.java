package androidcrew.bti7251_android;

import android.os.AsyncTask;
import android.widget.TextView;

public class TestTask extends AsyncTask<Integer, Float, Double> {

    private MainActivity _activity;
    TextView foo;

    public TestTask(MainActivity activity) {
        this._activity = activity;
        foo = this._activity.findViewById(R.id.output);
    }

    @Override
    protected Double doInBackground(Integer... integers) {
        try {
            for (int i = 1; i < 6; i++) {
                Thread.sleep(1000);
                this.publishProgress(Float.valueOf(i * 20));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return Double.valueOf(integers[0] * 2);
    }

    @Override
    protected void onPreExecute() {
       foo.setText("Starting");
    }

    @Override
    protected void onPostExecute(Double integer) {
        foo.setText("Result: " + integer);
    }

    @Override
    protected void onProgressUpdate(Float... values) {
        foo.setText("Progress: " + values[0]);
    }
}
