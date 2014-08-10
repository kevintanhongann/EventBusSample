package com.kevintan.eventbussample.fragments;

import com.kevintan.eventbussample.R;
import com.kevintan.eventbussample.bus.StickyEvent;
import com.kevintan.eventbussample.bus.UpdateActionBarTitleEvent;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import de.greenrobot.event.EventBus;

/**
 * {@link android.app.Fragment} shows last update of {@link com.kevintan.eventbussample.bus.StickyEvent}.
 * <p/>
 * Also update title on {@link android.app.ActionBar}.
 * <p/>
 * Demonstrate override of {@link EventBus}.
 */
public final class ThirdFragment extends SecondFragment {
	@Override
	public void onEvent(StickyEvent _stickyEvent) {
		Button updateBtn = (Button) getView().findViewById(R.id.update_event_btn);
		updateBtn.setText(_stickyEvent.getDataObject().toString());
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		/*Update ActionBar's title.*/
		EventBus.getDefault().post(new UpdateActionBarTitleEvent(getString(R.string.screen_3)));
		View v = view.findViewById(R.id.update_event_btn);
		v.setEnabled(false);
		view.findViewById(R.id.next_btn).setVisibility(View.GONE);
	}
}
