<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<html>
	
	<meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate" />
	<meta http-equiv="Pragma" content="no-cache" />
	<meta http-equiv="Expires" content="0" />
	
    <head>    
        <tiles:insertAttribute name="head" />
        <tiles:insertAttribute name="css" />
        <tiles:insertAttribute name="javascript" />
    </head>
    <body>
    	<div id="wrapper">
    		<tiles:insertAttribute name="nav" />
    		<div id="page-wrapper" style="padding-top: 10px;">
				<tiles:insertAttribute name="body" />
			</div>
			<tiles:insertAttribute name="footer" />
		</div>
    </body>
</html>
