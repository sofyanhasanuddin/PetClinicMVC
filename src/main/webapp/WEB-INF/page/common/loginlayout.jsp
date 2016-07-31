<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<html>
	
	<!-- <meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate" />
	<meta http-equiv="Pragma" content="no-cache" />
	<meta http-equiv="Expires" content="0" /> -->
	
    <head>    
        <tiles:insertAttribute name="head" />
        <tiles:insertAttribute name="css" />
    </head>
    <body>
		<tiles:insertAttribute name="body" />
		<tiles:insertAttribute name="footer" />
    </body>
    <tiles:insertAttribute name="javascript" />
</html>
