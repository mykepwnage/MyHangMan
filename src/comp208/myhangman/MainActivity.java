package comp208.myhangman;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity {

	int counter = 0;
	int randNumb = (int) ((Math.random() * (9 - 0)) + 0);
	String[] wordList = {
	        "divertissement",
	        "datelining",
	        "enate",
	        "separated",
	        "grape",
	        "upsprung",
	        "circumambulator",
	        "nonprotestation",
	        "underrealising",
	        "poeticising"};		
	
	String word;
	int numberOfDashes = word.length();
	String dashedWord;
	String dashes = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		final ImageView image = (ImageView) findViewById(R.id.imageView1);
		
		image.setImageDrawable(getResources().getDrawable(R.drawable.hangman0));
		
		final int[] hangmen = {
				R.drawable.hangman0,
				R.drawable.hangman1,
				R.drawable.hangman2,
				R.drawable.hangman3,
				R.drawable.hangman4, 
				R.drawable.hangman5,
				R.drawable.hangman6
				};				
		
		EditText txtGuess = (EditText) findViewById(R.id.editText1);
		TextView txtWord = (TextView) findViewById(R.id.textView3);

    	word = wordList[randNumb];
        
        for (int x = 0; x < numberOfDashes; x++) {
        	dashedWord = dashedWord + "-";
        }		

    	Editable guess = txtGuess.getText();
    	
    	txtWord.setText(dashes);
    	txtGuess.setOnKeyListener(new OnKeyListener() {
			
			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				// TODO Auto-generated method stub				
				
				char keyPressed = (char) event.getUnicodeChar();
				if (makeGuess(keyPressed)){
					return true;
				}
				else
				{
                    counter = counter +1;
    				image.setImageDrawable(getResources().getDrawable(hangmen[counter]));
					return false;
				}
			}
		});   	
    	

    	}   
	
        public boolean makeGuess(char keyPressed) {
        	boolean correct = false;
            
            for (int i = 0; i < numberOfDashes; i++) {

                char wordCharacter = word.charAt(i);

                if (keyPressed == wordCharacter) {
                    dashedWord = dashedWord.substring(0, i) + wordCharacter + dashedWord.substring(i + 1);
                    correct = true;
                } else {
                    dashedWord = dashedWord.substring(0, i) + "-" + dashedWord.substring(i + 1);
                }
            }    
    	    return correct;
        }
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
