package com.example.map_my_sona;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.google.android.material.textfield.TextInputLayout;

public class manualentry extends AppCompatActivity {

    TextInputLayout deptres;
    AutoCompleteTextView deptrestext;

    //
    TextInputLayout branch;
    AutoCompleteTextView branchtext;

    //
    TextInputLayout location;
    AutoCompleteTextView locationtext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manualentry);

        deptres=findViewById(R.id.deptresponsible);
        deptrestext=findViewById(R.id.deptresponsibletext);

        //
        branch=findViewById(R.id.branch);
        branchtext =findViewById(R.id.branchtext);

        //
        location =findViewById(R.id.location);
        locationtext=findViewById(R.id.locationtext);


        String[] dept={"ItBlock","UniversityBlock","CivilBlock","MechBlock","Auditorium","PgAuditorium","ValliapaAuditorium"};
        ArrayAdapter<String> itemAdapter=new ArrayAdapter<>(manualentry.this,R.layout.dropdowndept,dept);
        deptrestext.setAdapter(itemAdapter);

        String[] branch={"","","","",""};
        ArrayAdapter<String> branchAdapter=new ArrayAdapter<>(manualentry.this,R.layout.dropdownbranch,branch);
        branchtext.setAdapter(branchAdapter);

        String[] location={"electricity","watersupply","network","wiring","painting"};
        ArrayAdapter<String> locationAdapter=new ArrayAdapter<>(manualentry.this,R.layout.dropdownlocation,location);
        locationtext.setAdapter(locationAdapter);


    }
}