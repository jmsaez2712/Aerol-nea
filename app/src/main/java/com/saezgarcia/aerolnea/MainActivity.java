package com.saezgarcia.aerolnea;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.saezgarcia.aerolnea.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

    }


    /*Método que nos permite inflar el layour del menu de overflow*/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    /*Método que nos permite asignar acciones a cada uno de los items del menu de overflow*/
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        if (id == R.id.menu_terminos) {
            Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://terminosycondicionesdeusoejemplo.com"));
            startActivity(i);
            return true;
        } else if (id == R.id.menu_data){
            Toast.makeText(this, "No disponible en este momento", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.menu_documents){
            Toast.makeText(this, "No disponible en este momento", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.menu_compa){
            Toast.makeText(this, "No disponible en este momento", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.menu_travels){
            Toast.makeText(this, "No disponible en este momento", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.menu_cards){
            Toast.makeText(this, "No disponible en este momento", Toast.LENGTH_SHORT).show();
        } else if ( id == R.id.menu_fligth){
            Toast.makeText(this, "No disponible si no compras un billete", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.menu_info){
            Toast.makeText(this, "No disponible en este momento", Toast.LENGTH_SHORT).show();
        } else if(id == R.id.menu_discounts){
            Toast.makeText(this, "No disponible en este momento", Toast.LENGTH_SHORT).show();
        } else if(id == R.id.menu_reviste){
            Toast.makeText(this, "No disponible en este momento", Toast.LENGTH_SHORT).show();
        } else if(id == R.id.menu_help){
            Toast.makeText(this, "Hubo un error inesperado", Toast.LENGTH_SHORT).show();
        } else if(id == R.id.menu_contact){
            Toast.makeText(this, "Hubo un error inesperado", Toast.LENGTH_SHORT).show();
        } else if(id == R.id.menu_privacy){
            Toast.makeText(this, "Hubo un error inesperado", Toast.LENGTH_SHORT).show();
        } else if(id == R.id.menu_privacy2){
            Toast.makeText(this, "No disponible en este momento", Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }
}