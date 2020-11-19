package dao;

import java.util.List;

import entidade.Material;

public interface MaterialDAO {
	
	public void inserirMaterial(Material material);
	
	public void editarMaterial(Material material);
	
	public void deletarMaterial(Material material);
	
	public Material pesquisarMaterial(int id);
	
	public List<Material> listarTodos();

}
