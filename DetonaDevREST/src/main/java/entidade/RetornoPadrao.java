package entidade;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class RetornoPadrao {
	
	private int codigoRetorno; // 0 - sucesso / 1 - erro
	private String msgRetorno;
	
	public int getCodigoRetorno() {
		return codigoRetorno;
	}
	
	public void setCodigoRetorno(int codigoRetorno) {
		this.codigoRetorno = codigoRetorno;
	}
	
	public String getMsgRetorno() {
		return msgRetorno;
	}
	
	public void setMsgRetorno(String msgRetorno) {
		this.msgRetorno = msgRetorno;
	}

}
