/**
 * 
 */
package com.apmanager.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.apmanager.entity.Affiliate;
import com.apmanager.service.AffiliatesService;

/**
 * @author Pedro
 * @version 1.0 28/12/2022
 * Controlador que manipula el flujo de los servicios REST del microservicio TESTS.
 */
@RestController
@RequestMapping("api/controller/affiliates")
public class AffiliatesControllers {
	
	@Autowired
	private AffiliatesService affiliatesServiceImpl;
	
	@GetMapping
	@RequestMapping(value="",method = RequestMethod.GET)
	public ResponseEntity<?> consultarAffiliates() {
		List<Affiliate> affiliatesConsultados=this.affiliatesServiceImpl.consultarAffiliates();
		if(affiliatesConsultados.isEmpty()) {
			return new ResponseEntity<>(affiliatesConsultados, HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<>(affiliatesConsultados, HttpStatus.OK);
			}
	}
	
	@GetMapping
	@RequestMapping(value="/{id_affiliate}",method = RequestMethod.GET)
	public ResponseEntity<Affiliate> consultarAffiliate(@PathVariable Long id_affiliate) {
		Affiliate response=affiliatesServiceImpl.consultarAffiliate(id_affiliate);
		
		if(response==null) {
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}else {
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
	}
	
	public Integer number(String s) {
		try {
			double n_converted=Double.parseDouble(s);
			return (int) (n_converted-n_converted);
		}catch(Exception e) {
			return 1;
		}
	}
	@PostMapping
	@RequestMapping(value="",method = RequestMethod.POST)
	public ResponseEntity<?> guardarAffiliate(@RequestBody Affiliate affiliate) {
		if(affiliate.getName().isEmpty() || affiliate.getName().isEmpty() || affiliate.getAge()==0 || affiliate.getAge()<0) {
				return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);}
		if((number(affiliate.getName())+number(affiliate.getMail())==2) && affiliate.getAge()>=0){
			Affiliate affiliateGuardado=this.affiliatesServiceImpl.guardarAffiliates(affiliate);
			return ResponseEntity.status(HttpStatus.CREATED).body(affiliateGuardado);
		}
		else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);}
	}
	
	@PutMapping
	@RequestMapping(value="",method = RequestMethod.PUT)
	public ResponseEntity<Affiliate> actualizarAffiliate(@RequestBody Affiliate affiliate) {
		if(this.affiliatesServiceImpl.actualizarAffiliates(affiliate)==null  || affiliate.getName().isEmpty() || affiliate.getMail().isEmpty() || affiliate.getAge()<0 ||(number(affiliate.getName())+number(affiliate.getMail())!=2) || number(String.valueOf(affiliate.getAge()))==1) 
		{
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		try {
			Affiliate affiliateActualizado=this.affiliatesServiceImpl.actualizarAffiliates(affiliate);
			return ResponseEntity.status(HttpStatus.CREATED).body(affiliateActualizado);
		}catch(Exception e) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);}
	}
	
	
	@DeleteMapping
	@RequestMapping(value="/{id_affiliate}",method = RequestMethod.DELETE)
	public ResponseEntity<String> eliminarAffiliate(@PathVariable Long id_affiliate) {
		String resp=this.affiliatesServiceImpl.eliminarAffiliate(id_affiliate);
		if("OK".equalsIgnoreCase(resp)) {
			 return new ResponseEntity<>(null, HttpStatus.OK);
		}else {
			 return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}
	}
	

	
	
	
	
	

}
