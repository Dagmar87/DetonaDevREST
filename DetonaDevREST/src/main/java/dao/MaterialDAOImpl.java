package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entidade.Material;
import util.JdbcUtil;

public class MaterialDAOImpl implements MaterialDAO{

	@Override
	public void inserirMaterial(Material material) {
		// TODO Auto-generated method stub
		
		String sql = "INSERT INTO MATERIAL (DESCRICAO, CODIGO, FABRICANTE, CLASSIFICACAO, VALORUNITARIO) VALUES (?, ?, ?, ?, ?)";
		
		Connection conexao = null;
		
		try {
			
			conexao = JdbcUtil.getConexao();
			
			conexao.setAutoCommit(false);
			
			PreparedStatement ps = conexao.prepareStatement(sql);
			
			ps.setString(1, material.getDescricao());
			ps.setInt(2, material.getCodigo());
			ps.setString(3, material.getFabricante());
			ps.setString(4, material.getClassificacao());
			ps.setDouble(5, material.getValorUnitario());			
			
			ps.execute();
			
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
	public void editarMaterial(Material material) {
		// TODO Auto-generated method stub
		
		String sql = "UPDATE MATERIAL M SET M.DESCRICAO = ?, M.CODIGO = ?, M.FABRICANTE = ?, M.CLASSIFICACAO = ?, M.VALORUNITARIO = ? "
				   + " WHERE M.ID_MATERIAL = ?;";
		
		Connection conexao = null;
		
		try {
			
			conexao = JdbcUtil.getConexao();
			
			conexao.setAutoCommit(false);
			
			PreparedStatement ps = conexao.prepareStatement(sql);
			
			ps.setString(1, material.getDescricao());
			ps.setInt(2, material.getCodigo());
			ps.setString(3, material.getFabricante());
			ps.setString(4, material.getClassificacao());
			ps.setDouble(5, material.getValorUnitario());	
			ps.setInt(6, material.getId());
			
			ps.execute();
			
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
	public void deletarMaterial(Material material) {
		// TODO Auto-generated method stub
		
		String sql = "DELETE FROM MATERIAL M WHERE M.ID_MATERIAL = ?";
		
		Connection conexao = null;
		
		try {
			
			conexao = JdbcUtil.getConexao();
			
			conexao.setAutoCommit(false);
			
			PreparedStatement ps = conexao.prepareStatement(sql);
			
			ps.setInt(1, material.getId());
			
			ps.execute();
			
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
	public Material pesquisarMaterial(int id) {
		// TODO Auto-generated method stub
		
		String sql = "SELECT M.DESCRICAO, M.CODIGO, M.FABRICANTE, M.CLASSIFICACAO, M.VALORUNITARIO FROM MATERIAL M "
				   + " WHERE M.ID_MATERIAL = ?";
		
		Material material = null;
		
		Connection conexao;
		
		try {
			
			conexao = JdbcUtil.getConexao();
			
			PreparedStatement ps = conexao.prepareStatement(sql);
			
			ps.setInt(1, id);
			
			ResultSet res = ps.executeQuery();
			
			while(res.next()){
				
				material = new Material();
				material.setDescricao(res.getString("DESCRICAO"));
				material.setCodigo(res.getInt("CODIGO"));
				material.setFabricante(res.getString("FABRICANTE"));
				material.setClassificacao(res.getString("CLASSIFICACAO"));
				material.setValorUnitario(res.getDouble("VALORUNITARIO"));
				
			}
			
			ps.close();
			conexao.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return material;
	}

	@Override
	public List<Material> listarTodos() {
		// TODO Auto-generated method stub
		
		List<Material> listaRetorno = new ArrayList<Material>();
		
		String sql = "SELECT M.ID_MATERIAL, M.DESCRICAO, M.CODIGO, M.FABRICANTE, M.CLASSIFICACAO, M.VALORUNITARIO FROM MATERIAL M";
		
		System.out.println(sql);
		
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

}
