<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <jsp:include page="head.jsp"/>
    <body>
       <div id="header" class="col100 centerV">
           <img id="logoHeader" class="xlMarginLeft left" src="img/logo.png">
           <div id="bAccessHeader" class="lMarginRight">
                <button>ACCEDER</button>
            </div>
       </div>

       <div id="contentOptions" class="col100">
            <div id="titleEdit" class="col100">NUEVO ALUMNO</div>
            <div class="col100 centerH xlMarginTop">
                <div id="newStudentData" class="col30">
                    <input class="col100 sMarginTop" type="text" placeholder="Nombre">
                    <input class="col100 sMarginTop" type="text" placeholder="Apellidos">
                    <input  class="col100 mMarginTop" type="text" placeholder="Usuario">
                    <input class="col100 sMarginTop" type="password" placeholder="Contraseña">
                    <button class="col100 lMarginTop">Añadir</button>
                </div>
            </div>
       </div>
    </body>
</html>