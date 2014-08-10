package com.kevintan.eventbussample.fragments;

import android.app.Fragment;
import de.greenrobot.event.EventBus;

/**
 * {@link BaseFragment}  demonstrates a general pattern used in App.
 * <p/>
 * Generally only when the App has been in foreground evens could be handled, so that we connect bus in {@code
 * onResume()}, and disconnect in {@code onPause()}.
 * <p/>
 * {@link com.kevintan.eventbussample.fragments.BaseFragment#onEvent(Object)} to tricky {@link
 * de.greenrobot.event.EventBusException} for a least one subscribed method "onEvent".
 */
public abstract class BaseFragment extends Fragment {
	/**
	 * Handler for {@link }
	 *
	 * @param e
	 * 		Event {@link  }.
	 */
	public void onEvent(Object e) {

	}

	@Override
	public void onResume() {
		EventBus.getDefault().registerSticky(this);
		super.onResume();
	}

	@Override
	public void onPause() {
		EventBus.getDefault().unregister(this);
		super.onPause();
	}
}
