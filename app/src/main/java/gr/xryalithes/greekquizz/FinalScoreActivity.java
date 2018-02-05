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
    TextView messageTextView;



    String scoretext;
    String correct;
    String name;
    String text;
    int finalscore;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_score);


        playersNameFinalTextView = (TextView) findViewById(R.id.nameTextViewFinal);
        finalScoreFinalTextView = (TextView) findViewById(R.id.finalscoreTextViewFinal);
        correctAnswersFinalTetxView = (TextView) findViewById(R.id.correctanswersTextViewFinal);
        messageTextView = (TextView) findViewById(R.id.messageTextViewFinal);


        Intent i = getIntent();
        name = i.getStringExtra(quizz_activity.EXTRA_PNAME);
        finalscore = Integer.valueOf(i.getStringExtra(quizz_activity.EXTRA_FSCORE));
        scoretext = getString(R.string.finalScore) + i.getStringExtra(quizz_activity.EXTRA_FSCORE);
        correct = getString(R.string.correctAnswers) + i.getStringExtra(quizz_activity.EXTRA_QUESTIONS_ANSWERED);


        playersNameFinalTextView.setText(name);
        finalScoreFinalTextView.setText(scoretext);
        correctAnswersFinalTetxView.setText(correct);

        displayMessage( finalscore);

    }

    public void displayMessage(int fscore){

        if (finalscore<=40){
            messageTextView.setText(R.string.tryAgain);
        }
        if (finalscore>40 & finalscore<80){
            messageTextView.setText(R.string.youCanBeBetter);
        }
        if (finalscore==80 || finalscore==90){
            messageTextView.setText(R.string.veryGood);
        }
        if (finalscore==100){
            String message = getResources().getString(R.string.Perfect)+ "\n   "+getResources().getString(R.string.GreekGenius);
            messageTextView.setText(message);
        }

    }


    public void newGameButtonClick(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void shareResults(View view){
        Intent share = new Intent(android.content.Intent.ACTION_SEND);
        share.setType("text/plain");
        text = name + "'s " + scoretext + "  " + correct ;
        share.putExtra(Intent.EXTRA_SUBJECT, "GreekQuizz results");
        share.putExtra(android.content.Intent.EXTRA_TEXT, text);
        startActivity(Intent.createChooser(share, "Share it to:"));


    }
}
