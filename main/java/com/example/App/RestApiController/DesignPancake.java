package com.example.App.RestApiController;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import com.example.App.domain.Pancake;
import com.example.App.resources.PancakeDesignResource;
import com.example.App.resources.PancakeDesignAssembler;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
 import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
 import com.example.App.repository.JdbcPancake;

@RestController
@RequestMapping(path="/design", produces="application/json")
@CrossOrigin(origins="*")
public class DesignPancake {
	private JdbcPancake jdbcPancake; 
	
	@Autowired
    public DesignPancake(JdbcPancake jdbcPancake) { 
    	this.jdbcPancake = jdbcPancake; 
    	
    }
	
	@GetMapping("/recent")
	public CollectionModel<PancakeDesignResource> getRecentPancake() {
		ArrayList<Pancake> pancakes = (ArrayList<Pancake>) jdbcPancake.findAll();
		List<PancakeDesignResource> resource = new ArrayList<PancakeDesignResource>(); 
		for (Pancake p : pancakes) {
 		   resource.add( new PancakeDesignAssembler().toModel(p)); 
		}
		CollectionModel<PancakeDesignResource> result = new CollectionModel<PancakeDesignResource>(resource); 
		
		result.add(
				linkTo(methodOn(DesignPancake.class).getRecentPancake())
				.withSelfRel()
				); 
		return result;

	}
	
	@GetMapping("/{id}")
	public  ResponseEntity<Pancake> getPancakeById(@PathVariable("id")  int l ) { 
		try { 
		     Pancake  pancake = jdbcPancake.findById(l); 
		     return new ResponseEntity<>(pancake , HttpStatus.OK) ; 
		} catch (Exception e) { 
 			   return new ResponseEntity<>(null , HttpStatus.NOT_FOUND); 

		} 
	}
   
	@PostMapping( consumes="application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public  Pancake  postPancake( @RequestBody Pancake pancake) { 
		 return  jdbcPancake.save(pancake); 
	}
}

