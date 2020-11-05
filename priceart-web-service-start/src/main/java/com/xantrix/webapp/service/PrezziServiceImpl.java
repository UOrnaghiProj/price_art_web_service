package com.xantrix.webapp.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import com.xantrix.webapp.entity.DettListini;
import com.xantrix.webapp.repository.PrezziRepository;

@Service
@Transactional
public class PrezziServiceImpl implements PrezziService
{
	@Autowired
	PrezziRepository prezziRepository;

	@Override
	@Cacheable(value = "prezzo",key = "#CodArt.concat('-').concat(#Listino)", sync = true)
	public DettListini SelPrezzo(String CodArt, String Listino)
	{
		return prezziRepository.SelByCodArtAndList(CodArt, Listino);
	}
	
	@Override
	@Caching(evict = { 
			@CacheEvict(cacheNames="prezzo",key = "#CodArt.concat('-').concat(#IdList)")
			})
	public void DelPrezzo(String CodArt, String IdList) 
	{
		prezziRepository.DelRowDettList(CodArt, IdList);
	}

}
