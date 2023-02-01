package com.accenture.ra.rql.repository.enumuration;

import java.util.stream.Stream;

import com.accenture.ra.rql.constants.SearchOperationConst;

public enum SearchOperation {
	LIKE(SearchOperationConst.LIKE),
	NOT_LIKE(SearchOperationConst.NOT_LIKE),
    EQUAL(SearchOperationConst.EQUAL),
    NOT_EQUAL(SearchOperationConst.NOT_EQUAL), 
    GREATER_THAN(SearchOperationConst.GREATER_THAN), 
    GREATER_THAN_OR_EQUAL(SearchOperationConst.GREATER_THAN_OR_EQUAL), 
    LESS_THAN(SearchOperationConst.LESS_THAN), 
    LESS_THAN_OR_EQUAL(SearchOperationConst.LESS_THAN_OR_EQUAL);

    private String operationCode;

    SearchOperation(final String operationCode) {
        this.operationCode = operationCode;
    }

    public static SearchOperation getSearchOperationByCode(String operationCode) {
    	return Stream
    		.of(SearchOperation.values())
    		.filter(i -> i.getOperationCode().equalsIgnoreCase(operationCode))
    		.findFirst()
    		.get();
    }
    
    public String getOperationCode() {
    	return this.operationCode;
    }
}