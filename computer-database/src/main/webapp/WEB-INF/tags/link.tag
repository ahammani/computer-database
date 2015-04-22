<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ attribute name="target" required="true" type="java.lang.String"
	description="Sets the link target"%>
<%@ attribute name="page" required="true" type="java.lang.Integer"
	description=""%>
<%@ attribute name="limit" required="true" type="java.lang.Integer"
	description=""%>
<%@ attribute name="body" required="true" type="java.lang.String"
	description=""%>
<%@ attribute name="aria_label" required="false" type="java.lang.String"
	description=""%>
<%@ attribute name="search" required="false" type="java.lang.String"
	description=""%>
		
<a href="${target}?page=${page}&limit=${limit}&search=${search}">${body}</a>