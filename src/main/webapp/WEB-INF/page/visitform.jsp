<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<!-- visit form modal -->
<div id="visitModal" class="modal fade" role="dialog">
	<div class="modal-dialog modal-lg">
	    <!-- Modal content-->
	    <div class="modal-content">
	     	<div class="modal-body">
	     		<form id="visitForm" name="visitForm" data-smk-icon="glyphicon-remove-sign">
	     			<t:baseinput prefix="visit"/>
	     			<div class="backForm">
				      	<table class="form-tbl fontSm">
							<tr>
								<td><t:label value="Owner Name" mandatory="true"/></td>
								<td style="width:300px;" >
									<div class="form-group">
										<input type="text" 
											class="form-control input-sm" 
											name="name" 
											disabled="disabled"
											id="txtVisitOwnerName" />
									</div>
								</td>
							</tr>
							<tr>
								<td><t:label value="Note" mandatory=""/></td>
								<td>
									<div class="form-group">
										<textarea class="form-control input-sm" 
													rows="5" 
													name="note" 
													id="txtVisitNote">
										</textarea>
									</div>
								</td>
							</tr>
							<tr>
								<td><t:label value="Entry Date" mandatory="true"/></td>
								<td>
									<div class="form-group">
										<input type="text" 
											class="form-control input-sm" 
											id="txtVisitEntryDate" 
											name="entryDate" required />
									</div>
								</td>
							</tr>
							<tr>
								<td><t:label value="Leave Date" mandatory="true"/></td>
								<td>
									<div class="form-group">
										<input type="text" 
											class="form-control input-sm" 
											id="txtVisitLeaveDate" 
											name="leaveDate" required />
									</div>
								</td>
							</tr>
						</table>
					</div>
					
					<div style="border: 2px solid #86592d;width: 100%; margin-bottom: 8px;"></div>
				
					<div>
						<button type="button" class="btn btn-sm btn-primary" id="btnAddTreatment" >
			        		<i class="fa fa-plus"></i> Add Pet Treatment
			        	</button>
					</div>
					
					<table class="table table-striped table-bordered table-condensed fontSm" id="visitDtlDg" style="width:100%">
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
		    	<button type="button" class="btn btn-danger" data-dismiss="modal">
		      		Cancel
		      	</button>
		    	<button type="button" class="btn btn-primary" id="btnSaveVisit" >
		        		<i class="fa fa-save"></i> Save
		        </button>
		      </div>
	    </div>
	</div>
</div>

<!-- Treatment modal -->
<div id="treatmentModal" class="modal fade" role="dialog">
	<div class="modal-dialog">
	    <!-- Modal content-->
	    <div class="modal-content">
	     	<div class="modal-body">
	     		<form id="treatmentForm" name="treatmentForm" data-smk-icon="glyphicon-remove-sign">
	     			<t:baseinput prefix="visitDetail"/>
			      	<table class="form-tbl fontSm">
						<tr>
							<td><t:label value="Pet Name" mandatory="true"/></td>
							<td>
								<div class="form-group">
									<t:select label="name" 
											name="pet.id" 
											value="id" 
											id="txtOwnerPetName" 
											showSelect="true"
											required="required" />
								</div>
							</td>
						</tr>
						<tr>
							<td><t:label value="Treatment Type" mandatory="true"/></td>
							<td>
								<div class="form-group">
									<t:select label="name" 
											name="treatmentType.id" 
											value="id" 
											id="txtTreatmentType" 
											urlRemoteData="pet/listTreatment"
											required="required"
											multiple="multiple" />
								</div>							
							</td>
						</tr>
						<tr>
							<td><t:label value="Note" mandatory=""/></td>
							<td>
								<div class="form-group">
									<textarea class="form-control input-sm" 
											rows="5" 
											name="note" 
											id="txtTreatmentNote">
										</textarea>
								</div>
							</td>
						</tr>
					</table>
				</form>
			</div>
	      <div class="modal-footer">
	      	<button type="button" class="btn btn-danger" data-dismiss="modal">
	      		Cancel
	      	</button>
	      	<button type="button" class="btn btn-primary" id="btnSaveTreatment">
	      		<i class="fa fa-save"></i> Save
	      	</button>
	      </div>
	    </div>
	</div>
</div>

<script type="text/javascript" >

	var visitDtlDg, 
		editModeTreatment, 
		rowNumVisitDtlDg,
		owner;
	
	var fn; //Callback function from visit.jsp page, for refreshing datatables
	
	function addVisitPet( ownerId ) {
		
		clearRow( visitDtlDg );
		$('#visitForm').smkClear();
		$("#visitModal").modal('show');
		
		//remove the option
		$("#txtOwnerPetName option").each(function() {
    		$(this).remove();
		});
		
		owner = getData( "owner/getOwner/" + ownerId )
		
		$("#txtVisitOwnerName").val( owner.name );
		
		buildComboData("pet/listpetByOwner/" + owner.id, 'id', 'name', 'txtOwnerPetName', 'true');
		
	}
	
	function editVisit( idVisitAndOwner ) {

		var arr = idVisitAndOwner.split(",");
		
		clearRow( visitDtlDg );
		$('#visitForm').smkClear();
		
		//remove the option
		$("#txtOwnerPetName option").each(function() {
    		$(this).remove();
		});

		populateFormData( "visitForm", getData( "visit/getVisit/" + arr[0] ) );
		
		owner = getData( "owner/getOwner/" + arr[1] );
		
		$("#txtVisitOwnerName").val( owner.name );
		
		buildComboData("pet/listpetByOwner/" + owner.id, 'id', 'name', 'txtOwnerPetName', 'true');
		
		addRowsDt( visitDtlDg, getData( "visit/getListVisitDetail/" + arr[0] ) )
		
		$("#visitModal").modal('show');
		
	}
	
	function editTreatment( rowNum ) {
		
		editModeTreatment = true;
		rowNumVisitDtlDg = rowNum;
		
		$('#treatmentForm').smkClear();
		
		var vDetailObj = getRowDataByRowNum( visitDtlDg, rowNumVisitDtlDg ); 
		
		populateFormData( "treatmentForm", vDetailObj );
		
		$.each( vDetailObj.listTreatmentType, function(e) {
			var dataSelectedTreatment = $(this)[0];
			
			$( "#txtTreatmentType option" ).each(function(e) {
				var dataMasterTreatment = JSON.parse( $(this).attr("data") );
				
				if( dataMasterTreatment.id == dataSelectedTreatment.treatmentType.id ) {
					$(this).prop('selected','selected');
				}
				
			});
		});
		
		$('#treatmentModal').modal('show');
		
	}
	
	function deleteTreatment( rowNum ) {
		bootbox.confirm({ 
		    size: 'small',
		    message: "Are you sure want to delete pet ?", 
		    callback: function(result) {  
		    	if( result ) {
		    		var petObj = getRowDataByRowNum( visitDtlDg, rowNum );
		    		petObj.deleted = true;
		    		
		    		visitDtlDg.row( rowNum ).data( petObj );
		    		visitDtlDg.column( 1 ).search( false ).draw();
 				}
		    }
		});
	}
	
	$("#txtVisitEntryDate, #txtVisitLeaveDate,#txtSearchVisitEntryDate, #txtSearchVisitLeaveDate").datepicker({
		"format" : "dd/mm/yyyy"
	});

 	$(document).ready(function() {
 		
 		visitDtlDg = $("#visitDtlDg").DataTable({
 			"paging":   false,
 	        "ordering": false,
 	        "info":     false,
 	       	"searching": true,
 	       	"sDom": '<"top"i>rt<"clear">',
 	       	"language": {
 	          "emptyTable": "Owner's Pet does not have any treatments"
 	        },
	 	    "columns": [
				{ "data": "id" },
				{ "data": "deleted" },
	            { "data": "pet.name" },
	            { "data": "pet.type.name" },
	            { "data": "pet.birthDate" }
        	],
  	       	"columnDefs": [
                 {
                	 "targets": 5,
                     "render": function ( data, type, row, meta ) {
                    	 
                    	 var btnEdit = '<button type="button" visitDtlDgEdit data="' + meta.row + '" class="btn btn-primary">' +
										'<i class="fa fa-edit"></i>'+
	             						'</button>';
	             			
				     	var btnDelete = '<button type="button" visitDtlDgDelete data="' + meta.row + '" class="btn btn-primary">' +
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
             	
             	$("button[visitDtlDgEdit]").on("click",function() {
             		editTreatment( $(this).attr('data') );
             	});
             	
             	$("button[visitDtlDgDelete]").on("click",function() {
             		deleteTreatment( $(this).attr('data') );
             	});
                 
             },
             "preDrawCallback": function( settings ) {
             	$("button[visitDtlDgEdit]").unbind('click');
             	$("button[visitDtlDgDelete]").unbind('click');
             }
 		});
 		
 		$("#btnSaveTreatment").on("click",function(e) {
 			
 			if ($('#treatmentForm').smkValidate()) {
 			    
 				$("#treatmentModal").modal('hide');
 	 			
 	 			var pet = JSON.parse( $( "#txtOwnerPetName option:selected" ).attr("data") );
 	 			var vDetailTreatmentType = new Array();
 	 			
 	 			$( "#txtTreatmentType option:selected" ).each(function(e) {
 	 				vDetailTreatmentType.push( {"treatmentType" : JSON.parse( $(this).attr("data") ) } );
 	 			});
 	 			
 	 			var visitDetail =  {
 	 	 				"id" 				: $("#visitDetailId").val(),
 	 	 				"pet" 				: pet,
 	 	 				"listTreatmentType" : vDetailTreatmentType,
 	 	 				"note" 				: $("#txtTreatmentNote").val(),
 	 	 				"deleted" 			: false,
 	 	 				"version" 			: $("#visitDetailVersion").val(),
 	 	 				"createdDate"		: $("#visitDetailCreatedDate").val()
 	 	 			}
 	 			
 	 			//means update
 	 			if( editModeTreatment ) updateRowDtWithRowNum( visitDtlDg, rowNumVisitDtlDg, visitDetail ); 
 	 			else addRowDt( visitDtlDg, visitDetail );

 			  }
 			
 		});
 		
 		$("#btnSaveVisit").on("click",function(e) {
 			
 			if ($('#visitForm').smkValidate()) {
 				
 				if( ! visitDtlDg.data().count() ) {
 					alert("Please add PET");
 					return;	
 				}
 				
 				var objVisit = {
 		 				"id" 				: $("#visitId").val(),
 		 				"owner" 			: owner,
 		 				"note" 				: $("#txtVisitNote").val(),
 		 				"entryDate" 		: $("#txtVisitEntryDate").val(),
 		 				"leaveDate" 		: $("#txtVisitLeaveDate").val(),
 		 				"version" 			: $("#visitVersion").val(),
 		 				"createdDate" 		: $("#visitCreatedDate").val(),
 		 				"listVisitDetail" 	: getDataTablesData( visitDtlDg )
 		 		};
 		 			
	 			sendData( objVisit, "visit/save", function( data ) {
	 				$.notify( data.message, "success");
	 				$("#visitModal").modal('hide');
	 				
	 				if( fn )
	 					fn();
	 				
	 			});	
 			}
 			
 		});
 		
 		
 		$("#btnAddTreatment").on("click",function(e) {
 			editModeTreatment = false;
 			$('#treatmentForm').smkClear();
 			$("#treatmentModal").modal('show');
 		});
 		
	} );
 
 </script>