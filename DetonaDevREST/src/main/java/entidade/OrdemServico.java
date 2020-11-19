package entidade;

import java.util.Date;
import java.util.List;

public class OrdemServico {
	
	private int id;
	private Date dataEntrada;
	private Date dataServicoInicio;
	private Date dataServicoFim;
	private Veiculo veiculo;
	private Funcionario funcionario;
	private double valorTotal;
	private double valorMaoDeObra;
	private double quilometragem;
	private String obs;
	private String status;
	private Date validade;
	
	private List<ItemServico> listaItemServico;
	
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
	
	public Date getDataServicoInicio() {
		return dataServicoInicio;
	}
	
	public void setDataServicoInicio(Date dataServicoInicio) {
		this.dataServicoInicio = dataServicoInicio;
	}
	
	public Date getDataServicoFim() {
		return dataServicoFim;
	}
	
	public void setDataServicoFim(Date dataServicoFim) {
		this.dataServicoFim = dataServicoFim;
	}
	
	public Veiculo getVeiculo() {
		return veiculo;
	}
	
	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}
	
	public Funcionario getFuncionario() {
		return funcionario;
	}
	
	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
	
	public double getValorTotal() {
		return valorTotal;
	}
	
	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}
	
	public double getValorMaoDeObra() {
		return valorMaoDeObra;
	}
	
	public void setValorMaoDeObra(double valorMaoDeObra) {
		this.valorMaoDeObra = valorMaoDeObra;
	}
	
	public double getQuilometragem() {
		return quilometragem;
	}
	
	public void setQuilometragem(double quilometragem) {
		this.quilometragem = quilometragem;
	}
	
	public String getObs() {
		return obs;
	}
	
	public void setObs(String obs) {
		this.obs = obs;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public Date getValidade() {
		return validade;
	}
	
	public void setValidade(Date validade) {
		this.validade = validade;
	}

	public List<ItemServico> getListaItemServico() {
		return listaItemServico;
	}

	public void setListaItemServico(List<ItemServico> listaItemServico) {
		this.listaItemServico = listaItemServico;
	}
	
}
