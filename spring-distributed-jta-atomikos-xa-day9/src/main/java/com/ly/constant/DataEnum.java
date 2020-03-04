package com.ly.constant;

public enum DataEnum {

	    数据源1((Integer)1, "DATASOURCE1"),
	    数据源2((Integer)2, "DATASOURCE2"),
		;
		
		private Integer state;

	    private String info;

	    DataEnum(Integer state, String info) {
	        this.state = state;
	        this.info = info;
	    }

	    public Integer getState() {
	        return state;
	    }

	    public String getStateInfo() {
	        return info;
	    }

	    public static DataEnum stateOf(int index) {
	    	
	        for (DataEnum state : DataEnum.values()) {
	            if (state.getState() == index) {
	                return state;
	            }
	        }
	        return null;
	    }
	    
	    public static DataEnum stateOf(String stateInfo){
	    	return valueOf(stateInfo);
	    }
	    
	    public static String getName(int state){
	    	for(DataEnum t : DataEnum.values()){
	    		if(t.getState() == state){
	    			return t.info;
	    		}
	    	}
	    	return null;
	    }
	    public static DataEnum isDataSource(String stateInfo){
	    	for(DataEnum t : DataEnum.values()){
	    		if(t.getStateInfo().equals(stateInfo)){
	    			return t;
	    		}
	    	}
	    	return null;
	    }
}
