package com.innovasoft.conmenu;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button btnSignIn, btnSignUp;
    TextView txtSlogan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        btnSignIn = (Button) findViewById( R.id.btnSingIn );
        btnSignUp = (Button) findViewById( R.id.btnSignUp );

        txtSlogan = (TextView) findViewById( R.id.txtSlogan );
        Typeface face = Typeface.createFromAsset( getAssets(),"fonts/Nabila.ttf" );// agregamos la fuente para el texto de la app
        txtSlogan.setTypeface( face );

        //Evento para cuando se haga clic en el boton
        btnSignIn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        } );

        btnSignUp.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        } );

    }
}
