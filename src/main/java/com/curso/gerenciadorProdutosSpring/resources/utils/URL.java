package com.curso.gerenciadorProdutosSpring.resources.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

public class URL {

	//função para desencodar um String, seja com espaços e etc, usaremos o padrão UTF-8
	//caso de erro retorne uma string vazia
	public static String decodeParam(String s) {
		try {
			return URLDecoder.decode(s, "UTF-8");	
		} catch (UnsupportedEncodingException e) {
			return "";
		}
	}
	
	
	//pegar o String da URI, que são numeros separados por virgula, quebrar eles em pedaços, converter em inteiros e add a lista
	public static List<Integer> decodeIntList(String s){
		String[] vet = s.split(",");
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < vet.length; i++) {
			list.add(Integer.parseInt(vet[i]));
		}
		return list;
	}
	
	//expressão lambida que faz o mesmo do método acima
	//return Arrays.asList(s.split(",")).stream().map(x -> Integer.parseInt(x)).collect(Collectors.toList());
		
}
