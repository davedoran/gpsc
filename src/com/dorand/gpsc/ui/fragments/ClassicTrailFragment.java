package com.dorand.gpsc.ui.fragments;

import com.dorand.gpsc.service.http.impl.GPHttpService;
import com.dorand.gpsc.ui.R;
import com.dorand.gpsc.ui.fragments.adapters.TrailListAdapter;
import com.dorand.gpsc.ui.fragments.adapters.TrailListAdapter.TrailType;

public class ClassicTrailFragment extends TrailListFragment {

	@Override
	protected TrailListAdapter makeListAdapter() {
		return new TrailListAdapter(getActivity(), R.layout.trail_cell, this, TrailType.CLASSIC);
	}

	@Override
	protected void requestContent() {
		GPHttpService.getClassicSkiConditions(getResponseHandler());
	}

}
