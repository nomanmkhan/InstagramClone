package com.example.apinstagramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class SignUp extends AppCompatActivity implements View.OnClickListener {

    private Button btnSave;
    private EditText edtName, edtPP , edtPS, edtKP, edtKS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        btnSave = findViewById(R.id.btnSave);
        edtName = findViewById(R.id.edtName);
        edtPS = findViewById(R.id.edtPS);
        edtPP = findViewById(R.id.edtPP);
        edtKP = findViewById(R.id.edtKP);
        edtKS = findViewById(R.id.edtKS);


        btnSave.setOnClickListener(SignUp.this);

    }

    @Override
    public void onClick(View v) {

        try {


        final ParseObject kickBoxer = new ParseObject("KickBoxer");
        kickBoxer.put("name",edtName.getText().toString());
        kickBoxer.put("punchSpeed",Integer.parseInt(edtPS.getText().toString()));
        kickBoxer.put("punchPower",Integer.parseInt(edtPP.getText().toString()));
        kickBoxer.put("kickSpeed",Integer.parseInt(edtKS.getText().toString()));
        kickBoxer.put("kickPower",Integer.parseInt(edtKP.getText().toString()));
        kickBoxer.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e==null)
                    FancyToast.makeText(SignUp.this,kickBoxer.get("name")+ " is saved to server",FancyToast.LENGTH_SHORT,FancyToast.SUCCESS,true).show();
                else FancyToast.makeText(SignUp.this,e.getMessage(),FancyToast.LENGTH_LONG,FancyToast.ERROR,true).show();
            }
        });
        }catch (Exception e){
            FancyToast.makeText(SignUp.this, e.getMessage(),FancyToast.LENGTH_LONG,FancyToast.ERROR,true).show();
        }
    }


}
