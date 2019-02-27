package ass1.bcit.ca;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import ca.bcit.assignment1.R;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        // you need to have a list of data that you want the spinner to display
        List<String> spinnerArray =  new ArrayList<String>();
        spinnerArray.add("Select a continent");
        spinnerArray.add("Africa");
        spinnerArray.add("Americas");
        spinnerArray.add("Asia");
        spinnerArray.add("Europe");
        spinnerArray.add("Oceania");



        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, spinnerArray);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        final Spinner sItems = (Spinner) findViewById(R.id.spinner1);
        sItems.setAdapter(adapter);



        sItems.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                int iCurrentSelection = sItems.getSelectedItemPosition();
                if (iCurrentSelection == 1){
                    String selected = sItems.getSelectedItem().toString();
                    Intent intent = new Intent(MainActivity.this, Countries.class);
                    intent.putExtra("message_key", selected);
                    startActivity(intent);
                } else if (iCurrentSelection == 2){
                    String selected = sItems.getSelectedItem().toString();
                    Intent intent = new Intent(MainActivity.this, Countries.class);
                    intent.putExtra("message_key", selected);
                    startActivity(intent);
                } else if(iCurrentSelection == 3){
                    String selected = sItems.getSelectedItem().toString();
                    Intent intent = new Intent(MainActivity.this, Countries.class);
                    intent.putExtra("message_key", selected);
                    startActivity(intent);
                } else if(iCurrentSelection == 4){
                    String selected = sItems.getSelectedItem().toString();
                    Intent intent = new Intent(MainActivity.this, Countries.class);
                    intent.putExtra("message_key", selected);
                    startActivity(intent);
                } else if(iCurrentSelection == 5){
                    String selected = sItems.getSelectedItem().toString();
                    Intent intent = new Intent(MainActivity.this, Countries.class);
                    intent.putExtra("message_key", selected);
                    startActivity(intent);
                }

            }

            public void onNothingSelected(AdapterView<?> adapterView) {
                return;
            }
        });


    }


}
