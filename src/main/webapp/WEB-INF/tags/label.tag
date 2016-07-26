<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%@ attribute name="value" required="true" %>
<%@ attribute name="mandatory" required="true" %>
<%@ attribute name="name" required="false" %>
<%@ attribute name="id" required="false" %>

<label id="${id}" name="${name}" class="control-label">
	${value}
</label>

<c:if test="${not empty mandatory && fn:containsIgnoreCase( mandatory, 'true') }">
	<label class="mandatoryLabel">*</label>
</c:if>