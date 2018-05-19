<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<div class="backForm">
	<form id="searchForm">
		<table class="form-tbl fontSm" style="width:100%;">
			<tr>
				<td>
					<t:label value="Name" mandatory=""/>
				</td>
				<td class="form-inline">
					<input style="width: 300px" 
						type="text" 
						class="form-control input-sm" 
						id="txtSearchOwnerName" 
						placeholder="Search by owner name"/>
				</td>
			</tr>
			<tr>
				<td class="form-inline">
					<t:label value="Entry Date" mandatory=""/>
				</td>
				<td class="form-inline">
					<input style="width: 300px" 
						type="text" 
						class="form-control input-sm" 
						id="txtSearchVisitEntryDate" 
						placeholder="Search by entry date"/>
					&nbsp;&nbsp;
					<t:label value="Leave Date" mandatory=""/>
					<input style="width: 300px" 
						type="text" 
						class="form-control input-sm" 
						id="txtSearchVisitLeaveDate" 
						placeholder="Search by leave date"/>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<span class="pull-left">
						<button type="button" class="btn btn-sm btn-primary" id="btnSearch">
			        		<i class="fa fa-search"></i> Search
			        	</button>
			        	<button type="button" class="btn btn-sm btn-primary" id="btnClearSearch">
			        		<i class="fa fa-refresh"></i> Clear
			        	</button>
		        	</span>
				</td>
			</tr>
		</table>
	</form>
</div>

<div style="border: 2px solid #86592d;width: 100%; margin-bottom: 8px;"></div>

<table id="visitDg" class="table table-striped table-bordered table-hover table-condensed fontSm">
	<thead>
    	<tr>
    		<th style="width:3%;">No</th>
			<th>Name</th>
			<th>Entry Date</th>
			<th>Leave Date</th>
			<th>Pets</th>
			<th>Action</th>
		</tr>
	</thead>
</table>


<jsp:include page="visitform.jsp"/>
 
<script type="text/javascript" >

	var visitDg;
	
	function deleteVisit( id ) {
		bootbox.confirm({ 
		    size: 'small',
		    message: "Are you sure want to delete ?", 
		    callback: function(result) {  
		    	if( result ) {
 					sendData( { "id" : id }, "visit/delete", function(data) {
 						$.notify( data.message , "success");
 						visitDg.ajax.reload();
 					});
 				}
		    }
		});
	}
	
	function refreshVisitDg() {
		visitDg.ajax.reload();
	}

 	$(document).ready(function() {
 		
 		visitDg = $("#visitDg").DataTable({
 			"iDeferLoading": 0,
 			"processing": true,
 	        "info":     true,
 	       	"searching": false,
 	        "serverSide": true,
 	       	"aaSorting": [],
 	       	"language": {
  	          "emptyTable": "No visits available"
  	        },
	 	    "ajax" : {
	        	"url": "visit/datatables",
	        	"data": function( data ) {
	        		data["name"] = $("#txtSearchOwnerName").val();
	        		data["entryDate"] = ( $("#txtSearchVisitEntryDate").val() != "" ? $("#txtSearchVisitEntryDate").val() : null );
	        		data["leaveDate"] = ( $("#txtSearchVisitLeaveDate").val() != "" ? $("#txtSearchVisitLeaveDate").val() : null );
	        		planify(data);  
	          	} 
	 	    },
 	        "columns": [
				{ "data": "dummy" },        
 	            { "data": "owner.name" },
 	           	{ "data": "entryDate", "sClass": "text-center" },
 	            { "data": "leaveDate", "sClass": "text-center" },
 	           	{ "data": "listVisitDetail" }
 	        ],
 	       "columnDefs": [
                {
                	"targets": 5,
                    "render": function ( data, type, row ) {
                    	
                    	var btnEdit = '<button type="button" visitDgEdit data="' + row.id + ',' + row.owner.id + '" class="btn btn-sm btn-primary">' +
										'<i class="fa fa-edit"></i>'+
				             			'</button>';
				             			
                    	var btnDelete = '<button type="button" visitDgDelete data="' + row.id + '" class="btn btn-sm btn-danger">' +
										'<i class="fa fa-trash-o"></i>'+
				             			'</button>';
				             			
				       	var btnAddVisit = '<button type="button" visitDgVisit data="' + row.owner.id + '" class="btn btn-sm btn-primary">' +
										'<i class="fa fa-paw"></i>'+
				             			'</button>';
                    	
                        return btnEdit + ' ' + btnAddVisit + ' ' + btnDelete;
                    }
                },
                {
                	"targets": 4,
                	"orderable":false,
                    "render": function ( data, type, row, meta ) {
                    	
                    	var arr = new Array();

                    	$.each( row.listVisitDetail, function(e) {
                    		arr.push( this.pet.name );
                    	});
                    	
                    	return arr.join(", ");
                    }
                },
                {
                	"targets": 0,
                	"orderable":false,
                    "render": function ( data, type, row, meta ) {
                    	return visitDg.page.info().start  + parseInt(meta.row) + 1 + ".";
                    }
                }
            ],
            "drawCallback": function( settings ) {
            	
            	$("button[visitDgEdit]").on("click",function() {
            		editVisit( $(this).attr('data') );
            	});
            	
            	$("button[visitDgDelete]").on("click",function() {
            		fn = refreshVisitDg;
            		deleteVisit( $(this).attr('data') );
            	});
            	
            	$("button[visitDgVisit]").on("click",function() {
            		fn = refreshVisitDg;
            		addVisitPet( $(this).attr('data') );
            	});
                
            },
            "preDrawCallback": function( settings ) {
            	$("button[visitDgEdit]").unbind('click');
            	$("button[visitDgDelete]").unbind('click');
            	$("button[visitDgVisit]").unbind('click');
            }
 		});
 		
 		$("#btnSearch").on("click",function(e) {
 			visitDg.ajax.reload();
 		});
 		
 		$("#btnClearSearch").on("click",function(e) {
 			clearForm("searchForm");
 		});
 		
	} );
 
 </script>