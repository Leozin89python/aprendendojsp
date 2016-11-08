package br.com.aocp.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.aocp.dao.ClienteDao;
import br.com.aocp.entidade.ClientePessoaFisica;
import br.com.aocp.repository.RepositoryCliente;

public class ClientePessoaPaginacaoController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private RepositoryCliente repositoryCliente;

	public ClientePessoaPaginacaoController() {
		super();
		repositoryCliente = new ClienteDao();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try {
			String action = req.getParameter("action");
			String clienteId = req.getParameter("clienteId");
			
			if (action !=null && !action.isEmpty() && action.equals("delete")) {// se for pra deletar
				repositoryCliente.deleta(clienteId);
			}
		        
			// lista paginada
				String numeroPagina = req.getParameter("numeroPagina");
				
				List<ClientePessoaFisica> clientes = repositoryCliente.consultaPaginada(numeroPagina);
				
				if (clientes.isEmpty()){
					numeroPagina = ""+(Integer.parseInt(numeroPagina) - 1);
					clientes = repositoryCliente.consultaPaginada(numeroPagina);
				}
				
				req.setAttribute("clientes", clientes);
				req.setAttribute("numeroPagina", (numeroPagina != null ? numeroPagina : 1));
				
				int quantidadePagina = repositoryCliente.quantidadePagina();
				req.setAttribute("quantidadePagina", quantidadePagina);
				
				RequestDispatcher view = req.
						getRequestDispatcher("/listPaginada.jsp?quantidadePagina="+quantidadePagina
								+ "&numeroPagina="+numeroPagina);
				view.forward(req, resp);
		} catch (Exception e) {
			RequestDispatcher view = req.getRequestDispatcher("/listPaginada.jsp");
			req.setAttribute("erro", e.getMessage());
			view.forward(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try {
			doGet(req, resp);
		} catch (Exception e) {
			RequestDispatcher view = req.getRequestDispatcher("/listPaginada.jsp");
			req.setAttribute("erro", e.getMessage());
			view.forward(req, resp);
		}
	}
}
