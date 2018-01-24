package reddec.alexei.phonebookreddec.list;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import reddec.alexei.phonebookreddec.R;
import reddec.alexei.phonebookreddec.SharedStatesMap;
import reddec.alexei.phonebookreddec.control.ControlFragment;
import reddec.alexei.phonebookreddec.model.ModelPhonebook;


public class ListFragment extends Fragment implements IListFragment {
/*
    //ToolbarHelper mToolbarHelper;
    TextView tvOutput;
    EditText etEmail;
    Button btSend, btReturnMenu;
    ProgressBar progressBar;

    LinearLayout llForgot;
    TextInputLayout inputLayoutEmail;

    boolean mBackPressed = false;
*/

    SharedStatesMap mSharedStates;
    TextView tvOutput;
    RecyclerView mRecyclerView;
    ListAdapter adapterRv;
    List<ModelPhonebook> posts;

    ListFragmentPresenter mPresenter;

    /*
        @Subscribe
        public void onLogOutEvent(LogOutEvent e) {
            checkForLogOut();
        }

        private boolean checkForLogOut() {
            boolean shouldLogout = LoginDataManager.getLoginData() == null;
            if (shouldLogout) {
                LoginDataManager.createEmptyLoginData();
                Intent mLoginIntent = new Intent(this, HomeActivity_.class);
                mLoginIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                this.startActivity(mLoginIntent);
            }
            return shouldLogout;
        }
    */

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_list, container, false);
        try {
            mPresenter = new ListFragmentPresenter(this, getContext());
            mSharedStates = SharedStatesMap.getInstance();

            tvOutput = (TextView) v.findViewById(R.id.tv_notification);
            mRecyclerView = (RecyclerView) v.findViewById(R.id.products_rv);
            posts = new ArrayList<>();


            mRecyclerView.setLayoutManager(new android.support.v7.widget.LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
            adapterRv = new ListAdapter(posts, mProductListener);
            mRecyclerView.setAdapter(adapterRv);
            mRecyclerView.setHasFixedSize(true);
            mRecyclerView.setItemViewCacheSize(25);
            mRecyclerView.setDrawingCacheEnabled(true);
            mRecyclerView.invalidateItemDecorations();
            mRecyclerView.setSoundEffectsEnabled(false);
            mRecyclerView.clearAnimation();
            mRecyclerView.setWillNotDraw(true);
            mRecyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
            mRecyclerView.setVisibility(View.VISIBLE);


            mPresenter.loadContacts(posts);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return v;
    }


    @Override
    public void refreshResult(List<ModelPhonebook> list) {

        adapterRv.notifyItemInserted(list.size() - 1);
    }

    @Override
    public void showErrorResponse(Exception response) {
        tvOutput.setText(getString(R.string.msg_error_occurred) + "\n" + response.getMessage());
    }

    OnProductRequestClickedListener mProductListener = new OnProductRequestClickedListener() {
        @Override
        public void onCallRequestClicked(String number) {
            Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + number));
            startActivity(intent);
        }

        @Override
        public void onProfileRequestClicked(int position, ModelPhonebook contact) {
            Log.d("Click2", String.valueOf(position));

            mSharedStates.setKey("id", position);

            ListFragment fragment = new ListFragment();
            getActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .hide(fragment)
                    .add(R.id.home_fragment_container, new ControlFragment(), ControlFragment.class.getName())
                    .addToBackStack(ListFragment.class.getName())
                    .commit();
        }
    };

}