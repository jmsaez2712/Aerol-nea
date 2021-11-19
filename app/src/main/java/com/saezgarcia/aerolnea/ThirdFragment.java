package com.saezgarcia.aerolnea;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;


public class ThirdFragment extends Fragment {

    /*Definimos las diferentes variables de instancia que vamos a necesitar en la clase*/
    Billete b;

    String price, origin, destiny, date;
    String [] data;
    ArrayList<String> options;

    TextView tvFacture;
    Button btFinish;

    public ThirdFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Obtenemos el objeto y una cadena del bundle y lo asignamos a su correspondientes variables
        Bundle bundle = getArguments();
        b = bundle.getParcelable("ticket");
        price = bundle.getString("price");
        return inflater.inflate(R.layout.fragment_third, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        //Instanciamos los diferentes componentes que vamos a necesitar para mostrar en pantalla
        tvFacture = view.findViewById(R.id.tvFacture);
        btFinish = view.findViewById(R.id.btFinish);
        btFinish.setOnClickListener((View v)->{
            NavHostFragment.findNavController(this).navigate(R.id.action_thirdFragment_to_FirstFragment);
        });

        //Asignamos a las variables los valores que necesitamos, obteniendolos del objeto Billete
        origin = b.getOrigin();
        destiny = b.getDestiny();
        date = b.getDestiny();
        data = b.getData();
        options = b.getCheckbox();

        //Creamos una cadena y la formateamos con los datos que necesitamos para hacer la factura
        String exit = "\tDatos del comprador: ";
        for (int i = 0; i < data.length; i++){
            exit += "\n\t"+data[i];
        }
        exit+="\n\tFactura del billete \t\t\tIVA 21% \n";
        exit += "\tOrigen: "+origin+ "\t";
        exit += "\tDestino: "+destiny;
        exit+="\n\t"+"Precio: "+price+"\n \tExtras: ";
        for(int i = 0; i < options.size(); i++){
            exit+="\n\t - "+options.get(i);
        }

        //La agregamos al textView anteriormente instanciado.
        tvFacture.append(exit);

        super.onViewCreated(view, savedInstanceState);
    }
}