package androidcrew.bti7251_android;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidcrew.bti7251_android.background.DoStuffRunnable;
import androidcrew.bti7251_android.fragments.FragmentHost;
import androidcrew.bti7251_android.intents.IntentWithReturnValue;
import androidcrew.bti7251_android.lists.listview.PeopleList;
import androidcrew.bti7251_android.lists.recyclerview.PeopleRecycler;

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

        Button peopleListActivity = (Button)findViewById(R.id.buttonlist);
        peopleListActivity.setOnClickListener(l -> {
            Intent intent = new Intent(this, PeopleList.class);
            startActivity(intent);
        });

        Button recyclebutton = (Button)findViewById(R.id.buttonrecycle);
        recyclebutton.setOnClickListener(l -> {
            Intent intent = new Intent(this, PeopleRecycler.class);
            startActivity(intent);
        });
        SharedPreferences sharedPref = getPreferences(this.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        //Important Settings in shared preferences
        EditText cockPitsettings = findViewById(R.id.textcockpitname);
        cockPitsettings.setText( sharedPref.getString("Sergey", "No Cockpitname"));
        cockPitsettings.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
               editor.putString("Sergey", s.toString());
               editor.commit();
            }
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
                String returnValue = data.getStringExtra(IntentWithReturnValue.returnKey);
                TextView textView = findViewById(R.id.textviewcallback);
                textView.setText(returnValue);

            }
        }
    }



}
