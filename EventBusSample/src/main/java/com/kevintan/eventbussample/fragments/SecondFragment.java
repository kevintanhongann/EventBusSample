package com.kevintan.eventbussample.fragments;

import com.kevintan.eventbussample.R;
import com.kevintan.eventbussample.bus.MoveToFragmentEvent;
import com.kevintan.eventbussample.bus.StickyEvent;
import com.kevintan.eventbussample.bus.UpdateActionBarTitleEvent;
import com.kevintan.eventbussample.data.DataObject;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import de.greenrobot.event.EventBus;

/**
 * SecondFragment where user can update the {@link com.kevintan.eventbussample.bus.StickyEvent} or moving to {@link
 * com.kevintan.eventbussample.fragments.ThirdFragment} that enhances the original sample codes.
 * <p/>
 * Created by kevintanhongann on 11/27/13.
 * <p/>
 * Update:
 * <p/>
 * 1. Demonstrate that {@link de.greenrobot.event.EventBus#postSticky(Object)} works including with normal {@link
 * de.greenrobot.event.EventBus#post(Object)}.
 * <p/>
 * 2. Update {@link com.kevintan.eventbussample.bus.StickyEvent} and more to third {@link android.app.Fragment} that
 * shows new {@link com.kevintan.eventbussample.bus.StickyEvent}.
 * <p/>
 * 3. Post event to update {@link android.app.ActionBar}'s title.
 * <p/>
 * 4. Post {@link com.kevintan.eventbussample.bus.MoveToFragmentEvent} that will be handled by{@link
 * com.kevintan.eventbussample.MainActivity} to switch to {@link com.kevintan.eventbussample.fragments.ThirdFragment}.
 * <p/>
 * 5. Move calling {@link de.greenrobot.event.EventBus#removeStickyEvent(Class)} on {@link
 * com.kevintan.eventbussample.bus.StickyEvent} into {@link com.kevintan.eventbussample.MainActivity#onDestroy()}.
 *
 * @author Xinyue Zhao
 */
public class SecondFragment extends Fragment {
	private static final int LAYOUT = R.layout.fragment_second;

	/**
	 * Handler for {@link StickyEvent}
	 *
	 * @param e
	 * 		Event {@link StickyEvent}.
	 */
	public void onEvent(StickyEvent e) {
		Toast.makeText(getActivity(), e.getDataObject().toString(), Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onResume() {
		EventBus.getDefault().registerSticky(this, StickyEvent.class);
		super.onResume();
	}

	@Override
	public void onPause() {
		EventBus.getDefault().unregister(this);
		super.onPause();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		/*Update ActionBar's title.*/
		EventBus.getDefault().post(new UpdateActionBarTitleEvent(getString(R.string.screen_2)));
		return inflater.inflate(LAYOUT, container, false);
	}

	@Override
	public void onViewStateRestored(Bundle savedInstanceState) {
		super.onViewStateRestored(savedInstanceState);
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		View updateBtn = view.findViewById(R.id.update_event_btn);
		updateBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				/*Update the sticky, LOOK the postSticky() runs including post() that onEvent(StickyEvent e) will be called late.*/
				DataObject to = new DataObject("XinyueZ", "dev.xinyue.zhao@gmail.com");
				StickyEvent newEvent = new StickyEvent(to);
				EventBus.getDefault().postSticky(newEvent);
				Toast.makeText(getActivity(), "New Sticky is posted.", Toast.LENGTH_SHORT).show();
			}
		});

		View thirdFrgBtn = view.findViewById(R.id.next_btn);
		thirdFrgBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				/*Move to 3rd fragment.*/
				EventBus.getDefault().post(new MoveToFragmentEvent(new ThirdFragment()));
			}
		});
	}
}
