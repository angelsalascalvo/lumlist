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

       <div id="contentEditStudent" class="col100">
           <div id="titleEdit" class="col100">EDITAR PERFIL</div>
           
           <form action="student" method="post" enctype="multipart/form-data">
           <div id="infoEditCont" class="col100">
                <div id="personalSection" class="col20">
                
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
					
                    <div id="editPhoto" class="col100 sMarginTop centerH centerV">
                        <svg id="editIcon" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 383.947 383.947">
                            <polygon points="0,303.947 0,383.947 80,383.947 316.053,147.893 236.053,67.893 			"/>
                            <path d="M377.707,56.053L327.893,6.24c-8.32-8.32-21.867-8.32-30.187,0l-39.04,39.04l80,80l39.04-39.04
                                C386.027,77.92,386.027,64.373,377.707,56.053z"/>
                        </svg>
                        <strong id="bSelectPhoto" class="sMarginLeft">Editar foto</strong>
                    </div>
                    <button class="col100 xlMarginTop" type="submit">Guardar Cambios</button>                    
                </div>
               
                <div id="dataSection" class="col80">
                        <span class="col100 sMarginBottom">Datos personales:</span>
                        <div class="col100 mMarginBottom">
                            <div class="col33 sPaddingLT"><input class="col100" type="text" placeholder="Nombre" value="${student.name}" name="name" required></div>
                            <div class="col33 sPaddingLT"><input class="col100" type="text" placeholder="Apellidos" value="${student.surname}" name="surname" required></div>
                            <div class="col33 sPaddingLT"><input class="col100" type="date" value="${student.birthDate}" name="birth" required></div>
                        </div>

                        <span class="col100 sMarginBottom">Contacto:</span>
                        <div class="col100 mMarginBottom">
                                <div class="col50 sPaddingLT"><input class="col100" type="email" placeholder="Email de contacto" value="${student.email}" name="email" required></div>
                                <div class="col50 sPaddingLT"><input class="col100" type="number" placeholder="Telefono" value="${student.phone}" name="phone" required></div>                              	
                               	
                               	<c:choose>
								  	<c:when test="${student.linkedin!=null}">
				                      <div class="col50 sPaddingLT"><input class="col100" type="url" placeholder="Enlace perfil Linkedin" value="${student.linkedin}" name="linkedin"></div>
								  	</c:when>
								  	<c:otherwise>
				                      	<div class="col50 sPaddingLT"><input class="col100" type="url" placeholder="Enlace perfil Linkedin" name="linkedin"></div>
								  	</c:otherwise>
								</c:choose>
								
								<c:choose>
								  	<c:when test="${student.github!=null}">
				                      <div class="col50 sPaddingLT"><input class="col100" type="url" placeholder="Enlace perfil GitHub" value="${student.github}" name="github"></div>
								  	</c:when>
								  	<c:otherwise>
				                      	<div class="col50 sPaddingLT"><input class="col100" type="url" placeholder="Enlace perfil GitHub" name="github"></div>
								  	</c:otherwise>
								</c:choose>                      
                        </div>
                        
                        <span class="col100 sMarginBottom">Observaciones:</span>
                        <div class="col100 sPaddingLT mMarginBottom">
                            <textarea class="col100" placeholder="Observaciones" name="observations">${student.observations}</textarea>
                        </div>
                        
                        <span class="col100 sMarginBottom">Cambiar datos de login:</span>
                        <div class="col100 sPaddingLT mMarginBottom">
                        	<div class="col33 sPaddingLT"><input class="col100" type="text" value="${student.username}" disabled name="password"></div>
                        	<div class="col33 sPaddingLT"><input class="col100" type="password" value="00000000" name="password"></div>
                        </div>

                        <span class="col100 sMarginBottom">Disponibilidad:</span>
                        <div class="col100 mMarginBottom">
                            <div class="check col100">
                            	<!-- Marcar que boton de radio esta seleccionado -->
                                <label class="containerRadio">Disponible
                                 <c:choose>
									  <c:when test="${student.available}">
				                       	<input type="radio" name="status" checked="checked" value="true">
									  </c:when>
									  <c:otherwise>
				                       	<input type="radio" name="status" value="true">
									  </c:otherwise>
								 </c:choose>
                                    
                                    <span class="checkmarkRadio"></span>
                                </label>
                            </div>
                            <div class="check col100">
                                <label class="containerRadio">No disponible
	                                <c:choose>
										  <c:when test="${student.available}">
				                       		<input type="radio" name="status" value="false">
										  </c:when>
										  <c:otherwise>
					                       	<input type="radio" name="status" checked="checked" value="false">
										  </c:otherwise>
									 </c:choose>
                                    
                                    <span class="checkmarkRadio"></span>
                                </label>
                            </div>
                        </div>
                        
                        <span class="col100 sMarginBottom">Estudios:</span>
                        <div class="col100 mMarginBottom">
                        	<c:forEach items="${courses}" var="course">
	                        	<div class="check left">
	                                <label class="containerCheck">${course.name.toUpperCase()}
	                                    <input id="course${course.id}" type="checkbox" name="selected" value="${course.id}">
	                                    <span class="checkmark"></span>
	                                </label>
	                            </div>
                        	</c:forEach>
                        </div>
                      
                        <span class="col100">Curriculum:</span>
                        <div class="col100">
                                <div class="col50"><input class="col100" type="file" name="curriculum" accept=".pdf"></div>
                        </div>
                        <input type="hidden" value="${student.id}" name="id">
                        <input type="hidden" value="update" name="action">
                        <input id="browseImage" type="file" name="photo" accept=".jpg,.png" style="display:none"> 
                </div>
                
           	</div>
            </form>
       </div>
    </body>
    	
    <script>
    	$( document ).ready(function() {
    		
    		//Marcar los cursos asociados
    		var sc = ${studentCourses};
    		for(var i=0;i<sc.length;i++){
    			$("#course"+sc[i]).attr("checked", "checked");
    		}

    		//Emular click del input en el elemento html
    		$("#bSelectPhoto").on("click", function(){
    			$('#browseImage').trigger('click');
    		});
    		
           	//Detectar examinacion de una imagen en el formulario
            $("#browseImage").change(function() {
   	        	loadPreview(this);
 	        });
   	  
   	        
   	        //----------------------------------------------------------------------------------------------

   	        /**
   	        * METODO PARA CARGAR LA PREVISUALIZACIÓN DE UNA IMAGEN SELECCIONADA PARA EL FORMULARIO
   	        */
   	        function loadPreview(input) {
   	            if (input.files && input.files[0]) {
   	                //Establecer como atributo de la imagen la ruta de la imagen seleccionada
   	                var reader = new FileReader();
   	                reader.onload = function(e) {
   	                    $('#imageProfile').attr('src', e.target.result);
   	                }
   	                reader.readAsDataURL(input.files[0]);
   	            }else{
   	                $('#imageProfile').attr('src', "img/generic.jpg");
   	            }
   	        }
    		
    	});
    </script>
</html>