package sinhalacoder.com.wedagedara.utils;
/*---------------------o----------o----------------------
 * Created by Blasanka on 07,December,2019
 * Contact: blasanka95@gmail.com
 *-------------------------<>----------------------------*/

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SectionsStatePagerAdapter extends FragmentStatePagerAdapter {

    private final List<Fragment> mFragmentList = new ArrayList<>();
    private final HashMap<Fragment, Integer> mFragments = new HashMap<>();
    private final HashMap<String, Integer> mFragmentNumber = new HashMap<>();
    private final HashMap<Integer, String> mFragmentNames = new HashMap<>();

    public SectionsStatePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        return mFragmentList.get(i);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    public void addFragment(Fragment fragment, String fragmentName) {
        mFragmentList.add(fragment);
        mFragments.put(fragment, mFragmentList.size()-1);
        mFragmentNumber.put(fragmentName, mFragmentList.size()-1);
        mFragmentNames.put(mFragmentList.size()-1, fragmentName);
    }

    /**
     * return the fragment number for the name @param
     * @param fragmentName required to get number of the fragment
     * @return number of the fragment from fragmentList
     */
    private Integer getFragmentNumber (String fragmentName) {
        if (mFragmentNumber.containsKey(fragmentName)) {
            return mFragmentNumber.get(fragmentName);
        }
        return null;
    }

    /**
     * return the fragment number for the name @param
     * @param fragment required to get fragment number
     * @return fragment number for @param
     */
    private Integer getFragmentNumber (Fragment fragment) {
        if (mFragments.containsKey(fragment)) {
            return mFragments.get(fragment);
        }
        return null;
    }

    /**
     * return the fragment number for the name @param
     * @param fragmentNumber number of the fragment
     * @return the name of the fragment for @param
     */
    private String getFragmentName (Integer fragmentNumber) {
        if (mFragmentNames.containsKey(fragmentNumber)) {
            return mFragmentNames.get(fragmentNumber);
        }
        return null;
    }
}
