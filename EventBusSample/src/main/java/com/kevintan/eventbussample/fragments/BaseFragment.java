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
 * <p/>
 * Added {@link com.kevintan.eventbussample.fragments.BaseFragment#isStickyAvailable()} for subclasses whether using
 * sticky-mode or normal.
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
		if (isStickyAvailable()) {
			EventBus.getDefault().registerSticky(this);
		} else {
			EventBus.getDefault().register(this);
		}
		super.onResume();
	}

	@Override
	public void onPause() {
		EventBus.getDefault().unregister(this);
		super.onPause();
	}

	/**
	 * Is the {@link android.app.Fragment} ready to subscribe a sticky-event or not.
	 *
	 * @return {@code true} if the {@link android.app.Fragment}  available for sticky-events inc. normal events.
	 * <p/>
	 * <b>Default is {@code false}</b>.
	 */
	protected boolean isStickyAvailable() {
		return false;
	}
}
