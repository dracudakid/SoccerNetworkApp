package fs.tandat.soccernetwork;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.xml.sax.Parser;

import fs.tandat.soccernetwork.bean.User;
import fs.tandat.soccernetwork.helpers.UserHelper;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {

    String username;
    EditText userName,phone,email,password;
    Button edit,logout;
    User user;
    UserHelper userHelper;
    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        username  = getArguments().getString("username");
        View v=inflater.inflate(R.layout.fragment_profile, container, false);

        userName=(EditText)v.findViewById(R.id.txt_UsernameProfile);
        phone=(EditText)v.findViewById(R.id.txt_PhoneProfile);
        email=(EditText)v.findViewById(R.id.txt_EmailProfile);
        password=(EditText)v.findViewById(R.id.txt_PasswordProfile);
        edit=(Button)v.findViewById(R.id.btn_EditProfile);
        logout=(Button)v.findViewById(R.id.btn_Logout);
        user=new User();
        userHelper=new UserHelper(getActivity());
        user=new User();
        user=userHelper.getUser(username);
        userName.setText(user.getUsername());
        email.setText(user.getEmail());
        phone.setText(user.getPhone());
        password.setText(user.getPassword());

        //edit information
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                userHelper.updateUser(user.getUser_id(), userName.getText().toString(), password.getText().toString(),
                        email.getText().toString(), phone.getText().toString());
                Toast.makeText(getActivity(),"Edit is succesful",Toast.LENGTH_SHORT).show();
                return;


            }
        });
        //logout
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),LoginActivity.class);
                startActivity(intent);

            }
        });


        return v;

    }

}
