<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:set var="contexto" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Actualizar</title>
        <link rel="stylesheet" type="text/css" href="${contexto}/CSS/estilo.css" />
    </head>
    <body>

        <div id="principal">
            <h2>Actualizaci&oacute;n de datos</h2>
            <form method="post" action="conclusion?op=update">
                <input type="hidden" name="idProfesor" value="${profesor.idProfesor}" readonly="readonly" />
                <table>
                    <tr>
                        <td>Nombre</td>
                        <td><input type="text" name="nombre" value="${profesor.nombre}" /></td>
                    </tr>
                    <tr>
                        <td>Nombre</td>
                        <td><input type="text" name="apellidos" value="${profesor.apellidos}" /></td>
                    </tr>   

                    <c:forEach items="${profesor.modulos}" var="modulo" varStatus="i">
                        <tr>
                            <td>Libro</td>

                            <td><input type="text" name="modulo${i.count}" value="${modulo.denominacion}" />
                                <input type="hidden" name="idmodulo${i.count}" value="${modulo.idModulo}"/></td>
                        </tr>
                    </c:forEach>

                    <tr>

                        <td colspan="2"><input type="submit" name="enviar" value="Enviar" class="boton" /></td>
                    </tr>
                </table>
            </form>
        </div>
    </body>
</html>