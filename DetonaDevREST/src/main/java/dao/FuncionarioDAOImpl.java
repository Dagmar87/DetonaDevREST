package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entidade.Funcionario;
import util.JdbcUtil;

public class FuncionarioDAOImpl implements FuncionarioDAO{

	@Override
	public void inserirFuncionario(Funcionario funcionario) {
		// TODO Auto-generated method stub
		
		String sql = "INSERT INTO FUNCIONARIO (NOME , CODIGO, SENHA, TELEFONE, EMAIL) VALUES (?, ?, ?, ?, ?)";
		
		Connection conexao = null;
		
		try {
			
			conexao = JdbcUtil.getConexao();
			
			conexao.setAutoCommit(false);
			
			PreparedStatement ps = conexao.prepareStatement(sql);
			
			ps.setString(1, funcionario.getNome());
			ps.setString(2, funcionario.getCodigo());
			ps.setString(3, funcionario.getSenha());
			ps.setString(4, funcionario.getTelefone());
			ps.setString(5, funcionario.getEmail());
			
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
	public void editarFuncionario(Funcionario funcionario) {
		// TODO Auto-generated method stub
		
		String sql = "UPDATE FUNCIONARIO F SET F.NOME = ?, F.CODIGO = ?, F.SENHA = ?, F.TELEFONE = ?, F.EMAIL = ? "
				   + " WHERE F.CODIGO = ?";
		
		Connection conexao = null;
		
		try {
			
			conexao = JdbcUtil.getConexao();
			
			conexao.setAutoCommit(false);
			
			PreparedStatement ps = conexao.prepareStatement(sql);
			
			ps.setString(1, funcionario.getNome());
			ps.setString(2, funcionario.getCodigo());
			ps.setString(3, funcionario.getSenha());
			ps.setString(4, funcionario.getTelefone());
			ps.setString(5, funcionario.getEmail());
			ps.setString(6, funcionario.getCodigo());
			
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
	public void deletarFuncionario(Funcionario funcionario) {
		// TODO Auto-generated method stub
		
		String sql = "DELETE FROM FUNCIONARIO F WHERE F.CODIGO = ?";
		
		Connection conexao = null;
		
		try {
			
			conexao = JdbcUtil.getConexao();
			
			conexao.setAutoCommit(false);
			
			PreparedStatement ps = conexao.prepareStatement(sql);
			
			ps.setString(1, funcionario.getCodigo());
			
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
	public Funcionario pesquisarFuncionario(String codigo) {
		// TODO Auto-generated method stub
		
		String sql = "SELECT F.NOME, F.CODIGO, F.SENHA, F.TELEFONE, F.EMAIL FROM FUNCIONARIO F WHERE F.CODIGO = ?";
		
		Funcionario funcionario = null;
		
		Connection conexao;
		
		try {
			
			conexao = JdbcUtil.getConexao();
			
			PreparedStatement ps = conexao.prepareStatement(sql);
			
			ps.setString(1, codigo);
			
			ResultSet res = ps.executeQuery();
			
			while(res.next()){
				
				funcionario = new Funcionario();
				funcionario.setNome(res.getString("NOME"));
				funcionario.setCodigo(res.getString("CODIGO"));
				funcionario.setSenha(res.getString("SENHA"));
				funcionario.setTelefone(res.getString("TELEFONE"));
				funcionario.setEmail(res.getString("EMAIL"));
				
			}
			
			ps.close();
			conexao.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return funcionario;
	}

	@Override
	public List<Funcionario> listarTodos() {
		// TODO Auto-generated method stub
		
		List<Funcionario> listaRetorno = new ArrayList<Funcionario>();
		
		String sql = "SELECT F.NOME, F.CODIGO, F.SENHA, F.TELEFONE, F.EMAIL FROM FUNCIONARIO F";
		
		System.out.println(sql);
		
		Connection conexao;
		
		try {
			
			conexao = JdbcUtil.getConexao();
			
			PreparedStatement ps = conexao.prepareStatement(sql);

			ResultSet res = ps.executeQuery();
			
			while(res.next()){
				
				Funcionario funcionario = new Funcionario();
				funcionario.setNome(res.getString("NOME"));
				funcionario.setCodigo(res.getString("CODIGO"));
				funcionario.setSenha(res.getString("SENHA"));
				funcionario.setTelefone(res.getString("TELEFONE"));
				funcionario.setEmail(res.getString("EMAIL"));
				
				listaRetorno.add(funcionario);
				
			}
			
			ps.close();
			conexao.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return listaRetorno;
	}

}
