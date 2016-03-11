package ly.generalassemb.drewmahrt.tictactoe;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private static final String KEY_INDEX = "index";
    public static final String DATA_KEY = "dataKey";
    public static final int REQUEST_CODE = 27;


    EditText playerOneEdit;
    EditText playerTwoEdit;
    TextView lastWinner;

    Button startGameButton;

    ArrayList<String> playerNames;
    ArrayList<String> winner;

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        //setIntent();

        setOnclickListener();

    }

    private void initViews() {
        playerNames = new ArrayList<>();
        winner = new ArrayList<>();
        playerOneEdit = (EditText) findViewById(R.id.player_one_name);
        playerTwoEdit = (EditText) findViewById(R.id.player_two_name);
        lastWinner = (TextView) findViewById(R.id.last_winner_text);
        startGameButton = (Button) findViewById(R.id.start_game_button);
    }


    private void setIntent() {

        intent = new Intent(MainActivity.this, GameActivity.class);
        playerNames.add(playerOneEdit.getText().toString());
        Log.d(TAG, "playerone edit : " + playerOneEdit + "list is : " + playerNames);
        playerNames.add(playerTwoEdit.getText().toString());

        intent.putStringArrayListExtra(DATA_KEY, playerNames);

        Log.d(TAG, "playernames list: " + playerNames);
    }

    private void setOnclickListener() {
        startGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setIntent();
                startActivityForResult(intent, REQUEST_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE) {
            Log.d(TAG, "passed request code");
            if (resultCode == RESULT_OK) {
                Log.d(TAG, "passed result");

                if (data != null) {
                    //receive intent info
                    winner = data.getStringArrayListExtra(DATA_KEY);
                    lastWinner.setText("The last winner is: " + winner.get(0));
                }
            }
        }


        super.onActivityResult(requestCode, resultCode, data);

    }

    private void setCounterPref(){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        //editor.putInt(PREF_KEY_COUNTER, buttonPressCount);
        editor.commit();
    }
}
