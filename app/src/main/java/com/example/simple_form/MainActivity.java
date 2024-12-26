package com.example.simple_form;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private RadioGroup rGroup;
    private EditText nom;
    private EditText prenom;
    private CheckBox hac;
    private CheckBox sess;
    private Button butt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        rGroup = findViewById(R.id.rg);
        nom = findViewById(R.id.n);
        prenom = findViewById(R.id.pren);
        hac = findViewById(R.id.hackt);
        sess = findViewById(R.id.session);
        butt = findViewById(R.id.but);

        butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(rGroup.getCheckedRadioButtonId() == -1){
                    Toast.makeText(v.getContext(), "Veuillez choisir votre genre !",Toast.LENGTH_LONG).show();
                    return;
                }
                if( nom.getText().toString().isEmpty()){
                    Toast.makeText(v.getContext(), "Veuillez saisir votre nom !",Toast.LENGTH_LONG).show();
                    return;
                }
                if( prenom.getText().toString().isEmpty()){
                    Toast.makeText(v.getContext(), "Veuillez saisir votre prenom !",Toast.LENGTH_LONG).show();
                    return;
                }
                if(!hac.isChecked() && !sess.isChecked()){
                    Toast.makeText(v.getContext(),"Veuillez faire ou moins un choix !",Toast.LENGTH_LONG).show();
                    return;
                }
                String choix = "";
                if(hac.isChecked() && sess.isChecked())
                    choix = "Hackathon et session de certification.";
                else if(hac.isChecked())
                    choix = "Hackathon";
                else
                    choix = "Session certification";

            Etudiant etudiant = new Etudiant();
            int ref = rGroup.getCheckedRadioButtonId();
            RadioButton checkedGenre = findViewById(ref);

            etudiant.setGenre(checkedGenre.getText().toString());
            etudiant.setNom(nom.getText().toString());
            etudiant.setPrenom(prenom.getText().toString());
            etudiant.setChoix(choix);

            new AlertDialog.Builder(MainActivity.this).setTitle("Attention !").setMessage("Voulez vous enregistrer ?").setPositiveButton(
                    "Oui",((dialog, which) -> {
                        Intent intent = new Intent(MainActivity.this, DisplayActivity.class);
                        intent.putExtra("etudiant",etudiant);
                        startActivity(intent);
                    })
            ).setNegativeButton("Annuler", null).show();
            }
        });


    }
}