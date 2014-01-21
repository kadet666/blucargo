package com.blusoft.blucargo.gwt.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("testService")
public interface TestService extends RemoteService {

	String test();

}
