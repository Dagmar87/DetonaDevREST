package entidade;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "VEICULO")
public class Veiculo {

 @Id	
 @Column(name = "PLACA", nullable = false, length=7)
 private String placa;
 @Column(name = "FABRICANTE", nullable = false)
 private String fabricante;
 @Column(name = "TIPO", nullable = false)
 private String tipo;
 @Column(name = "COR", nullable = false)
 private String cor;
 @Column(name = "MODELO", nullable = false)
 private String modelo;
 @Column(name = "ANO", nullable = false, length=4)
 private int ano;
 
 
 @ManyToOne
 @JoinColumn(name = "ID_CLIENTE", referencedColumnName = "CPF", nullable = false)
 private Cliente cliente;
 
public String getPlaca() {
	return placa;
}

public void setPlaca(String placa) {
	this.placa = placa;
}

public String getFabricante() {
	return fabricante;
}

public void setFabricante(String fabricante) {
	this.fabricante = fabricante;
}

public String getTipo() {
	return tipo;
}

public void setTipo(String tipo) {
	this.tipo = tipo;
}

public String getCor() {
	return cor;
}

public void setCor(String cor) {
	this.cor = cor;
}

public String getModelo() {
	return modelo;
}

public void setModelo(String modelo) {
	this.modelo = modelo;
}

public int getAno() {
	return ano;
}

public void setAno(int ano) {
	this.ano = ano;
}

public Cliente getCliente() {
	return cliente;
}

public void setCliente(Cliente cliente) {
	this.cliente = cliente;
}
	
	
}

