package dao;

import java.util.List;

import entidade.Material;
import entidade.OrdemServico;

public interface OrdemServicoDAO {
	
	public void inserirOrdemServico(OrdemServico ordemServico);
	
	public void editarOrdemServico(OrdemServico ordemServico);
	
	public void deletarOrdemServico(OrdemServico ordemServico);
	
	public List<Material> listarMaterial();
	
	public List<OrdemServico> pesquisarOrdemServico(String status, double valorTotal, String placa, int ano, String nome, 
			java.util.Date dataServicoInicio, java.util.Date dataServicoFim, String modelo, int idMaterial);

}
