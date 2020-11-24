package entidade;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "MATERIAL")
@SequenceGenerator(name = "S_MATERIAL", sequenceName = "S_MATERIAL", initialValue = 1, allocationSize = 1)

public class Material {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "S_MATERIAL")
	@Column(name = "ID", nullable = false)	
	private int id;
	@Column(name = "CODIGO", nullable = false)	
	private String codigo;
	@Column(name = "DESCRICAO", nullable = false)	
	private String descricao;
	@Column(name = "CLASSE", nullable = false)	
	private String classe;
	@Column(name = "VALORUNITARIO", nullable = false)	
	private double valorUnitario;

	@OneToMany(mappedBy = "material", cascade = CascadeType.ALL)
	private List<ItemServico> itensServico;
	
public int getID() {
	return id;
}
public void setID(int id) {
	this.id = id;
}
public String getCodigo() {
	return codigo;
}
public void setCodigo(String codigo) {
	this.codigo = codigo;
}
public String getDescricao() {
	return descricao;
}
public void setDescricao(String descricao) {
	this.descricao = descricao;
}
public String getClasse() {
	return classe;
}
public void setClasse(String classe) {
	this.classe = classe;
}
public double getValorUnitario() {
	return valorUnitario;
}
public void setValorUnitario(double valorUnitario) {
	this.valorUnitario = valorUnitario;
}
	

	
	
	
}
