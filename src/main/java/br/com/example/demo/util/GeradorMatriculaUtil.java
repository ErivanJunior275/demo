package br.com.example.demo.util;

import java.util.Random;

public class GeradorMatriculaUtil {

	private static final Random gerador = new Random();  
	
	private GeradorMatriculaUtil() {}

	public static String gerarMatricula() {
		return String.format("%06d", gerador.nextInt(999999));
	}
}
