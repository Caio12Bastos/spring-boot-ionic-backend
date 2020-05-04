package com.brq.cursomc.services.validation.utils;

public class CpfCnpjUtils {

	//CPF
	private static final int[] TAMANHO_CPF = {11 , 10 , 9 , 8 , 7 , 6 , 5 , 4 , 3 , 2};
	
	//CNPJ
	private static final int[] TAMANHO_CNPJ = {6 , 5 , 4 , 3 , 2 , 9 , 8 , 7 , 6 , 5 , 4 , 3 , 2 };
	
	private static int calculador(final String valor, final int[] tamanho) {
		int soma = 0;
		
		for(int i = valor.length() - 1, digito; i >= 0; i--) {
			digito = Integer.parseInt(valor.substring(i, i + 1));
			soma += digito * tamanho[tamanho.length - valor.length() + i];
		}
		soma = 11 - (soma % 11);
		return soma > 9 ? 0 : soma;
	}
	
	public static boolean validaCPF(final String valorCpf) {
		if((valorCpf == null) || (valorCpf.length() != 11) || valorCpf.matches(valorCpf.charAt(0) + "{11}")) 
			return false;   
		
		final Integer digito1 = calculador(valorCpf.substring(0, 9), TAMANHO_CPF);
		final Integer digito2 = calculador(valorCpf.substring(0, 9) + digito1, TAMANHO_CPF);
		
		return valorCpf.equals(valorCpf.substring(0, 9) + digito1.toString() + digito2.toString());
	}
	
	public static boolean validaCNPJ(final String valorCnpj) {
		if((valorCnpj == null) || (valorCnpj.length() != 14) || valorCnpj.matches(valorCnpj.charAt(0) + "{14}")) 
			return false;   
		
		final Integer digito1 = calculador(valorCnpj.substring(0, 12), TAMANHO_CNPJ);
		final Integer digito2 = calculador(valorCnpj.substring(0, 12) + digito1, TAMANHO_CNPJ);
		
		return valorCnpj.equals(valorCnpj.substring(0, 12) + digito1.toString() + digito2.toString());
	}
}
