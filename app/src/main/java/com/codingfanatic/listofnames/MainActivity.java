package com.codingfanatic.listofnames;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.annotations.Nullable;
//import com.firebase.ui.database.FirebaseListAdapter;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    //1. Instantiated ListView and ArrayList objects
    ListView mListView;
    ArrayList<String> mArrayList = new ArrayList<>();
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //2. Instantiated ArrayAdapter using Activity, list_listview xml layout, and ArrayList object
        //3. Assigned reference to ListView object using List View layout from activity_main xml layout
        //   Set Adapter for the ListView object
        final ArrayAdapter<String> mArrayAdapter = new ArrayAdapter<String>(this, R.layout.list_listview);
        
        mListView = (ListView) findViewById(R.id.list_view1);
        mListView.setAdapter(mArrayAdapter);

        //4. Instantiated FirebaseDatabase and DatabaseReference objects
        myRef = FirebaseDatabase.getInstance().getReference().child("message");

        //5. Add a Child Event Listener to the DatabaseReference object
        myRef.addChildEventListener(new ChildEventListener() {
            
            //6. Override the onChildAdded method for the ChildEventListener for the DatabaseReference object
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                String value = snapshot.getValue(String.class);
                mArrayList.add(value);
                mArrayAdapter.notifyDataSetChanged();
            }
        
            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                mArrayAdapter.notifyDataSetChanged();
            }
        
            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
        
            }
        
            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
        
            }
        
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
        
            }
        });
    }
}
