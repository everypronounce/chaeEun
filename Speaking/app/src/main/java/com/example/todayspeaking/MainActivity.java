package com.example.todayspeaking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;

public class MainActivity extends AppCompatActivity {

    TextView readText;
    EditText writeEdit;
    TextView readData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        writeEdit = findViewById(R.id.write_edit);
        readText = findViewById(R.id.read_text);
        readData = findViewById(R.id.read_data);

        Button readBtn = findViewById(R.id.read_btn);
        Button readDataBtn = findViewById(R.id.readData_btn);
        readBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //쓰기
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference();

                //key값이 sentence인 곳에 데이터 추가하기
                myRef.child("sentence").push().setValue(writeEdit.getText().toString()); //push는 타임스탬프로 key값 생성

//                읽기
//                myRef.addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot snapshot) {
//                        String value = snapshot.getValue(String.class);
//                        readText.setText("value: " + value);
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError error) {
//                        readText.setText("error: " + error.toException());
//                    }
//                });
            }
        });

        //전체 데이터 읽기
        readDataBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("sentence"); //sentence가 데이터베이스에서 key 값

                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        Object sentence = snapshot.getValue(Object.class);
                        readData.setText(sentence.toString());
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        readData.setText("error: " + error.toException());
                    }
                });
            }
        });
    }
}