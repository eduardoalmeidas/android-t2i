package school.cesar.eduardo.projetoandroid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import school.cesar.eduardo.projetoandroid.model.Posts;

import static school.cesar.eduardo.projetoandroid.R.layout.post_layout;

public class PostsAdapter extends ArrayAdapter<Posts> {
    private Context context;
    private final List<Posts> list;

    public PostsAdapter(Context context, List<Posts> list) {
        super(context, R.layout.post_layout);
        this.context = context;
        this.list = list;
    }
    @Override
    public Posts getItem(int position){
        return  list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }


    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);

        Posts currentPost = list.get(position);

        View line = inflater.inflate(post_layout, null);


        TextView titlePost = line.findViewById(R.id.title_post);

        titlePost.setText(currentPost.getTitle());
        return line;
    }
}
