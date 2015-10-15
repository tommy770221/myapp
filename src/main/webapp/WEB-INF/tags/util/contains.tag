<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<%@attribute name="collection"%>
<%@attribute name="item"%>


<%
	if (item != null && item.length() > 0) {
		if (collection.contains(item)) {
			out.println("true");
		} else {
			out.println("false");
		}
	} else {
		out.println("false");
	}
 %>
