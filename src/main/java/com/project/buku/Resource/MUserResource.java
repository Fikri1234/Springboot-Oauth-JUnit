/**
 * 
 */
package com.project.buku.Resource;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.project.buku.DTO.MUserDTO;
import com.project.buku.Entity.MUser;
import com.project.buku.Service.MUserService;

import io.swagger.annotations.ApiOperation;

/**
 * @author Fikri
 *
 */
@Component
@Path("/user")
public class MUserResource {
	
	Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	MUserService mUserService;
	
	@ApiOperation(value = "Retrieve user data by Id")
	@GET
	@Path("/{id}")
	public Response retrieveById(@PathParam("id") Long id) {
		
		MUserDTO dto = new MUserDTO();
		Optional<MUser> mUser = mUserService.findById(id);
		
		if (mUser.isPresent()) {
			BeanUtils.copyProperties(mUser.get(), dto);
			log.info("data user: {}", dto);
			return Response.status(200).entity(dto).build();
		} else {
			log.error("id not found: ", id);
			return Response.status(404).entity("Unable to find id " + id).build();
		}
	}
	
	@ApiOperation(value = "Retrievea all user data")
	@GET
	@Path("/")
	public Response retrieveAll() {
		
		List<MUserDTO> dtos = new ArrayList<MUserDTO>();
		List<MUser> entities = mUserService.findAll();
		
		if (entities.size() > 0) {
			dtos = entities.stream().map(MUserDTO::new).collect(Collectors.toList());
		}
		
		return Response.status(200).entity(dtos).build();
	}
	
	@ApiOperation(value = "Insert user data")
	@POST
	
    public Response postMUser(MUserDTO dto) {
        log.info("REST API for insert postMUser: {}", dto);
        
        try {
        	MUser entity = new MUser();
 			BeanUtils.copyProperties(dto, entity);
 			
 			mUserService.save(entity);
 			return Response.status(201).entity("SUCCESS").build();
        } catch (Exception e) {
			// TODO: handle exception
        	e.printStackTrace();
        	return Response.status(409).entity(e.getMessage()).build();
		}
    }
	
	@ApiOperation(value = "Updateuser data")
	@PUT
	
    public Response putMUser(MUserDTO dto) {
        log.info("REST API for update putMUser");
        
        Optional<MUser> opt = mUserService.findById(dto.getId());
        
        if (!opt.isPresent()) {
 			log.error("Unable to update. MUser with id {} not found",dto.getId());
 			return Response.status(404).entity("Unable to update. MUser with id " +dto.getId()+ " not found").build();
 		}
        
        try {
        	MUser entity = new MUser();
 			BeanUtils.copyProperties(dto, entity);
 			
 			mUserService.update(entity);
 			return Response.status(200).entity("SUCCESS").build();
        } catch (Exception e) {
			// TODO: handle exception
        	e.printStackTrace();
        	return Response.status(409).entity(e.getMessage()).build();
		}
    }
	
	@ApiOperation(value = "Delete user data by Id")
	@DELETE
	@Path("/{id}")
    public Response deleteMUserById(@PathParam("id") Long id) {
        log.info("REST API for delete delete MUserById by id : {}", id);
        
        Optional<MUser> opt = mUserService.findById(id);
        
        if (!opt.isPresent()) {
 			log.error("Unable to delete.MUser with id {} not found",id);
 			return Response.status(404).entity("Unable to delete.MUser with id " +id+ " not found").build();
 		}
 		
        try {
        	mUserService.deleteById(id);
        	return Response.status(200).entity("SUCCESS").build();
        } catch (Exception e) {
        	e.printStackTrace();
        	return Response.status(500).entity(e.getMessage()).build();
        }
    }
	
	@ApiOperation(value = "Delete all user data")
	@DELETE
	
    public Response deleteMUserAll(@PathParam("id") Long id) {
        log.info("REST API for delete deleteMUserAll : {}", id);
 		
        try {
        	mUserService.deleteAll();
        	return Response.status(200).entity("SUCCESS").build();
        } catch (Exception e) {
        	e.printStackTrace();
        	return Response.status(500).entity(e.getMessage()).build();
        }
    }

}
