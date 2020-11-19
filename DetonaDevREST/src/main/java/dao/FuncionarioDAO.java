package dao;

import java.util.List;

import entidade.Funcionario;

public interface FuncionarioDAO {
	
	public void inserirFuncionario(Funcionario funcionario);
	
	public void editarFuncionario(Funcionario funcionario);
	
	public void deletarFuncionario(Funcionario funcionario);
	
	public Funcionario pesquisarFuncionario(String codigo);
	
	public List<Funcionario> listarTodos();

}
