<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
    <jsp:include page="head.jsp" />  
    <body>
       <div id="header" class="col100 centerV" style="position: fixed">
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
       
       <div id="contentSearch" class="col100">
            <!-- Barra lateral de busqueda -->
            <div id="sidebarSearch" class="width20" style="position: fixed;">
                <div id="searchbarSide" class="col100">
                    <input id="search" class="col100" type="text" placeholder="Buscar..." value="${key}">
                    <svg id="bSearch" viewBox="0 0 515.558 515.558">
                        <path d="m378.344 332.78c25.37-34.645 40.545-77.2 40.545-123.333 0-115.484-93.961-209.445-209.445-209.445s-209.444 93.961-209.444 209.445 93.961 209.445 209.445 209.445c46.133 0 88.692-15.177 123.337-40.547l137.212 137.212 45.564-45.564c0-.001-137.214-137.213-137.214-137.213zm-168.899 21.667c-79.958 0-145-65.042-145-145s65.042-145 145-145 145 65.042 145 145-65.043 145-145 145z"/>
                    </svg>
                </div>
                <span class="col100 lMarginTop"><strong>Disponibilidad:</strong></span>
                <div class="check col100 mMarginTop">
                    <label class="containerRadio">Disponible
                        <input class="fStatus" type="radio" name="status" value="true">
                        <span class="checkmarkRadio"></span>
                    </label>
                </div>

                <div class="check col100">
                    <label class="containerRadio">No disponible
                        <input class="fStatus" type="radio" name="status" value="false">
                        <span class="checkmarkRadio"></span>
                    </label>
                </div>
                <span class="col100 lMarginTop mMarginBottom"><strong>Por curso:</strong></span>
                
           			<c:forEach items="${courses}" var="cou">		                
		                <div class="check col100">
                             <label class="containerRadio">${cou.name.toUpperCase()}
                                 <input class="fCourse" type="radio" name="course" value="${cou.name}">
                                 <span class="checkmarkRadio"></span>
                             </label>
                         </div>
                	</c:forEach>

                <div id="showAll" class=" col100 centerH">
                    <button id="bShowAll">Ver todos</button>
                </div>
            </div>

            <!-- Contenedor con resultados de la busqueda -->
            <div id="resultSearch" class="col80">

				<c:forEach items="${students}" var="student">
					<a href="./student?action=show&id=${student.id}">
		                <div id="result${student.id}" class="elementFind col166">
		                	<div id="photoContainer" class="col100">
			                 	<!-- Si tiene foto la cargamos -->
			                    <c:choose>
								  <c:when test="${student.hasPhoto}">
			                      	 	 <img id="imageProfile" class="col100" src="uploads/${student.id}.jpg">
								  </c:when>
								  <c:otherwise>
			                      	 	 <img id="imageProfile" class="col100" src="img/generic.jpg">
								  </c:otherwise>
								</c:choose>
		                   	</div>
		                   	
		                    <span id="infoName" class="col100 mMarginTop">${student.name}</span>
		                    <span id="infoSurname" class="col100">${student.surname}</span>
		                    <div class="studiesElement col100 sMarginTop">
		                    	<c:forEach items="${student.coursesNames}" var="course">
		                        	<span class="left sMarginTop sMarginRight">${course.toUpperCase()}</span>
		                        </c:forEach>
		                    </div>
		                    
		                    <!-- Marcar como disponible o no -->
		                    <c:choose>
							  <c:when test="${student.available}">
		                      	 	<button id="infoAvai" class="col100 mMarginTop">Disponible</button>
							  </c:when>
							  <c:otherwise>
		                      	 	<button id="infoAvai" class="red col100 mMarginTop">No disponible</button>
							  </c:otherwise>
							</c:choose>
		                </div>
	                </a>
               	</c:forEach>
            </div>
       </div>
    </body>
    <script>
    	$( document ).ready(function() {
    		
   			//Acciones al pulsar el filtro de estado
    		$(".fStatus").on("click", function(){ search() });   			
    		//Acciones al pulsar el filtro de curso
    		$(".fCourse").on("click", function(){ search() });
    		//Acciones al buscar con la barra de busqueda
    		$('#search').on("keypress",function(e){ 
    			if(e.which == 13) {
    				search();
    		    }
   			});
    		$("#bSearch").on("click", function(){ search() });
    		//Acciones al pulsar el boton de ver todos
    		$("#bShowAll").on("click", function(){
    			url="./index?action=search";
    			window.location.href = url;
    		});   			
    		
    		
    		/*
    		* METODO QUE PERMITE CONFECCIONAR LA RUTA DE BUSQUEDA EN FUNCION DE LOS PARAMETROS MARCADOS
    		*/
    		function search(){
    			url="./index?action=search";
    			var filterCourse = $('.fCourse:checked').val();
    			//Agregar filtro curso seleccionado
    			if(filterCourse!=undefined){
    				url+= "&course="+encodeURIComponent(filterCourse);
    			}
    			//Filtro de estado
    			var filterStatus = $('.fStatus:checked').val();
    			//Agregar filtro curso seleccionado
    			if(filterStatus!=undefined){
    				url+= "&status="+encodeURIComponent(filterStatus);
    			}
    			
    			//Agregar clave de busqueda
    			if($("#search").val()==""){
    				url+="&key="+encodeURIComponent("all");
    			}else{
    				url+="&key="+encodeURIComponent($("#search").val());
    			}
    			
        		window.location.href = url;
    		}
    	});
    	
    	//ACTUALIZAR VALORES FILTROS SEGUN LA BUSQUEDA REALIZADA
    	
    	//Estado
    	if(getUrlVars()["status"]!=undefined){
    		$(".fStatus[value="+getUrlVars()["status"]+"]").attr("checked", "checked");
    	}
    	if(getUrlVars()["course"]!=undefined){
    		$(".fCourse[value='"+decodeURIComponent(getUrlVars()["course"])+"']").attr("checked", "checked");
    	}
    	
    	/*
    	* METODO PARA OBTENER LOS PARAMETROS INDICADOS EN LA RUTA
    	*/
    	function getUrlVars() {
    	    var vars = {};
    	    var parts = window.location.href.replace(/[?&]+([^=&]+)=([^&]*)/gi, function(m,key,value) {
    	        vars[key] = value;
    	    });
    	    return vars;
    	}
    	
    	//Incluir salto de linea cada 7 elementos
    	var cont =0;
   		$( ".elementFind" ).each(function(index) {
   			cont++;
   		  	if(cont==7){
   		  		cont=1;
   		  		$(this).css("clear", "both");
   		  	}
   		});
   		
   		//Mostrar indicacion si no se obtienen resultados
   		if($(".elementFind").length==0){
   			var element = "<center>Sin resultados</center>";
   			$("#resultSearch").append(element);
   		}
    </script>
</html>