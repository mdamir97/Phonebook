package reddec.alexei.phonebookreddec.control;

import reddec.alexei.phonebookreddec.model.ModelPhonebook;

public interface IControlFragment {

    void refreshResult(ModelPhonebook obj);

    void showErrorResponse(Exception response);

    void refreshEditResult(ModelPhonebook obj);
}
