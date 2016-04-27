package fs.tandat.soccernetwork;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

import fs.tandat.soccernetwork.bean.Match;
import fs.tandat.soccernetwork.helpers.MatchHelper;


/**
 * A simple {@link Fragment} subclass.
 */
public class YourMatchesFragment extends Fragment {
    TableLayout tableLayout;
    FragmentTransaction ft;
    String username;

    public YourMatchesFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        username = getArguments().getString("username");
        View view = inflater.inflate(R.layout.fragment_your_matches, container, false);
//        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext());
//        username = prefs.getString("username", "");
//        Log.d("USERNAME", username);
        tableLayout = (TableLayout) view.findViewById(R.id.tblYourMatches);

        // get fragment transaction to change fragment
        ft = getFragmentManager().beginTransaction();

        loadYourMatches();

        return view;
    }

    private void loadYourMatches() {
        MatchHelper matchHelper = new MatchHelper(getActivity());
        ArrayList<Match> your_matches = matchHelper.getYourMatches(username);

        TableLayout.LayoutParams tlParams = new TableLayout.LayoutParams(
                TableLayout.LayoutParams.WRAP_CONTENT,
                TableLayout.LayoutParams.WRAP_CONTENT);
        TableRow.LayoutParams trParams = new TableRow.LayoutParams(
                TableRow.LayoutParams.WRAP_CONTENT,
                TableRow.LayoutParams.WRAP_CONTENT);

        if(your_matches != null){
            for(int i=0; i<your_matches.size(); i++){
                final Match m = your_matches.get(i);

                TableRow tr = new TableRow(getActivity());
                tr.setGravity(Gravity.CENTER);
                tr.setLayoutParams(tlParams);

                TextView txtMatchID = new TextView(getActivity());
                txtMatchID.setText(m.getMatch_id() + "");
                txtMatchID.setLayoutParams(trParams);
                txtMatchID.setGravity(Gravity.CENTER);
                tr.addView(txtMatchID);

                TextView txtFieldName = new TextView(getActivity());
                txtFieldName.setText(m.getField_name());
                txtFieldName.setLayoutParams(trParams);
                txtFieldName.setGravity(Gravity.CENTER);
                tr.addView(txtFieldName);

                TextView txtHostName = new TextView(getActivity());
                txtHostName.setText(m.getUsername());
                txtHostName.setLayoutParams(trParams);
                txtHostName.setGravity(Gravity.CENTER);
                tr.addView(txtHostName);

                TextView txtMaxPlayers = new TextView(getActivity());
                txtMaxPlayers.setText(m.getMaximum_players() + "");
                txtMaxPlayers.setLayoutParams(trParams);
                txtMaxPlayers.setGravity(Gravity.CENTER);
                tr.addView(txtMaxPlayers);

                TextView txtPrice = new TextView(getActivity());
                txtPrice.setText(m.getPrice() + "");
                txtPrice.setLayoutParams(trParams);
                txtPrice.setGravity(Gravity.CENTER);
                tr.addView(txtPrice);

                TextView txtStartTime = new TextView(getActivity());
                txtStartTime.setText(m.getStart_time());
                txtStartTime.setLayoutParams(trParams);
                tr.addView(txtStartTime);

                TextView txtEndTime = new TextView(getActivity());
                txtEndTime.setText(m.getEnd_time());
                txtEndTime.setLayoutParams(trParams);
                tr.addView(txtEndTime);

                tr.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        MatchDetailFragment fragment = new MatchDetailFragment();
                        // set Arguments
                        Bundle args = new Bundle();
                        args.putString("username", username);
                        args.putInt("match_id", m.getMatch_id());
                        fragment.setArguments(args);

                        ft.replace(R.id.fragment_container, fragment);
                        ft.commit();
                    }
                });
                tableLayout.addView(tr);
            }
        }
    }
}
