<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%@ attribute name="prefix" required="true" %>

<input type="hidden" name="id" id="${prefix}Id">
<input type="hidden" name="version" id="${prefix}Version">
<input type="hidden" name="createdDate" id="${prefix}CreatedDate">