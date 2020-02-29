<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
    <jsp:include page="head.jsp"/>
    <body>
       <div id="header" class="col100 centerV">
           <img id="logoHeader" class="xlMarginLeft left" src="img/logo.png">
          <!-- Botones en funcion del logueo -->
	    	<c:choose>
			  	<c:when test="${(user!=null) and (user=='admin')}">
	            	<jsp:include page="menu/admin.jsp" />
			  	</c:when>
		  		<c:when test="${(user!=null) and (user=='student')}">
	            	<jsp:include page="menu/student.jsp" />
			  	</c:when>
			  	<c:otherwise>
	                <jsp:include page="menu/visit.jsp" />
			  	</c:otherwise>
			</c:choose>
       </div>

       <div id="contentOptions" class="col100">
            <div id="titleEdit" class="col100">NUEVO ALUMNO</div>
            <div class="col100 centerH xlMarginTop">
                <div id="newStudentData" class="col30">
                	<form action="student" method="post">
	                    <input class="col100 sMarginTop" type="text" placeholder="Nombre" name="name">
	                    <input class="col100 sMarginTop" type="text" placeholder="Apellidos" name="surname">
	                    <input  class="col100 mMarginTop" type="text" placeholder="Usuario" name="username">
	                    <input class="col100 sMarginTop" type="password" placeholder="Contraseña" name="passwd">
	                    <input type="hidden" value="store" name="action">
	                    <button class="col100 lMarginTop" type="submit">Añadir</button>
                    </form>
                </div>
            </div>
       </div>
    </body>
</html>