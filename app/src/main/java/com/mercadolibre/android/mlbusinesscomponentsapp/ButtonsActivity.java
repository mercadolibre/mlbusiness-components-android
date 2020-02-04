package com.mercadolibre.android.mlbusinesscomponentsapp;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;
import com.mercadolibre.android.mlbusinesscomponents.components.explodingbutton.ProgressButton;
import com.mercadolibre.android.mlbusinesscomponents.components.explodingbutton.OnFinishAnimationListener;


public class ButtonsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_botones);
        ProgressButton buttonProgress = findViewById(R.id.progressTest1);
        buttonProgress.Builder()
                .setTextSize(16)
                .setTextInformation("Procesar Pago","Cargando")
                .setColorText(R.color.ui_meli_red);

        buttonProgress.setOnClickListener(v ->
                Toast.makeText(ButtonsActivity.this,"hola", Toast.LENGTH_SHORT).show()
        );

        buttonProgress.addFinishAnimationListener(() -> Toast.makeText(ButtonsActivity.this, "hola", Toast.LENGTH_SHORT).show());

        new Handler().postDelayed(() -> buttonProgress.finishProgress(
                R.color.ui_meli_red,
                null
        ), 3000);
    }

    private OnFinishAnimationListener getFinishAnimationListener(){
        return () -> Toast.makeText(this, "A nimation finish", Toast.LENGTH_SHORT).show();
    }
}
