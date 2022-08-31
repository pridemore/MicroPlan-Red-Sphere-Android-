package com.example.microplanredsphereandroid;

import static android.app.Activity.RESULT_OK;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import com.example.microplanredsphereandroid.utils.Constants.*;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.microplanredsphereandroid.utils.Utils;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;


public class DeclarationAndAcceptanceFragment extends Fragment {
    public final int REQ_CODE = 859;
    public final int REQ_CODE_2 = 8539;
    public final int REQ_CODE_3 = 8739;
    private static final String TAG = "Declaration and Acceptance";
    final Calendar myCalendar= Calendar.getInstance();
    ImageView backIcon;
    ImageView menu;
    TextView title;
    private Bitmap bitmapBorrowerSignature, bitmapWitnessSignature, bitmapWitnessSignature2;
    private ImageView borrowerSignature, witness1Signature, witness2Signature;
    Button btn_previous, btn_nxt,buttonBorrowerSignature,buttonWitness1Signature,buttonWitness2Signature;
    private TextInputEditText dateOfBorrowerSignature,dateOfWitness1Signature,dateOfWitness2Signature;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_declaration_and_acceptance, container, false);
        backIcon=view.findViewById(R.id.left_icon);
        menu=view.findViewById(R.id.right_icon);
        title=view.findViewById(R.id.title);
        btn_previous=view.findViewById(R.id.btn_previous);
        btn_nxt=view.findViewById(R.id.btn_nxt);
        dateOfBorrowerSignature=view.findViewById(R.id.dateOfBorrowerSignature);
        dateOfWitness1Signature=view.findViewById(R.id.dateOfWitness1Signature);
        dateOfWitness2Signature=view.findViewById(R.id.dateOfWitness2Signature);
        buttonBorrowerSignature=view.findViewById(R.id.buttonBorrowerSign);
        buttonWitness1Signature=view.findViewById(R.id.buttonWitness1Sign);
        buttonWitness2Signature=view.findViewById(R.id.buttonWitness2Sign);
        borrowerSignature=view.findViewById(R.id.borrowerSignature);
        witness1Signature=view.findViewById(R.id.witness1Signature);
        witness2Signature=view.findViewById(R.id.witness2Signature);
        title.setText("Declaration and Acceptance");

        btn_previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((NewApplicationActivity)getActivity()).replaceFragment(new NxtOfKin2DetailsFragment());
//                Intent intent=new Intent(getActivity(),HomeActivity.class);
//                startActivity(intent);
            }
        });

        btn_nxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((NewApplicationActivity)getActivity()).replaceFragment(new DeductionSsbFormFragment());
            }
        });

        DatePickerDialog.OnDateSetListener date =new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH,month);
                myCalendar.set(Calendar.DAY_OF_MONTH,day);
                //updateLabel();
            }
        };
        dateOfBorrowerSignature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(getActivity(),getDate(dateOfBorrowerSignature),myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show();

            }
        });
        dateOfWitness1Signature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new DatePickerDialog(getActivity(),getDate(dateOfWitness1Signature),myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show();

            }
        });

        dateOfWitness2Signature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(getActivity(),getDate(dateOfWitness2Signature),myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show();

            }
        });

        buttonBorrowerSignature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(requireContext(), Signature.class), REQ_CODE);
            }
        });

        buttonWitness1Signature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(requireContext(), Signature.class), REQ_CODE_2);
            }
        });

        buttonWitness2Signature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(requireContext(), Signature.class), REQ_CODE_3);
            }
        });




        return view;
    }

    private DatePickerDialog.OnDateSetListener getDate(TextInputEditText dateView){
        DatePickerDialog.OnDateSetListener date =new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH,month);
                myCalendar.set(Calendar.DAY_OF_MONTH,day);
                String myFormat="MM/dd/yy";
                SimpleDateFormat dateFormat=new SimpleDateFormat(myFormat, Locale.US);
                dateView.setText(dateFormat.format(myCalendar.getTime()));
            }
        };
        return date;
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQ_CODE && resultCode == RESULT_OK) {
            bitmapBorrowerSignature = Utils.getLatestSignature(requireContext());
            borrowerSignature.setImageBitmap(bitmapBorrowerSignature);
        }
        if (requestCode == REQ_CODE_2 && resultCode == RESULT_OK) {
            bitmapWitnessSignature = Utils.getLatestSignature(requireContext());
            witness1Signature.setImageBitmap(bitmapWitnessSignature);
        }
        if (requestCode == REQ_CODE_3 && resultCode == RESULT_OK) {
            bitmapWitnessSignature2 = Utils.getLatestSignature(requireContext());
            witness2Signature.setImageBitmap(bitmapWitnessSignature2);
        }
    }
}