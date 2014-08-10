package com.kevintan.eventbussample;

import com.kevintan.eventbussample.bus.MoveToFragmentEvent;
import com.kevintan.eventbussample.bus.StickyEvent;
import com.kevintan.eventbussample.bus.UpdateActionBarTitleEvent;
import com.kevintan.eventbussample.data.DataObject;
import com.kevintan.eventbussample.fragments.SecondFragment;
import com.kevintan.eventbussample.fragments.ThirdFragment;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import de.greenrobot.event.EventBus;

/**
 * Sample main {@link android.app.Activity}.
 * <p/>
 * Created by kevintanhongann.
 * <p/>
 * Update:
 * <p/>
 * 1. Added event-handler that updates title of {@link android.app.ActionBar}.
 * <p/>
 * 2. Added event-handler that controls showing {@link com.kevintan.eventbussample.fragments.SecondFragment}, {@link
 * com.kevintan.eventbussample.fragments.ThirdFragment}.
 * <p/>
 * 3. Remove {@link com.kevintan.eventbussample.bus.StickyEvent} in {@link MainActivity#onDestroy()}.
 * @author Xinyue Zhao
 */
public class MainActivity extends Activity {
	private static final int LAYOUT = R.layout.activity_main;

	/**
	 * Handler for {@link com.kevintan.eventbussample.bus.UpdateActionBarTitleEvent}
	 *
	 * @param e
	 * 		Event {@link  com.kevintan.eventbussample.bus.UpdateActionBarTitleEvent}.
	 */
	public void onEvent(UpdateActionBarTitleEvent e) {
		getActionBar().setTitle(e.getTitle());
	}

	/**
	 * Handler for {@link com.kevintan.eventbussample.bus.MoveToFragmentEvent}
	 *
	 * @param e
	 * 		Event {@link com.kevintan.eventbussample.bus.MoveToFragmentEvent }.
	 */
	public void onEvent(MoveToFragmentEvent e) {
		if (e.getFragment() instanceof ThirdFragment) {
			getFragmentManager().beginTransaction().replace(R.id.container, e.getFragment()).addToBackStack(null)
					.commit();
		} else if (e.getFragment() instanceof SecondFragment) {
			DataObject object = new DataObject("kevin tan", "kevintan@kevintan.com");
			EventBus.getDefault().postSticky(new StickyEvent(object));
			getFragmentManager().beginTransaction().replace(R.id.container, e.getFragment()).addToBackStack(null)
					.commit();
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(LAYOUT);
		EventBus.getDefault().register(this);
		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment())
					.commit();
		}
	}

	@Override
	protected void onDestroy() {
		EventBus.getDefault().unregister(this);
		EventBus.getDefault().removeStickyEvent(StickyEvent.class);
		super.onDestroy();
	}


	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {
		private static final int LAYOUT = R.layout.fragment_main;

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
		                         Bundle savedInstanceState) {
			EventBus.getDefault().post(new UpdateActionBarTitleEvent(getString(R.string.screen_1)));
			View rootView = inflater.inflate(LAYOUT, container, false);
			View testBtn = rootView.findViewById(R.id.btn_test);
			testBtn.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					EventBus.getDefault().post(new MoveToFragmentEvent(new SecondFragment()));
				}
			});
			return rootView;
		}
	}

}
