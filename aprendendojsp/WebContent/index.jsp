<%@page import="br.com.aocp.entidade.ClientePessoaFisica"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<script type="text/javascript" src="resources/script/jquery.min.js"></script>
<script type="text/javascript" src="resources/script/jquery.Jcrop.min.js"></script>
<script type="text/javascript" src="resources/script/jquery.Jcrop.js"></script>
<script type="text/javascript" src="resources/script/jquery.color.js"></script>
<script type="text/javascript" src="resources/script/script.js"></script>

<link href="resources/css/jquery.Jcrop.min.css" rel="stylesheet" />
<link href="resources/css/jquery.Jcrop.css" rel="stylesheet" />
<link href="resources/css/css.css" rel="stylesheet" />
<title>Aprendendo JSP</title>

</head>
<body>
	<div>
	    <c:out value="${erro}"/>
		<div style="float: left; width: 32%;" id="teste1">
			<form method="post" action="ClientePessoaContoller?action=save"
				name="frmAdd" onsubmit="return checkCoordinates();"
				class="form-style-1" id="salvarCliente"
				enctype="application/x-www-form-urlencoded"> 
				<input type="hidden" name="x" id="x"/>
				<input type="hidden" name="y" id="y"/>
		        <input type="hidden" name="w" id="w"/>
		        <input type="hidden" name="h" id="h"/>
		        <input type="hidden" name="base64" id="base64" value="${cliente.foto}"/>
				
				<input type="hidden" id="idTemp" name="idTemp" readonly="readonly"
					value="<c:out value="${cliente.id}" />">
				
				<table>
				<tr>
				  <td>
				<table title="Cadastro"> 
					<tr>
						<td>Id:</td>
						<td><input type="text" id="id" name="id" readonly="readonly"
							value="<c:out value="${cliente.id}" />"></td>
					</tr>
					<tr>
						<td>Nome:</td>
						<td><input type="text" id="nome" name="nome"
							value="<c:out value="${cliente.nome}" />"></td>
					</tr>
					<tr>
						<td>Cpf:</td>
						<td><input type="text" id="cpf" name="cpf"
							value="<c:out value="${cliente.cpf}" />"></td>
					</tr>

					<tr>
						<td>Data nascimento:</td>
						<td><input type="text" id="datanascimento"
							name="datanascimento"
							value="<fmt:formatDate pattern="dd/MM/yyyy" value="${cliente.dataNacimento}" />">
						</td>
					</tr>

					<tr>
						<td>Endereco:</td>
						<td><input type="text" id="endereco" name="endereco"
							value="<c:out value="${cliente.endereco}" />"></td>
					</tr>
					
					<tr>
						<td>Ativo:</td>
						<td><input type="checkbox" id="ativo" name="ativo" 
						<%
						if (request.getAttribute("cliente") != null) {
						 ClientePessoaFisica pessoaFisica = (ClientePessoaFisica) request.getAttribute("cliente");
						 if (pessoaFisica.getAtivo() != null && pessoaFisica.getAtivo().equals("checked")){
							 out.print("checked=\"checked\"");
							 out.print("value=\"checked\"");
							 out.print(" ");
						 }
						}
						%> 
						onchange="this.checked ? this.value = 'checked': 'unchecked'"></td>
					</tr>
					
					
					<tr>
						<td>Sexo:</td>
						<td>
							 <input type="radio" name="sexo"
							  <%
							  if (request.getAttribute("cliente") != null) {
									 ClientePessoaFisica pessoaFisica = (ClientePessoaFisica) request.getAttribute("cliente");
							  	if (pessoaFisica.getSexo() != null && pessoaFisica.getSexo().equals("masculino")){
							  		out.print("checked=\"checked\"");
							  		out.print(" ");
							  	}
							  }	 
							  %>
							  value="masculino" >Masculino</input>
							  
							  
							 <input type="radio" name="sexo" 
							    <%
								  if (request.getAttribute("cliente") != null) {
										 ClientePessoaFisica pessoaFisica = (ClientePessoaFisica) request.getAttribute("cliente");
								  	if (pessoaFisica.getSexo() != null && pessoaFisica.getSexo().equals("feminino")){
								  		out.print("checked=\"checked\"");
								  		out.print(" ");
								  	}
								  }	 
							   %>
							 value="feminino">Feminino</input>
						</td>
					</tr>
					
					
					<tr>
						<td>Experi??ncia:</td>
						<td>
							 <input type="checkbox" name="experiencia"
							  <%
							  if (request.getAttribute("cliente") != null) {
									 ClientePessoaFisica pessoaFisica = (ClientePessoaFisica) request.getAttribute("cliente");
							  	if (pessoaFisica.contaisExperiencia("java")){
							  		out.print("checked=\"checked\"");
							  		out.print(" ");
							  	}
							  }	 
							  %>
							  value="java" >Java</input>
							  
							  
							<input type="checkbox" name="experiencia"
							    <%
								  if (request.getAttribute("cliente") != null) {
										 ClientePessoaFisica pessoaFisica = (ClientePessoaFisica) request.getAttribute("cliente");
										if (pessoaFisica.contaisExperiencia("php")){
									  		out.print("checked=\"checked\"");
									  		out.print(" ");
								  		}
								  }	 
							   %>
							 value="php">Php</input>
							 
							
							<input type="checkbox" name="experiencia"
							    <%
								  if (request.getAttribute("cliente") != null) {
										 ClientePessoaFisica pessoaFisica = (ClientePessoaFisica) request.getAttribute("cliente");
										if (pessoaFisica.contaisExperiencia("c++")){
									  		out.print("checked=\"checked\"");
									  		out.print(" ");
									  	}
								  }	 
							   %>
							 value="c++">C++</input>
						</td>
					</tr>

					<tr>
						<td>Numero:</td>
						<td><input type="text" id="numeroLogradouro"
							name="numeroLogradouro"
							value="<c:out value="${cliente.numeroLogradouro}" />"></td>
					</tr>
					<tr>
						<td />
						<td>
						
						  <input type="submit" value="Salvar" id="salvar" style="width:83px;"/>
						  <input type="submit" value="Listar" id="listar" style="width:83px;" onclick="javascript:document.getElementById('salvarCliente').action = 'ClientePessoaContoller?action=listar';"/>
						</td>
					</tr>
					
					<tr>
						<td/> 
						<td>
						  <input type="submit" style="width:170px;" value="Lista P??ginada" id="listarPaginada" onclick="javascript:document.getElementById('salvarCliente').action = 'ClientePessoaPaginacaoController?numeroPagina=1';"/>
						</td>
					</tr>
				</table>
				</td>
				
				 <td>
				 	<input type="file" id="fileUpload" value="Fotografia" name="fileUpload" onchange="visualizarImg();">
					<img src="<c:out value="${cliente.foto}"/>" id="target"  />
					<input type="button" value="Recortar" onclick="iniciarRecorte()">
					<input type="button" value="Delete Foto" onclick="deletaFoto();">
				 </td>
				</tr>
			   </table>
			</form>

		</div>

		<c:choose>
			<c:when test="${cliente.id != null}">
				<form method="post" action="ClienteTelefoneContoller?action=save"
					class="form-style-1"
					onsubmit="if (document.getElementById('idTemp').value != '');">
					<input type="hidden" id="idTemp" name="idTemp" readonly="readonly"
						value="<c:out value="${cliente.id}" />">
					<table>
						<tr>
							<td>Numero:</td>
							<td><input type="text" id="numero" name="numero"></td>
							<td>Tipo:</td>
							<td><select id="comboTipoFone" name="comboTipoFone">
									<option value="Celular" label="Celular" selected="selected">Celular</option>
									<option value="Residencia" label="Residencia">Residencia</option>
									<option value="Fixo" label="Fixo">Fixo</option>
									<option value="Recado" label="Recado">Recado</option>
							</select></td>
							<td><input type="submit" value="Salvar" /></td>
						</tr>
						<tr >
							<th colspan="2" align="left">N??mero</th>
							<th>Tipo</th>
						</tr>

						<c:forEach items='${cliente.telefones}' var='t'>
							<tr>
								<td style="width: 70px;" colspan="2"><c:out
										value="${t.numero}" /></td>
								<td align="left"><c:out value="${t.tipoTelefone}" />
								</td>
								<td align="right"><a
									href="ClienteTelefoneContoller?idTemp=${t.clientePessoaFisica.id}&action=delet&idFone=<c:out value="${t.id}"/>">Remover</a>
								</td>
							</tr>
							<br />
						</c:forEach>
					</table>
				</form>
			</c:when>
		</c:choose>
	</div>
</body>
</html>