package aadd.web.localizacion;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@SessionScoped
public class CarritoServerLocaleWeb implements Serializable{
	
	@Inject
	private FacesContext facesContext;
	
	@Inject
	private ActiveLocale localeConfig;
	
	
	
	private List<String> items;

	private String item;
	
	public CarritoServerLocaleWeb() {
		System.out.println("Nuevo CarritoWeb");
        items = new ArrayList<>();
    }

    public void agregarItem() {
        items.add(item);
    }

    public List<String> getItems() {
        return items;
    }

    public int getCantidad() {
        return items.size();
    }

    public void limpiarCarrito() {
        items.clear();
        facesContext.addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "",localeConfig.getBundle().getString("successClean")));
        
    }

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}
    
    
}
