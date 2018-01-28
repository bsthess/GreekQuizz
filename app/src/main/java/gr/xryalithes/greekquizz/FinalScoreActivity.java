package gr.xryalithes.greekquizz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class FinalScoreActivity extends AppCompatActivity {

    TextView playersNameFinalTextView;
    TextView finalScoreFinalTextView;
    TextView correctAnswersFinalTetxView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_score);


        playersNameFinalTextView = (TextView) findViewById(R.id.nameTextViewFinal);
        finalScoreFinalTextView = (TextView) findViewById(R.id.finalscoreTextViewFinal);
        correctAnswersFinalTetxView = (TextView) findViewById(R.id.correctanswersTextViewFinal);

        Intent i = getIntent();
        String name = i.getStringExtra(quizz_activity.EXTRA_PNAME);
        String score = getString(R.string.finalScore) + i.getStringExtra(quizz_activity.EXTRA_FSCORE);
        String correct = getString(R.string.correctAnswers) + i.getStringExtra(quizz_activity.EXTRA_QUESTIONS_ANSWERED);

        playersNameFinalTextView.setText(name);
        finalScoreFinalTextView.setText(score);
        correctAnswersFinalTetxView.setText(correct);


    }


    public void newGameButtonClick(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
