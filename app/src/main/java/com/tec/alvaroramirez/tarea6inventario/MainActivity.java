package com.tec.alvaroramirez.tarea6inventario;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView listViewProductos;
    DatabaseReference dbProductos;
    List<Producto> listaProductos;
    DatabaseReference deleteDB;

    static ArrayAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listViewProductos = findViewById(R.id.listViewProductos);
        dbProductos = FirebaseDatabase.getInstance().getReference("productos");
        listaProductos = new ArrayList<>();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), Agregar.class);
                startActivity(intent);
            }
        });





        /*SharedPreferences sharedPreferences = getSharedPreferences("cr.ac.itcr.andreifuentes.notasenclase", Context.MODE_PRIVATE);
        HashSet<String> set = (HashSet<String>)sharedPreferences.getStringSet("notes", null);

        if (set == null){
            notas.add("nota 1");
        }
        else {
            notas = new ArrayList(set);
        }*/




        /*listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(), Agregar.class);
                intent.putExtra("note_id", i);
                startActivity(intent);
            }
        });
*/


        listViewProductos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                final int producto_id_por_eliminar = i;

                new AlertDialog.Builder(MainActivity.this)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Está seguro?")
                        .setMessage("Desea eliminar el producto?")
                        .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                /*notas.remove(note_id_por_eliminar);
                                arrayAdapter.notifyDataSetChanged();

                                SharedPreferences sharedPreferences = getSharedPreferences("cr.ac.itcr.andreifuentes.notasenclase", Context.MODE_PRIVATE);
                                HashSet<String> set = new HashSet<>(notas);
                                sharedPreferences.edit().putStringSet("notes", set).apply();*/

                                deleteProduct(listaProductos.get(producto_id_por_eliminar).getId());
                                listaProductos.remove(producto_id_por_eliminar);

                            }
                        })
                        .setNegativeButton("No", null)
                        .show();


                return true;
            }
        });


    }

    private void deleteProduct(String idProducto){
        deleteDB = FirebaseDatabase.getInstance().getReference("productos").child(idProducto);

        deleteDB.removeValue();

        Toast.makeText(this,"Producto eliminado",Toast.LENGTH_SHORT);

    }

    @Override
    protected void onStart() {
        super.onStart();

        dbProductos.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                listaProductos.clear();
                for(DataSnapshot pSnapShot: dataSnapshot.getChildren()){
                    Producto producto = pSnapShot.getValue(Producto.class);

                    listaProductos.add(producto);
                }

                ListaProductos adapter = new ListaProductos(MainActivity.this,listaProductos);
                listViewProductos.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }
}
