package com.accenture.ra.rql.specification;

import org.springframework.data.jpa.domain.Specification;

import com.accenture.ra.rql.entity.User;
import com.accenture.ra.rql.repository.criteria.SpecSearchCriteria;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;


public class UserSpecification implements Specification<User> {
	private static final long serialVersionUID = 1L;

	private SpecSearchCriteria criteria;
	
	public UserSpecification(SpecSearchCriteria criteria) {
		this.criteria = criteria;
	}

	@Override
    public Predicate toPredicate(
    		Root<User> root, 
    		CriteriaQuery<?> query, 
    		CriteriaBuilder builder) {
 
    	switch( criteria.getOperation() ) {
    		case LIKE -> {
    			if (root.get(criteria.getKey()).getJavaType() == String.class) {
	                return builder.like(
	                	root.<String>get(criteria.getKey()),
                		"%" + criteria.getValue() + "%"
	                );
	            } 
    			return null;
    		}
    		case EQUAL -> {
    			return builder.equal(
            		root.<String>get(criteria.getKey()),
            		criteria.getValue()
            	);
    		}
    		case NOT_EQUAL -> {
    			 return builder.notEqual(
                	root.get(criteria.getKey()), 
                	criteria.getValue()
 	             );
    		}
    		case GREATER_THAN_OR_EQUAL -> {
    			return builder.greaterThanOrEqualTo(
    				root.<String> get(
    					criteria.getKey()),
    					criteria.getValue().toString()
    				);
    		}
    		case GREATER_THAN -> {
    			return builder.greaterThan(
    				root.<String> get(
    					criteria.getKey()),
    					criteria.getValue().toString()
    				);
    		}
    		case LESS_THAN_OR_EQUAL -> {
    			return builder.lessThanOrEqualTo(
    				root.<String> get(
    					criteria.getKey()),
    					criteria.getValue().toString()
    			);
    		}
    		case LESS_THAN -> {
    			return builder.lessThan(
					root.<String> get(
						criteria.getKey()),
						criteria.getValue().toString()
				);
    		}
    		default -> {
    			return null;
    		}
    	}
	}
}