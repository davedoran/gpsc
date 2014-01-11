package com.dorand.gpsc.ui.fragments;

import com.dorand.gpsc.service.http.impl.GPHttpService;
import com.dorand.gpsc.service.intf.IGPTrailListDelegate;
import com.dorand.gpsc.ui.R;
import com.dorand.gpsc.ui.fragments.adapters.TrailListAdapter;
import com.dorand.gpsc.ui.fragments.adapters.TrailListAdapter.TrailType;

public class SkateTrailFragment extends TrailListFragment implements IGPTrailListDelegate {

	@Override
	protected TrailListAdapter makeListAdapter() {
		return new TrailListAdapter(getActivity(), R.layout.trail_cell, this, TrailType.SKATE);
	}

	@Override
	protected void requestContent() {
		GPHttpService.getSkateSkiConditions(getResponseHandler());
	}
}
