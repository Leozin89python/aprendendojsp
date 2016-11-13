package br.com.aocp.dao;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.postgresql.PGConnection;
import org.postgresql.largeobject.LargeObject;
import org.postgresql.largeobject.LargeObjectManager;

import br.com.aocp.connection.SingletonConnetion;
import br.com.aocp.entidade.ClientePessoaFisica;
import br.com.aocp.entidade.Telefone;
import br.com.aocp.repository.RepositoryCliente;

public class ClienteDao implements RepositoryCliente {
	private Connection connection;

	public ClienteDao() {
		connection = SingletonConnetion.getConnection();
	}

	@Override
	public void salvar(ClientePessoaFisica clientePessoaFisica) {
		String sql = "INSERT INTO cliente_pessoa_fisica(nome, cpf, dataNacimento, endereco, numerologradouro, foto)VALUES ( ?, ?, ?, ?, ?, ?);";
		try {
			PreparedStatement insert = connection.prepareStatement(sql);
			constroiStatement(clientePessoaFisica, insert);
			insert.execute();
			connection.commit();
		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			throw new RuntimeException(e);
		}

	}

	@Override
	public void atualiza(ClientePessoaFisica clientePessoaFisica) {
		String sql = "UPDATE cliente_pessoa_fisica SET nome=?, cpf=?, dataNacimento=?, endereco=?, numeroLogradouro=?, foto =? where id = "
				+ clientePessoaFisica.getId();
		try {
			PreparedStatement update = connection.prepareStatement(sql);

			constroiStatement(clientePessoaFisica, update);
			update.execute();
			connection.commit();
		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			throw new RuntimeException(e);
		}

	}

	@Override
	public void deleta(String clientePessoaFisica) {
		String sql = "DELETE FROM cliente_pessoa_fisica where id = "
				+ clientePessoaFisica;
		try {

			remoeTelefoneClienteTodos(clientePessoaFisica);
			PreparedStatement delete = connection.prepareStatement(sql);
			delete.execute();
			connection.commit();
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			throw new RuntimeException(e);
		}
	}

	@Override
	public ClientePessoaFisica consulta(Long cod) {
		ClientePessoaFisica retorno = new ClientePessoaFisica();
		try {
			String sql = "select * FROM cliente_pessoa_fisica where id = " + cod;
			PreparedStatement find = connection.prepareStatement(sql);
			ResultSet resultSet = find.executeQuery();
			while (resultSet.next()) {
				controiCliente(resultSet, retorno);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return retorno;
	}

	@Override
	public List<ClientePessoaFisica> consultaTodos() {

		List<ClientePessoaFisica> retorno = new ArrayList<ClientePessoaFisica>();
		String sql = "select * FROM cliente_pessoa_fisica ";

		try {
			PreparedStatement find = connection.prepareStatement(sql);
			ResultSet resultSet = find.executeQuery();
			while (resultSet.next()) {
				ClientePessoaFisica obClientePessoaFisica = new ClientePessoaFisica();
				controiCliente(resultSet, obClientePessoaFisica);
				retorno.add(obClientePessoaFisica);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return retorno;
	}

	private void controiCliente(ResultSet resultSet,
			ClientePessoaFisica obClientePessoaFisica) throws Exception {
		obClientePessoaFisica.setId(resultSet.getLong("id"));
		obClientePessoaFisica.setCpf(resultSet.getString("cpf"));
		obClientePessoaFisica.setDataNacimento(resultSet.getDate("datanacimento"));
		obClientePessoaFisica.setEndereco(resultSet.getString("endereco"));
		obClientePessoaFisica.setNome(resultSet.getString("nome"));
		obClientePessoaFisica.setNumeroLogradouro(resultSet.getInt("numerologradouro"));
		
		obClientePessoaFisica.setFoto(resultSet.getString("foto"));
		
		obClientePessoaFisica.getTelefones().clear();
		obClientePessoaFisica.getTelefones().addAll(getFones(obClientePessoaFisica));
	}

	private Collection<? extends Telefone> getFones(
			ClientePessoaFisica obClientePessoaFisica) {
		List<Telefone> fonesClientes = new ArrayList<Telefone>();
		try {
			String sql = "select * FROM telefone_cliente where clientepessoafisica = "+ obClientePessoaFisica.getId();
			PreparedStatement find = connection.prepareStatement(sql);
			ResultSet resultSet = find.executeQuery();
			while (resultSet.next()) {
				Telefone telefone = new Telefone();
				telefone.setClientePessoaFisica(obClientePessoaFisica);
				telefone.setId(resultSet.getLong("id"));
				telefone.setNumero(resultSet.getString("numero"));
				telefone.setTipoTelefone(resultSet.getString("tipotelefone"));
				fonesClientes.add(telefone);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return fonesClientes;
	}

	private void constroiStatement(ClientePessoaFisica clientePessoaFisica,
			PreparedStatement insert) throws Exception {
		insert.setString(1, clientePessoaFisica.getNome());
		insert.setString(2, clientePessoaFisica.getCpf());
		insert.setDate(3, new java.sql.Date(clientePessoaFisica.getDataNacimento().getTime()));
		insert.setString(4, clientePessoaFisica.getEndereco());
		insert.setInt(5, clientePessoaFisica.getNumeroLogradouro());
		insert.setString(6, clientePessoaFisica.getFoto());
	}

	@Override
	public void deleta(Long cod) {
		this.deleta(cod.toString());
	}

	@Override
	public ClientePessoaFisica consulta(String cod) {
		return this.consulta(Long.parseLong(cod));
	}

	@Override
	public void salvarFoneCliente(Telefone telefone) {
		String sql = "INSERT INTO telefone_cliente(tipotelefone, numero, clientepessoafisica) VALUES (?, ?, ?);";
		try {
			PreparedStatement insert = connection.prepareStatement(sql);

			insert.setString(1, telefone.getTipoTelefone());
			insert.setString(2, telefone.getNumero());
			insert.setLong(3, telefone.getClientePessoaFisica().getId());
			insert.execute();
			connection.commit();
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			throw new RuntimeException(e);
		}
	}

	@Override
	public void removeTelefoneCliente(String pessoaFisicaFone) {
		String sql = "DELETE FROM telefone_cliente where id = "
				+ pessoaFisicaFone;
		try {
			PreparedStatement delete = connection.prepareStatement(sql);
			delete.execute();
			connection.commit();
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			throw new RuntimeException(e);
		}
	}

	public void remoeTelefoneClienteTodos(String pessoaFisicaEmail) {
		String sql = "DELETE FROM telefone_cliente where clientepessoafisica = "+ pessoaFisicaEmail;
		try {
			PreparedStatement delete = connection.prepareStatement(sql);
			delete.execute();
			connection.commit();
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<ClientePessoaFisica> consultaPaginada(String numeroPagina) throws Exception {
		int total_pessoas_por_pagina = 6;
		if (numeroPagina == null || (numeroPagina != null && numeroPagina.trim().isEmpty())){
			numeroPagina = "0";
		}
		int offSet = (Integer.parseInt(numeroPagina) * total_pessoas_por_pagina) - total_pessoas_por_pagina; 
		
		if (offSet < 0){
			offSet = 0;
		}
		
		List<ClientePessoaFisica> retorno = new ArrayList<ClientePessoaFisica>();
		String sql = "select * FROM cliente_pessoa_fisica limit " + total_pessoas_por_pagina + " OFFSET  " + offSet + "; ";

			PreparedStatement find = connection.prepareStatement(sql);
			ResultSet resultSet = find.executeQuery();
			while (resultSet.next()) {
				ClientePessoaFisica obClientePessoaFisica = new ClientePessoaFisica();
				controiCliente(resultSet, obClientePessoaFisica);
				retorno.add(obClientePessoaFisica);
			}
		return retorno;
	}

	@Override
	public int quantidadePagina() throws Exception {
		String sql = "select count(1) as totalPessoas FROM cliente_pessoa_fisica;";
		int quantidadePagina = 1;
		double total_pessoas_por_pagina = 6.0;
			PreparedStatement find = connection.prepareStatement(sql);
			ResultSet resultSet = find.executeQuery();
			if (resultSet.next()) {
				double totalPessoas = resultSet.getDouble("totalPessoas");
				if (totalPessoas > total_pessoas_por_pagina){
					
					double quantidadePaginaTemp = Float.parseFloat(""+(totalPessoas / total_pessoas_por_pagina));

					if (!(quantidadePaginaTemp % 2 == 0)){
						quantidadePagina =   new Double(quantidadePaginaTemp).intValue() + 1;
					}
					else {
						quantidadePagina = new Double(quantidadePaginaTemp).intValue();
					}
				}else {
					quantidadePagina = 1;
				}
			}
		return quantidadePagina;
	}
	
	public static byte[] carregaLargeObject(Long oid) throws Exception {
		if (oid > 0) {
			LargeObjectManager lobj = ((PGConnection) SingletonConnetion
					.getConnection()).getLargeObjectAPI();
			LargeObject objeto = lobj.open(oid);
			InputStream inputStream = objeto.getInputStream();

			ArrayList<Byte> retornoAux = new ArrayList<Byte>();
			int aux = -1;
			while ((aux = inputStream.read()) > -1)
				retornoAux.add((byte) aux);

			byte[] retorno = new byte[retornoAux.size()];
			for (int i = 0; i < retornoAux.size(); i++)
				retorno[i] = retornoAux.get(i);

			objeto.close();
			inputStream.close();

			return retorno;
		}
		return null;
	}
	
	public Long gravaLargeObject(byte[] dados)
			throws Exception {
		if (dados == null)
			return 0L;

		// instancia o LargeObjectManager
		LargeObjectManager lobj = ((PGConnection) SingletonConnetion.getConnection()).getLargeObjectAPI();

		// instancia o LargeObject
		long oid = lobj.createLO(LargeObjectManager.READ
				| LargeObjectManager.WRITE);

		// abre o LargeObject
		LargeObject obj = lobj.open(oid, LargeObjectManager.WRITE);

		// escreve os dados
		obj.write(dados);

		// fecha o LargeObject
		obj.close();

		// retorna a OID do LargeObject
		return oid;
	}


}
