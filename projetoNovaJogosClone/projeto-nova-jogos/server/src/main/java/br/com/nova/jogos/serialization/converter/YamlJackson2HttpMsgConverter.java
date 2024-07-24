package br.com.nova.jogos.serialization.converter;

import org.springframework.http.MediaType;
import org.springframework.http.converter.json.AbstractJackson2HttpMessageConverter;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

public class YamlJackson2HttpMsgConverter extends AbstractJackson2HttpMessageConverter {

	public YamlJackson2HttpMsgConverter() {

		super(new YAMLMapper().setSerializationInclusion(Include.NON_NULL),
				MediaType.parseMediaType("application/x-yaml"));
	}

}
