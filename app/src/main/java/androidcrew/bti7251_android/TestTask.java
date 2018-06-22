package androidcrew.bti7251_android;

import android.os.AsyncTask;
import android.widget.TextView;

public class TestTask extends AsyncTask<Integer, Float, Double> {

    private MainActivity _activity;

    public TestTask(MainActivity activity) {
        this._activity = activity;
    }

    @Override
    protected Double doInBackground(Integer... integers) {
        try {
            for (int i = 0; i < 5; i++) {
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
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Double integer) {
        TextView foo = this._activity.findViewById(R.id.output);
        foo.setText("Result: " + integer);
    }

    @Override
    protected void onProgressUpdate(Float... values) {
        TextView foo = this._activity.findViewById(R.id.output);
        foo.setText("Progress: " + values[0]);
    }
}
