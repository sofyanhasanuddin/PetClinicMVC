package org.sofyan.latihan.app.ctrl;

import java.util.HashMap;
import java.util.List;

import net.sf.jasperreports.engine.JREmptyDataSource;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sofyan.latihan.app.bean.DataTableReturnBean;
import org.sofyan.latihan.app.bean.OwnerSearchBean;
import org.sofyan.latihan.app.ctrl.message.Message;
import org.sofyan.latihan.app.model.Owner;
import org.sofyan.latihan.app.repository.specification.CustomerSpecification;
import org.sofyan.latihan.app.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.collect.Lists;

@Controller
public class OwnerCtrl {
	
	public static Logger log = LoggerFactory.getLogger( OwnerCtrl.class );
	
	@Autowired
	private OwnerService ownerServiceImpl;
	
	@RequestMapping( value 		= "/page/owner/getOwner/{id}",
					headers 	=  "Content-type=application/json",
					method 		= RequestMethod.GET,
					produces 	= MediaType.APPLICATION_JSON_VALUE )
	@ResponseBody
	public Owner getOwner(@PathVariable("id") Long ownerId) {
		
		log.debug("Get owner with id : {}", ownerId);
	
		if( ownerId == null )
			return null;
	
		return this.ownerServiceImpl.findOne(ownerId);
	
	}
	
	@RequestMapping( value 		= "/page/owner/save",
					 headers 	= "Content-type=application/json",
					 method 	= RequestMethod.POST,
					 produces 	= MediaType.APPLICATION_JSON_VALUE )
	@ResponseBody
	public Message save(@RequestBody Owner owner) {

		String msg;
		if( owner.getId() == null || owner.getId().equals( 0L )) msg = "Successfully save owner";
		else msg = "Successfully edit owner";
		
		this.ownerServiceImpl.save( owner );
		
		return Message.successMessage( msg );
		
	}
	
	@RequestMapping( value 		= "/page/owner/delete",
			 		headers 	= "Content-type=application/json",
			 		method 		= RequestMethod.POST,
			 		produces 	= MediaType.APPLICATION_JSON_VALUE )
	@ResponseBody
	public Message delete(@RequestBody Owner owner) {
		
		owner = this.ownerServiceImpl.findOne( owner.getId() );
		
		this.ownerServiceImpl.delete( owner );
	
		return Message.successMessage("Successfully delete owner");
		
	}
	
	@RequestMapping(method=RequestMethod.GET,value="/page/owner/datatables")
	@ResponseBody
	public DataTableReturnBean ownerDataTable(@ModelAttribute OwnerSearchBean osb) {
		
		PageRequest pr = buildPageRequest( osb.getStartPaging(), osb.getLength(), createOrder(osb) );
		
		Page<Owner> pageOwner = this.ownerServiceImpl.findAll( CustomerSpecification.findByCriteria(osb), pr );
		
		return new DataTableReturnBean( osb.getDraw(), 
				pageOwner.getTotalElements(), 
				pageOwner.getTotalElements(), 
				pageOwner.getContent());
		
	}
	
	@RequestMapping(value = "/page/owner/report", 
					method = RequestMethod.GET)
	public ModelAndView getPdf() {
	    
		ModelAndView modelAndView = new ModelAndView("ownerReport");

		modelAndView.addObject("format", "pdf" );
		modelAndView.addObject("datasource", new JREmptyDataSource() );
		modelAndView.addObject("OWNER_DS", this.ownerServiceImpl.findAll() );
	    
	    return modelAndView;
	    
	}
	
	
	private Order[] createOrder(OwnerSearchBean osb) {
		
		if( CollectionUtils.isEmpty( osb.getOrder() ) ) {
			return null;
		}
		
		List<Order> listOrder = Lists.newArrayList();
		
		for(HashMap<String, String> map : osb.getOrder()) {
			
			Direction dir = (map.get("dir").equals("desc") ? Direction.DESC : Direction.ASC );
			String prop = map.get("column");
			
			switch( prop ) {
				case "1" : prop = "name"; break;
				case "2" : prop = "telephone"; break;
				case "3" : prop = "city"; break;
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

}
