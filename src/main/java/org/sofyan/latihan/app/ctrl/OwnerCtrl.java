package org.sofyan.latihan.app.ctrl;

import java.util.HashMap;

import org.apache.commons.collections.CollectionUtils;
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

@Controller
public class OwnerCtrl {
	
	@Autowired
	private OwnerService ownerServiceImpl;
	
	@RequestMapping( value 		= "/page/owner/getOwner/{id}",
					headers 	=  "Content-type=application/json",
					method 		= RequestMethod.GET,
					produces 	= MediaType.APPLICATION_JSON_VALUE )
	@ResponseBody
	public Owner getOwner(@PathVariable("id") Long ownerId) {
	
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
		
		this.ownerServiceImpl.save( owner );
		
		String msg;
		if( owner.getId() == null || owner.getId().equals( 0 )) msg = "Successfully save owner";
		else msg = "Successfully edit owner";
				
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
		
		int start = ( ( osb.getStart() / osb.getLength() ) + 1 ) - 1;
		
		PageRequest pr = buildPageRequest( start, osb.getLength(), createOrder(osb) );
		
		Page<Owner> pageOwner = this.ownerServiceImpl.findAll( CustomerSpecification.findByCriteria(osb), pr );
		
		return new DataTableReturnBean( osb.getDraw(), 
				pageOwner.getTotalElements(), 
				pageOwner.getTotalElements(), 
				pageOwner.getContent());
		
	}
	
	private Order[] createOrder(OwnerSearchBean osb) {
		
		if( CollectionUtils.isEmpty( osb.getOrder() ) ) {
			return null;
		}
		
		Order[] order = new Order[ osb.getOrder().size() ];
		int index = 0;
		
		for(HashMap<String, String> map : osb.getOrder()) {
			
			Direction dir = (map.get("dir").equals("desc") ? Direction.DESC : Direction.ASC );
			String prop = map.get("column");
			
			switch( prop ) {
				case "1" : prop = "name"; break;
				case "2" : prop = "telephone"; break;
				case "3" : prop = "city"; break;
				default : prop = "createdDate";
			}
			
			order[index++] = new Order(dir, prop);
		}
		
		return order;
		
	}
	
	private PageRequest buildPageRequest(int start, int max, Order... order) {
		
		if( order == null )
			return new PageRequest( start, max );
		else
			return new PageRequest( start, max, new Sort(order) );
		
	}

}