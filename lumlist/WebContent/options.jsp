<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
    <jsp:include page="head.jsp" />  
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
            <div id="titleEdit" class="col100">HOLA ${admin.username.toUpperCase()}!</div>
            <div class="col100 centerH xxlMarginTop">
                <div id="optionsAdmin" class="col50">
                	<a href="./courses">
                    <div class="col100 centerV centerH">
                            <svg viewBox="0 0 458.706 458.706" xmlns="http://www.w3.org/2000/svg">
                                <path d="m425.941 47.977v-47.977l-196.588 114.676-196.588-114.676v47.977l196.588 91.273z"/>
                                <path d="m229.353 172.015-229.353-106.486v273.277c0 13.107 7.811 24.953 19.858 30.115l209.495 89.785 209.495-89.785c12.047-5.161 19.858-17.008 19.858-30.115v-273.277s-229.353 106.486-229.353 106.486z"/>
                            </svg>
                        <span class="mMarginLeft">ADMINISTRAR CURSOS</span>
                    </div>
                    </a>
                    
                    <a href="./student?action=add">
                    <div class="col100 centerV centerH">
                            <svg xmlns="http://www.w3.org/2000/svg" style="width: 38px" viewBox="0 0 469.333 469.333">

                                <path d="M298.667,277.333c-56.853,0-170.667,28.48-170.667,85.333v42.667h341.333v-42.667
                                    C469.333,305.813,355.52,277.333,298.667,277.333z"/>
                                <path d="M298.667,234.667c47.147,0,85.333-38.293,85.333-85.333C384,102.187,345.813,64,298.667,64s-85.333,38.187-85.333,85.333
                                    C213.333,196.373,251.52,234.667,298.667,234.667z"/>
                                <polygon points="170.667,192 106.667,192 106.667,128 64,128 64,192 0,192 0,234.667 64,234.667 64,298.667 106.667,298.667 
                                    106.667,234.667 170.667,234.667"/>
                                    
                            </svg>
                                                      
                        <span class="mMarginLeft">NUEVO ALUMNO</span>
                    </div>
                    </a>
                    
                    <a href="./index?action=search">
                    <div class="col100 centerV centerH">
                        <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 341.333 341.333">
                            <path d="M170.667,170.667c47.147,0,85.333-38.293,85.333-85.333C256,38.187,217.813,0,170.667,0S85.333,38.187,85.333,85.333
                                C85.333,132.373,123.52,170.667,170.667,170.667z"/>
                            <path d="M170.667,213.333C113.813,213.333,0,241.813,0,298.667v42.667h341.333v-42.667
                                C341.333,241.813,227.52,213.333,170.667,213.333z"/>
                        </svg>   
                        <span class="mMarginLeft">VER ALUMNOS</span>
                    </div>
                    </a>
                </div>
            </div>
       </div>
    </body>
</html>