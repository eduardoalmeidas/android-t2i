package school.cesar.eduardo.projetoandroid;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import school.cesar.eduardo.projetoandroid.model.Posts;
import school.cesar.eduardo.projetoandroid.model.Users;

public class PostDetailActivity extends AppCompatActivity {

    private Users mUsers;
    private ProgressDialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_detail);

        final TextView title = (TextView) findViewById(R.id.mTextTitle);
        final TextView body = (TextView) findViewById(R.id.mTextBody);
        final TextView userId = (TextView) findViewById(R.id.mTextPUserId);


        Intent intent = getIntent();
        final String id = Integer.toString(intent.getIntExtra("ID", 0));

        final PostsService postService = PostsService.retrofit.create(PostsService.class);
        final Call<Posts> call = postService.getPostId(id);


        dialog = new ProgressDialog(PostDetailActivity.this);
        dialog.setMessage("Carregando...");
        dialog.setCancelable(false);
        dialog.show();
        call.enqueue(new Callback<Posts>() {
            @Override
            public void onResponse(Call<Posts> call, Response<Posts> response) {
                if (dialog.isShowing())
                    dialog.dismiss();
                Posts post = response.body();
                title.setText(post.getTitle());
                body.setText(post.getBody());
                userId.setText(Integer.toString(post.getUserId()));
            }


            @Override
            public void onFailure(Call<Posts> call, Throwable t) {
                if (dialog.isShowing())
                    dialog.dismiss();
                Toast.makeText(getBaseContext(), "Não foi possível fazer a conexão", Toast.LENGTH_SHORT).show();

            }
        });

    }
}
