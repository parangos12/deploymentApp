/**
 * 
 */
package com.apmanager.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apmanager.entity.Affiliate;
import com.apmanager.repository.AffiliatesRepository;
import com.apmanager.service.AffiliatesService;

/**
 * @author Pedro
 * @version 1.0 28/12/2022
 * Clase que implementa los metodos de logica de negocio de la interface AffiliatesService.
 */
@Service
public class AffiliatesServiceImpl implements AffiliatesService {
	/**
	 * Bean Repository de Spring inyectado para ejecutar el CRUD.
	 */
	
	@Autowired
	private AffiliatesRepository affiliatesRepository;
	
	@Override
	public List<Affiliate> consultarAffiliates() {
		
		List<Affiliate> affiliatesDataSource = StreamSupport.stream(
				this.affiliatesRepository.findAll().spliterator(), false)
				.collect(Collectors.toList());
		if(affiliatesDataSource.isEmpty()==true) {
			return null;
		} else {
		return affiliatesDataSource;}
	}

	@Override
	public Affiliate consultarAffiliate(Long id_affiliate) {
		Optional<Affiliate> optAffiliate= this.affiliatesRepository.findById(id_affiliate);
			return optAffiliate.get();
		}

	@Override
	public Affiliate guardarAffiliates(Affiliate affiliate) {
			System.out.println("OLEEEEE");
			return this.affiliatesRepository.save(affiliate);
		}
	

	@Override
	public Affiliate actualizarAffiliates(Affiliate affiliate) {
		if(consultarAffiliate(affiliate.getId_affiliate())!=null) {
			return this.affiliatesRepository.save(affiliate);
		}else {
			return null;
		}
	}

	@Override
	public String eliminarAffiliate(Long id_affiliate) {
		if(consultarAffiliate(id_affiliate)==null) {
			return "NO OK";
		}else {
			this.affiliatesRepository.deleteById(id_affiliate);
			return "OK";
		}
	}

}
