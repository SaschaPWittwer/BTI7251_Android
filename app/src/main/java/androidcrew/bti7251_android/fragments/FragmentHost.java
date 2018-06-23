package androidcrew.bti7251_android.fragments;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import androidcrew.bti7251_android.R;

public class FragmentHost extends AppCompatActivity implements MyFragment.OnFragmentInteractionListener {

    TextView messageFromFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_host);


        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        fragmentTransaction.replace(R.id.mainframe, MyFragment.newInstance());
        fragmentTransaction.commit();
        messageFromFragment = (TextView)findViewById(R.id.fragmentMessage);
    }


    @Override
    public void onFragmentInteraction(String s) {
        messageFromFragment.setText(s);
    }
}
