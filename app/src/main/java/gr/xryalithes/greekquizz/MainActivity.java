package gr.xryalithes.greekquizz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_NAME = "gr.xryalithes.greekquizz.MESSAGE";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startQuizz(View view) {


        Intent intent = new Intent(this, quizz_activity.class);
        EditText editText = (EditText) findViewById(R.id.name);
        String playersName = editText.getText().toString();

        // if player's name is empty, show message

        if (playersName.matches("")) {
            Toast.makeText(this, getString(R.string.youDidNotEnterPlayersName), Toast.LENGTH_SHORT).show();
            return;
        }

        //start new activity

        intent.putExtra(EXTRA_NAME, playersName);
        startActivity(intent);


    }


}
