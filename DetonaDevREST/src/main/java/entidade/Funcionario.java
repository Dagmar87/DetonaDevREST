package entidade;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "FUNCIONARIO")
public class Funcionario {
	@Id
	@Column(name = "CODIGO", nullable = false)
	private int codigo;
	@Column(name = "NOME", nullable = false)
	private String nome;
	@Column(name = "EMAIL", nullable = false)
	private String email;
	@Column(name = "TELEFONE", nullable = false)
	private String telefone;
	@Column(name = "SENHA", nullable = false)
	private String senha;
	
	@OneToMany(mappedBy = "funcionario", cascade = CascadeType.ALL)
	private List<OrdemServico> ordensServico;

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
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

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public List<OrdemServico> getOrdensServico() {
		return ordensServico;
	}

	public void setOrdensServico(List<OrdemServico> ordensServico) {
		this.ordensServico = ordensServico;
	}

	
}
