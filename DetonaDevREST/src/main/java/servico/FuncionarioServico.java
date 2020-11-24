package servico;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import dao.FuncionarioDAO;
import dao.FuncionarioDAOImpl;
import entidade.Funcionario;
import entidade.RetornoFuncionario;

@Path("/funcionario")
public class FuncionarioServico {
	
	FuncionarioDAO funcionarioDAO;
	
	public FuncionarioServico() {
		
		this.funcionarioDAO = new FuncionarioDAOImpl();
		
	}

	@Path("/")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String ola() {
		return "Funcionario DetonaDevREST !!!";
	}

	@Path("/inserirFuncionario")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public RetornoFuncionario inserirFuncionario(Funcionario funcionario) {

		RetornoFuncionario retorno = new RetornoFuncionario();
		retorno.setCodigoRetorno(0);
		retorno.setMsgRetorno("SUCESSO!!!");
		
		if(!this.funcionarioDAO.inserirFuncionario(funcionario)){
			
			retorno.setCodigoRetorno(1);
			retorno.setMsgRetorno("FALHA: Funcionario não inserido!!!");
			
		}
		
		return retorno;
	}
	
	@Path("/login")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public RetornoFuncionario login(Funcionario funcionario) {

		RetornoFuncionario retorno = new RetornoFuncionario();
		retorno.setCodigoRetorno(0);
		retorno.setMsgRetorno("SUCESSO!!!");
		
		Funcionario recuperadoBase = funcionarioDAO.buscarFuncionario(funcionario.getCodigo());
		
		if(!(recuperadoBase != null && recuperadoBase.getSenha().equals(funcionario.getSenha()))){
			
			retorno.setCodigoRetorno(1);
			retorno.setMsgRetorno("FALHA: Funcionario invalido!!!");
			
		}
		
		return retorno;
		
	}

}
