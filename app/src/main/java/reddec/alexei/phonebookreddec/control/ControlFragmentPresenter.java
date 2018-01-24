package reddec.alexei.phonebookreddec.control;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import reddec.alexei.phonebookreddec.model.ModelPhonebook;

public class ControlFragmentPresenter {

    //private Retrofit retrofit;
    private IControlFragment mainView;
    Context mContext;

    public ControlFragmentPresenter(IControlFragment mainView, Context ctx) {
        this.mainView = mainView;
        this.mContext = ctx;
    }

    public void loadContact(String id) {
        try {
            ModelPhonebook obj = getObjectFromSP(mContext, "Phonebook", "Account" + id, ModelPhonebook.class);

            mainView.refreshEditResult(obj);

        } catch (Exception ex) {
            mainView.showErrorResponse(ex);
        }
    }
    public void saveContact(ModelPhonebook obj, Integer id) {
        try {
            //Map<String, ?> keys = mContext.getSharedPreferences("Phonebook", 0).getAll();

            deleteObjectFromSP(mContext, "Phonebook", "Account" + id);

            saveObjectToSP(mContext, "Phonebook", "Account" + id, obj);

            mainView.refreshResult(obj);

        } catch (Exception ex) {
            mainView.showErrorResponse(ex);
        }
    }

    public static void saveObjectToSP(Context context, String preferenceFileName, String serializedObjectKey, Object object) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(preferenceFileName, 0);
        SharedPreferences.Editor sharedPreferencesEditor = sharedPreferences.edit();
        final Gson gson = new Gson();
        String serializedObject = gson.toJson(object);
        sharedPreferencesEditor.putString(serializedObjectKey, serializedObject);
        sharedPreferencesEditor.apply();
    }

    public static <GenericClass> GenericClass getObjectFromSP(Context context, String preferenceFileName, String preferenceKey, Class<GenericClass> classType) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(preferenceFileName, 0);
        if (sharedPreferences.contains(preferenceKey)) {
            final Gson gson = new Gson();
            return gson.fromJson(sharedPreferences.getString(preferenceKey, ""), classType);
        }
        return null;
    }

    public static void deleteObjectFromSP(Context context, String preferenceFileName, String preferenceKey) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(preferenceFileName, 0);
        SharedPreferences.Editor sharedPreferencesEditor = sharedPreferences.edit();
        sharedPreferencesEditor.remove(preferenceKey);
        sharedPreferencesEditor.apply();
    }

}