package com.example.apinstagramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.List;

public class SignUp extends AppCompatActivity implements View.OnClickListener {

    private Button btnSave, btnGetData;
    private EditText edtName, edtPP , edtPS, edtKP, edtKS;
    private TextView txtView;
    private String allKickBoxer;

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
        txtView = findViewById(R.id.txtView);
        btnGetData = findViewById(R.id.btnGetData);

//        txtView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                allKickBoxer = "";
//                 ParseQuery<ParseObject> parseQuery = ParseQuery.getQuery("KickBoxer");
//                 parseQuery.getInBackground("lCq44UZHZi", new GetCallback<ParseObject>() {
//                     @Override
//                     public void done(ParseObject object, ParseException e) {
//
//                         if (object != null && e == null){
//                             txtView.setText(object.get("name")+ " - " + " Punch Power: " + object.get("punchPower").toString());
//                         }
//
//                     }
//                 });
//            }
//        });


        btnSave.setOnClickListener(SignUp.this);

        btnGetData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ParseQuery<ParseObject> queryAll = ParseQuery.getQuery("KickBoxer");
                queryAll.findInBackground(new FindCallback<ParseObject>() {
                    @Override
                    public void done(List<ParseObject> objects, ParseException e) {
                        if (objects.size() > 0 ){
                            for (ParseObject KickBoxer : objects){
                                allKickBoxer = allKickBoxer + KickBoxer.get("name") + "\n";
                            }

                            FancyToast.makeText(SignUp.this,allKickBoxer,FancyToast.LENGTH_LONG,FancyToast.SUCCESS,true).show();


                        } else FancyToast.makeText(SignUp.this, e.getMessage(),FancyToast.LENGTH_LONG,FancyToast.ERROR,true).show();
                    }
                });

            }
        });

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
