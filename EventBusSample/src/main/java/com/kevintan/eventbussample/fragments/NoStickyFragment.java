package com.kevintan.eventbussample.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.kevintan.eventbussample.R;
import com.kevintan.eventbussample.bus.NormalEvent;
import com.kevintan.eventbussample.bus.SimpleEvent;
import com.kevintan.eventbussample.bus.StickyEvent;
import com.kevintan.eventbussample.bus.UpdateActionBarTitleEvent;

import de.greenrobot.event.EventBus;

/**
 * {@link NoStickyFragment} can not accept a sticky-event, but normal event is no problem.
 * <p/>
 * UI can't refresh although it implements handler that handles the {@link com.kevintan.eventbussample.bus.StickyEvent}.
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
		TextView textView = (TextView) getView().findViewById(R.id.text_tv);
		textView.setText(e.getDataObject().toString());
	}

	/**
	 * Handler for {@link com.kevintan.eventbussample.bus.NormalEvent}
	 *
	 * @param e
	 * 		Event {@link com.kevintan.eventbussample.bus.NormalEvent}.
	 */
	public void onEvent(NormalEvent e) {
		TextView textView = (TextView) getView().findViewById(R.id.text_tv);
		textView.setText("Got a normal event.");

	}

	/**
	 * Handler for {@link com.kevintan.eventbussample.bus.SimpleEvent}.
	 *
	 * @param e
	 * 		Event {@link com.kevintan.eventbussample.bus.SimpleEvent}.
	 */
	public void onSimpleEvent(SimpleEvent e) {
		Toast.makeText(getActivity(),"onSimpleEvent handling SimpleEvent", Toast.LENGTH_LONG).show();
	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		/*Update ActionBar's title.*/
		EventBus.getDefault().post(new UpdateActionBarTitleEvent(getString(R.string.screen_4)));
		return inflater.inflate(LAYOUT, container, false);
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		view.findViewById(R.id.normal_event_btn).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				/*Can send a normal event and handle it.*/
				EventBus.getDefault().post(new NormalEvent());
			}
		});

		view.findViewById(R.id.simple_event_btn).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				EventBus.getDefault().post(new SimpleEvent());
			}
		});
	}

	@Override
	public void onResume() {
		super.onResume();
		EventBus.getDefault().register(this, "onSimpleEvent");
	}
}
