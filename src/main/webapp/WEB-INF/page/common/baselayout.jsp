<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
	
	<!-- <meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate" />
	<meta http-equiv="Pragma" content="no-cache" />
	<meta http-equiv="Expires" content="0" /> -->
	
	<sec:csrfMetaTags />
	
    <head>    
        <tiles:insertAttribute name="head" />
        <tiles:insertAttribute name="css" />
        <tiles:insertAttribute name="javascript" />
    </head>
    <body>
    	<form action="<%=request.getContextPath()%>/logout" name="logout" method="post">
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		</form>
    	<div id="wrapper">
    		<tiles:insertAttribute name="nav" />
    		<div id="page-wrapper" style="padding-top: 10px;">
				<tiles:insertAttribute name="body" />
			</div>
			<tiles:insertAttribute name="footer" />
		</div>
    </body>
</html>
