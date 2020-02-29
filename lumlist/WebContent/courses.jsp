<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
    <jsp:include page="head.jsp" />  
    <body>
        <!-- VENTANA MODAL -->
        <div id="modal" class="col100 row100 centerVH absolute" style="display: none">
            <div id="inside" class="col50 absolute">
                <div id="closeModal">
                    <svg viewBox="0 0 386.667 386.667" xmlns="http://www.w3.org/2000/svg">
                        <path d="m386.667 45.564-45.564-45.564-147.77 147.769-147.769-147.769-45.564 45.564 147.769 147.769-147.769 147.77 45.564 45.564 147.769-147.769 147.769 147.769 45.564-45.564-147.768-147.77z"/>
                    </svg>
                </div>
                <div id="newStudy" class="col100">
                	<form id="fAdd" action="courses" method="post">
                		 <span class="col100 titModal"> AÑADIR CURSO</span>
                    	<input class="col100 xlMarginTop" type="text" placeholder="Nombre" name="name">
                    	<input type="hidden" name="action" value="store">
                    	<button id="bSubmitAdd" class="col100 mMarginTop" type="button">Añadir</button>
                	</form>
                </div>
                <div id="editStudy" class="col100" style="display: none">
                	<form id="fEdit" action="courses" method="post">
	                    <span class="col100 titModal"> EDITAR CURSO</span>  
	                    <input id="nameEdit" class="col100 xlMarginTop" type="text" placeholder="Nombre" name="name">
	                    <input id="idEdit" type="hidden" name="id">
                        <input type="hidden" name="action" value="update">
	                    <button id="bSubmitEdit" class="col100 mMarginTop" type="button">Guardar Cambios</button>
                    </form>
                </div>
            </div>
        </div>

        <!-- CONTENIDO -->
       <div id="header" class="col100 centerV">
           	<a href="./">
	   			<img id="logoHeader" class="xlMarginLeft left" src="img/logo.png">
	   		</a>
      		
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

       <div id="contentStudies" class="col100">
            <div id="titleEdit" class="col100">CURSOS</div>
            <div class="col100 xlMarginTop centerH">
                <div class="col50">
                	<c:forEach items="${courses}" var="course">
	                    <div id="${course.id}" class="studyElement col100 centerV">
	                        <strong class="nameCourse left">${course.name}</strong>
	                        <button class="right bEdit">Editar</button>
	                        <!-- Formulario de eliminar -->
	                        <form class="removeForm${course.id}" action="courses" method="post">
	                        	<input type="hidden" name="id" value="${course.id}">
                    	        <input type="hidden" name="action" value="remove">
	                        	<button class="bSubmitRemove right red sMarginLeft" type="button" value="${course.id}">Eliminar</button>                    
	                        </form>
	                            
	                    </div>
                    </c:forEach>
                </div>
            </div>
       </div>

       <div id="bAddStudy">
            <button>
                <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 472.615 472.615">
                    <polygon points="278.565,194.051 278.565,0 194.053,0 194.053,194.051 0,194.051 0,278.564 194.053,278.564 194.053,472.615 
                    278.565,472.615 278.565,278.564 472.615,278.564 472.615,194.051"/>
                </svg>
            </button>
       </div>
    </body>
    
    <script>
    	$( document ).ready(function() {
    		//Click boton ventana modal
    		$("#closeModal").on("click", function(){
    			$("#modal").hide();
    		})
    		
    		//Click boton añadir curso
    		$("#bAddStudy").on("click", function(){
    			$("#modal").show();
    			$("#newStudy").show();
    			$("#editStudy").hide();
    		});
    		
    		//Click boton editar curso
    		$(".bEdit").on("click", function(){   		
				var id = $(this).parent().attr("id");
   				$("#idEdit").val(id);
   				$("#nameEdit").val($("#"+id+" .nameCourse").text());
   				//Mostrar ventana modal
   				$("#modal").show();
    			$("#newStudy").hide();
    			$("#editStudy").show();
    		});
    		
    		//Click boton eliminar curso
    		$(".bSubmitRemove").on("click", function(){
    			var resp = confirm("¿Desea eliminar el recurso?");
    			if(resp){
    				var id=$(this).val();
    				$(".removeForm"+id).submit();
    			}
    		});
    		
    		//Click boton añadir
    		$("#bSubmitAdd").on("click", function(){ $("#fAdd").submit(); });
    		$("#bSubmitEdit").on("click", function(){ $("#fEdit").submit(); });
    	});
    </script>
</html>