package com.example.listilla;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    // Model: Record (intents=puntuació, nom)
    class Record {
        public int intents;
        public String nom;

        public Record(int _intents, String _nom ) {
            intents = _intents;
            nom = _nom;
        }
    }
    // Model = Taula de records: utilitzem ArrayList
    ArrayList<Record> records;

    // ArrayAdapter serà l'intermediari amb la ListView
    ArrayAdapter<Record> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicialitzem model
        records = new ArrayList<Record>();
        // Afegim alguns exemples
        records.add( new Record(33,"Manolo") );
        records.add( new Record(12,"Pepe") );
        records.add( new Record(42,"Laura") );

        // Inicialitzem l'ArrayAdapter amb el layout pertinent
        adapter = new ArrayAdapter<Record>( this, R.layout.list_item, records )
        {
            @Override
            public View getView(int pos, View convertView, ViewGroup container)
            {
                // getView ens construeix el layout i hi "pinta" els valors de l'element en la posició pos
                if( convertView==null ) {
                    // inicialitzem l'element la View amb el seu layout
                    convertView = getLayoutInflater().inflate(R.layout.list_item, container, false);
                }
                // "Pintem" valors (també quan es refresca)
                ((TextView) convertView.findViewById(R.id.nom)).setText(getItem(pos).nom);
                ((TextView) convertView.findViewById(R.id.intents)).setText(Integer.toString(getItem(pos).intents));
                return convertView;
            }

        };

        ArrayList<String> noms = new ArrayList<String>();
        noms.add("Manolo");
        noms.add("Pepe");
        noms.add("Laura");
        noms.add("Mario");
        noms.add("Perico");
        noms.add("Lourdes");
        noms.add("Manuel");
        noms.add("Lucía");
        noms.add("Merche");
        noms.add("Sara");
        noms.add("Marta");
        noms.add("Carla");
        noms.add("Carlota");
        noms.add("Diego");
        noms.add("Juan");

        ArrayList<String> cognoms = new ArrayList<String>();
        cognoms.add("González");
        cognoms.add("Martínez");
        cognoms.add("López");
        cognoms.add("Gutiérrez");
        cognoms.add("González");
        cognoms.add("Alonso");
        cognoms.add("Abelló");
        cognoms.add("Arias");
        cognoms.add("Cordovilla");
        cognoms.add("Ureña");
        cognoms.add("Pérez");
        cognoms.add("Huici");
        cognoms.add("Messi");
        cognoms.add("Ronaldo");
        cognoms.add("Silva");

        // busquem la ListView i li endollem el ArrayAdapter
        ListView lv = (ListView) findViewById(R.id.recordsView);
        lv.setAdapter(adapter);

        // botó per afegir entrades a la ListView
        Button b = (Button) findViewById(R.id.button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < 500; i++) {
                    int intents = new Random().nextInt(100) + 1;
                    String nom = noms.get(new Random().nextInt(noms.size()));
                    String cognom = cognoms.get(new Random().nextInt(cognoms.size()));
                    String nomComplet = nom + " " + cognom;
                    records.add(new Record(intents, nomComplet));
                }
                adapter.notifyDataSetChanged();
            }
        });

    }
}