package es.ucm.fdi.iw.service;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import es.ucm.fdi.iw.model.User;
import es.ucm.fdi.iw.repository.UserRepository;

/**
 * Authenticates login attempts against a JPA database
 */
public class IwUserDetailsService implements UserDetailsService {

	private static Logger log = LogManager.getLogger(IwUserDetailsService.class);

    //private EntityManager entityManager;
    /*
    @PersistenceContext
    public void setEntityManager(EntityManager em){
        this.entityManager = em;
    }*/

	@Autowired
    private UserRepository userRepository;

    public UserDetails loadUserByUsername(String username){
    	try {
	        User u = userRepository.findByUsername(username.toLowerCase());
            if(u == null || u.isDeleted()){
                log.info("Usuario deshabilitado o no encontrado: " + username);
                throw new UsernameNotFoundException("Este usuario está deshabilitado o no existe");
            }
	        ArrayList<SimpleGrantedAuthority> roles = new ArrayList<>();
	        for (String r : u.getRoles().split("[,]")) {
	        	roles.add(new SimpleGrantedAuthority("ROLE_" + r));
		        log.info("Roles for " + username + " include " + roles.get(roles.size()-1));
	        }
	        return new org.springframework.security.core.userdetails.User(
	        		u.getUsername(), u.getPassword(), roles); 
	    } catch (Exception e) {
    		log.info("No such user: " + username + " (error = " + e.getMessage() + ")");
    		throw new UsernameNotFoundException(username);
    	}
    }
}