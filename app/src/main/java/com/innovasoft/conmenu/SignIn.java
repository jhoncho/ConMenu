package com.innovasoft.conmenu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.innovasoft.conmenu.Common.Common;
import com.innovasoft.conmenu.Model.Usuarios;
import com.rengwuxian.materialedittext.MaterialEditText;

public class SignIn extends AppCompatActivity {

    EditText edtTelefono, edtPassword;
    Button btnSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_sign_in );

        edtTelefono=(MaterialEditText)findViewById( R.id.edtTelefono );
        edtPassword=(MaterialEditText)findViewById( R.id.edtPassword );

        btnSignIn =(Button)findViewById( R.id.btnSingIn );
        // iniciamos la coneccion con firebase

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference tabla_usuario= database.getReference("Usuario");

        btnSignIn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final ProgressDialog mDialog=new ProgressDialog( SignIn.this );
                mDialog.setMessage( "Por favor espera" );
                mDialog.show();


                tabla_usuario.addValueEventListener( new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        //verificamos si el usuario no existe en la base de datos
                        if (dataSnapshot.child(edtTelefono.getText().toString()).exists())
                        {
                        // obtenemos la informacion del usuario

                        mDialog.dismiss();
                        Usuarios usuarios = dataSnapshot.child( edtTelefono.getText().toString()).getValue(Usuarios.class);

                        if (usuarios.getPassword().equals( edtPassword.getText().toString() ))
                        {
                            Intent homeIntent = new Intent( SignIn.this,Home.class );
                            Common.currentUser=usuarios;
                            startActivity( homeIntent );
                        }
                        else
                            {
                                Toast.makeText( SignIn.this, "Inicio de Sesion Fallido", Toast.LENGTH_SHORT ).show();
                            }
                        }
                        else
                        {
                            mDialog.dismiss();
                            Toast.makeText( SignIn.this, "No se encontro el usuario", Toast.LENGTH_SHORT ).show();
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
