package com.brq.cursomc.controllers.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class URLUtilsController {

	public static String decodeParam(String valor) throws UnsupportedEncodingException {
		return URLDecoder.decode(valor, "UTF-8");
	}
	
	public static List<Integer> decodeIntegerList(String valor) {
		
		return Arrays.asList(valor.split(",")).stream()
				.map(valoInteiro -> Integer.parseInt(valoInteiro))
				.collect(Collectors.toList());
	}
}
