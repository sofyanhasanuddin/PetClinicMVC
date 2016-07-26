package org.sofyan.latihan.app.performance.monitoring;

import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Component;

@ManagedResource(objectName="PerformanceMBeanLog:name=BeanLog", description="My Performance Log Bean")
@Component
public class PerformanceMBeanLog {
	
	@ManagedOperation(description="Get Performance Map Log")
	public String getMapLogValue() {
		
		StringBuilder sb = new StringBuilder();
		PerformanceLog.logMap.forEach((k,v) -> sb.append( k ) );
		
		return sb.toString();
	}

}
