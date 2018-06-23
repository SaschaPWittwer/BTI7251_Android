package androidcrew.bti7251_android;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class IntentWithReturnValue extends AppCompatActivity {
    final static String returnKey = "RETURN_VALUE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_with_return_value);

        EditText editText = findViewById(R.id.texttoreturn);
        Button button = findViewById(R.id.buttonsendtexttomain);

        button.setOnClickListener(l -> {
            Intent output = new Intent();
            output.putExtra(returnKey, editText.getText().toString());
            setResult(RESULT_OK, output);
            finish();
        });

    }
}
