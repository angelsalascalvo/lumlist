<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
                    <span class="col100 titModal"> AÑADIR CURSO</span>
                    <input class="col100 xlMarginTop" type="text" placeholder="Nombre">
                    <button class="col100 mMarginTop">Añadir</button>
                </div>
                <div id="editStydy" class="col100" style="display: none">
                    <span class="col100 titModal"> EDITAR CURSO</span>
                    <input class="col100 xlMarginTop" type="text" placeholder="Nombre">
                    <button class="col100 mMarginTop">Guardar Cambios</button>
                </div>
            </div>
        </div>

        <!-- CONTENIDO -->
       <div id="header" class="col100 centerV">
           <img id="logoHeader" class="xlMarginLeft left" src="img/logo.png">
           <div id="bAccessHeader" class="lMarginRight">
                <button>ACCEDER</button>
            </div>
       </div>

       <div id="contentStudies" class="col100">
            <div id="titleEdit" class="col100">CURSOS</div>
            <div class="col100 xlMarginTop centerH">
                <div class="col50">
                	<c:forEach items="${studies}" var="study">
	                    <div class="studyElement col100 centerV">
	                        <strong class="left">DAW</strong>
	                        <button class="right bEdit">Editar</button>
	                        <button class="right red sMarginLeft">Eliminar</button>                        
	                    </div>
                    </c:forEach>
                </div>
            </div>
       </div>

       <div id="bAddStudy" class="absolute">
            <button>
                <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 472.615 472.615">
                    <polygon points="278.565,194.051 278.565,0 194.053,0 194.053,194.051 0,194.051 0,278.564 194.053,278.564 194.053,472.615 
                    278.565,472.615 278.565,278.564 472.615,278.564 472.615,194.051"/>
                </svg>
            </button>
       </div>
    </body>
</html>