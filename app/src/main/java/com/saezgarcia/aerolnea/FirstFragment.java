package com.saezgarcia.aerolnea;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.saezgarcia.aerolnea.databinding.FragmentFirstBinding;

import java.util.ArrayList;

public class FirstFragment extends Fragment {

    private Spinner spinnerOrigin;
    private Spinner spinnerDestiny;

    private EditText etDate, etName, etSurname, etDirection, etEmail, etPhone;

    private ArrayList<String> stringOptions;
    private CheckBox[] options;
    private String[] data;

    private CheckBox cbClase, cbComidas, cbAsiento, cbMascota;
    private Switch swSeguro;

    private RadioButton rbYes, rbNo;
    private RadioGroup radioGroup;

    private FragmentFirstBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentFirstBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    /*Método del fragmento donde definiremos las diferentes variables para este fragmento y sus correspondientes acciones*/
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.ibPremium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });

        //Le asignamos una accion al boton con un listener
        binding.btPagar.setOnClickListener((View v)->{
            nextActivity();
        } );

        //Le asignamos una accion al boton con un listener
        binding.ibPremium.setOnClickListener((View v)->{
            Snackbar sb = Snackbar.make(view.findViewById(R.id.btPagar), "Hubo un error en el pago", BaseTransientBottomBar.LENGTH_SHORT);
            sb.setAction("Reintentar",new SnackBarListener());
            sb.show();;
        });

    //region checkbox listener En esta parte le asignamos a cada checkbox un listener para poder mostrar un toast cuando se seleccionen
        binding.cbClase.setOnClickListener((View v)->{
            Toast.makeText(view.getContext(), "Has seleccionado: "+binding.cbClase.getText().toString(), Toast.LENGTH_SHORT).show();
        });

        binding.cbComidas.setOnClickListener((View v)->{
            Toast.makeText(view.getContext(), "Has seleccionado: "+binding.cbComidas.getText().toString(), Toast.LENGTH_SHORT).show();
        });

        binding.cbVentanilla.setOnClickListener((View v)->{
            Toast.makeText(view.getContext(), "Has seleccionado: "+binding.cbVentanilla.getText().toString(), Toast.LENGTH_SHORT).show();
        });

        binding.cbMascota.setOnClickListener((View v)->{
            Toast.makeText(view.getContext(), "Has seleccionado: "+binding.cbMascota.getText().toString(), Toast.LENGTH_SHORT).show();
        });
    //endregion checkbox listener



        binding.swSeguro.setOnCheckedChangeListener((compoundButton, b) -> {
            if(b){
                DialogFragment df = new ExitDialog();
                df.show(this.getParentFragment().getParentFragmentManager(),"tag");
            }
        });

        //region spinners
        Spinner spinner = view.findViewById(R.id.spinner);
    // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(view.getContext(),
                R.array.tratamiento, android.R.layout.simple_spinner_item);
    // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    // Apply the adapter to the spinner
        spinner.setAdapter(adapter);


        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(view.getContext(), R.array.destinies,
                android.R.layout.simple_spinner_dropdown_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spinner2.setAdapter(adapter2);
        binding.spinner3.setAdapter(adapter2);
        //endregion spinners

    }

    /*Método que nos permite recoger los diferentes datos, pasarlos a un objeto Billete y poder enviarlo al siguiente fragmento*/
    private void nextActivity() {
        Bundle bundle = new Bundle();

        options = new CheckBox[]{binding.cbClase, binding.cbComidas, binding.cbVentanilla, binding.cbMascota};
        stringOptions = checkOptions(options);

        if(binding.rdYes.isChecked()){
            stringOptions.add("Movilidad reducida: "+binding.rdYes.getText().toString());
        } else if (binding.rbNo.isChecked()){
            stringOptions.add("Movilidad reducida: "+binding.rbNo.getText().toString());
        }

        if(binding.swSeguro.isActivated()){
            stringOptions.add(binding.swSeguro.getText().toString());
        }

        Billete b = new Billete(binding.spinner2.getSelectedItem().toString(),
                binding.spinner3.getSelectedItem().toString(),
                binding.etFecha.getText().toString(),
                stringOptions,getData());

        bundle.putParcelable("ticket",b);

        NavHostFragment.findNavController(this).navigate(R.id.action_FirstFragment_to_SecondFragment,bundle);

    }

    /*Método que nos recoge los diferenes datos de los inputs y lo almacena en un array que será posteriormente tratado*/
    public String[] getData(){
            data = new String [] {"Nombre: "+binding.etNombre.getText().toString(),
                "Apellido: "+binding.etApellido.getText().toString(),
                "Dirección: "+binding.etDireccion.getText().toString(),
                "E-mail: "+binding.etEmail.getText().toString(),
                "Teléfono: "+binding.etTelefono.getText().toString()};

        return data;
    }

    /*Obtenemos que checkbox y radiobuttons están seleccionados y los almacenamos en un arraylist para su posterior tratamiento*/
    public ArrayList<String> checkOptions (CheckBox[] options){
        CheckBox[] arrayOptions = options;
        ArrayList <String> stringOptions = new ArrayList<String>();
        for (int i = 0; i < arrayOptions.length; i++){
            if(arrayOptions[i].isChecked()){
                stringOptions.add(arrayOptions[i].getText().toString()+": 15€");
            }
        }
        return stringOptions;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


}