package br.com.backend.requisitos.enums;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public enum CategoriaRequisito {
	FUNCIONAL(0, "Funcional"),
	NAOFUNCIONAL(1, "Nao Funcional");
	
	private final Integer number;
	private final String value;

    private CategoriaRequisito(Integer number, String value) {
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

    public static Map<CategoriaRequisito, String> getPerfis() {
        Map<CategoriaRequisito, String> map = new ConcurrentHashMap<>();
        for (CategoriaRequisito userType : CategoriaRequisito.values()) {
            map.put(userType, userType.toString());
        }
        return map;
    }
    
    public static CategoriaRequisito getPerfil(int codigoInt) {
		
    	CategoriaRequisito CategoriaEnum = null;
    	
    	for (CategoriaRequisito _perfilNumber : CategoriaRequisito.values()) {
    		if(_perfilNumber.getNumber() == codigoInt) return CategoriaEnum = _perfilNumber;
    	}
		
		return CategoriaEnum;
	}
}
