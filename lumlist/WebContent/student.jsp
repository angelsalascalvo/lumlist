<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
    <jsp:include page="head.jsp" />  
    <body>
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

       <div id="contentStudent" class="col100">
            <!-- panel lateral informacion personal -->
            <div id="personalSection" class="col25 row100 centerH">
                <div class="col65">
                	<div id="photoContainer" class="col100">
	                	<!-- Asignar fotografia si existe -->
	                	<c:choose>
						  <c:when test="${existPhoto}">
	                       	<img id="imageProfile" class="col100" src="uploads/${student.id}.jpg">
						  </c:when>
						  <c:otherwise>
	                       	<img id="imageProfile" class="col100" src="img/generic.jpg">
						  </c:otherwise>
						</c:choose>
                    </div>
                    
                    <span id="infoName" class="col100 lMarginTop">${student.name}</span>
                    <span id="infoSurname" class="col100">${student.surname}</span>
                                        
                    <c:choose>
					  <c:when test="${student.available}">
                       	<button id="infoAvai" class="col100 xlMarginTop">Disponible</button>
					  </c:when>
					  <c:otherwise>
                       	<button id="infoAvai" class="red col100 xlMarginTop">No disponible</button>
					  </c:otherwise>
					</c:choose>
                    
                    
                     <!-- Opciones de administrador  -->
                    <c:choose>
					  	<c:when test="${user=='admin'}">
					  		<div class="col100 mMarginTop">
				            	<a href="./student?action=edit&id=${student.id}"><button class="col100 sMarginTop">Editar Alumno</button></a>
                    		</div>
                    		<div class="col100">
                    			<button id="bRemoveStudent" class="col100 sMarginTop red">Eliminar Alumno</button>
                    		</div>
					  	</c:when>
				  	</c:choose>
                    
                </div>
            </div>
            
            <!-- panel con informacion completa -->
            <div id="dataSection" class="col30 row100" >
                <span class="titleData col100 lMarginTop">Datos personales:</span>
                <span class="col100 mMarginTop contentInfo">
                    <color class="grayFont">Nombre:</color> ${student.name}
                    <br> <color class="grayFont">Apellidos:</color> ${student.surname}
                    <br> <color class="grayFont">Fecha nacimiento:</color> ${newDate}
                    <br> <color class="grayFont">Edad:</color> ${age} años
                </span>
                <span class="titleData col100 lMarginTop">Estudios:</span>
                <div id="studies" class="col100 mMarginTop contentInfo">
                    <c:forEach items="${courses}" var="course">
                    	<span>${course.name.toUpperCase()}</span>
                   	</c:forEach>
                </div>

                <span class="titleData col100 lMarginTop">Contacto:</span>
                <div class="contactData col100 mMarginTop centerV contentInfo">
                    <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 511.626 511.626">
                        <path d="M49.106,178.729c6.472,4.567,25.981,18.131,58.528,40.685c32.548,22.554,57.482,39.92,74.803,52.099
                            c1.903,1.335,5.946,4.237,12.131,8.71c6.186,4.476,11.326,8.093,15.416,10.852c4.093,2.758,9.041,5.852,14.849,9.277
                            c5.806,3.422,11.279,5.996,16.418,7.7c5.14,1.718,9.898,2.569,14.275,2.569h0.287h0.288c4.377,0,9.137-0.852,14.277-2.569
                            c5.137-1.704,10.615-4.281,16.416-7.7c5.804-3.429,10.752-6.52,14.845-9.277c4.093-2.759,9.229-6.376,15.417-10.852
                            c6.184-4.477,10.232-7.375,12.135-8.71c17.508-12.179,62.051-43.11,133.615-92.79c13.894-9.703,25.502-21.411,34.827-35.116
                            c9.332-13.699,13.993-28.07,13.993-43.105c0-12.564-4.523-23.319-13.565-32.264c-9.041-8.947-19.749-13.418-32.117-13.418H45.679
                            c-14.655,0-25.933,4.948-33.832,14.844C3.949,79.562,0,91.934,0,106.779c0,11.991,5.236,24.985,15.703,38.974
                            C26.169,159.743,37.307,170.736,49.106,178.729z"/>
                        <path d="M483.072,209.275c-62.424,42.251-109.824,75.087-142.177,98.501c-10.849,7.991-19.65,14.229-26.409,18.699
                            c-6.759,4.473-15.748,9.041-26.98,13.702c-11.228,4.668-21.692,6.995-31.401,6.995h-0.291h-0.287
                            c-9.707,0-20.177-2.327-31.405-6.995c-11.228-4.661-20.223-9.229-26.98-13.702c-6.755-4.47-15.559-10.708-26.407-18.699
                            c-25.697-18.842-72.995-51.68-141.896-98.501C17.987,202.047,8.375,193.762,0,184.437v226.685c0,12.57,4.471,23.319,13.418,32.265
                            c8.945,8.949,19.701,13.422,32.264,13.422h420.266c12.56,0,23.315-4.473,32.261-13.422c8.949-8.949,13.418-19.694,13.418-32.265
                            V184.437C503.441,193.569,493.927,201.854,483.072,209.275z"/>
                    </svg>
                    <span>${student.email}</span>
                </div>

                <div class="contactData col100 sMarginTop centerV contentInfo">
                    <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 513.64 513.64">
                        <path d="M499.66,376.96l-71.68-71.68c-25.6-25.6-69.12-15.359-79.36,17.92c-7.68,23.041-33.28,35.841-56.32,30.72
                            c-51.2-12.8-120.32-79.36-133.12-133.12c-7.68-23.041,7.68-48.641,30.72-56.32c33.28-10.24,43.52-53.76,17.92-79.36l-71.68-71.68
                            c-20.48-17.92-51.2-17.92-69.12,0l-48.64,48.64c-48.64,51.2,5.12,186.88,125.44,307.2c120.32,120.32,256,176.641,307.2,125.44
                            l48.64-48.64C517.581,425.6,517.581,394.88,499.66,376.96z"/>
                    </svg>                        
                    <span>${student.phone}</span>
                </div>

				<c:if test = "${student.linkedin!=null}">
	                <div class="contactData col100 sMarginTop centerV contentInfo">
	                    <svg viewBox="0 0 512 512"xmlns="http://www.w3.org/2000/svg">
	                        <path fill="#0077b5" d="m256 0c-141.363281 0-256 114.636719-256 256s114.636719 256 256 256 256-114.636719 256-256-114.636719-256-256-256zm-74.390625 387h-62.347656v-187.574219h62.347656zm-31.171875-213.1875h-.40625c-20.921875 0-34.453125-14.402344-34.453125-32.402344 0-18.40625 13.945313-32.410156 35.273437-32.410156 21.328126 0 34.453126 14.003906 34.859376 32.410156 0 18-13.53125 32.402344-35.273438 32.402344zm255.984375 213.1875h-62.339844v-100.347656c0-25.21875-9.027343-42.417969-31.585937-42.417969-17.222656 0-27.480469 11.601563-31.988282 22.800781-1.648437 4.007813-2.050781 9.609375-2.050781 15.214844v104.75h-62.34375s.816407-169.976562 0-187.574219h62.34375v26.558594c8.285157-12.78125 23.109375-30.960937 56.1875-30.960937 41.019531 0 71.777344 26.808593 71.777344 84.421874zm0 0"/>
	                    </svg>                 
	                    <a href="${student.linkedin}" target="_blank" style="color:#0077b5"><span>Perfil linkedin</span></a>
	                </div>
				</c:if>
				
				<c:if test = "${student.github!=null}">
	                <div class="contactData col100 sMarginTop centerV contentInfo">
	                    <svg viewBox="0 0 512 512" xmlns="http://www.w3.org/2000/svg">
	                        <path fill="#710557" d="m256 0c-140.609375 0-256 115.390625-256 256 0 119.988281 84.195312 228.984375 196 256v-84.695312c-11.078125 2.425781-21.273438 2.496093-32.550781-.828126-15.128907-4.464843-27.421875-14.542968-36.546875-29.910156-5.816406-9.8125-16.125-20.453125-26.878906-19.671875l-2.636719-29.882812c23.253906-1.992188 43.371093 14.167969 55.3125 34.230469 5.304687 8.921874 11.410156 14.152343 19.246093 16.464843 7.574219 2.230469 15.707032 1.160157 25.183594-2.1875 2.378906-18.972656 11.070313-26.074219 17.636719-36.074219v-.015624c-66.679687-9.945313-93.253906-45.320313-103.800781-73.242188-13.976563-37.074219-6.476563-83.390625 18.238281-112.660156.480469-.570313 1.347656-2.0625 1.011719-3.105469-11.332032-34.230469 2.476562-62.546875 2.984375-65.550781 13.078125 3.867187 15.203125-3.890625 56.808593 21.386718l7.191407 4.320313c3.007812 1.792969 2.0625.769531 5.070312.542969 17.371094-4.71875 35.683594-7.324219 53.726563-7.558594 18.179687.234375 36.375 2.839844 54.464844 7.75l2.328124.234375c-.203124-.03125.632813-.148437 2.035157-.984375 51.972656-31.480469 50.105469-21.191406 64.042969-25.722656.503906 3.007812 14.128906 31.785156 2.917968 65.582031-1.511718 4.65625 45.058594 47.300781 19.246094 115.753906-10.546875 27.933594-37.117188 63.308594-103.796875 73.253907v.015624c8.546875 13.027344 18.816406 19.957032 18.761719 46.832032v105.722656c111.808594-27.015625 196-136.011719 196-256 .003906-140.609375-115.386719-256-255.996094-256zm0 0"/>
	                    </svg>                 
	                    <a href="${student.github}" target="_blank" style="color: #710557"><span>Perfil GitHub</span></a>
	                </div>
                </c:if>
                
                
                <span class="titleData col100 lMarginTop">Observaciones:</span>
                <c:choose>
				  <c:when test="${student.observations==null}">
                      <span class="col100 mMarginTop contentInfo">Ninguna</span>
				  </c:when>
				  <c:otherwise>
                      	<span class="col100 mMarginTop contentInfo">${student.observations}</span>
				  </c:otherwise>
				</c:choose>
                               
            </div>
            
            <!-- panel con curriculum -->
            <div id="cvSection" class="col45 row100">
                <span class="titleData lMarginTop col100">Curriculum:</span>
                <!-- Comprobar si existe un curriculum para establecer uno generico en caso negativo -->
                <c:choose>
				  <c:when test="${existCurri}">
                      	 <embed class="mMarginTop"src='uploads/${student.id}.pdf' width='100%' height='90%' alt='pdf' pluginspage='http://www.adobe.com/products/acrobat/readstep2.html'>
				  </c:when>
				  <c:otherwise>
                      	 <embed class="mMarginTop"src='doc/generic.pdf' width='100%' height='90%' alt='pdf' pluginspage='http://www.adobe.com/products/acrobat/readstep2.html'>
				  </c:otherwise>
				</c:choose>
                   
    
            </div>
       </div>
    </body>
    
    <script>
	    $( document ).ready(function() {
	    	
	    	var id= ${student.id};
	    	
	    	$("#bRemoveStudent").on("click", function(){
	    		var resp = confirm("¿Desea eliminar este alumno?");
	    		if(resp){
	    			window.location.href = "./student?action=remove&id="+id;
	    		}
	    	});
	    });
    </script>
</html>