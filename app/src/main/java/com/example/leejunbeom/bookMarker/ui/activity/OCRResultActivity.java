package com.example.leejunbeom.bookMarker.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;

import com.example.leejunbeom.bookMarker.model.OCR.ocrProcessing.AsyncProcessTask;
import com.example.leejunbeom.test.R;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public class OCRResultActivity extends AppCompatActivity {

    String imageUrl;
    String outputPath;

    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ocrresult);

        tv = (TextView)findViewById(R.id.ocr_result_textView);

        Bundle extras = getIntent().getExtras();
        if( extras != null) {
            imageUrl = extras.getString("IMAGE_PATH" );
            outputPath = extras.getString( "RESULT_PATH" );
        }
        Log.i("input path2", imageUrl);
        Log.i("output path2", outputPath);
        // Starting recognition process
        new AsyncProcessTask(this).execute(imageUrl, outputPath);
    }

    public void updateResults(Boolean success) {
        if (!success)
            return;
        try {
            StringBuffer contents = new StringBuffer();

            FileInputStream fis = openFileInput(outputPath);
            try {
                Reader reader = new InputStreamReader(fis, "UTF-8");
                BufferedReader bufReader = new BufferedReader(reader);
                String text = null;
                while ((text = bufReader.readLine()) != null) {
                    contents.append(text).append(System.getProperty("line.separator"));

                }
            } finally {
                fis.close();
            }

            //
            String resultText = contents.toString();
            Log.i("result1", resultText);
            //resultText = resultText.replaceAll("[^a-zA-Z0-9\\.]+", "");
            resultText = resultText.replaceAll("[\n\r]+", " ");
            resultText = resultText.trim();

            //displayMessage(resultText);
            tv.setText(resultText);

            Log.i("result2", resultText);
        } catch (Exception e) {
            Log.e("Error: ", e.getMessage());
        }
    }
    /*

    public void displayMessage( String text )
    {
        tv.post( new MessagePoster( text ) );
    }

    class MessagePoster implements Runnable {
        public MessagePoster( String message )
        {
            _message = message;
        }

        public void run() {
            tv.append( _message + "\n" );
            setContentView( tv );
        }

        private final String _message;
    }
    */
}
