package school.cesar.eduardo.projetoandroid;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Parcelable;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;


import java.io.Serializable;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import school.cesar.eduardo.projetoandroid.model.Users;

public class ListaActivity extends AppCompatActivity {

    ProgressDialog dialog;
    public static final String EXTRA_USERS = "users";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

    }
    @Override
    protected void onStart() {
        super.onStart();
        final ListView lista = (ListView) findViewById(R.id.lista);
        UsersService usersService = UsersService.retrofit.create(UsersService.class);
        dialog = new ProgressDialog(ListaActivity.this);
        dialog.setMessage("Carregando...");
        dialog.setCancelable(false);
        dialog.show();
        final Call<List<Users>> call = usersService.getPeople();
        call.enqueue(new Callback<List<Users>>() {
            @Override
            public void onResponse(Call<List<Users>> call, Response<List<Users>> response) {
                if (dialog.isShowing())
                    dialog.dismiss();

                final List<Users> listUsers= (List<Users>) response.body();

                if (listUsers != null) {

                    ListaAdapter adapter = new ListaAdapter(getBaseContext(), listUsers);
                    lista.setAdapter(adapter);
                    lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                            Users users= (Users) adapterView.getItemAtPosition(i);
                            Intent intent = new Intent(ListaActivity.this, UserDetail.class);
                            intent.putExtra("ID", listUsers.get(i).getId());

                            startActivity(intent);
                        }
                    });
                }
            }



            @Override
            public void onFailure(Call<List<Users>> call, Throwable t) {
                if (dialog.isShowing())
                    dialog.dismiss();
                Toast.makeText(getBaseContext(), "Problema de acesso", Toast.LENGTH_LONG).show();
            }
        });

    }



}
