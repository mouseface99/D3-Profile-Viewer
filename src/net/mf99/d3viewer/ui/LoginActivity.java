package net.mf99.d3viewer.ui;

import net.mf99.d3viewer.Const;
import net.mf99.d3viewer.Const.ServerPath;
import net.mf99.d3viewer.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

/**
 * Activity which displays a login screen to the user, offering registration as
 * well.
 */
public class LoginActivity extends Activity {

	// Values for email and password at the time of the login attempt.
	private String mBattleTag;	

	// UI references.
	private EditText mBattleTagView;
	private Button btnStart;
	private Spinner mRegionView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_login);

		// Set up the login form.
		mBattleTagView = (EditText) findViewById(R.id.battletag);
		btnStart = (Button)findViewById(R.id.sign_in_button);
		btnStart.setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						attemptLogin();
					}
				});
		
		mRegionView = (Spinner) findViewById(R.id.server_region);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				R.layout.myspinner,
				new String[]{"TW", "US", "EU", "KR", "SEA"});
		adapter.setDropDownViewResource(R.layout.myspinner);
		mRegionView.setAdapter(adapter);
	}

	/**
	 * Attempts to sign in or register the account specified by the login form.
	 * If there are form errors (invalid email, missing fields, etc.), the
	 * errors are presented and no actual login attempt is made.
	 */
	public void attemptLogin() {
		btnStart.setEnabled(false);
		// Reset errors.
		mBattleTagView.setError(null);

		// Store values at the time of the login attempt.
		mBattleTag = mBattleTagView.getText().toString();

		boolean cancel = false;
		View focusView = null;		

		// Check for a valid email address.
		if (TextUtils.isEmpty(mBattleTag)) {
			mBattleTagView.setError(getString(R.string.error_field_required));
			focusView = mBattleTagView;
			cancel = true;
		} else if (!mBattleTag.contains("#")) {
			mBattleTagView.setError(getString(R.string.error_invalid_battletag));
			focusView = mBattleTagView;
			cancel = true;
		} else {
			String num = mBattleTag.substring(mBattleTag.indexOf('#')+1, mBattleTag.length());
			if(num.length() != 4 || !TextUtils.isDigitsOnly(num)){
				mBattleTagView.setError(getString(R.string.error_invalid_battletag));
				focusView = mBattleTagView;
				cancel = true;
			}
		}

		if (cancel) {
			// There was an error; don't attempt login and focus the first
			// form field with an error.
			focusView.requestFocus();
			btnStart.setEnabled(true);
		} else {			
			Const.DATA_BATTLE_ACCOUNT = mBattleTag.substring(0, mBattleTag.indexOf('#'));
			Const.DATA_BATTLE_CODE = mBattleTag.substring(mBattleTag.indexOf('#')+1, mBattleTag.length());
			Const.DATA_BATTLE_REGION = ((String)mRegionView.getSelectedItem()).toLowerCase();	
			
			ServerPath.PROFILE_PATH =  ServerPath.PREFIX + Const.DATA_BATTLE_REGION + ServerPath.HOST + 
									   "profile/" + Const.DATA_BATTLE_ACCOUNT + "-" + Const.DATA_BATTLE_CODE + "/";
			
			Intent intent = new Intent(LoginActivity.this, HeroListActivity.class);
			LoginActivity.this.startActivity(intent);
			finish();
		}
	}
}
