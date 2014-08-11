package com.kevintan.eventbussample.fragments;

import com.kevintan.eventbussample.R;
import com.kevintan.eventbussample.bus.StickyEvent;
import com.kevintan.eventbussample.bus.UpdateActionBarTitleEvent;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import de.greenrobot.event.EventBus;

/**
 * {@link NoStickyFragment} can not accept a sticky-event, UI can't refresh although it implements handler that handles
 * the {@link com.kevintan.eventbussample.bus.StickyEvent}.
 */
public final class NoStickyFragment extends BaseFragment {
	private static final int LAYOUT = R.layout.fragment_no_sticky;

	/**
	 * Handler for {@link com.kevintan.eventbussample.bus.StickyEvent}
	 *
	 * @param e
	 * 		Event {@link  com.kevintan.eventbussample.bus.StickyEvent}.
	 */
	public void onEvent(StickyEvent e) {
		if (getView() instanceof TextView) {
			TextView textView = (TextView) getView();
			textView.setText(e.getDataObject().toString());
		}
	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		/*Update ActionBar's title.*/
		EventBus.getDefault().post(new UpdateActionBarTitleEvent(getString(R.string.screen_4)));
		return inflater.inflate(LAYOUT, container, false);
	}
}
