package androidcrew.bti7251_android;

import android.content.Intent;
import android.content.ServiceConnection;
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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidcrew.bti7251_android.background.DoStuffRunnable;
import androidcrew.bti7251_android.fragments.FragmentHost;
import androidcrew.bti7251_android.intents.IntentWithReturnValue;
import androidcrew.bti7251_android.lists.Person;
import androidcrew.bti7251_android.lists.listview.PeopleList;
import androidcrew.bti7251_android.lists.recyclerview.PeopleRecycler;
import androidcrew.bti7251_android.service.CockpitService;
import androidcrew.bti7251_android.service.SergeyServiceConnection;

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

        Intent sergeyService = new Intent(this, CockpitService.class);
        SergeyServiceConnection sergeyServiceConnection = new SergeyServiceConnection();
        bindService(sergeyService, sergeyServiceConnection,BIND_AUTO_CREATE);


        Button sergeyButton = findViewById(R.id.buttonsergeyaction);
        sergeyButton.setOnClickListener(l -> {
            sergeyServiceConnection.logStuff("Huereee Gayyy!!!!!");
        });

        Button raperButton = findViewById(R.id.buttonrandomraper);
        raperButton.setOnClickListener(l -> {
            sergeyServiceConnection.getCockpitService().addPerson();
        });

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference personReference = database.getReference("Person");

        EditText currentRaperView = findViewById(R.id.multicurrentraper);

        personReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Person currentRaper = dataSnapshot.getValue(Person.class);
                currentRaperView.setText(currentRaper.toString());

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
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
        AsyncTask.execute(new DoStuffRunnable(this));
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

    @Override
    protected void onPostResume() {
        super.onPostResume();

        Intent service = new Intent(this, CockpitService.class);
        //startService(service);
    }
}
