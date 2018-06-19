package br.com.backend.requisitos.enums;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public enum PerfilIntegranteProjeto {
	GERENTE(0, "Gerente"),
	ANALISTA(1, "Analista"),
	DESENVOLVEDOR(2, "Desenvolvedor"),
	VISITANTE(3, "Visitante");
	
	private final Integer number;
	private final String value;

    private PerfilIntegranteProjeto(Integer number, String value) {
    	this.number = number;
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
    
    public Integer getNumber() {
		return number;
	}
    
    @Override
    public String toString() {
        return this.value;
    }

    public static Map<PerfilIntegranteProjeto, String> getPerfis() {
        Map<PerfilIntegranteProjeto, String> map = new ConcurrentHashMap<>();
        for (PerfilIntegranteProjeto userType : PerfilIntegranteProjeto.values()) {
            map.put(userType, userType.toString());
        }
        return map;
    }
    
    public static PerfilIntegranteProjeto getPerfil(int codigoInt) {
		
    	PerfilIntegranteProjeto perfilEnum = null;
    	
    	for (PerfilIntegranteProjeto _perfilNumber : PerfilIntegranteProjeto.values()) {
    		if(_perfilNumber.getNumber() == codigoInt) return perfilEnum = _perfilNumber;
    	}
		
		return perfilEnum;
	}
}
