package reddec.alexei.phonebookreddec.utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowInsets;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView;
import android.widget.ListView;
import reddec.alexei.phonebookreddec.ApplicationContext;


//http://stackoverflow.com/questions/1109022/close-hide-the-android-soft-keyboard
public class KeyboardUtility {
  
  /*
   * Here lies the strangest part of Android ever made. 
   * Make sure there hasn't been set android:windowSoftInputMode="any_value" in AndroidManifest
   * Or else it probably won't work ...
   */

    public static void hideKeyboard(View v) {
        try {
            if (ApplicationContext.get() != null) {
                InputMethodManager imm = (InputMethodManager) ApplicationContext.get().getSystemService(Context.INPUT_METHOD_SERVICE);
                //clear focus before hiding keyboard
                v.clearFocus();
                v.setFocusable(false);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                //imm.toggleSoftInput(0, InputMethodManager.HIDE_IMPLICIT_ONLY);
                //getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void showKeyboard(View v) {
        try {
            if (ApplicationContext.get() != null) {
                InputMethodManager imm = (InputMethodManager) ApplicationContext.get().getSystemService(Context.INPUT_METHOD_SERVICE);
                //getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
                //request focus to view before showing keyboard
                v.requestFocus();
                v.setVisibility(View.VISIBLE);
                v.setFocusable(true);
                v.setFocusableInTouchMode(true);
                //v.setActivated(true);
                //v.setPressed(true);
                //imm.showSoftInput(v, 0);
                //imm.showSoftInput(v, InputMethodManager.SHOW_FORCED);
                //imm.showSoftInput(v, InputMethodManager.SHOW_IMPLICIT);
                imm.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, 0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setOnScrollHideListener(ListView listview) {
        listview.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                hideKeyboard(view);
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });
    }

    public static void setOnScrollHideListener(RecyclerView recyclerView) {
        recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                hideKeyboard(recyclerView);
            }
        });
    }

    /**
     * with translucent statusbar the window always fills the screen, to move view when keyboard is shown you must pass bottom inset to view.
     *
     * @param child  - view that must resize;
     * @param parent - view that gets the insets first.
     */
    public static void insetBottomFromParent(final View child, View parent) {
        if (child != null && parent != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                parent.setOnApplyWindowInsetsListener(new View.OnApplyWindowInsetsListener() {
                    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
                    @Override
                    public WindowInsets onApplyWindowInsets(View v, WindowInsets insets) {
                        child.onApplyWindowInsets(insets);
                        return insets;
                    }
                });
            }

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                child.setOnApplyWindowInsetsListener(new View.OnApplyWindowInsetsListener() {
                    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
                    @Override
                    public WindowInsets onApplyWindowInsets(View v, WindowInsets insets) {
                        v.onApplyWindowInsets(insets.replaceSystemWindowInsets(0, 0, 0, insets.getSystemWindowInsetBottom()));
                        return insets;
                    }
                });
            }
        }
    }

    /**
     * just adds onApplyWindowInsetsListener to the view, and only uses bottom inset from that.
     *
     * @param v
     */
    public static void addBottomInsetListener(View v) {
        if (v != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                v.setOnApplyWindowInsetsListener(new View.OnApplyWindowInsetsListener() {
                    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
                    @Override
                    public WindowInsets onApplyWindowInsets(View v, WindowInsets insets) {
                        return v.onApplyWindowInsets(insets.replaceSystemWindowInsets(0, 0, 0, insets.getSystemWindowInsetBottom()));
                    }
                });
            }
        }
    }

}
