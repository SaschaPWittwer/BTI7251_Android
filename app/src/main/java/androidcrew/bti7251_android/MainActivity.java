package androidcrew.bti7251_android;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void onAsyncTaskButtonClick(View btn){
        new TestTask(this).execute(5);
    }

    public void onImageIntentButtonClick(View btn) {
        //Intent imageIntent = new Intent()
    }
}
