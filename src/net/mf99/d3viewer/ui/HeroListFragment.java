package net.mf99.d3viewer.ui;

import java.io.IOException;
import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;

import uk.co.senab.actionbarpulltorefresh.library.ActionBarPullToRefresh;
import uk.co.senab.actionbarpulltorefresh.library.PullToRefreshLayout;
import uk.co.senab.actionbarpulltorefresh.library.listeners.OnRefreshListener;
import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;
import net.mf99.d3viewer.Const;
import net.mf99.d3viewer.Const.ServerPath;
import net.mf99.d3viewer.R;
import net.mf99.d3viewer.Utils;
import net.mf99.d3viewer.data.HeroListAdapter;
import net.mf99.d3viewer.data.unit.HeroShort;
import net.mf99.d3viewer.data.unit.Profile;

/**
 * A list fragment representing a list of Heros. This fragment
 * also supports tablet devices by allowing list items to be given an
 * 'activated' state upon selection. This helps indicate which item is
 * currently being viewed in a {@link HeroDetailFragment}.
 * <p>
 * Activities containing this fragment MUST implement the {@link Callbacks}
 * interface.
 */
public class HeroListFragment extends ListFragment implements OnRefreshListener {
	public static HeroListAdapter mAdapter = null;
	private PullToRefreshLayout mPullToRefreshLayout;
	Context mContext;

    /**
     * The serialization (saved instance state) Bundle key representing the
     * activated item position. Only used on tablets.
     */
    private static final String STATE_ACTIVATED_POSITION = "activated_position";

    /**
     * The fragment's current callback object, which is notified of list item
     * clicks.
     */
    private Callbacks mCallbacks = sDummyCallbacks;

    /**
     * The current activated item position. Only used on tablets.
     */
    private int mActivatedPosition = ListView.INVALID_POSITION;

    /**
     * A callback interface that all activities containing this fragment must
     * implement. This mechanism allows activities to be notified of item
     * selections.
     */
    public interface Callbacks {
        /**
         * Callback for when an item has been selected.
         */
        public void onItemSelected(HeroShort hero);
    }

    /**
     * A dummy implementation of the {@link Callbacks} interface that does
     * nothing. Used only when this fragment is not attached to an activity.
     */
    private static Callbacks sDummyCallbacks = new Callbacks() {
        @Override
        public void onItemSelected(HeroShort hero) {
        	// do nothing
        }
    };

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public HeroListFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();

        if(mAdapter == null)
        	mAdapter = new HeroListAdapter(mContext, 0, 0, 0);
        
        setListAdapter(mAdapter);
        if(mAdapter.isTemp){
        	CarrerDownloadTask downloadTask = new CarrerDownloadTask();
            downloadTask.execute("BattleTag");
        }       
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Restore the previously serialized activated item position.
        if (savedInstanceState != null
                && savedInstanceState.containsKey(STATE_ACTIVATED_POSITION)) {
            setActivatedPosition(savedInstanceState.getInt(STATE_ACTIVATED_POSITION));
        }
        
        // This is the View which is created by ListFragment
        ViewGroup viewGroup = (ViewGroup) view;        

        // We need to create a PullToRefreshLayout manually
        mPullToRefreshLayout = new PullToRefreshLayout(viewGroup.getContext());
        mPullToRefreshLayout.setBackgroundResource(R.drawable.bg_main);

        // We can now setup the PullToRefreshLayout
        ActionBarPullToRefresh.from(getActivity())

                // We need to insert the PullToRefreshLayout into the Fragment's ViewGroup
                .insertLayoutInto(viewGroup)

                // We need to mark the ListView and it's Empty View as pullable
                // This is because they are not dirent children of the ViewGroup
                .theseChildrenArePullable(getListView(), getListView().getEmptyView())

                // We can now complete the setup as desired
                .listener(this)                
                .setup(mPullToRefreshLayout);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        // Activities containing this fragment must implement its callbacks.
        if (!(activity instanceof Callbacks)) {
            throw new IllegalStateException("Activity must implement fragment's callbacks.");
        }

        mCallbacks = (Callbacks) activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();

        // Reset the active callbacks interface to the dummy implementation.
        mCallbacks = sDummyCallbacks;
    }

    @Override
    public void onListItemClick(ListView listView, View view, int position, long id) {
        super.onListItemClick(listView, view, position, id);

        // Notify the active callbacks interface (the activity, if the
        // fragment is attached to one) that an item has been selected.
        if(position != 0)
        	mCallbacks.onItemSelected(mAdapter.getItem(position));
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (mActivatedPosition != ListView.INVALID_POSITION) {
            // Serialize and persist the activated item position.
            outState.putInt(STATE_ACTIVATED_POSITION, mActivatedPosition);
        }
    }

    /**
     * Turns on activate-on-click mode. When this mode is on, list items will be
     * given the 'activated' state when touched.
     */
    public void setActivateOnItemClick(boolean activateOnItemClick) {
        // When setting CHOICE_MODE_SINGLE, ListView will automatically
        // give items the 'activated' state when touched.
        getListView().setChoiceMode(activateOnItemClick
                ? ListView.CHOICE_MODE_SINGLE
                : ListView.CHOICE_MODE_NONE);
    }

    private void setActivatedPosition(int position) {
        if (position == ListView.INVALID_POSITION) {
            getListView().setItemChecked(mActivatedPosition, false);
        } else {
            getListView().setItemChecked(position, true);
        }

        mActivatedPosition = position;
    }
    
    class CarrerDownloadTask extends AsyncTask<String, Void, Profile>{

    	@Override
		protected Profile doInBackground(String... params) {
    		try {
				return Utils.translateJsonToProfile(
									Utils.downloadJSONData(ServerPath.PROFILE_PATH), 
									Utils.getRegion(Const.DATA_BATTLE_REGION));
				
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		
    		return null;
		}
    	
		@Override
		protected void onPostExecute(Profile result) {
			try{
				super.onPostExecute(result);
				
				if(result == null){
					Toast.makeText(getActivity(), "Download Profile fail, pull down to refresh !", Toast.LENGTH_SHORT).show();
					getActivity().setTitle(R.string.loading_fail);
				}
				else{
					mAdapter = new HeroListAdapter(mContext, result);
					setListAdapter(mAdapter);
					getActivity().setTitle(Const.DATA_BATTLE_ACCOUNT + "#" + Const.DATA_BATTLE_CODE); 
				}
				
				if(mPullToRefreshLayout != null)
					mPullToRefreshLayout.setRefreshComplete();
			}catch(Exception ex){}
		}
    }

	@Override
	public void onRefreshStarted(View view) {
		getActivity().setTitle(R.string.loading);
		CarrerDownloadTask downloadTask = new CarrerDownloadTask();
        downloadTask.execute("BattleTag");				
	}
}
