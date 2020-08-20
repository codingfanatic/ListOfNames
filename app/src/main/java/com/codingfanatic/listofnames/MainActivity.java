package com.codingfanatic.listofnames;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import
import android.view.View;
import
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseDatabase database = FirebaseDatabase.getInstance();

        /*
         * Create a DatabaseReference to the data; works with standard DatabaseReference methods
         * like limitToLast() and etc.
         */
        DatabaseReference myRef = database.getReference().child("message");

        //myRef.setValue("I want to read this into my app");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                Log.d(TAG, "Value is: " + value);
            }
        
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

         // Find the ListView
         mListView = (ListView) findViewById(R.id.list_view);

         // Now set the adapter with a given layout
         mListView.setAdapter(new FirebaseListAdapter<Person>(this, Person.class,
                 R.layout.list_listview, myRef) {
 
             // Populate view as needed
             protected void populateView(View view, Person person, int position) {
                 ((TextView) view.findViewById(android.R.id.text1)).setText(person.getName());
             }
         });

    }
}
