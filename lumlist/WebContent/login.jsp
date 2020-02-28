<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <jsp:include page="head.jsp" />  
    <body id="cover">
        <div class="width100 centerV row100">
            <div class="col100">
                <div class="col100 centerH">
                    <img class="col20" src="img/logo.png">
                </div>
                <div class="col100 centerH">
                    <div id="fieldsLogin" class="col30 xlMarginTop">
                        <form method="post" action="login">
                            <input class="col100" type="text" placeholder="Usuario" name="user">
                            <input class="col100 sMarginTop" type="password" placeholder="Contraseña" name="pwd">
                            <p class="errorText col100 sMarginTop">${error}</p>
                            <button class="col100 sMarginTop" type="submit">Acceder</button>
                            
                        </form>
                    </div>        
                </div>
            </div>
        </div>
    </body>
</html>