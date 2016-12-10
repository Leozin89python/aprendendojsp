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
	private List<Telefone> telefones = new ArrayList<Telefone>();
	private String ativo = "";
	private String sexo;
	private String experiencia;
	
	
	public boolean contaisExperiencia(String ex){
		if (experiencia !=null && !experiencia.isEmpty()){
			String[] exArray = experiencia.split(",");
			for (String e : exArray) {
				if (e.trim().equalsIgnoreCase(ex.trim())){
					return true;
				}
			}
		}
		return false;
	}
	
	public void setExperiencia(String experiencia) {
		this.experiencia = experiencia;
	}
	
	public String getExperiencia() {
		return experiencia;
	}
	
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	
	public String getSexo() {
		return sexo;
	}
	
	public void setAtivo(String ativo) {
		this.ativo = ativo;
	}
	
	public String getAtivo() {
		if (ativo == null || ativo.trim() == "" || ativo.equals("false") || ativo.equals("unchecked")){
			return "unchecked";
		}else if (ativo != null && ativo.equals("true") || ativo.equals("checked")){
			return "checked";
		}
		return ativo;
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
