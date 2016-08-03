/**
 * 
 */

/* copyright stackoverflow */
function planify(data) {
    for (var i = 0; i < data.columns.length; i++) {
        column = data.columns[i];
        column.searchRegex = column.search.regex;
        column.searchValue = column.search.value;
        delete(column.search);
    }
}

function getDataTablesData( dt ) {
	
	if( dt == null)
		throw new Error("Datatable parameter should not be null");
	
	var objArr = new Array();

	dt.rows().every( function ( rowIdx, tableLoop, rowLoop ) {
	    objArr.push( this.data() );
	} );

	return objArr;
	
}

function getRowDataByDataId( dt, id ) {
	
	var obj;
	var leng = dt.rows().data().length;
	
	for(var i = 0; i < leng; i++) {
		obj = dt.rows().data()[i];
		if( obj.id == id )
			return obj;
	} 
	
	return null;
	
}

function getRowDataByRowNum( dt, rowNum ) {
	return dt.row(rowNum).data();
}

function addRowDt( dt, obj ) {
	dt.row.add( obj ).draw( false );
}

function addRowsDt( dt, obj ) {
	dt.rows.add( obj ).draw( false );
}

function updateRowDt( dt, obj ) {
	
	var success = false;
	
	dt.rows().every( function ( rowIdx, tableLoop, rowLoop ) {
		if( this.data().id == obj.id ) {
			this.data( obj )
			success = true;
			return false;
		}
	} );
	
	dt.draw();
	
	if( !success )
		throw new Error("Fail to update row");
	
}

function updateRowDtWithRowNum( dt, rowNum, obj ) {
	
	dt.row(rowNum).data( obj ).draw();
	
}

function clearRow( dt ) {
	dt.clear().draw();
	dt.search( '' ) //also clear filter
	  .columns().search( '' )
	  .draw();
}

function resetForm( form ) {
	$("#" + form).trigger('reset');
}

function clearForm( form ) {
	
	$('#' + form ).find(':input').each( function() {
		
		switch( this.type ) {
			case "button" :
			case "submit" :
				//console.log("(CLEAR FORM) Found button or submit, skip it....");
				break;
			case "checkbox" :
				this.checked = false;
				break
			case "radio"	:
				//console.log("(CLEAR FORM) Found radio, skip it....");
				break;
			case "hidden"	:
				this.value = "";
				break;
			default : 
				var tagName = this.tagName.toLowerCase();
				if( "select" == tagName ) this.selectedIndex = 0;
				else {
					this.value = "";
				}
				break;
		}
		
	});
	
}

function buildComboData(url, value, label, id, showSelect) {
	getDataFunctionParam( url, function( data ) {

		if( showSelect ) {
			$('<option>')
				.val('')
				.text('--Select--')
				.appendTo('#'+id);
		}
			
		$.each(data,function() {
			$('<option>')
				.val(this[value])
				.text(this[label])
				.attr("data",JSON.stringify(this))
				.appendTo('#' + id);
		});
			
	});
}

function buildComboLocalData(data, value, label, id, showSelect) {
	
	if( showSelect ) {
		$('<option>')
			.val('')
			.text('--Select--')
			.appendTo('#'+id);
	}
		
	$.each(data,function() {
		$('<option>')
			.val(this[value])
			.text(this[label])
			.attr("data",JSON.stringify(this))
			.appendTo('#' + id);
	});
	
}

function buildCheckboxData(url, value, label, idPrefix, name, required, inline, containerId) {
	getDataFunctionParam( url, function( data ) {

		var classN = inline ? "checkbox-inline" : "checkbox";
		
		$.each(data,function() {
			
			var lbl = $('<label>')
						.attr("class", classN )
						.text(this[label]);
			
			var chk = $('<input>')
				.attr("type","checkbox")
				.val(this[value])
				.attr("id",idPrefix+this[label])
				.attr("name",name)
				.attr("data",JSON.stringify(this));
			
			if( required)
				chk.attr(required,'');
			
			lbl.prepend( chk );
			$("#"+containerId).append( lbl );
			
		});
			
	});
}

/**
 * Populate a data. 
 * Usually use when viewing data to edit
 */
function populateFormData( form, data ) {
	
	$('#' + form ).find(':input').each( function() {
		switch( this.type ) {
			case "button" :
			case "submit" :
				//console.log("(POPULATE FORM DATA) Find button or submit, skip it....");
				break;
			case "checkbox" :
				var val = getJsonValue( data, this.name );
				if (val === true || val === false ) { //If boolean value
					this.checked = val;
				} else if( val == this.value ) {
					this.checked = true;
				}
				break;
			case "radio"	:
				//console.log("(POPULATE FORM DATA) radio, NOT IMPLEMENET YET skip it....");
				break;
			case "hidden"	:
				if( this.id != null && this.id != "") {
					this.value = getJsonValue( data, this.name );
				}
				break;
			default : 
				if( this.id ) {
					var value = getJsonValue( data, this.name );
					if( value == "undefined") return;
					$(this).val( value );
				}
				break;
		}
		
	});
	
	return true;
}

/**
 * Copyright stack overflow
 */
function getJsonValue( obj, path ) {
	
	try {
		
		if( path == null || path == "" ) return "";
			
		if( path.indexOf(".") != -1 ) {
			return eval("obj."+path);
		} else {
			return obj[path];
		}
		
	}catch(e) {
		return "";
	}
		
}

function sendData( obj, url, fn ) {
	
	$.ajax({
		headers: { 
	        'Accept': "application/json",
	        'Content-Type':  "application/json" 
	    },
		url : url,
		cache: false,
		type : "post",
		async: false,
		data : JSON.stringify( obj ),
		success:function(data) {
			fn(data);
	    }
	});
	
}

/**
 * Follow rest pattern
 */
function getData( url ) {
	
	var returnData = null;
	
	$.ajax({
		headers: { 
			'Accept': "application/json",
	        'Content-Type':  "application/json" 
	    },
		url : url,
		cache: false,
		type : "get",
		async: false,
    }).then(function(data) {
       returnData = data;
    });
	
	return returnData;
	
}

/**
 * Follow rest pattern
 */
function getDataFunctionParam( url, fn ) {
	
	var returnData = null;
	
	$.ajax({
		headers: { 
			'Accept': "application/json",
	        'Content-Type':  "application/json" 
	    },
		url : url,
		cache: false,
		type : "get",
		async: false,
    }).then(function( data ) {
       fn( data );
    });
	
	return returnData;
	
}