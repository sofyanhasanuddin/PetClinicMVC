package org.sofyan.latihan.app.performance.monitoring;

import java.util.Map;

import com.google.common.collect.Maps;

public class PerformanceLog {
	
	public static Map<String, Map<String, MethodPerformanceLog>> logMap = Maps.newConcurrentMap();
	
	public static Boolean enablePerfomanceLog = new Boolean(false);
	
	private PerformanceLog() {}
	
	public static void start(String tName, String methodName) {
		
		if( !enablePerfomanceLog )
			return;
		
		if( isProfilingMainThread(tName) )
			return;
		
		MethodPerformanceLog mp = new MethodPerformanceLog();
		mp.setMethodName( methodName );
		
		if( !PerformanceLog.logMap.containsKey( tName ) ) {//first entries
			mp.setFirstEntries(true);
			
			Map<String, MethodPerformanceLog> map = Maps.newLinkedHashMap();
			map.put( methodName, mp );
			
			PerformanceLog.logMap.put( tName, map );
		} else {
			PerformanceLog.logMap.get( tName ).put( methodName, mp );
		}
		
		mp.start();
		
	}
	
	public static void end(String tName, String methodName) {
		
		if( !enablePerfomanceLog )
			return;
		
		if( isProfilingMainThread(tName) )
			return;
		
		MethodPerformanceLog mpEnd = PerformanceLog.logMap.get( tName ).get( methodName );
		if( mpEnd != null ) {
	        mpEnd.end();
	        
	        if( mpEnd.isFirstEntries() ) { //reach the very first entries, time to print log and clear
	        	
	        	StringBuilder sb = new StringBuilder();
	        	sb.append("Start profiling with thread name : " + tName).append("\n");
	        	
	        	PerformanceLog.logMap.get( tName ).forEach( 
	        			(k,v) -> sb.append("method : " + k + ", executed in : " + v.getTotalTime() ).append(" seconds \n") );
	        	
	        	sb.append("Finish profiling with thread name : " + tName).append("\n");
	        	
	        	System.out.println( sb.toString() );
	        	
	        	PerformanceLog.logMap.get( tName ).clear(); //clear the second map
	        	PerformanceLog.logMap.remove( tName ); //remove the first
	        	
	        }
		}
		
	}
	
	//No need to profile the main thread
	public static boolean isProfilingMainThread(String tName) {
		return "main".equalsIgnoreCase(tName);
	}

}
