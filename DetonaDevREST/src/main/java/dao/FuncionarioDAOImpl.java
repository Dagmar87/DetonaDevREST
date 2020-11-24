package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import entidade.Funcionario;
import util.JpaUtil;

public class FuncionarioDAOImpl implements FuncionarioDAO{

	@Override
	public boolean inserirFuncionario(Funcionario funcionario) {
		// TODO Auto-generated method stub
		
		boolean retorno = true;
		
		EntityManager ent = JpaUtil.getEntityManager();
		EntityTransaction trans = ent.getTransaction();
		trans.begin();
		
		Funcionario funBase = ent.find(Funcionario.class, funcionario.getCodigo());
		
		if(funBase == null){
			
			ent.persist(funcionario);
			trans.commit();
			
		} else {
			
			return false;
			
		}
		
		ent.close();
		return retorno;
	}

	@Override
	public Funcionario buscarFuncionario(int codigoFuncionario) {
		// TODO Auto-generated method stub
		
		EntityManager ent = JpaUtil.getEntityManager();
		
		Funcionario retorno = ent.find(Funcionario.class, codigoFuncionario);
		
		return retorno;
	}

}
