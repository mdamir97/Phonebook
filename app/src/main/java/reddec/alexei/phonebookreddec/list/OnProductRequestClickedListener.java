package reddec.alexei.phonebookreddec.list;

import reddec.alexei.phonebookreddec.model.ModelPhonebook;

public interface OnProductRequestClickedListener {

    void onProfileRequestClicked(int id, ModelPhonebook contact);

    void onCallRequestClicked(String number);
}
