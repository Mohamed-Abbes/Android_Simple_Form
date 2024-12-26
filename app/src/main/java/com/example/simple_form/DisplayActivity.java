package com.example.simple_form;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DisplayActivity extends AppCompatActivity {
    private Button retButt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_display);
        TextView textView = findViewById(R.id.text_view);

        Etudiant etudiant = (Etudiant) getIntent().getSerializableExtra("etudiant");

        String displayText = "Bonjour " + etudiant.getGenre() + " " + etudiant.getNom() + " " + etudiant.getPrenom()
                + "\n Vous avez choisi de participer à: " + etudiant.getChoix();
        textView.setText(displayText);

        retButt = findViewById(R.id.ret);
        retButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DisplayActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

}
