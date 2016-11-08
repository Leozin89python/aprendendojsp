<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="resources/css/css.css" rel="stylesheet" />
<title>Aprendendo lista paginada JSP</title>
</head>
<body>

    <%
    
    int quantidadePagina = Integer.parseInt(request.getParameter("quantidadePagina"));
    int numeroPagina = Integer.parseInt(request.getParameter("numeroPagina"));
    
    %>
    <c:out value="Aprendendo lista paginada JSP"/>
    <c:out value="${erro}"/>
    
	<form class="form-style-1">
		<table>
			<tr style="text-align: left; background-color: #4691A4;">
				<th style="width: 250px;">Id</th>
				<th>Nome</th>
				<th>Cpf</th>
				<th>Data Nascimento</th>
				<th>Endereço</th>
				<th>Número</th>
				<th>Atualizar</th>
				<th>Remover</th>

			</tr>
			<c:forEach items='${clientes}' var='c'>
				<tr>
					<td style="min-width: 150px;"><c:out value="${c.id}" /></td>
					<td style="min-width: 200px;"><c:out value="${c.nome}" /></td>
					<td style="min-width: 150px;"><c:out value="${c.cpf}" /></td>
					<td><fmt:formatDate pattern="dd/MM/yyyy"
							value="${c.dataNacimento}" /></td>
					<td style="min-width: 150px;"><c:out value="${c.endereco}" /></td>
					<td><c:out value="${c.numeroLogradouro}" /></td>
					<td><a
						href="ClientePessoaContoller?action=edit&clienteId=<c:out value="${c.id}"/>">Atualizar</a></td>
					<td><a
						href="ClientePessoaPaginacaoController?
						clienteId=<c:out value="${c.id}"/>
						&numeroPagina=<%=numeroPagina%>
						&action=delete
						&clienteId=<c:out value="${c.id}"/>">Remover</a></td>
				</tr>
			</c:forEach>
		</table>
		<%
			if (quantidadePagina > 0) {
				int totalPaginas = quantidadePagina;
				for (int i = 1; i <= totalPaginas; i++) {
					if (i == numeroPagina) {
						out.println("<b>" + i + "</b>");
					} else {
						out.println("<a href=ClientePessoaPaginacaoController?numeroPagina="
								+ i + "> " + i + "</a>");
					}
				}
			} else {
				out.println("<a href=ClientePessoaPaginacaoController?numeroPagina=1>"
						+ 1 + "</a>");
			}
		%>
		
		<br>
		<%
			if (numeroPagina <= quantidadePagina && (numeroPagina - 1) > 0) {
				out.println("<a href =ClientePessoaPaginacaoController?numeroPagina="
						+ (numeroPagina - 1) + ">Anterior</a>");
			}

			out.println("<b>" + numeroPagina + "</b>");

			if (quantidadePagina > numeroPagina) {
				out.println("<a href =ClientePessoaPaginacaoController?numeroPagina="
						+ (numeroPagina + 1) + ">Próxima</a>");
			}
		%>
		
		<br>
		<c:out value="Página Atual: ${numeroPagina}"/>
		
		<br>
		<c:out value="Total de Páginas: ${quantidadePagina}"/>
		
		<br>	
	</form>
	<form class="form-style-1" action="index.jsp">
		<input type="submit" value="Voltar">
	</form>
</body>
</html>