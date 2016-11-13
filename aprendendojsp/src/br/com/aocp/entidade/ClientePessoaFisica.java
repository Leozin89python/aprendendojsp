package br.com.aocp.entidade;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ClientePessoaFisica {

	private Long id;
	private String nome;
	private String cpf;
	private java.util.Date dataNacimento;
	private String endereco;
	private Integer numeroLogradouro;
	private String foto;
	private String fotoBase64;
	private String imgHtml;

	private List<Telefone> telefones = new ArrayList<Telefone>();
	
	
	public void setImgHtml(String imgHtml) {
		this.imgHtml = imgHtml;
	}
	
	public String getImgHtml() {
		return imgHtml;
	}
	
	public void setFotoBase64(String fotoBase64) {
		this.fotoBase64 = fotoBase64;
	}
	
	public String getFotoBase64() {
		return fotoBase64;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}
	public String getFoto() {
		return foto;
	}
	
	public List<Telefone> getTelefones() {
		return telefones;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Date getDataNacimento() {
		return dataNacimento;
	}

	public void setDataNacimento(Date dataNacimento) {
		this.dataNacimento = dataNacimento;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public Integer getNumeroLogradouro() {
		return numeroLogradouro;
	}

	public void setNumeroLogradouro(Integer numeroLogradouro) {
		this.numeroLogradouro = numeroLogradouro;
	}

}
