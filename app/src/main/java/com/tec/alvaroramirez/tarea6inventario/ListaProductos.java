package com.tec.alvaroramirez.tarea6inventario;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by alvaroramirez on 5/24/18.
 */

public class ListaProductos extends ArrayAdapter<Producto> {
    private Activity context;
    private List<Producto> listaProductos;

    public ListaProductos(Activity context, List<Producto> listaProductos){
        super(context,R.layout.content_main,listaProductos);
        this.context = context;
        this.listaProductos = listaProductos;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView,ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.content_main,null,true);

        TextView txtNombre = listViewItem.findViewById(R.id.txtNombre);
        TextView txtDescripcion = listViewItem.findViewById(R.id.txtDescripcion);
        TextView txtPrecio = listViewItem.findViewById(R.id.txtPrecio);

        Producto producto = listaProductos.get(position);

        txtNombre.setText(producto.getNombre());
        txtPrecio.setText("Precio: " + producto.getPrecio().toString());
        txtDescripcion.setText("Descripcion: " + producto.getDescripcion());


        return listViewItem;
    }
}
