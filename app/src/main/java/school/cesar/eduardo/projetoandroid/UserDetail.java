package school.cesar.eduardo.projetoandroid;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import school.cesar.eduardo.projetoandroid.model.Users;

public class UserDetail extends AppCompatActivity {
    private TextView mTextName;
    private EditText mTextUserName;
    private TextView mTextEmail;

    private TextView mTextUsuario;


    private ProgressDialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);

        final TextView name = (TextView) findViewById(R.id.mTextName);
        final TextView userName = (TextView) findViewById(R.id.mTextUserName);
        final TextView email = (TextView) findViewById(R.id.mTextEmail);


        Intent intent = getIntent();
        final String id = Integer.toString(intent.getIntExtra("ID", 0));

        final UsersService usersService = UsersService.retrofit.create(UsersService.class);
        final Call<Users> call = usersService.getUserId(id);
        dialog = new ProgressDialog(UserDetail.this);
        dialog.setMessage("Carregando...");
        dialog.setCancelable(false);
        dialog.show();
        call.enqueue(new Callback<Users>() {
            @Override
            public void onResponse(Call<Users> call, Response<Users> response) {
                if (dialog.isShowing())
                    dialog.dismiss();
                Users user = response.body();
                name.setText(user.getName());
                userName.setText(user.getUsername());
                email.setText(user.getEmail());

            }

            @Override
            public void onFailure(Call<Users> call, Throwable t) {
                if (dialog.isShowing())
                    dialog.dismiss();
                Toast.makeText(getBaseContext(), "Não foi possível fazer a conexão", Toast.LENGTH_SHORT).show();

            }
        });

        MyFragment fragment = MyFragment.newInstance();

        //Recupera a instancia do fragment manager responsável  por gerenciar os fragments.
        FragmentManager manager = getFragmentManager();
        //Inicia uma transação para adicionar, remover ou alterar o fragment.
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragment_place, fragment);
        //Realiza a alteração. Importante sem o commando abaixo o fragment não é atualizado.
        transaction.commit();
    }
}