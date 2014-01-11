package com.dorand.gpsc.ui.fragments;

import com.dorand.gpsc.service.http.impl.GPHttpService;
import com.dorand.gpsc.service.intf.IGPTrailListDelegate;
import com.dorand.gpsc.ui.R;
import com.dorand.gpsc.ui.fragments.adapters.TrailListAdapter;
import com.dorand.gpsc.ui.fragments.adapters.TrailListAdapter.TrailType;

public class BackCountryTrailFragment extends TrailListFragment implements IGPTrailListDelegate {

	@Override
	protected TrailListAdapter makeListAdapter() {
		return new TrailListAdapter(getActivity(), R.layout.trail_cell, this, TrailType.BACKCOUNTRY);
	}

	@Override
	protected void requestContent() {
		GPHttpService.getBackCountrySkiConditions(getResponseHandler());
	}
}
