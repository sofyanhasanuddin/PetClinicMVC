<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<div class="backForm">
	<table class="form-tbl fontSm" style="width:100%;">
		<tr>
			<td class="form-inline">
				<form id="searchForm">
					<t:label value="Name" mandatory=""/>
					<input style="width: 300px" 
						type="text" 
						class="form-control input-sm" 
						id="txtSearchOwnerName" 
						placeholder="Search by owner name"/>
				</form>
			</td>
		</tr>
		<tr>
			<td>
				<span class="pull-left">
					<button type="button" class="btn btn-sm btn-primary" id="btnSearch">
		        		<i class="fa fa-search"></i> Search
		        	</button>
		        	<button type="button" class="btn btn-sm btn-primary" id="btnClearSearch">
		        		<i class="fa fa-refresh"></i> Clear
		        	</button>
	        	</span>
	        	<span class="pull-right">
		        	<button type="button" class="btn btn-sm btn-primary" id="btnAddOwner">
		        		<i class="fa fa-plus"></i> Add New Owner
		        	</button>
	        	</span>
			</td>
		</tr>
	</table>
</div>

<div style="border: 2px solid #86592d;width: 100%; margin-bottom: 8px;"></div>

<table id="ownerDg" class="table table-striped table-bordered table-hover table-condensed fontSm">
	<thead>
    	<tr>
    		<th style="width:3%;">No</th>
			<th>Name</th>
			<th>Telephone</th>
			<th>City</th>
			<th>Member Since</th>
			<th>Action</th>
		</tr>
	</thead>
</table>

<!-- Owner modal -->
<div id="ownerModal" class="modal fade" role="dialog">
	<div class="modal-dialog modal-lg">
	    <!-- Modal content-->
	    <div class="modal-content">
	     	<div class="modal-body">
	     		<form id="ownerForm" name="ownerForm" data-smk-icon="glyphicon-remove-sign">
	     			<t:baseinput prefix="owner"/>
	     			<div class="backForm">
				      	<table class="form-tbl fontSm">
							<tr>
								<td><t:label value="Owner name" mandatory="true"/></td>
								<td style="width:300px;" >
									<div class="form-group">
										<input type="text" 
											class="form-control input-sm" 
											name="name" 
											id="txtOwnerName" 
											required />
									</div>
								</td>
							</tr>
							<tr>
								<td><t:label value="Address" mandatory="true"/></td>
								<td>
									<div class="form-group">
										<textarea class="form-control input-sm" 
													rows="5" 
													name="address" 
													id="txtOwnerAddress"
													required>
										</textarea>
									</div>
								</td>
							</tr>
							<tr>
								<td><t:label value="City" mandatory="true"/></td>
								<td>
									<div class="form-group">
										<input type="text" 
												class="form-control input-sm" 
												name="city" 
												id="txtOwnerCity"
												required />
									</div>
								</td>
							</tr>
							<tr>
								<td><t:label value="Telephone" mandatory="true"/></td>
								<td>
									<div class="form-group">
										<input type="text" 
											class="form-control input-sm" 
											name="telephone" 
											id="txtOwnerTelphone"
											data-smk-type="number"
											data-smk-max="9999999998"
											data-smk-min="1"
											required />
									</div>
								</td>
							</tr>
						</table>
					</div>
					
					<div style="border: 2px solid #86592d;width: 100%; margin-bottom: 8px;"></div>
				
					<div>
						<button type="button" class="btn btn-sm btn-primary" id="btnAddPets" >
			        		<i class="fa fa-plus"></i> Add new pet
			        	</button>
					</div>
					
					<table class="table table-striped table-bordered table-condensed fontSm" id="petDg" style="width:100%">
					     <thead>
					         <tr>
					         	<th style="text-align:center;">Id</th>
					         	<th style="text-align:center;">Deleted</th>
					            <th style="text-align:center;">Name</th>
					            <th style="text-align:center;">Type</th>
					            <th style="text-align:center;">Date of Birth</th>
					            <th style="text-align:center;">Action</th>
					         </tr>
					     </thead>
					</table>
					
				</form>
			</div>
	      <div class="modal-footer">
	      	<button type="button" class="btn btn-primary" id="btnSaveOwner" >
	        		<i class="fa fa-save"></i> Save
	        </button>
	      	<button type="button" class="btn btn-danger" data-dismiss="modal">
	      		Cancel
	      	</button>
	      </div>
	    </div>
	</div>
</div>

<!-- Pet modal -->
<div id="petModal" class="modal fade" role="dialog">
	<div class="modal-dialog">
	    <!-- Modal content-->
	    <div class="modal-content">
	     	<div class="modal-body">
	     		<form id="petForm" name="petForm" data-smk-icon="glyphicon-remove-sign">
	     			<t:baseinput prefix="pet"/>
			      	<table class="form-tbl fontSm">
						<tr>
							<td><t:label value="Pet Name" mandatory="true"/></td>
							<td>
								<div class="form-group">
									<input type="text" 
										class="form-control input-sm" 
										id="txtPetName" 
										name="name" required />
								</div>
							</td>
						</tr>
						<tr>
							<td><t:label value="Type" mandatory="true"/></td>
							<td>
								<div class="form-group">
									<t:select label="name" 
											name="type.id" 
											value="id" 
											id="txtPetType" 
											urlRemoteData="pet/listPetType"
											required="required"
											showSelect="true" />
								</div>							
							</td>
						</tr>
						<tr>
							<td><t:label value="Date of Birth" mandatory="true"/></td>
							<td>
								<div class="form-group">
									<input type="text" 
										class="form-control input-sm" 
										id="txtPetDOB" 
										name="birthDate" 
										required />
								</div>
							</td>
						</tr>
					</table>
				</form>
			</div>
	      <div class="modal-footer">
	      	<button type="button" class="btn btn-primary" id="btnSavePet">
	      		<i class="fa fa-save"></i> Save
	      	</button>
	      	<button type="button" class="btn btn-danger" data-dismiss="modal">
	      		Cancel
	      	</button>
	      </div>
	    </div>
	</div>
</div>

<jsp:include page="visitform.jsp"/>
 
<script type="text/javascript" >

	var ownerDg, petDg;
	var editModePet,rowNumPet;
	
	function editOwner( id ) {
			
		$("#ownerModal").modal('show');
		
		var ownerObj = getRowDataByDataId( ownerDg, id );
		
		populateFormData( "ownerForm", ownerObj );
		
		addRowsDt( petDg, getData( "pet/listpetByOwner/" + ownerObj.id ) );
		
	}
	
	function deleteOwner( id ) {
		bootbox.confirm({ 
		    size: 'small',
		    message: "Are you sure want to delete ?", 
		    callback: function(result) {  
		    	if( result ) {
 					sendData( { "id" : id }, "owner/delete", function(data) {
 						$.notify( data.message , "success");
 						ownerDg.ajax.reload();
 					});
 				}
		    }
		});
	}
	
	function editPet( rowNum ) {
		editModePet = true;
		rowNumPet = rowNum;
		
		var petObj = getRowDataByRowNum( petDg, rowNumPet );
		
		populateFormData( "petForm", petObj );
		
		$("#txtPetDOB").datepicker('setValue',petObj.birthDate);
		
		$("#petModal").modal('show');
	}
	
	function deletePet( rowNum ) {
		bootbox.confirm({ 
		    size: 'small',
		    message: "Are you sure want to delete pet ?", 
		    callback: function(result) {  
		    	if( result ) {
		    		var petObj = getRowDataByRowNum( petDg, rowNum );
		    		petObj.deleted = true;
		    		
		    		petDg.row( rowNum ).data( petObj );
		    		petDg.column( 1 ).search( false ).draw();
 				}
		    }
		});
	}
	
	function addVisit( id ) {
		addVisitPet( id );
	}
	
	$("#txtPetDOB").datepicker({
		"format" : "dd/mm/yyyy"
	});

 	$(document).ready(function() {
 		
 		ownerDg = $("#ownerDg").DataTable({
 			"iDeferLoading": 0,
 			"processing": true,
 	        "info":     true,
 	       	"searching": false,
 	        "serverSide": true,
 	       	"aaSorting": [],
 	       	"language": {
  	          "emptyTable": "No owners available"
  	        },
	 	    "ajax" : {
	        	"url": "owner/datatables",
	        	"data": function( data ) {
	        		data["name"] = $("#txtSearchOwnerName").val();
	        		planify(data);  
	          	} 
	 	    },
 	        "columns": [
				{ "data": "dummy" },        
 	            { "data": "name" },
 	           	{ "data": "telephone" },
 	            { "data": "city" },
 	           	{ "data": "createdDate" }
 	        ],
 	       "columnDefs": [
                {
                	"targets": 5,
                    "render": function ( data, type, row ) {
                    	
                    	var btnEdit = '<button type="button" ownerDgEdit data="' + row.id + '" class="btn btn-sm btn-primary">' +
										'<i class="fa fa-edit"></i>'+
				             			'</button>';
				             			
                    	var btnDelete = '<button type="button" ownerDgDelete data="' + row.id + '" class="btn btn-sm btn-danger">' +
										'<i class="fa fa-trash-o"></i>'+
				             			'</button>';
				             			
				       	var btnAddVisit = '<button type="button" ownerDgVisit data="' + row.id + '" class="btn btn-sm btn-primary">' +
										'<i class="fa fa-paw"></i>'+
				             			'</button>';
                    	
                        return btnEdit + ' ' + btnAddVisit + ' ' + btnDelete;
                    }
                },
                {
                	"targets": 0,
                	"orderable":false,
                    "render": function ( data, type, row, meta ) {
                    	return ownerDg.page.info().start  + parseInt(meta.row) + 1 + ".";
                    }
                }
            ],
            "drawCallback": function( settings ) {
            	
            	$("button[ownerDgEdit]").on("click",function() {
            		clearRow( petDg );
            		editOwner( $(this).attr('data') );
            	});
            	
            	$("button[ownerDgDelete]").on("click",function() {
            		deleteOwner( $(this).attr('data') );
            	});
            	
            	$("button[ownerDgVisit]").on("click",function() {
            		addVisit( $(this).attr('data') );
            	});
                
            },
            "preDrawCallback": function( settings ) {
            	$("button[ownerDgEdit]").unbind('click');
            	$("button[ownerDgDelete]").unbind('click');
            	$("button[ownerDgVisit]").unbind('click');
            }
 		});
 		
 		petDg = $("#petDg").DataTable({
 			"paging":   false,
 	        "ordering": false,
 	        "info":     false,
 	       	"searching": true,
 	       	"sDom": '<"top"i>rt<"clear">',
 	       	"language": {
 	          "emptyTable": "Owner does not have any pets"
 	        },
	 	    "columns": [
				{ "data": "id" },
				{ "data": "deleted" },
	            { "data": "name" },
	            { "data": "type.name" },
	            { "data": "birthDate" }
        	],
  	       	"columnDefs": [
                 {
                	 "targets": 5,
                     "render": function ( data, type, row, meta ) {
                    	 
                    	 var btnEdit = '<button type="button" petDgEdit data="' + meta.row + '" class="btn btn-primary">' +
										'<i class="fa fa-edit"></i>'+
	             						'</button>';
	             			
				     	var btnDelete = '<button type="button" petDgDelete data="' + meta.row + '" class="btn btn-primary">' +
											'<i class="fa fa-trash-o"></i>'+
					             			'</button>';
				     	
				         return btnEdit + ' ' + btnDelete;
                     }
                 },
                 {
                     "targets": [0],
                     "visible": false,
                     "searchable": true
                 },
                 {
                     "targets": [1],
                     "visible": false,
                     "searchable": true
                 }
             ],
             "drawCallback": function( settings ) {
             	
             	$("button[petDgEdit]").on("click",function() {
             		editPet( $(this).attr('data') );
             	});
             	
             	$("button[petDgDelete]").on("click",function() {
             		deletePet( $(this).attr('data') );
             	});
                 
             },
             "preDrawCallback": function( settings ) {
             	$("button[petDgEdit]").unbind('click');
             	$("button[petDgDelete]").unbind('click');
             }
 		});
 		
 		$("#btnSavePet").on("click",function(e) {
 			
 			if ($('#petForm').smkValidate()) {
 			    
 				$("#petModal").modal('hide');
 	 			
 	 			var type = JSON.parse( $( "#txtPetType option:selected" ).attr("data") );
 	 			
 	 			var petObj =  {
 	 	 				"id" 				: $("#petId").val(),
 	 	 				"name" 				: $("#txtPetName").val(),
 	 	 				"birthDate" 		: $("#txtPetDOB").val(),
 	 	 				"version" 			: $("#petVersion").val(),
 	 	 				"deleted" 			: false,
 	 	 				"type" 				: type,
 	 	 				"listVisitDetail" 	: []
 	 	 			}
 	 			
 	 			//means update
 	 			if( editModePet ) updateRowDtWithRowNum( petDg, rowNumPet, petObj ); 
 	 			else addRowDt( petDg, petObj );

 			  }
 			
 		});
 		
 		$("#btnSaveOwner").on("click",function(e) {
 			
 			if ($('#ownerForm').smkValidate()) {
 				
 				if( ! petDg.data().count() ) {
 					alert("Please add PET");
 					return;	
 				}
 				
 				var owner = {
 		 				"id" 			: $("#ownerId").val(),
 		 				"name" 			: $("#txtOwnerName").val(),
 		 				"address" 		: $("#txtOwnerAddress").val(),
 		 				"city" 			: $("#txtOwnerCity").val(),
 		 				"telephone" 	: $("#txtOwnerTelphone").val(),
 		 				"version" 		: $("#ownerVersion").val(),
 		 				"createdDate" 	: $("#ownerCreatedDate").val(),
 		 				"listPets" 		: getDataTablesData( petDg ),
 		 				"listVisits" 	: []
 		 		};
 		 			
	 			sendData( owner, "owner/save", function( data ) {
	 				$.notify( data.message, "success");
	 				$("#ownerModal").modal('hide');
	 				
			 		ownerDg.ajax.reload();
			 		clearForm("ownerForm");		 		
	 			}); 				
 			}
 			
 		});
 		
 		$("#btnAddOwner").on("click",function(e) {
 			$('#ownerForm').smkClear();
 			clearRow( petDg );
 			$("#ownerModal").modal('show');
 			
 		});
 		
 		$("#btnAddPets").on("click",function(e) {
 			editModePet = false;
 			$('#petForm').smkClear();
 			$("#petModal").modal('show');
 		});
 		
 		$("#btnSearch").on("click",function(e) {
 			ownerDg.ajax.reload();
 		});
 		
 		$("#btnClearSearch").on("click",function(e) {
 			clearForm("searchForm");
 		});
 		
	} );
 
 </script>