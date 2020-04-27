package e.udayb.celebapp;


import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;


/**
 * A simple {@link Fragment} subclass.
 */
public class SecondaryFragment extends Fragment {
    private RadioButton button1;
    private RadioButton button2;
    private RadioButton button3;
    private SharedPreferences pref;


    public SecondaryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_secondary, container, false);
        button1 = (RadioButton) v.findViewById(R.id.radioButton1);
        button2 = (RadioButton) v.findViewById(R.id.radioButton2);
        button3 = (RadioButton) v.findViewById(R.id.radioButton3);

        pref = this.getActivity().getSharedPreferences("value", Context.MODE_PRIVATE);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pref.edit().putInt("choice",4).apply();
                Intent intent = getActivity().getIntent();
                getActivity().overridePendingTransition(0,0);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                getActivity().finish();
                getActivity().overridePendingTransition(0,0);
                startActivity(intent);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pref.edit().putInt("choice",6).apply();
                Intent intent = getActivity().getIntent();
                getActivity().overridePendingTransition(0,0);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                getActivity().finish();
                getActivity().overridePendingTransition(0,0);
                startActivity(intent);


            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pref.edit().putInt("choice",8).apply();
                Intent intent = getActivity().getIntent();
                getActivity().overridePendingTransition(0,0);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                getActivity().finish();
                getActivity().overridePendingTransition(0,0);
                startActivity(intent);


            }
        });
        return v;
    }

}
