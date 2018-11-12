package school.cesar.eduardo.projetoandroid;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private MySharedPreference preference;
    EditText edtLogin;
    EditText edtPassword;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        preference = MySharedPreference.getInstance(this);

        preference.setUserName("admin");
        preference.setPassword("123");

        edtLogin = findViewById(R.id.edtLogin);
        edtPassword = findViewById(R.id.edtPassword);
        btnLogin = findViewById(R.id.btnLogin);
        login();
    }

    public void login(){
       btnLogin.setOnClickListener(new View.OnClickListener() {
           @Override


           public void onClick(View v) {

               if(edtLogin.getText().toString().equals("admin") && edtPassword.getText().toString().equals("123") ) {
                   Intent i = new Intent(MainActivity.this, Main2Activity.class);
                   startActivity(i);
               }else{
                   Toast.makeText(MainActivity.this,"Dados incorretos!",Toast.LENGTH_SHORT).show();
               }


           }
       });

    }
}
