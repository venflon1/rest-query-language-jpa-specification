package com.accenture.ra.rql.repository.criteria;

import com.accenture.ra.rql.repository.enumuration.SearchOperation;

public class SpecSearchCriteria {
	private String key;
    private SearchOperation operation;
    private Object value;
    private boolean orPredicate;
    
	public SpecSearchCriteria(
			String key, 
			SearchOperation operation, 
			Object value) {
		this(key, operation, value, Boolean.FALSE);
	}
	
	public SpecSearchCriteria(
			String key, 
			SearchOperation operation, 
			Object value, 
			boolean orPredicate) {
		super();
		this.key = key;
		this.operation = operation;
		this.value = value;
		this.orPredicate = orPredicate;
	}
    
	public boolean isOrPredicate() {
    	return this.orPredicate;
    }

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public SearchOperation getOperation() {
		return operation;
	}

	public void setOperation(SearchOperation operation) {
		this.operation = operation;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}
}