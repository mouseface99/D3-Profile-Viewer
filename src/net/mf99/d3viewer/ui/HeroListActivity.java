package net.mf99.d3viewer.ui;

import net.mf99.d3viewer.Const;
import net.mf99.d3viewer.R;
import net.mf99.d3viewer.data.unit.Hero;
import net.mf99.d3viewer.data.unit.HeroShort;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;


/**
 * An activity representing a list of Heros. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link HeroDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 * <p>
 * The activity makes heavy use of fragments. The list of items is a
 * {@link HeroListFragment} and the item details
 * (if present) is a {@link HeroDetailFragment}.
 * <p>
 * This activity also implements the required
 * {@link HeroListFragment.Callbacks} interface
 * to listen for item selections.
 */
public class HeroListActivity extends FragmentActivity
        implements HeroListFragment.Callbacks {

   

	/**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hero_list);
        

        if (findViewById(R.id.hero_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-large and
            // res/values-sw600dp). If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;

            // In two-pane mode, list items should be given the
            // 'activated' state when touched.
            ((HeroListFragment)getSupportFragmentManager().findFragmentById(R.id.hero_list)).setActivateOnItemClick(true);
        }
        
        
    }

    /**
     * Callback method from {@link HeroListFragment.Callbacks}
     * indicating that the item with the given ID was selected.
     */
    @Override
    public void onItemSelected(HeroShort hero) {
        if (mTwoPane) {
            // In two-pane mode, show the detail view in this activity by
            // adding or replacing the detail fragment using a
            // fragment transaction.
            Bundle arguments = new Bundle();
            arguments.putLong(Const.KEY_HERO_ID, hero.mId);
            HeroDetailFragment fragment = new HeroDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.hero_detail_container, fragment)
                    .commit();

        } else {
            // In single-pane mode, simply start the detail activity
            // for the selected item ID.
            Intent detailIntent = new Intent(this, HeroDetailActivity.class);  
            detailIntent.putExtra(Const.KEY_HERO_ID, hero.mId);
            startActivity(detailIntent);
        }
    }
    
    @Override
   	protected void onDestroy() {
   		// TODO Auto-generated method stub
   		super.onDestroy();
   		HeroListFragment.mAdapter = null;
   	}
}
