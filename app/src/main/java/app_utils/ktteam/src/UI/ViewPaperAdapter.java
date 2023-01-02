package app_utils.ktteam.src.UI;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import app_utils.ktteam.src.UI.Fragments.ActiveFragment;
import app_utils.ktteam.src.UI.Fragments.ProfileFragment;
import app_utils.ktteam.src.UI.Fragments.SearchFragment;

public class ViewPaperAdapter extends FragmentStatePagerAdapter {
    public ViewPaperAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0:
                return new SearchFragment();
            case 1:
                return  new ActiveFragment();
            case 2:
                return new ProfileFragment();
            default:
                return new SearchFragment();
        }

    }
    @Override
    public int getCount() {
        return 3;
    }
}
