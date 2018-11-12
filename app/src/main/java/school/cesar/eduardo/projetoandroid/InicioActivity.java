package school.cesar.eduardo.projetoandroid;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import school.cesar.eduardo.projetoandroid.model.Posts;
import school.cesar.eduardo.projetoandroid.model.Users;

public class InicioActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ProgressDialog dialog;
    public static final String EXTRA_POSTS = "posts";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

}
    @Override
    protected void onStart() {
        super.onStart();
        final ListView lista = (ListView) findViewById(R.id.lista_post);
        PostsService postsService = PostsService.retrofit.create(PostsService.class);
        dialog = new ProgressDialog(InicioActivity.this);
        dialog.setMessage("Carregando...");
        dialog.setCancelable(false);
        dialog.show();
        final Call<List<Posts>> call = postsService.getPosts();
        call.enqueue(new Callback<List<Posts>>() {

            @Override
            public void onResponse(Call<List<Posts>> call, Response<List<Posts>> response) {

                if (dialog.isShowing())
                    dialog.dismiss();

                final List<Posts> listPosts = (List<Posts>) response.body();

                if (listPosts != null) {

                    PostsAdapter adapter = new PostsAdapter(getBaseContext(), listPosts);
                    lista.setAdapter(adapter);
                    lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                            Posts posts = (Posts) adapterView.getItemAtPosition(i);
                            Intent intent = new Intent(InicioActivity.this, PostDetailActivity.class);
                            intent.putExtra("ID", listPosts.get(i).getId());

                            startActivity(intent);
                        }
                    });
                }
            }


            @Override
            public void onFailure(Call<List<Posts>> call, Throwable t) {
                if (dialog.isShowing())
                    dialog.dismiss();
                Toast.makeText(getBaseContext(), "Problema de acesso", Toast.LENGTH_LONG).show();
            }
        });
    }
        @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();



        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if(id== R.id.nav_profile){
            Intent i = new Intent(InicioActivity.this, InicioActivity.class);
            startActivity(i);
        }else if(id == R.id.nav_usuarios){
            Log.i("ERRO", "--------------------------------");
            Intent i = new Intent(InicioActivity.this, ListaActivity.class);
            startActivity(i);
        }else if(id == R.id.nav_posts){

        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

