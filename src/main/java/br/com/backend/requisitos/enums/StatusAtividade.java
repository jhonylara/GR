package br.com.backend.requisitos.enums;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public enum StatusAtividade {
	HAINICIAR(0, "Ha iniciar"),
	DESENVOLVENDO(1, "Desenvolvendo"),
	TESTANDO(2, "Testando"),
	PARADO(3, "Parado"),
	CONCLUIDO(4, "Concluido");
	
	private final Integer number;
	private final String value;

    private StatusAtividade(Integer number, String value) {
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

    public static Map<StatusAtividade, String> getPerfis() {
        Map<StatusAtividade, String> map = new ConcurrentHashMap<>();
        for (StatusAtividade userType : StatusAtividade.values()) {
            map.put(userType, userType.toString());
        }
        return map;
    }
    
    public static StatusAtividade getPerfil(int codigoInt) {
		
    	StatusAtividade StatusEnum = null;
    	
    	for (StatusAtividade _statusNumber : StatusAtividade.values()) {
    		if(_statusNumber.getNumber() == codigoInt) return StatusEnum = _statusNumber;
    	}
		
		return StatusEnum;
	}	
}
