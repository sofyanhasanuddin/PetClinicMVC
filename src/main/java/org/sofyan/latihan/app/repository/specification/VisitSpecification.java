package org.sofyan.latihan.app.repository.specification;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.sofyan.latihan.app.bean.SearchBean;
import org.sofyan.latihan.app.bean.VisitSearchBean;
import org.sofyan.latihan.app.model.Visit;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

public class VisitSpecification {
	
	public static Specification<Visit> findByCriteria(SearchBean sb) {
		
		return new Specification<Visit>() {

			@Override
			public Predicate toPredicate(Root<Visit> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				
				query.distinct( true );
				
				List<Predicate> predicates = new ArrayList<Predicate>();
				
				VisitSearchBean vs = (VisitSearchBean) sb;
				
				predicates.add( cb.equal( root.join("listVisitDetail").get("deleted"), false ));

                if ( !StringUtils.isEmpty( vs.getName() )) {
                    predicates.add( cb.like( cb.lower( root.join("owner").get("name") ), vs.getName() + "%"));
                }
                
                if( vs.getEntryDate() != null && vs.getLeaveDate() != null ) {
                	Predicate greater = cb.greaterThanOrEqualTo( root.get("entryDate"), vs.getEntryDate() );
                	Predicate less = cb.lessThanOrEqualTo( root.get("leaveDate"), vs.getLeaveDate() );
                	
                	predicates.add( cb.and(greater, less) );
                } else {
                	
                	if( vs.getEntryDate() != null )
                		predicates.add( cb.equal( root.get("entryDate"), vs.getEntryDate() ));
                	else if( vs.getLeaveDate() != null )
                		predicates.add( cb.equal( root.get("leaveDate"), vs.getLeaveDate() ));
                }

                return cb.and(predicates.toArray(new Predicate[] {}));
				
			}
		};
		
	}

}
