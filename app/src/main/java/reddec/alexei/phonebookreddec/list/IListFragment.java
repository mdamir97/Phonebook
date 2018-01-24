package reddec.alexei.phonebookreddec.list;

import java.util.List;

import reddec.alexei.phonebookreddec.model.ModelPhonebook;

public interface IListFragment {

    void refreshResult(List<ModelPhonebook> list);

    void showErrorResponse(Exception response);

}
