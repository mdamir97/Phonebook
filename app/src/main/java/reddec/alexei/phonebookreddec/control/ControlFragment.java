package reddec.alexei.phonebookreddec.control;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Map;

import reddec.alexei.phonebookreddec.R;
import reddec.alexei.phonebookreddec.SharedStatesMap;
import reddec.alexei.phonebookreddec.model.ModelPhonebook;
import reddec.alexei.phonebookreddec.utils.KeyboardUtility;


public class ControlFragment extends Fragment implements IControlFragment {

    String id;
    SharedStatesMap mSharedStates;
    TextView tvOutput, tvHeader;
    Button btSave;
    EditText etName, etSurname, etPhone1, etPhone2, etPhone3;
    ControlFragmentPresenter mPresenter;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_control, container, false);
        try {
            mPresenter = new ControlFragmentPresenter(this, getContext());
            mSharedStates = SharedStatesMap.getInstance();
            id = mSharedStates.getKey("id");
            if (id==null) id="";

            tvHeader = (TextView) v.findViewById(R.id.tv_header);
            btSave = (Button) v.findViewById(R.id.bt_save);
            btSave.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    onSaveClicked();
                }
            });
            tvOutput = (TextView) v.findViewById(R.id.tv_output);
            //inputLayoutEmail = (TextInputLayout) v.findViewById(R.id.input_layout_email);
            etName = (EditText) v.findViewById(R.id.et_name);
            KeyboardUtility.showKeyboard(etName);
            etSurname = (EditText) v.findViewById(R.id.et_surname);
            etPhone1 = (EditText) v.findViewById(R.id.et_phone1);
            etPhone2 = (EditText) v.findViewById(R.id.et_phone2);
            etPhone3 = (EditText) v.findViewById(R.id.et_phone3);

            if (!id.equals("")) {
                tvHeader.setText(getString(R.string.edit_contact));
                mPresenter.loadContact(id);
            } else {
                tvHeader.setText(getString(R.string.add_contact));
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return v;
    }





    private void onSaveClicked() {
        Integer idContact=0;
        if (id.equals("") || id.equals("0")) {
            Map<String, ?> keys = getContext().getSharedPreferences("Phonebook", 0).getAll();
            idContact = keys.size()+1;
        } else {
            idContact = Integer.parseInt(id);
        }

        String name = etName.getText().toString();
        String surname = etSurname.getText().toString();
        String phone1 = etPhone1.getText().toString();
        String phone2 = etPhone2.getText().toString();
        String phone3 = etPhone3.getText().toString();

        //check the input
        if (name.length()==0 && surname.length()==0) {
            Snackbar.make(tvOutput, getString(R.string.check_input_name), Snackbar.LENGTH_LONG).setAction("Action", null).show();
        } else if (phone1.length()==0 && phone2.length()==0 && phone3.length()==0) {
            Snackbar.make(tvOutput, getString(R.string.check_input_number), Snackbar.LENGTH_LONG).setAction("Action", null).show();
        } else if (!isValidPhoneNumber(phone1) && phone1.length() >0) {
            Snackbar.make(tvOutput, getString(R.string.check_syntax_number), Snackbar.LENGTH_LONG).setAction("Action", null).show();
        } else if (!isValidPhoneNumber(phone2) && phone2.length() >0) {
            Snackbar.make(tvOutput, getString(R.string.check_syntax_number), Snackbar.LENGTH_LONG).setAction("Action", null).show();
        } else if (!isValidPhoneNumber(phone3) && phone3.length() >0) {
            Snackbar.make(tvOutput, getString(R.string.check_syntax_number), Snackbar.LENGTH_LONG).setAction("Action", null).show();
        } else {
            ModelPhonebook obj = new ModelPhonebook(idContact, name, surname, phone1, phone2, phone3);

            mPresenter.saveContact(obj, idContact);
        }
    }

    public static boolean isValidPhoneNumber(String phoneNumber) {
        if (!TextUtils.isEmpty(phoneNumber)) {
            return Patterns.PHONE.matcher(phoneNumber).matches();
        }
        return false;
    }

    @Override
    public void refreshResult(ModelPhonebook obj) {
        //progressBar.setVisibility(View.GONE);
        //tvOutput.setText("Done");
        Snackbar.make(tvOutput, getString(R.string.saved), Snackbar.LENGTH_LONG).setAction("Action", null).show();
        KeyboardUtility.hideKeyboard(etName);
        getActivity().onBackPressed();
    }

    @Override
    public void showErrorResponse(Exception response) {
        tvOutput.setText(getString(R.string.msg_error_occurred) + "\n" + response.getMessage());
    }

    @Override
    public void refreshEditResult(ModelPhonebook obj) {
        etName.setText(obj.getName());
        etSurname.setText(obj.getSurname());
        etPhone1.setText(obj.getPhone1());
        etPhone2.setText(obj.getPhone2());
        etPhone3.setText(obj.getPhone3());
    }

}