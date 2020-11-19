package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import entidade.Funcionario;
import entidade.ItemServico;
import entidade.Material;
import entidade.OrdemServico;
import entidade.Veiculo;
import util.JdbcUtil;

public class OrdemServicoDAOImpl implements OrdemServicoDAO{
	
	private Integer recuperaIdOrdemServico(){
		
		String sql = "select S_ID_ORDEMSERVICO.nextval from dual";
		
		Integer idRetorno = null;
		
		Connection conexao;
		
		try {
			
			conexao = JdbcUtil.getConexao();
			
			PreparedStatement ps = conexao.prepareStatement(sql);

			ResultSet res = ps.executeQuery();
			
			while (res.next()){
				
				idRetorno = res.getInt(1);
				
			}
			
			ps.close();
			conexao.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return idRetorno;
		
	}
	
	private Integer recuperaIdItemServico(){
		
		String sql = "select S_ID_ITEMSERVICO.nextval from dual";
		
		Integer idRetorno2 = null;
		
		Connection conexao;
		
		try {
			
			conexao = JdbcUtil.getConexao();
			
			PreparedStatement ps = conexao.prepareStatement(sql);

			ResultSet res = ps.executeQuery();
			
			while (res.next()){
				
				idRetorno2 = res.getInt(1);
				
			}
			
			ps.close();
			conexao.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return idRetorno2;
		
	}

	@Override
	public void inserirOrdemServico(OrdemServico ordemServico) {
		// TODO Auto-generated method stub
		
		int idOrdemServico = this.recuperaIdOrdemServico();
		
		ordemServico.setId(idOrdemServico);
		
		ItemServico itemServico = new ItemServico();
		
		int idItemServico = this.recuperaIdItemServico();
		
		itemServico.setId(idItemServico);		
		
		String sqlOrdemServico = "INSERT INTO ORDEMSERVICO (ID_ORDEMSERVICO, DATAENTRADA, DATASERVICOINICIO, "
					           + " DATASERVICOFIM, VEICULO, FUNCIONARIO, VALORTOTAL, VALORMAODEOBRA, "
							   + " QUILOMETRAGEM, OBS, STATUS, VALIDADE) "
					           + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		String sqlVeiculo = "INSERT INTO VEICULO (PLACA, MODELO, FABRICANTE, TIPO, ANO, COR, ID_CLIENTE) "
						  + " VALUES (?, ?, ?, ?, ?, ?, ?)";
		
		String sqlCliente = "INSERT INTO CLIENTE (NOME, CPF, TELEFONE, EMAIL) VALUES (?, ?, ?, ?)";
		
		String sqlItemServico = "INSERT INTO ITEMSERVICO (ID_ITEMSERVICO, ID_MATERIAL, ID_ORDEMSERVICO, QTD) "
							  + " VALUES (?, ?, ?, ?)";
		
		Connection conexao = null;
		
		try {
			
			conexao = JdbcUtil.getConexao();
			
			conexao.setAutoCommit(false);
			
			PreparedStatement ps = conexao.prepareStatement(sqlCliente);
			
			ps.setString(1, ordemServico.getVeiculo().getCliente().getNome());
			ps.setString(2, ordemServico.getVeiculo().getCliente().getCpf());
			ps.setString(3, ordemServico.getVeiculo().getCliente().getTelefone());
			ps.setString(4, ordemServico.getVeiculo().getCliente().getEmail());
			
			ps.execute();
			
			ps = conexao.prepareStatement(sqlVeiculo);
			
			ps.setString(1, ordemServico.getVeiculo().getPlaca());
			ps.setString(2, ordemServico.getVeiculo().getModelo());
			ps.setString(3, ordemServico.getVeiculo().getFabricante());
			ps.setString(4, ordemServico.getVeiculo().getTipo());
			ps.setInt(5, ordemServico.getVeiculo().getAno());
			ps.setString(6, ordemServico.getVeiculo().getCor());
			ps.setString(7, ordemServico.getVeiculo().getCliente().getCpf());
			
			ps.execute();
			
			ps = conexao.prepareStatement(sqlOrdemServico);
			
			ps.setInt(1, ordemServico.getId());
			ps.setDate(2, new java.sql.Date(ordemServico.getDataEntrada().getTime()));
			ps.setDate(3, new java.sql.Date(ordemServico.getDataServicoInicio().getTime()));
			ps.setDate(4, new java.sql.Date(ordemServico.getDataServicoFim().getTime()));
			ps.setString(5, ordemServico.getVeiculo().getPlaca());
			ps.setString(6, ordemServico.getFuncionario().getCodigo());
			ps.setDouble(7, ordemServico.getValorTotal());
			ps.setDouble(8, ordemServico.getValorMaoDeObra());
			ps.setDouble(9, ordemServico.getQuilometragem());
			ps.setString(10, ordemServico.getObs());
			ps.setString(11, ordemServico.getStatus());
			ps.setDate(12, new java.sql.Date(ordemServico.getValidade().getTime()));
			
			ps.execute();
			
			for(ItemServico itemServico2 : ordemServico.getListaItemServico()){
				
				ps = conexao.prepareStatement(sqlItemServico);
				
				ps.setInt(1, itemServico2.getId());
				ps.setInt(2, itemServico2.getMaterial().getId());
				ps.setInt(3, ordemServico.getId());
				ps.setInt(4, itemServico2.getQtd());
				
				ps.execute();
								
			}
			
			ps.close();
			conexao.commit();
			conexao.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
			if(conexao != null){
				try {
					conexao.rollback();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		}
		
	}

	@Override
	public void editarOrdemServico(OrdemServico ordemServico) {
		// TODO Auto-generated method stub
		
		String sqlOrdemServico = "UPDATE ORDEMSERVICO OS SET OS.DATAENTRADA = ?, OS.DATASERVICOINICIO = ?, OS.DATASERVICOFIM = ?, "
						       + " OS.VEICULO = ?, OS.FUNCIONARIO = ?, OS.VALORTOTAL = ?, OS.VALORMAODEOBRA = ?, OS.QUILOMETRAGEM = ?, "
						       + " OS.OBS = ?, OS.VALIDADE = ? "
						       + " WHERE OS.ID_ORDEMSERVICO = ?";
		
		String sqlVeiculo = "UPDATE VEICULO V SET V.PLACA = ?, V.MODELO = ?, V.FABRICANTE = ?, V.TIPO = ?, " 
						  + " V.ANO = ?, V.COR = ?, V.ID_CLIENTE = ? "
						  + " WHERE V.PLACA = ?";
		
		String sqlCliente = "UPDATE CLIENTE C SET C.NOME = ?, C.CPF = ?, C.TELEFONE = ?, C.EMAIL = ? " 
						  + " WHERE C.CPF = ?";
		
		String sqlItemServico = "UPDATE ITEMSERVICO ITS SET ITS.ID_MATERIAL = ?, ITS.ID_ORDEMSERVICO = ?, ITS.QTD = ? " 
							  + " WHERE ITS.ID_ITEMSERVICO = ?";
		
		Connection conexao = null;
		
		try {
			
			conexao = JdbcUtil.getConexao();
			
			conexao.setAutoCommit(false);
			
			PreparedStatement ps = conexao.prepareStatement(sqlCliente);
			
			ps.setString(1, ordemServico.getVeiculo().getCliente().getNome());
			ps.setString(2, ordemServico.getVeiculo().getCliente().getCpf());
			ps.setString(3, ordemServico.getVeiculo().getCliente().getTelefone());
			ps.setString(4, ordemServico.getVeiculo().getCliente().getEmail());
			ps.setString(5, ordemServico.getVeiculo().getCliente().getCpf());
			
			ps.execute();
			
			ps = conexao.prepareStatement(sqlVeiculo);
			
			ps.setString(1, ordemServico.getVeiculo().getPlaca());
			ps.setString(2, ordemServico.getVeiculo().getModelo());
			ps.setString(3, ordemServico.getVeiculo().getFabricante());
			ps.setString(4, ordemServico.getVeiculo().getTipo());
			ps.setInt(5, ordemServico.getVeiculo().getAno());
			ps.setString(6, ordemServico.getVeiculo().getCor());
			ps.setString(7, ordemServico.getVeiculo().getCliente().getCpf());
			ps.setString(8, ordemServico.getVeiculo().getPlaca());
			
			ps.execute();
			
			ps = conexao.prepareStatement(sqlOrdemServico);
			
			ps.setDate(1, new java.sql.Date(ordemServico.getDataEntrada().getTime()));
			ps.setDate(2, new java.sql.Date(ordemServico.getDataServicoInicio().getTime()));
			ps.setDate(3, new java.sql.Date(ordemServico.getDataServicoFim().getTime()));
			ps.setString(4, ordemServico.getVeiculo().getPlaca());
			ps.setString(5, ordemServico.getFuncionario().getCodigo());
			ps.setDouble(6, ordemServico.getValorTotal());
			ps.setDouble(7, ordemServico.getValorMaoDeObra());
			ps.setDouble(8, ordemServico.getQuilometragem());
			ps.setString(9, ordemServico.getObs());
			ps.setString(10, ordemServico.getStatus());
			ps.setDate(11, new java.sql.Date(ordemServico.getValidade().getTime()));
			ps.setInt(12 , ordemServico.getId());
			
			ps.execute();
			
			for(ItemServico itemServico2 : ordemServico.getListaItemServico()){
				
				ps = conexao.prepareStatement(sqlItemServico);
				
				ps.setInt(1, itemServico2.getMaterial().getId());
				ps.setInt(2, ordemServico.getId());
				ps.setInt(3, itemServico2.getQtd());
				ps.setInt(4, itemServico2.getId());
				
				ps.execute();
								
			}
			
			ps.close();
			conexao.commit();
			conexao.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
			if(conexao != null){
				try {
					conexao.rollback();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		}
		
	}

	@Override
	public void deletarOrdemServico(OrdemServico ordemServico) {
		// TODO Auto-generated method stub
		
		String sqlOrdemServico = "DELETE FROM ORDEMSERVICO OS WHERE OS.ID_ORDEMSERVICO = ?";
		
		String sqlVeiculo = "DELETE FROM VEICULO V WHERE V.PLACA = ?";
		
		String sqlCliente = "DELETE FROM CLIENTE C WHERE C.CPF";
		
		String sqlItemServico = "DELETE FROM ITEMSERVICO ITS WHERE ITS.ID_ITEMSERVICO = ?";
		
		Connection conexao = null;
		
		try {
			
			conexao = JdbcUtil.getConexao();
			
			conexao.setAutoCommit(false);
			
			PreparedStatement ps = conexao.prepareStatement(sqlItemServico);
			
			ItemServico itemServico = new ItemServico();
			
			ps.setInt(1, itemServico.getId());
			
			ps.execute();
			
			ps = conexao.prepareStatement(sqlOrdemServico);
			
			ps.setInt(1, ordemServico.getId());
			
			ps.execute();
			
			ps = conexao.prepareStatement(sqlVeiculo);
			
			ps.setString(1, ordemServico.getVeiculo().getPlaca());
			
			ps.execute();
			
			ps = conexao.prepareStatement(sqlCliente);
			
			ps.setString(1, ordemServico.getVeiculo().getCliente().getCpf());
			
			ps.execute();
			
			ps.close();
			conexao.commit();
			conexao.close();
			
		} catch (Exception e) {
			e.printStackTrace();
			if(conexao != null) {
				try {
					conexao.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
			}
		}
		
	}

	@Override
	public List<Material> listarMaterial() {
		// TODO Auto-generated method stub
		
		List<Material> listaRetorno = new ArrayList<Material>();
		
		String sql = "SELECT M.ID_MATERIAL, M.DESCRICAO, M.CODIGO, M.FABRICANTE, M.CLASSIFICACAO, M.VALORUNITARIO FROM MATERIAL M "
				   + " ORDER BY M.CLASSIFICACAO ASC";
		
		Connection conexao;
		
		try {
			 
			conexao = JdbcUtil.getConexao();
			
			PreparedStatement ps = conexao.prepareStatement(sql);
			
			ResultSet res = ps.executeQuery();
			
			while(res.next()){
				
				Material material = new Material();
				material.setId(res.getInt("ID_MATERIAL"));
				material.setDescricao(res.getString("DESCRICAO"));
				material.setCodigo(res.getInt("CODIGO"));
				material.setFabricante(res.getString("FABRICANTE"));
				material.setClassificacao(res.getString("CLASSIFICACAO"));
				material.setValorUnitario(res.getDouble("VALORUNITARIO"));
				
				listaRetorno.add(material);
				
			}
			
			ps.close();
			conexao.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return listaRetorno;
	}

	@Override
	public List<OrdemServico> pesquisarOrdemServico(String status, double valorTotal, String placa, int ano,
			String nome, Date dataServicoInicio, Date dataServicoFim, String modelo, int idMaterial) {
		// TODO Auto-generated method stub
		
		List<OrdemServico> listaRetorno = new ArrayList<OrdemServico>();
		
		String sql = "SELECT DISTINCT OS.DATAENTRADA, "
				   + " V.PLACA, "
				   + " V.ANO, "
				   + " V.MODELO, "
				   + " F.NOME, "
				   + " OS.VALORTOTAL, "
				   + " OS.STATUS "
				   + " FROM ORDEMSERVICO OS "
				   + " JOIN VEICULO V "
				   + " ON OS.VEICULO = V.PLACA "
				   + " JOIN FUNCIONARIO F "
				   + " ON OS.FUNCIONARIO = F.CODIGO "
				   + " JOIN ITEMSERVICO ITS "
				   + " ON ITS.ID_ORDEMSERVICO = OS.ID_ORDEMSERVICO "
				   + " WHERE OS.VEICULO = V.PLACA "
				   + this.montarWherePesquisa(status, valorTotal, placa, ano, nome, dataServicoInicio, dataServicoFim, modelo, idMaterial);
		
		System.out.println(sql);
		
		Connection conexao;
		
		try {
			
			conexao = JdbcUtil.getConexao();
			
			PreparedStatement ps = conexao.prepareStatement(sql);

			ResultSet res = ps.executeQuery();
			
			while (res.next()){
				
				OrdemServico ordemServico = new OrdemServico();
				
				ordemServico.setId(res.getInt("ID_ORDEMSERVICO"));
				ordemServico.setValorTotal(res.getDouble("VALORTOTAL"));
				ordemServico.setDataServicoInicio(res.getDate("DATASERVICOINICIO"));
				ordemServico.setDataServicoFim(res.getDate("DATASERVICOFIM"));
				ordemServico.setStatus(res.getString("STATUS"));
				
				Veiculo veiculo = new Veiculo();
				
				veiculo.setPlaca(res.getString("PLACA"));
				veiculo.setModelo(res.getString("MODELO"));
				veiculo.setAno(res.getInt("ANO"));
				
				ordemServico.setVeiculo(veiculo);
				
				Funcionario funcionario = new Funcionario();
				
				funcionario.setCodigo(res.getString("CODIGO"));
				funcionario.setNome(res.getString("NOME"));
				
				ordemServico.setFuncionario(funcionario);
				
				listaRetorno.add(ordemServico);
				
			}
			
			ps.close();
			conexao.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return listaRetorno;
	}
	
	private String montarWherePesquisa(String status, double valorTotal, String placa, int ano,
			String nome, Date dataServicoInicio, Date dataServicoFim, String modelo, int idMaterial){
		
		String where = "";
		
		SimpleDateFormat dataSimples = new SimpleDateFormat("dd/MM/yyyy");
		
		if(status != null && !status.isEmpty()) {
			where += " AND UPPER(OS.STATUS) LIKE UPPER ('%" + status + "%') ";
		}
		
		if(valorTotal > 0) {
			where += " AND OS.VALORTOTAL = " + valorTotal;
		}
		
		if(placa != null && !placa.isEmpty()) {
			where += " AND UPPER(V.PLACA) = UPPER('%" + placa + "%') ";
		}
		
		if(ano > 0) {
			where += " AND V.ANO = " + ano;
		}
		
		if(nome != null && !nome.isEmpty()) {
			where += " AND UPPER(F.NOME) = UPPER('%" + nome + "%') ";
		}
		
		if(dataServicoInicio != null) {
			where += " AND OS.DATASERVICOINICIO = '" + dataSimples.format(dataServicoInicio) + "', 'DD/MM/YYYY' ";
		}
		
		if(dataServicoFim != null) {
			where += " AND OS.DATASERVICOFIM = '" + dataSimples.format(dataServicoFim) + "', 'DD/MM/YYYY' ";
		}
		
		if(modelo != null && !modelo.isEmpty()) {
			where += " AND UPPER(V.MODELO) LIKE UPPER ('%" + modelo + "%') ";
		}
		
		if(idMaterial > 0) {
			where += " AND ITS.ID_MATERIAL  = " + idMaterial;
		}
		
		return where;
		
	}

}
