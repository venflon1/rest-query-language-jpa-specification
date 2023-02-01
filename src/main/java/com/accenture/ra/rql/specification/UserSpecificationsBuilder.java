package com.accenture.ra.rql.specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.data.jpa.domain.Specification;

import com.accenture.ra.rql.entity.User;
import com.accenture.ra.rql.repository.criteria.SpecSearchCriteria;
import com.accenture.ra.rql.repository.enumuration.SearchOperation;

public class UserSpecificationsBuilder {
    private final List<SpecSearchCriteria> params;

    public UserSpecificationsBuilder() {
        params = new ArrayList<>();
    }
    
    public final UserSpecificationsBuilder with(
    		String key, 
    		String codeOperation, 
    		Object value) {
       return this.with(key, codeOperation, value, Boolean.FALSE);
    }

    public final UserSpecificationsBuilder with(
    		String key, 
    		String codeOperation, 
    		Object value,
    		boolean orPredicate) {
        SearchOperation operation = SearchOperation.getSearchOperationByCode(codeOperation);
        if( Objects.isNull(operation) ) {
        	return this;
        }
        
        this.params.add(
        	new SpecSearchCriteria(key, operation, value, orPredicate)
        );
        return this;
    }

    public Specification<User> build() {
        if ( params.isEmpty() ) {
        	return null;
        }

        Specification<User> result = new UserSpecification(params.get(0));
        for (int i = 1; i<params.size(); i++) {
            result = params.get(i).isOrPredicate()
              ? Specification.where(result).or(new UserSpecification(params.get(i))) 
              : Specification.where(result).and(new UserSpecification(params.get(i)));
        }
        
        return result;
    }
}