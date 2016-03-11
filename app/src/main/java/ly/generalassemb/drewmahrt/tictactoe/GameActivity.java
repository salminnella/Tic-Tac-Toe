package ly.generalassemb.drewmahrt.tictactoe;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class GameActivity extends AppCompatActivity {

    TextView space1;
    TextView space2;
    TextView space3;
    TextView space4;
    TextView space5;
    TextView space6;
    TextView space7;
    TextView space8;
    TextView space9;
    TextView turn;

    Player player1;
    Player player2;
    ArrayList<String> winner;

    int counter;
    String strTurn;
    ArrayList<TextView> viewArrayList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        counter = 0;
        initializeViews();
        receiveIntent();
        setTurn();

    }

    public void initializeViews() {
        winner = new ArrayList<>();
        player1 = new Player();
        player2 = new Player();
        space1 = (TextView) findViewById(R.id.textView);
        space2 = (TextView) findViewById(R.id.textView2);
        space3 = (TextView) findViewById(R.id.textView3);
        space4 = (TextView) findViewById(R.id.textView4);
        space5 = (TextView) findViewById(R.id.textView5);
        space6 = (TextView) findViewById(R.id.textView6);
        space7 = (TextView) findViewById(R.id.textView7);
        space8 = (TextView) findViewById(R.id.textView8);
        space9 = (TextView) findViewById(R.id.textView9);

        setOnClickListeners(space1);
        setOnClickListeners(space2);
        setOnClickListeners(space3);
        setOnClickListeners(space4);
        setOnClickListeners(space5);
        setOnClickListeners(space6);
        setOnClickListeners(space7);
        setOnClickListeners(space8);
        setOnClickListeners(space9);

        turn = (TextView) findViewById(R.id.game_message_text);

    }

    public void receiveIntent(){

        Intent receiveIntent = getIntent();
        ArrayList<String> nameList;

        nameList = receiveIntent.getStringArrayListExtra(MainActivity.DATA_KEY);

        player1.setName(nameList.get(0));
        player2.setName(nameList.get(1));

    }

    public void setTurn() {
        turn.setText("It's " + player1.getName() + "'s! Turn");
        strTurn = player1.getName();
        setGameMarker();
    }

    public void setGameMarker() {

        player1.setGameMarker("X");
        player2.setGameMarker("O");

    }

    public void setOnClickListeners(final TextView space){

        space.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // use if statement to check for first click with counter variable
                if (space.getText().toString().equals("O") || space.getText().toString().equals("X")) {


                } else {
                    if (strTurn.equals(player1.getName())) {
                        space.setText(player1.getGameMarker());
                        space.setTextColor(Color.BLACK);
                        checkForWinner();
                        changeTurn();
                    } else {
                        space.setText(player2.getGameMarker());
                        space.setTextColor(Color.BLACK);
                        checkForWinner();
                        changeTurn();
                    }
                    counter += 1;
                }

            }
        });

    }

    public boolean checkForWinner(){
        if (space1.getText().toString().equals(space2.getText().toString()) &&
                space2.getText().toString().equals(space3.getText().toString()) ||
                space1.getText().toString().equals(space4.getText().toString()) &&
                space4.getText().toString().equals(space6.getText().toString()) ||
                space1.getText().toString().equals(space5.getText().toString()) &&
                space5.getText().toString().equals(space9.getText().toString()) ||
                space4.getText().toString().equals(space5.getText().toString()) &&
                space5.getText().toString().equals(space6.getText().toString()) ||
                space7.getText().toString().equals(space8.getText().toString()) &&
                space8.getText().toString().equals(space9.getText().toString()) ||
                space2.getText().toString().equals(space5.getText().toString()) &&
                space5.getText().toString().equals(space8.getText().toString()) ||
                space3.getText().toString().equals(space6.getText().toString()) &&
                space6.getText().toString().equals(space9.getText().toString()) ||
                space3.getText().toString().equals(space5.getText().toString()) &&
                space5.getText().toString().equals(space7.getText().toString())) {
            new AlertDialog.Builder(GameActivity.this).setMessage("The winner is " + strTurn + "!!!!!")
                    .setPositiveButton("Play Again", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // play again

                            // clear all spaces
                            clearBoard();
                            // set turn back to player 1
                            setTurn();

                            // save win count for player?? -- maybe later
                        }
                    })
                    .setNegativeButton("New Players", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            winner.add(0, strTurn);
                            Intent intentToMain = getIntent();
                            intentToMain.putStringArrayListExtra(MainActivity.DATA_KEY, winner);
                            setResult(RESULT_OK, intentToMain);
                            finish();

                        }
                    }).show();
        }
        if (counter == 8) {
            new AlertDialog.Builder(GameActivity.this).setMessage("The only way to win is not to play the game fool")
                    .setPositiveButton("Play Again", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // play again

                            // clear all spaces
                            clearBoard();
                            // set turn back to player 1
                            setTurn();

                            // save win count for player?? -- maybe later
                        }
                    })
                    .setNegativeButton("New Players", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            winner.add(0, strTurn);
                            Intent intentToMain = getIntent();
                            intentToMain.putStringArrayListExtra(MainActivity.DATA_KEY, winner);
                            finish();
                        }
                    }).show();
            counter = 0;
        }


    }

    private void clearBoard() {
        space1.setText("1");
        space1.setTextColor(Color.TRANSPARENT);
        space2.setText("2");
        space2.setTextColor(Color.TRANSPARENT);
        space3.setText("3");
        space3.setTextColor(Color.TRANSPARENT);
        space4.setText("4");
        space4.setTextColor(Color.TRANSPARENT);
        space5.setText("5");
        space5.setTextColor(Color.TRANSPARENT);
        space6.setText("6");
        space6.setTextColor(Color.TRANSPARENT);
        space7.setText("7");
        space7.setTextColor(Color.TRANSPARENT);
        space8.setText("8");
        space8.setTextColor(Color.TRANSPARENT);
        space9.setText("9");
        space9.setTextColor(Color.TRANSPARENT);

    }

    private void changeTurn() {
        if (strTurn.equals(player1.getName())) {
            strTurn = player2.getName();

        } else {
            strTurn = player1.getName();
        }

        turn.setText("It's " + strTurn + "'s! Turn");
    }

}
