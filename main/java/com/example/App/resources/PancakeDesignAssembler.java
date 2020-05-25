package com.example.App.resources;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;

import com.example.App.RestApiController.DesignPancake;
import com.example.App.domain.Pancake;

public class PancakeDesignAssembler extends 
RepresentationModelAssemblerSupport<Pancake , PancakeDesignResource>{


	public PancakeDesignAssembler() {
		super(DesignPancake.class, PancakeDesignResource.class);
 	}

	@Override
	public PancakeDesignResource toModel(Pancake pancake) {
		return createModelWithId(pancake.getid(), pancake); 
	}
	
	@Override 
	public PancakeDesignResource  instantiateModel(Pancake pancake) {
		return new  PancakeDesignResource(pancake); 
		
	}

}
