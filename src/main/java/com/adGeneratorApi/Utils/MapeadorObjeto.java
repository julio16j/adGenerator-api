package com.adGeneratorApi.Utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MapeadorObjeto {
	
	public static <T> T  converterStringJson (String conteudo, Class<T> tipoAlvo) throws JsonMappingException, JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper ();
		return mapper.readValue( conteudo, tipoAlvo);
	}
}
