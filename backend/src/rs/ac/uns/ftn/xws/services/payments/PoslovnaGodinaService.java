package rs.ac.uns.ftn.xws.services.payments;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import model.Drzava;
import model.PoslovnaGodina;

import org.apache.log4j.Logger;

import rs.ac.uns.ftn.xws.util.Authenticate;
import daoBean.PoslovnaGodinaDaoLocal;

@Path("/poslovna-godina")
public class PoslovnaGodinaService {
	private static Logger log = Logger.getLogger(PoslovnaGodinaService.class);
	
	@EJB
	private PoslovnaGodinaDaoLocal drzavaDao;
	 
	@GET 
    @Produces(MediaType.APPLICATION_JSON)
//	@Authenticate
    public List<PoslovnaGodina> findAll() {
		List<PoslovnaGodina> retVal = null;
		try {
			retVal = drzavaDao.findAll();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return retVal;
    }
	
	@GET 
	@Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
	@Authenticate
    public PoslovnaGodina findById(@PathParam("id") String id) {
		PoslovnaGodina retVal = null;
		try {
			retVal = drzavaDao.findById(Integer.parseInt(id));
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return retVal;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	@Authenticate
    public PoslovnaGodina create(PoslovnaGodina entity) {
		
		PoslovnaGodina retVal = null;
		try {
			
			retVal = drzavaDao.persist(entity);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return retVal;
    }
    
    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	@Authenticate
    public PoslovnaGodina update(PoslovnaGodina entity) {
    	log.info("PUT");
    	PoslovnaGodina retVal = null;
        try {
        	retVal = drzavaDao.merge(entity);
        } catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return retVal;
    }
    
    @DELETE 
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
	@Authenticate
	public void removeItem(@PathParam("id") Integer id) {
    	try {
    		drzavaDao.remove(id);
        } catch (Exception e) {
        	log.error(e.getMessage(), e);
        }
    	
    }
}
