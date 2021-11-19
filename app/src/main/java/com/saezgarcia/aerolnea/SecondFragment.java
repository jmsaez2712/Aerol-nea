package com.saezgarcia.aerolnea;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.navigation.fragment.NavHostFragment;

import com.saezgarcia.aerolnea.databinding.FragmentSecondBinding;

import java.util.ArrayList;
import java.util.Random;

public class SecondFragment extends Fragment {

    private FragmentSecondBinding binding;

    /*Definimos las diferentes variables de instancia que vamos a necesitar en la clase*/
    Billete b;

    TextView tvPrice;
    TextView tvExtras;

    String origin, destiny, date;
    String [] data;
    ArrayList<String> options;
    String price;

    Button btPagar, btCancel;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        Bundle bundle = getArguments();
        //Obtenemos el objeto y una cadena del bundle y lo asignamos a su correspondientes variables
        b = bundle.getParcelable("ticket");
        binding = FragmentSecondBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /*Asignamos a las variables sus correspodientes valores para poder trabajar con ellas y mostrarlas por pantalla*/
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Asignamos a los botones sus listener correspondientes para poder vincularle acciones
        binding.btContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToNextActivity();
            }
        });

        binding.btCancelar.setOnClickListener((View v)->{
            NavHostFragment.findNavController(this).navigate(R.id.action_SecondFragment_to_FirstFragment);
        });

        /*Obtenemos los diferentes valores que necesitamos del objeto Billete y las asignamos a sus correspondientes variables. */
        origin = b.getOrigin();
        destiny = b.getDestiny();
        date = b.getDate();

        options = b.getCheckbox();

        price = returnPrice();

        //Agragamos al los diferentes textview las diferentes cadenas que necesitamos mostrar por pantalla
        binding.tvTotalPrice.append("\n"+price);

        for (int i = 0; i < options.size(); i++) {
            binding.tvExtrasSecond.append("\n\t\t"+options.get(i));
        }


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    /*Método que nos genera la semilla con la suma de los codigos ASCII de los diferentes valores
    a partir de la fecha, el origen y el destino del viaje. Tras eso, nos devuelve una cadena,
    que es el precio por el billete para esos tres valores de origen, destino y fecha.*/
    public String returnPrice(){
        int seed = 0;
        String allData = origin+destiny+date;

        for (int i = 0; i < allData.length(); i++){
            seed += (int) allData.charAt(i);
        }

        int price;
        Random r = new Random(seed);

        price = r.nextInt() * 1000;
        if(price < 0){
            price = r.nextInt() *1000;
        }

        return String.valueOf(price).substring(0,3);
    }

    /*Método donde metemos los datos necesarios en un bundle y vamos al siguiente fragment*/
    public void goToNextActivity(){
        Bundle bundle = new Bundle();
        bundle.putParcelable("ticket", b);
        bundle.putString("price", price);
        NavHostFragment.findNavController(this).navigate(R.id.action_SecondFragment_to_thirdFragment, bundle);
    }

}