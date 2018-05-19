<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%@ attribute name="idPrefix" required="true" %>
<%@ attribute name="name" required="true" %>
<%@ attribute name="inline" required="true" %>
<%@ attribute name="required" required="false" %>
<%@ attribute name="urlRemoteData" required="true" %>
<%@ attribute name="value" required="true" %>
<%@ attribute name="label" required="true" %>
<%@ attribute name="containerId" required="true" %>

<c:if test="${ not empty urlRemoteData }">
	<script type="text/javascript">
		buildCheckboxData('${urlRemoteData}','${value}','${label}','${idPrefix}','${name}','${required}','${inline}','${containerId}');
	</script>
</c:if>