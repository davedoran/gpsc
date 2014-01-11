package com.dorand.gpsc.service.http.intf;

import java.util.List;

import com.dorand.gpsc.service.intf.IGPTrailStatus;

public interface IGPTrailConditionsResponse {

	List<IGPTrailStatus> getTrailStatus();

}
