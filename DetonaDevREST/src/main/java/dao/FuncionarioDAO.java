package dao;

import entidade.Funcionario;

public interface FuncionarioDAO {
	
	public boolean inserirFuncionario(Funcionario funcionario);
	
	public Funcionario buscarFuncionario(int codigoFuncionario);

}
