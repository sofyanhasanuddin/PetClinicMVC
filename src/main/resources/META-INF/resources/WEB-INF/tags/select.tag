<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%@ attribute name="id" required="true" %>
<%@ attribute name="name" required="true" %>
<%@ attribute name="required" required="false" %>
<%@ attribute name="urlRemoteData" required="false" %>
<%@ attribute name="value" required="true" %>
<%@ attribute name="label" required="true" %>
<%@ attribute name="multiple" required="false" %>
<%@ attribute name="showSelect" required="false" %>

<select class="form-control input-sm" ${required} ${multiple} id="${ id }" name="${ name }">
</select>

<c:if test="${ not empty urlRemoteData }">
	<script type="text/javascript">
		buildComboData('${urlRemoteData}','${value}','${label}','${id}', '${showSelect}');
	</script>
</c:if>