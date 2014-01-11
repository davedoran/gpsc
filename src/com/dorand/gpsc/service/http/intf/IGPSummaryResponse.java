package com.dorand.gpsc.service.http.intf;

import java.util.List;

import com.dorand.gpsc.service.intf.IGPSummaryEntry;

public interface IGPSummaryResponse {

	List<IGPSummaryEntry> getSummaryInfo();

}
