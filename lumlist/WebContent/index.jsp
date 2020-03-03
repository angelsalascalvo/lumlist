<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
    <jsp:include page="head.jsp" />
    <body id="cover">
    	<!-- Botones en funcion del logueo -->
    	<c:choose>
		  	<c:when test="${(user!=null) and (user=='admin')}">
            	<div id="bAccess" class="absolute">
				    <a href=".?action=options"><button>OPCIONES</button></a>
				    <a href="./login?action=logout"><button class="bSecond">CERRAR SESION</button></a>
				</div>
		  	</c:when> 
	  		<c:when test="${(user!=null) and (user=='student')}">
            	<div id="bAccess" class="absolute">
				     <a href=".?action=account"><button>EDITAR PERFIL</button></a>
				     <a href="./login?action=logout"><button class="bSecond">CERRAR SESION</button></a>
				</div>
		  	</c:when>
		  	<c:otherwise>
                <div id="bAccess" class="absolute">
				    <a href="./login"><button>ACCEDER</button></a>
				</div>
		  	</c:otherwise>
		</c:choose>
    	
        <div class="width100 centerV row100">
            <div id="cover" class="col100">
                <div class="col100 centerH">
                    <img class="col30" src="img/logo.png">
                </div>
                <div class="col100 centerH xlMarginTop">
                    <input id="searchBar" class="col50" type="text" placeholder="Buscar...">
                    <svg id="searchIcon"viewBox="0 0 515.558 515.558">
                        <path d="m378.344 332.78c25.37-34.645 40.545-77.2 40.545-123.333 0-115.484-93.961-209.445-209.445-209.445s-209.444 93.961-209.444 209.445 93.961 209.445 209.445 209.445c46.133 0 88.692-15.177 123.337-40.547l137.212 137.212 45.564-45.564c0-.001-137.214-137.213-137.214-137.213zm-168.899 21.667c-79.958 0-145-65.042-145-145s65.042-145 145-145 145 65.042 145 145-65.043 145-145 145z"/>
                    </svg>
                </div>

                <!-- OPCIONES INFERIORES -->
                <div id="bottomOptions"class="col100 centerH">
                    <div style="width: fit-content">
                        <div id="avaiUser" class="left centerV centerH xxlMarginTop">
                                <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 349.27 349.3">
                                    <path d="M31.09,275.68C31,179.15,109.4,100.85,206.19,101.11A174.65,174.65,0,1,1,31.09,275.68ZM276.68,210c-26.09,26.24-56,55.61-82.21,81.75-1,1-2.06,1.89-3.87,3.55l-41-41.23-30.41,30.17a12.79,12.79,0,0,0,1.15,1.43c22.64,22.66,47.7,47.2,70.31,69.88h0c36.42-36.51,77.2-75.24,113.65-111.72.9-.9,2-2,2.94-3,0,0-1.84-1.73-2.51-2.41" transform="translate(-31.09 -101.11)"/>
                                </svg>
                            <span class="sMarginLeft">Ver todos los alumnos disponibles</span>
                        </div>

                        <div id="allUser" class="left centerH centerV sMarginTop" style="clear: both;">
                                <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 312.4 312.4">
                                    <path d="M132,264.54c-.31-85.41,69.74-155.73,155.43-156A156.2,156.2,0,0,1,289,420.94C200.93,421.39,131.92,350.21,132,264.54Zm86.21,19.24H364.46v-38H218.25Z" transform="translate(-132.04 -108.54)"/>
                                </svg>
                            <span class="sMarginLeft">Ver todos los alumnos</span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        
        <div id="credits">
        	<a href=".?action=credits"><span>Créditos</span></a>
        </div>
    </body>
    
    <script>
    	$( document ).ready(function() {
		  	//Acciones al buscar con la barra de busqueda
		  	$('#searchBar').on("keypress",function(e){ 
					if(e.which == 13) {
						search();
				    }
		  	});
		  	$("#searchIcon").on("click", function(){ search() });
			  
		  	/*
	  		* METODO QUE PERMITE CONFECCIONAR LA RUTA DE BUSQUEDA EN FUNCION DE LOS PARAMETROS MARCADOS
	  		*/
	  		function search(){
	  			url="./index?action=search";
	 
	  			//Agregar clave de busqueda
	  			if($('#searchBar').val()!=""){
	  				url+="&key="+encodeURIComponent($("#searchBar").val());
	  				window.location.href = url;
	  			}
	  		}
		  	
		  	//---------------------------------------------------------------------------------------
		  	
		  	//Mostrar usuarios disponibles
		  	$("#avaiUser").on("click", function(){
		  		window.location.href = "./index?action=search&status=true";
		  	});
		  	
		  	//Mostrar todos los usuarios
		  	$("#allUser").on("click", function(){
		  		window.location.href = "./index?action=search&key=all";
		  	});
    	});
    </script>
</html>