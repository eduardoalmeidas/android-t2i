package school.cesar.eduardo.projetoandroid;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MyFragment extends Fragment {
    private static MyFragment fragment;


    public static MyFragment newInstance() {
        if(fragment == null) {
            fragment = new MyFragment();
        }

        return fragment;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.post_layout, container, false);
    }
}
