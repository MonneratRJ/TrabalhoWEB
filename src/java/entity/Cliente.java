package entity;

import java.util.GregorianCalendar;

public class Cliente {
	
	private GregorianCalendar dataCadastro;
	private String nome;
	private String email;
	private String cpf;
	private String telefone;
	private GregorianCalendar dataNascimento;
	private String sexo;
	private String logradouro;
	private Integer numero;
	private String cep;
	private String complemento;
	private String cidade;
	private String uf;
	private String observacoes;
	
	public Cliente() {
		super();
	}
	
	public Cliente(GregorianCalendar dataCadastro, String nome, String email, String cpf, String telefone,
			GregorianCalendar dataNascimento, String sexo, String logradouro, Integer numero, String cep,
			String complemento, String cidade, String uf, String observacoes) {
		super();
		this.dataCadastro = dataCadastro;
		this.nome = nome;
		this.email = email;
		this.cpf = cpf;
		this.telefone = telefone;
		this.dataNascimento = dataNascimento;
		this.sexo = sexo;
		this.logradouro = logradouro;
		this.numero = numero;
		this.cep = cep;
		this.complemento = complemento;
		this.cidade = cidade;
		this.uf = uf;
		this.observacoes = observacoes;
	}
	
	public GregorianCalendar getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(GregorianCalendar dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public GregorianCalendar getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(GregorianCalendar dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	@Override
	public String toString() {
		return "Cliente [dataCadastro=" + dataCadastro + ", nome=" + nome + ", email=" + email + ", cpf=" + cpf
				+ ", telefone=" + telefone + ", dataNascimento=" + dataNascimento + ", sexo=" + sexo + ", logradouro="
				+ logradouro + ", numero=" + numero + ", cep=" + cep + ", complemento=" + complemento + ", cidade="
				+ cidade + ", uf=" + uf + ", observacoes=" + observacoes + "]";
	}
}
