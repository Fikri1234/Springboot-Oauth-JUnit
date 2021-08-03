/**
 * 
 */
package com.project.buku.Resource;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.inject.Singleton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import com.project.buku.DTO.BukuDTO;
import com.project.buku.Entity.Buku;
import com.project.buku.Service.BukuService;

import io.swagger.annotations.ApiOperation;

/**
 * @author Fikri
 *
 */

@RestController
@RequestMapping("/buku")
@Singleton
public class BukuResource {
	
	Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private BukuService bukuService;
	
	@Autowired
    private RequestMappingHandlerMapping requestMappingHandlerMapping;

    @GetMapping("/endpoints/all")
    public ResponseEntity<List<String>> getEndpoints() {
        return new ResponseEntity<>(
                requestMappingHandlerMapping
                        .getHandlerMethods()
                        .keySet()
                        .stream()
                        .map(RequestMappingInfo::toString)
                        .collect(Collectors.toList()),
                HttpStatus.OK
        );
    }
	
	@ApiOperation(value = "Retrieve data by Id")
	@GetMapping("/{id}")
	public ResponseEntity<?> retrieveById(@PathVariable("id") Long id) {
		
		BukuDTO dto = new BukuDTO();
		Optional<Buku> buku = bukuService.findById(id);
		
		if (buku.isPresent()) {
			BeanUtils.copyProperties(buku.get(), dto);
			log.info("data buku: {}", dto);
			return new ResponseEntity<>(dto, HttpStatus.OK);
		} else {
			log.error("id not found: ", id);
			return new ResponseEntity<>("Unable to find id " + id, HttpStatus.NOT_FOUND);
		}
	}
	
	@ApiOperation(value = "Retrieve all data")
	@GetMapping("/")
	public ResponseEntity<?> retrieveAll() {
		
		List<BukuDTO> dtos = new ArrayList<BukuDTO>();
		List<Buku> entities = bukuService.findAll();
		
		if (entities.size() > 0) {
			dtos = entities.stream().map(BukuDTO::new).collect(Collectors.toList());
			return new ResponseEntity<>(dtos, HttpStatus.OK);
		}
		
		return new ResponseEntity<>(dtos, HttpStatus.OK);
	}
	
	@ApiOperation(value = "Insert data")
	@PostMapping("/")
    public ResponseEntity<?> postBuku(@RequestBody BukuDTO dto) {
        log.info("REST API for insert postBuku: {}", dto);
        
        try {
        	Buku entity = new Buku();
 			BeanUtils.copyProperties(dto, entity);
 			
 			bukuService.save(entity);
 			return new ResponseEntity<>("SUCCESS", HttpStatus.CREATED);
        } catch (Exception e) {
			// TODO: handle exception
        	e.printStackTrace();
        	return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
		}
    }
	
	@ApiOperation(value = "Update data")
	@PutMapping("/")
    public ResponseEntity<?> putBuku(@RequestBody BukuDTO dto) {
        log.info("REST API for update putBuku");
        
        Optional<Buku> opt = bukuService.findById(dto.getId());
        
        if (!opt.isPresent()) {
 			log.error("Unable to update. Buku with id {} not found",dto.getId());
 			return new ResponseEntity<>("Unable to update. Buku with id " +dto.getId()+ " not found", HttpStatus.NOT_FOUND);
 		}
        
        try {
        	Buku entity = new Buku();
 			BeanUtils.copyProperties(dto, entity);
 			
 			bukuService.update(entity);
 			return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
        } catch (Exception e) {
			// TODO: handle exception
        	e.printStackTrace();
        	return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
		}
    }
	
	@ApiOperation(value = "Delete data by Id")
	@DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBukuById(@PathVariable("id") Long id) {
        log.info("REST API for delete deleteBukuById by id : {}", id);
        
        Optional<Buku> opt = bukuService.findById(id);
        
        if (!opt.isPresent()) {
 			log.error("Unable to delete.Buku with id {} not found",id);
 			return new ResponseEntity<>("Unable to delete.Buku with id " +id+ " not found", HttpStatus.NOT_FOUND);
 		}
 		
        try {
        	bukuService.deleteById(id);
        	return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
        } catch (Exception e) {
        	e.printStackTrace();
        	return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
	
	@ApiOperation(value = "Delete all data")
	@DeleteMapping("/")
    public ResponseEntity<?> deleteBukuAll() {
        log.info("REST API for delete All : {}");
 		
        try {
        	bukuService.deleteAll();
        	return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
        } catch (Exception e) {
        	e.printStackTrace();
        	return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
	
	@ApiOperation(value = "Retrieve all paging data")
	@GetMapping("/page/{page}/size/{size}")
	public ResponseEntity<?> retrieveAll(@PathVariable("page") int page, @PathVariable("size") int size) {
		
		List<BukuDTO> dtos = new ArrayList<BukuDTO>();
		Page<Buku> pages = bukuService.findPaginated(page, size);
		
		if (pages.hasContent()) {
			Page<BukuDTO> dtoPage = pages.map(new Function<Buku, BukuDTO>() {
			    @Override
			    public BukuDTO apply(Buku entity) {
			    	BukuDTO dto = new BukuDTO(entity);
			        return dto;
			    }
	        });
			return new ResponseEntity<>(dtoPage.getContent(), HttpStatus.OK);
		}
		
		return new ResponseEntity<>(dtos, HttpStatus.OK);
	}

}
