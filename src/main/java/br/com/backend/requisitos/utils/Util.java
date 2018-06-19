package br.com.backend.requisitos.utils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.backend.requisitos.entity.Integrante;
import br.com.backend.requisitos.entity.Projeto;

public class Util {
	public static Calendar currentDate() {
		return Calendar.getInstance();
	}
	
	public static Integrante insertProjetoToIntegrante(Projeto projeto, Integrante integrante) {
		try {
			List<Projeto> projetos = new ArrayList<Projeto>();
			projetos.add(new Projeto(projeto));
			
			integrante.setProjetos(projetos);
			return integrante;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static Projeto insetIntegranteToProjeto(Projeto projeto, Integrante integrante) {
		try {
			List<Integrante> integrantes = new ArrayList<Integrante>();
			if(projeto.getIntegrantes() != null && !projeto.getIntegrantes().isEmpty())
				integrantes = projeto.getIntegrantes();

			integrantes.add(new Integrante(integrante));
			
			projeto.setIntegrantes(integrantes);
			return projeto;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
