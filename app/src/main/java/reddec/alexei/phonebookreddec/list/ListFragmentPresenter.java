package reddec.alexei.phonebookreddec.list;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;

import java.util.List;
import java.util.Map;

import reddec.alexei.phonebookreddec.model.ModelPhonebook;

public class ListFragmentPresenter {

    //private Retrofit retrofit;
    private IListFragment mainView;
    Context mContext;

    public ListFragmentPresenter(IListFragment mainView, Context ctx) {
        this.mainView = mainView;
        this.mContext = ctx;
    }

    public void loadContacts(List<ModelPhonebook> list) {
        Gson gson = new Gson();

        try {
            Map<String, ?> keys = mContext.getSharedPreferences("Phonebook", 0).getAll();
            for (Map.Entry<String, ?> entry : keys.entrySet()) {
                Log.d("Phonebook map values", entry.getKey() + ": " + entry.getValue().toString());
                ModelPhonebook obj = gson.fromJson(entry.getValue().toString(), ModelPhonebook.class);
                list.add(obj);
            }
            Log.d("Phonebook size:", String.valueOf(keys.size()) + ";;; " + list.size());

            mainView.refreshResult(list);

        } catch (Exception ex) {
            mainView.showErrorResponse(ex);
        }
    }
}