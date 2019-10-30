package com.innovasoft.conmenu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.innovasoft.conmenu.Model.Usuarios;
import com.rengwuxian.materialedittext.MaterialEditText;

public class Registrate extends AppCompatActivity {

    MaterialEditText edtTelefono, edtNombre, edtPassword;
    Button btnRegistrase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_registrate );

        edtNombre=(MaterialEditText)findViewById( R.id.edtNombre );
        edtTelefono=(MaterialEditText)findViewById( R.id.edtTelefono );
        edtPassword=(MaterialEditText)findViewById( R.id.edtPassword );

        btnRegistrase =(Button) findViewById( R.id.btnSingUp);

        // iniciamos la coneccion con firebase

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference tabla_usuario= database.getReference("Usuario");

        btnRegistrase.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog mDialog=new ProgressDialog( Registrate.this );
                mDialog.setMessage( "Por favor espera" );
                mDialog.show();

                tabla_usuario.addValueEventListener( new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        //comprovamos si ya el teléfono del usuario

                        if (dataSnapshot.child( edtTelefono.getText().toString()).exists())
                        {
                            Toast.makeText( Registrate.this,"Numero de Telefono ya Registrado", Toast.LENGTH_SHORT ).show();
                        }
                        else
                            {
                                mDialog.dismiss();
                                Usuarios usuarios=new Usuarios( edtNombre.getText().toString(),edtPassword.getText().toString() );
                                tabla_usuario.child( edtTelefono.getText().toString() ).setValue( usuarios );
                                Toast.makeText( Registrate.this,"Registro Satisfactorio", Toast.LENGTH_SHORT ).show();
                                finish();
                            }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                } );

            }
        } );
    }
}
