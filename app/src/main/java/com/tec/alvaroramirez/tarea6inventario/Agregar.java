package com.tec.alvaroramirez.tarea6inventario;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashSet;

/**
 * Created by alvaroramirez on 5/23/18.
 */

public class Agregar extends AppCompatActivity {

    int note_id;
    EditText edtNombre;
    EditText edtPrecio;
    EditText edtDescripcion;
    Button btnAceptar;

    DatabaseReference dbProductos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar);

        dbProductos = FirebaseDatabase.getInstance().getReference("productos");

        Intent intent = getIntent();
        note_id = intent.getIntExtra("note_id", -1);

        edtNombre = findViewById(R.id.edtNombre);
        edtPrecio = findViewById(R.id.edtPrecio);
        edtDescripcion = findViewById(R.id.edtDescripcion);
        btnAceptar = findViewById(R.id.btnAceptar);

        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                add();
            }
        });


    }

    private void add(){
        String name = edtNombre.getText().toString();
        String precio = edtPrecio.getText().toString();
        String descripcion = edtDescripcion.getText().toString();

        if(!TextUtils.isEmpty(name)){
            String id = dbProductos.push().getKey();

            Producto producto = new Producto(id, name,Float.parseFloat(precio),descripcion);

            dbProductos.child(id).setValue(producto);

            Toast.makeText(this,"Producto agregado",Toast.LENGTH_SHORT).show();

            edtNombre.setText("");
            edtPrecio.setText("");
            edtDescripcion.setText("");
        }
        else{
            Toast.makeText(this, "Se necesita nombre",Toast.LENGTH_LONG).show();
        }
    }
}
