package androidcrew.bti7251_android.lists.listview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import androidcrew.bti7251_android.R;
import androidcrew.bti7251_android.lists.DataProvider;

public class PeopleList extends AppCompatActivity {
    DataProvider dataProvider;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_people_list);

        dataProvider = new DataProvider(30);

        listView = (ListView)findViewById(R.id.listviewpeople);

        ArrayAdapter listAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, dataProvider.getPeople());
        listView.setAdapter(listAdapter);
    }
}
