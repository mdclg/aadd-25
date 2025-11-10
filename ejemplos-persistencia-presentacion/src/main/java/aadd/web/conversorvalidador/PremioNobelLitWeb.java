package aadd.web.conversorvalidador;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@SuppressWarnings("serial")
@Named
@ViewScoped
public class PremioNobelLitWeb implements Serializable{
	
	@Inject
	private NobelApp premios;

	private PremioNobelLit premioNobel;
	
	private String inputNobel;

	
	public PremioNobelLit getPremioNobel() {
		return premioNobel;
	}

	public void setPremioNobel(PremioNobelLit premioNobel) {
		this.premioNobel = premioNobel;
	}

	public String getInputNobel() {
		return inputNobel;
	}

	public void setInputNobel(String inputNobel) {
		this.inputNobel = inputNobel;
	}

	
	public void addNobel() {
		System.out.println("Llama a ADD NOBEL");
		String[] partes = inputNobel.split("-");
		premios.addNewNobel(Integer.valueOf(partes[0]), partes[1]);
	}


}
