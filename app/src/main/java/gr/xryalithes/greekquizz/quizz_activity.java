package gr.xryalithes.greekquizz;

import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatRadioButton;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


public class quizz_activity extends AppCompatActivity {
    public static final String EXTRA_QUESTIONS_ANSWERED = "questions answered";
    public static final String EXTRA_PNAME = "NameOfPlayer";
    public static final String EXTRA_FSCORE = "score";
    public static final String CORRECT_ANSWERS = "correctAnswers";
    public static final String SCORE = "score";
    public static final String QUESTION_NUMBER = "question number";
    public static final String NAME = "name";
    public static final String QUESTION = "question";
    public static final String ANSWER = "question";
    public static final String SUBMIT_BUTTON_VISIBLE = "submit button visible";
    public static final String NEXT_BUTTON_VISIBLE = "next button visible";
    public static final String RADIO_GROUP_VISIBLE = "radio button visible";

    ////////////     declare  variables and views /////////////
    int correctAnswers;
    int questionNumber;
    int score;

    boolean submitImageButtonVisible;
    boolean nextImageButtonVisible;
    boolean radioGroupVisible;

    String name;
    String[] question;
    String[] answer;
    String[] questions;
    String[] answers;

    ////////////////////////DECLARE VIEWS//////////////////////////////////////////////////////////
    CheckBox cbx1;
    CheckBox cbx2;
    CheckBox cbx3;
    AppCompatRadioButton rB1;
    AppCompatRadioButton rB2;
    AppCompatRadioButton rB3;
    RadioGroup rdg;
    ImageView image;
    TextView questionsText;
    TextView scoreText;
    TextView nameView;
    TextView questionNumberTextView;
    ImageButton newGameImageButton;
    ImageButton submitImageButton;
    ImageButton nextImageButton;
    EditText editText;



    @Override
    public void onSaveInstanceState(Bundle savedData) {
        super.onSaveInstanceState(savedData);

        // Save the current data of the game
        // declare variables for saving views state


        // put values in the Bundle object
        savedData.putInt(CORRECT_ANSWERS, correctAnswers);
        savedData.putInt(SCORE, score);
        savedData.putInt(QUESTION_NUMBER, questionNumber);
        savedData.putString(NAME, name);
        savedData.putStringArray(QUESTION, question);
        savedData.putStringArray(ANSWER, answer);
        submitImageButtonVisible = submitImageButton.isShown();
        nextImageButtonVisible = nextImageButton.isShown();
        radioGroupVisible = rdg.isShown();
        savedData.putBoolean(SUBMIT_BUTTON_VISIBLE, submitImageButtonVisible);
        savedData.putBoolean(NEXT_BUTTON_VISIBLE, nextImageButtonVisible);
        savedData.putBoolean(RADIO_GROUP_VISIBLE, radioGroupVisible);

    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    protected void onCreate(Bundle savedData) {
        super.onCreate(savedData);
        setContentView(R.layout.activity_quizz_activity);

////////////////initialize variables and views//////////////////////////////////

        cbx1 = (CheckBox) findViewById(R.id.checkbox1);
        cbx2 = (CheckBox) findViewById(R.id.checkbox2);
        cbx3 = (CheckBox) findViewById(R.id.checkbox3);
        rB1 = (AppCompatRadioButton) findViewById(R.id.radioButton1);
        rB2 = (AppCompatRadioButton) findViewById(R.id.radioButton2);
        rB3 = (AppCompatRadioButton) findViewById(R.id.radioButton3);
        rdg = (RadioGroup) findViewById(R.id.radiogroup);
        questionsText = (TextView) findViewById(R.id.questionTextView);
        scoreText = (TextView) findViewById(R.id.scoreTextView);
        questionNumberTextView = (TextView) findViewById(R.id.currentQuestionTextView);
        newGameImageButton = (ImageButton) findViewById(R.id.newGameImageButton);
        image = findViewById(R.id.questionImage);
        editText = findViewById(R.id.editText);
        questions = getResources().getStringArray(R.array.questionsArray);
        answers = getResources().getStringArray(R.array.answersArray);
        submitImageButton = (ImageButton) findViewById(R.id.submitImageButton);
        nextImageButton = (ImageButton) findViewById(R.id.nextImageButton);

//////////////////////////// restore saved values on config change /////////////////////////////
        if (savedData != null && savedData.containsKey(SUBMIT_BUTTON_VISIBLE)) {

            correctAnswers = savedData.getInt(CORRECT_ANSWERS);
            score = savedData.getInt(SCORE);
            questionNumber = savedData.getInt(QUESTION_NUMBER);
            name = savedData.getString(NAME);
            question = savedData.getStringArray(QUESTION);
            answer = savedData.getStringArray(ANSWER);

            //Restore visibility of views

            submitImageButtonVisible = savedData.getBoolean(SUBMIT_BUTTON_VISIBLE);
            nextImageButtonVisible = savedData.getBoolean(NEXT_BUTTON_VISIBLE);
            radioGroupVisible = savedData.getBoolean(RADIO_GROUP_VISIBLE);

            if (!submitImageButtonVisible) {
                submitImageButton.setVisibility(View.INVISIBLE);
            }
            if (submitImageButtonVisible) {
                submitImageButton.setVisibility(View.VISIBLE);
            }
            if (!nextImageButtonVisible) {
                nextImageButton.setVisibility(View.INVISIBLE);
            }
            if (nextImageButtonVisible) {
                nextImageButton.setVisibility(View.VISIBLE);
            }
            if (!radioGroupVisible) {
                rdg.setVisibility(View.GONE);
            }
        }

        // Get the Intent that started this activity and extract the player's name string//////////////
        Intent intent = getIntent();

        name = intent.getStringExtra(MainActivity.EXTRA_NAME);


        nameView = findViewById(R.id.textViewUp);
        nameView.setText(name);
        nameView.setAllCaps(true);

        /////////////////////////////////  display initial values on views /////////////////////////
        displayScore(score);
        displayQuestionNumber(questionNumber);
        displayQuestion(questionNumber);
        displayAnswers(questionNumber);

    }


    ////////////////  display the question        ////////////////////////////////////////////
    public void displayQuestion(int questionNumber) {

        String tempString = questions[questionNumber];
        question = tempString.split(",");
        questionsText.setText(question[1]);

        ////////////displaying the corresponding question image/////////////

        Resources res = getResources();
        TypedArray imagesArray = res.obtainTypedArray(R.array.images);
        Drawable imageTodisplay = imagesArray.getDrawable(questionNumber);
        image.setImageDrawable(imageTodisplay);

    }

    ////////////////displaying the possible answers , according to question number variable ////////////////////////////////////////////////////////
    public void displayAnswers(int questionNumber) {

        String tempString = answers[questionNumber];
        String[] answer = tempString.split(",");

//////////  condition to display edit Text question /////////////////////////////////
        if (questionNumber == (questions.length - 2)) {

            rdg.setVisibility(View.GONE);
            editText.setVisibility(View.VISIBLE);
            return;
        }
/////////////// condition to display check boxes question  /////////////////////////////
        if (questionNumber == (questions.length - 1)) {

            editText.setVisibility(View.GONE);


            cbx1.setVisibility(View.VISIBLE);
            cbx2.setVisibility(View.VISIBLE);
            cbx3.setVisibility(View.VISIBLE);
            cbx1.setText(answer[1]);
            cbx2.setText(answer[2]);
            cbx3.setText(answer[3]);
            return;
        }

        /////////////// display answer on radio buttons///////////////////////////////
        rB1.setText(answer[1]);
        rB2.setText(answer[2]);
        rB3.setText(answer[3]);


    }

    /////////// DISPLAY THE SCORE    ///////////////////////////////////////////////////////
    public void displayScore(int score) {

        scoreText.setText("SCORE" + "\n" + score);
    }

    public void displayQuestionNumber(int questionNumber) {

        questionNumberTextView.setText(getString(R.string.QuestionNumberText) + "\n" + (questionNumber + 1) + "/" + questions.length);
    }

    /////////// all the good thinks here...checking the submitted answer/////////////////////////////////////////////////////////////

    public void checkAnswer() {

        answer = answers[questionNumber].split(",");    //example: answer= "1,choice1,choice2,choice3,5"
////////////////////////CHECKING EDIT TEXT STATUS////////////////////////////////////////
        if (editText.getVisibility() == View.VISIBLE) {
            String textAnswer = editText.getText().toString();

            if (textAnswer.isEmpty()) {
                Toast.makeText(this, getString(R.string.PleaseTypeAnswer), Toast.LENGTH_SHORT).show();
                submitImageButton.setVisibility(View.VISIBLE);
                return;
            }
            /////////if submitted answer is equal to the answer that has the correct answer index in the array,then ...win!///////////
            if (textAnswer.equalsIgnoreCase(answer[1])) {
                score = score + 10;
                correctAnswers++;
                Toast.makeText(this, getString(R.string.CorrectAnswer), Toast.LENGTH_SHORT).show();
                displayScore(score);
                nextImageButton.setVisibility(View.VISIBLE);
                editText.setInputType(0);


                return;
            }
            Toast.makeText(this, getString(R.string.WrongAnswer) + "\n" + (getString(R.string.CorrectAnswerIs) + answer[1]), Toast.LENGTH_SHORT).show();
            nextImageButton.setVisibility(View.VISIBLE);
            editText.setInputType(0);

            return;
        }


/////////CHECKING CHECKBOXES STATUS/////////////////////////////

        if (cbx1.getVisibility() == View.VISIBLE) {////question  10 has checkboxes

            if (cbx2.isChecked() & cbx3.isChecked()) {
                if (!cbx1.isChecked()) {
                    score = score + 10;
                    correctAnswers++;
                    Toast.makeText(this, getString(R.string.CorrectAnswer), Toast.LENGTH_SHORT).show();
                    displayScore(score);
                    endGame();
                    return;
                }
            }

            Toast.makeText(this, getString(R.string.WrongAnswer) + "\n" + (getString(R.string.CorrectAnswerIs) + " " + answer[2] + " and " + answer[3]), Toast.LENGTH_SHORT).show();

            endGame();
            return;


        }

////////////////////////CHECKING RADIOBUTTONS STATUS/////////////////////////////////////////////

        if (rdg.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, getString(R.string.PleaseMakeAChoice), Toast.LENGTH_SHORT).show();
            submitImageButton.setVisibility(View.VISIBLE);
            return;
        }

        RadioButton selectedRadioButton = findViewById(rdg.getCheckedRadioButtonId());
        String choice = selectedRadioButton.getText().toString();

        int correctAnswerIndex = Integer.parseInt(answer[4]);   // correctAnswer number is stored always at index 4 of answers array
        int index = -1;
        for (int i = 0; i < answer.length; i++) {
            if (answer[i].equals(choice)) {// loop for checking if the choiced button string is equal to correct answer in answer array
                index = i;
                break;
            }
        }

        if (index == correctAnswerIndex) { //if choice = correct answer
            score = score + 10;
            correctAnswers++;
            Toast.makeText(this, getString(R.string.CorrectAnswer), Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(this, getString(R.string.WrongAnswer) + "\n" + (getString(R.string.CorrectAnswerIs) + " " + answer[correctAnswerIndex]), Toast.LENGTH_SHORT).show();

        }

        rdg.clearCheck();
        displayScore(score);
        nextImageButton.setVisibility(View.VISIBLE);

    }


    ///////////////////////////////////////////////////////////////////////////////////
    //////////HERE IS WHAT HAPPENS WHEN PUSHED SUBMIT//////////////////////////////////
    public void submitButtonClick(View view) {

        submitImageButton.setVisibility(View.INVISIBLE);


        checkAnswer();

    }

    ////////////////////////////////////////////////////////////////////////////////
    ///////////DISPLAYING NEXT QUESTION/////////////////////////////////////////////
    public void nextQuestion(View v) {

        submitImageButton.setVisibility(View.VISIBLE);
        nextImageButton.setVisibility(View.INVISIBLE);


        questionNumber = questionNumber + 1;

        if (questionNumber >= questions.length) {
            endGame();
            return;
        }
        displayQuestion(questionNumber);
        displayQuestionNumber(questionNumber);
        displayAnswers(questionNumber);

    }

    ////////////////////////////////////////////////////////////////////////////////////
    //////// UPPON LAST QUESTION,GAME ENDS...///////////////////////////////////////////
    public void endGame() {
        Toast.makeText(this, getString(R.string.gameover), Toast.LENGTH_LONG).show();

        String sc = String.valueOf(score);
        String ca = String.valueOf(correctAnswers);
/////// STARTING THE FINAL SCORE ACTIVITY AND PASSING BY THE NAME,SCORE AND CORRECT ANSWERS NUMBER///////////////
        Intent i = new Intent(this, FinalScoreActivity.class);
        i.putExtra(EXTRA_PNAME, name);
        i.putExtra(EXTRA_FSCORE, sc);
        i.putExtra(EXTRA_QUESTIONS_ANSWERED, ca);

        startActivity(i);


    }

    //////////// WHAT HAPPENS WHEN BUTTON NEW GAME IS PRESSED...STARTING MAIN ACTIVITY////////////////////////////////////
    public void newGameButtonClick(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


}
