package school.cesar.eduardo.projetoandroid;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import school.cesar.eduardo.projetoandroid.model.Users;

import static school.cesar.eduardo.projetoandroid.R.layout.*;

public class ListaAdapter extends ArrayAdapter<Users> {
    private Context context;
    private final List<Users> list;


    public ListaAdapter(Context context, List<Users> list) {
        super(context, R.layout.item_lista);
        this.context = context;
        this.list = list;
    }


    @Override
    public Users getItem(int position){
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

        Users currentPessoa = list.get(position);

        View line = inflater.inflate(item_lista, null);


        TextView nome = line.findViewById(R.id.nome);

        nome.setText(currentPessoa.getName());
        return line;
    }
}
