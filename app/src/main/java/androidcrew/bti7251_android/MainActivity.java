package androidcrew.bti7251_android;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    static final int ACTIVITY_REQUEST_ID = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button fragmentbutton = (Button)findViewById(R.id.buttonFragment);
        fragmentbutton.setOnClickListener(l -> {
            Intent intent = new Intent(this, FragmentHost.class);
            startActivity(intent);
        });

        Button callBackButton = (Button)findViewById(R.id.buttonactivitycallback);
        callBackButton.setOnClickListener(l -> {
            Intent intent = new Intent(this, IntentWithReturnValue.class);
            startActivityForResult(intent, ACTIVITY_REQUEST_ID);
        });

    }

    public void onAsyncTaskButtonClick(View btn){
        new TestTask(this).execute(5);
    }

    public void onImageIntentButtonClick(View btn) {
        //Intent imageIntent = new Intent()
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == ACTIVITY_REQUEST_ID) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                String returnValue = data.getStringExtra(IntentWithReturnValue.returKey);
                TextView textView = findViewById(R.id.textviewcallback);
                textView.setText(returnValue);

            }
        }
    }



}
