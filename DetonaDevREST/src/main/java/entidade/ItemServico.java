package entidade;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "ITEMSERVICO")
@SequenceGenerator(name = "S_ITEMSERVICO", sequenceName = "S_ITEMSERVICO", initialValue = 1, allocationSize = 1)

public class ItemServico {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "S_ITEMSERVICO")
	@Column(name = "ID", nullable = false)
	private int id;
//	@Column(name = "ID_ORDEMSERVICO", nullable = false)	
//	private int idOrdemServico;
//	@Column(name = "ID_MATERIAL", nullable = false)	
//	private int idMaterial;

	@ManyToOne
	@JoinColumn(name = "ID_ORDEMSERVICO", referencedColumnName = "ID", nullable = false)
	private OrdemServico ordensServico;

	@ManyToOne
	@JoinColumn(name = "ID_MATERIAL", referencedColumnName = "ID", nullable = false)
	private Material material;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public OrdemServico getOrdensServico() {
		return ordensServico;
	}

	public void setOrdensServico(OrdemServico ordensServico) {
		this.ordensServico = ordensServico;
	}

	public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}

}
