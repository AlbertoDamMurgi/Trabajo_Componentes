package com.example.usuario.trabajo_componentes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(Bd.grupos.isEmpty()){
            Bd.rellenarDatos();
        }

    }
}
