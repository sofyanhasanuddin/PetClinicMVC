package org.sofyan.latihan.app.ctrl;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sofyan.latihan.app.bean.DataTableReturnBean;
import org.sofyan.latihan.app.bean.VisitSearchBean;
import org.sofyan.latihan.app.ctrl.message.Message;
import org.sofyan.latihan.app.model.Visit;
import org.sofyan.latihan.app.model.VisitDetail;
import org.sofyan.latihan.app.model.VisitDetailTreatmentType;
import org.sofyan.latihan.app.repository.specification.VisitSpecification;
import org.sofyan.latihan.app.service.VisitDetailService;
import org.sofyan.latihan.app.service.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Lists;

@Controller
public class VisitCtrl {
	
	public static Logger log = LoggerFactory.getLogger( VisitCtrl.class );
	
	@Autowired
	private VisitService visitServiceImpl;
	
	@Autowired
	private VisitDetailService visitDetailServiceImpl;
	
	@RequestMapping( value 		= "/page/visit/getVisit/{id}",
					headers 	=  "Content-type=application/json",
					method 		= RequestMethod.GET,
					produces 	= MediaType.APPLICATION_JSON_VALUE )
	@ResponseBody
	public Visit getVisit(@PathVariable("id") Long visitId) {
	
	if( visitId == null )
		return null;
	
		return this.visitServiceImpl.findOne( visitId );
	
	}
	
	@RequestMapping( value 		= "/page/visit/save",
					 headers 	= "Content-type=application/json",
					 method 	= RequestMethod.POST,
					 produces 	= MediaType.APPLICATION_JSON_VALUE )
	@ResponseBody
	public Message save(@RequestBody Visit visit) {

		String msg;
		if( visit.getId() == null || visit.getId().equals( 0L )) msg = "Successfully save visit";
		else msg = "Successfully edit visit";
		
		this.visitServiceImpl.save( visit );
		
		return Message.successMessage( msg );
		
	}
	
	@RequestMapping( value 		= "/page/visit/delete",
			 		headers 	= "Content-type=application/json",
			 		method 		= RequestMethod.POST,
			 		produces 	= MediaType.APPLICATION_JSON_VALUE )
	@ResponseBody
	public Message delete(@RequestBody Visit visit) {
		
		visit = this.visitServiceImpl.findOne( visit.getId() );
		
		this.visitServiceImpl.delete( visit );
	
		return Message.successMessage("Successfully delete visit");
		
	}
	
	@RequestMapping(method=RequestMethod.GET,value="/page/visit/datatables")
	@ResponseBody
	public DataTableReturnBean visitDataTable(@ModelAttribute VisitSearchBean vsb) {
		
		PageRequest pr = buildPageRequest( vsb.getStartPaging(), vsb.getLength(), createOrder(vsb) );
		
		Page<Visit> pageVisit = this.visitServiceImpl.readAll( VisitSpecification.findByCriteria(vsb), pr );
		
		List<Long> listIds = pageVisit.getContent()
				.stream()
				.filter( v->v.getId() != null && !v.getId().equals( 0L ) )
				.map( v->v.getId() )
				.collect( Collectors.toList() );

		//Get one to many value for visit detail
		if( !CollectionUtils.isEmpty( listIds ) ) {
			Map<Long, List<VisitDetail>> mapVisit = this.visitDetailServiceImpl.findAllByVisitIdIn( listIds )
										.stream()
										.collect( Collectors.groupingBy( vd -> vd.getVisit().getId() ) );
		
			pageVisit.getContent()
				.forEach( vd -> vd.setListVisitDetail( mapVisit.get( vd.getId() )) );
		}
		
		return new DataTableReturnBean( vsb.getDraw(), 
				pageVisit.getTotalElements(), 
				pageVisit.getTotalElements(), 
				pageVisit.getContent());
		
	}
	
	@RequestMapping( value 		= "/page/visit/getListVisitDetail/{id}",
					headers 	=  "Content-type=application/json",
					method 		= RequestMethod.GET,
					produces 	= MediaType.APPLICATION_JSON_VALUE )
	@ResponseBody
	public List<VisitDetail> getListVisitDetailByVisitId(@PathVariable("id") Long visitId) {
	
		List<VisitDetail> lvd = this.visitDetailServiceImpl.findAllByVisitIdIn(Lists.newArrayList( visitId) );
		if( CollectionUtils.isEmpty( lvd ) )
			return null;
		
		//Get all id visit detail
		List<Long> vDids = lvd.stream()
							  .map( vd -> vd.getId() ).collect( Collectors.toList() );
		
		//Get All detail treatment type based on all visit id, and group it based on visit detail id
		Map<Long,List<VisitDetailTreatmentType>> map = this.visitDetailServiceImpl
				.findAllVisitDetailTreatmentTypeBasedOnVisitDetailIds( vDids )
				.stream()
				.collect( Collectors.groupingBy( v-> v.getVisitDetail().getId() ));
		
		lvd.forEach( v -> v.setListTreatmentType( map.get( v.getId() )) );
		
		return lvd;
	
	}
	
	private Order[] createOrder(VisitSearchBean osb) {
		
		if( CollectionUtils.isEmpty( osb.getOrder() ) ) {
			return null;
		}
		
		List<Order> listOrder = Lists.newArrayList();
		
		for(HashMap<String, String> map : osb.getOrder()) {
			
			Direction dir = (map.get("dir").equals("desc") ? Direction.DESC : Direction.ASC );
			String prop = map.get("column");
			
			switch( prop ) {
				case "1" : prop = "owner.name"; break;
				case "2" : prop = "entryDate"; break;
				case "3" : prop = "leaveDate"; break;
				default : prop = "createdDate";
			}
			
			listOrder.add( new Order(dir, prop) );
		}
		
		return listOrder.toArray(new Order[]{});
		
	}
	
	private PageRequest buildPageRequest(int start, int max, Order... order) {
		
		if( order == null )
			return new PageRequest( start, max );
		else
			return new PageRequest( start, max, new Sort(order) );
		
	}
	
	@InitBinder
	public void binder(WebDataBinder binder) {
	    
		binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
		    public void setAsText(String value) {
		        try {
		            setValue(new SimpleDateFormat("dd/MM/yyyy").parse(value));
		        } catch(ParseException e) {
		            setValue(null);
		        }
		    }

		    public String getAsText() {
		        return new SimpleDateFormat("dd/MM/yyyy").format((Date) getValue());
		    }        

		});
		
	}

}
