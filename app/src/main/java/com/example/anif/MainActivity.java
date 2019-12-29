package com.example.anif;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

/**
 * @version 0.01
 * @author Ido Perez
 * @since 2.9.03
 */
public class MainActivity extends AppCompatActivity {
    EditText et0;
    TextView tv0;
    String line = " ", text = " ";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et0 = (EditText) findViewById(R.id.et0);
        tv0 = (TextView) findViewById(R.id.tv0);

        /**
         * Reading from the IF and set him on the text View.
         */
        try {
            FileInputStream fis= openFileInput("Text");
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuffer sb = new StringBuffer();

            line = br.readLine();
            while (line != null) {
                sb.append(line+'\n');
                line = br.readLine();
            }
            text =sb.toString();
            isr.close();
            tv0.setText(text);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * close the app and saving the text in the IF
     * @param view
     * @throws IOException
     */
    public void Exit(View view) throws IOException {
        if(line != null)
            line = line + et0.getText().toString();
        else
            line= et0.getText().toString();
        FileOutputStream fos = openFileOutput("Text",MODE_PRIVATE);
        OutputStreamWriter osw = new OutputStreamWriter(fos);
        BufferedWriter bw = new BufferedWriter(osw);
        bw.write(text+" "+line);
        bw.close();

        finish();
    }

    /**
     * reset the strings values and the IF values.
     * @param view
     * @throws IOException
     */
    public void Reset(View view) throws IOException {
        text = " ";
        line = " ";
        et0.setText("Text:");
        tv0.setText(text);
        FileOutputStream fos = null;
        try {
            fos = openFileOutput("Text",MODE_PRIVATE);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        OutputStreamWriter osw = new OutputStreamWriter(fos);
        BufferedWriter bw = new BufferedWriter(osw);
        bw.write(" ");
        bw.close();
    }

    /**
     * saving the text in the IF.
     * @param view
     * @throws IOException
     */
    public void Save(View view) throws IOException {
        if(line != null)
        line = line + " "+et0.getText().toString();
        else
            line= et0.getText().toString();
        tv0.setText(text+" "+line);
        FileOutputStream fos = openFileOutput("Text",MODE_PRIVATE);
        OutputStreamWriter osw = new OutputStreamWriter(fos);
        BufferedWriter bw = new BufferedWriter(osw);
        bw.write(text+" "+line);
        bw.close();
    }
    /**
     * option menu with credit and main that moving from the activitis.
     */
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    /**
     * comparing ids and moving to the chosen activity.
     * @param item
     * @return
     */
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        if(id == R.id.Credits){
            Intent si = new Intent(this, Credits.class);
            startActivity(si);
        }
        return true;

    }
}
