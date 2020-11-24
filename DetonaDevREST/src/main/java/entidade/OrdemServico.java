package entidade;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "MATERIAL")
@SequenceGenerator(name = "S_ORDEMSERVICO", sequenceName = "S_ORDEMSERVICO", initialValue = 1, allocationSize = 1)
public class OrdemServico {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "S_ORDEMSERVICO")
	@Column(name = "ID", nullable = false)	
	private int id;
	@Column(name = "DATAENTRADA", nullable = false)	
	private Date dataEntrada;
	@Column(name = "DATAINICIO", nullable = false)	
	private Date dataInicio;
	@Column(name = "DATAFIM", nullable = false)	
	private Date dataFim;
	@Column(name = "STATUS", nullable = false)	
	private String Status;
	@Column(name = "QUILOMETRAGEM", nullable = false)	
	private int quilometragem;
	@Column(name = "VALORMAODEOBRA", nullable = false)	
	private double valorMaoDeObra;
	@Column(name = "VALORTOTAL", nullable = false)	
	private double valorTotal;
	@Column(name = "VALIDADE", nullable = false)	
	private int validade;
	@Column(name = "OBS", nullable = false)	
	private String obs;
	
	@ManyToOne
	@JoinColumn(name = "ID_VEICULO", referencedColumnName = "PLACA", nullable = false)
	private Veiculo veiculo;
	
	@ManyToOne
	@JoinColumn(name = "ID_FUNCIONARIO", referencedColumnName = "CODIGO", nullable = false)
	private Funcionario funcionario;
	
	@OneToMany(mappedBy = "ordensServico", cascade = CascadeType.ALL)
	private List<ItemServico> itensServico;
	


	public List<ItemServico> getItensServico() {
		return itensServico;
	}

	public void setItensServico(List<ItemServico> itensServico) {
		this.itensServico = itensServico;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDataEntrada() {
		return dataEntrada;
	}

	public void setDataEntrada(Date dataEntrada) {
		this.dataEntrada = dataEntrada;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	public int getQuilometragem() {
		return quilometragem;
	}

	public void setQuilometragem(int quilometragem) {
		this.quilometragem = quilometragem;
	}

	public double getValorMaoDeObra() {
		return valorMaoDeObra;
	}

	public void setValorMaoDeObra(double valorMaoDeObra) {
		this.valorMaoDeObra = valorMaoDeObra;
	}

	public double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public int getValidade() {
		return validade;
	}

	public void setValidade(int validade) {
		this.validade = validade;
	}

	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public Veiculo getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}
	
	
}
