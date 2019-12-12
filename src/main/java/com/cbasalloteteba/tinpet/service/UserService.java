package com.cbasalloteteba.tinpet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cbasalloteteba.tinpet.formbean.RegisterFormBean;
import com.cbasalloteteba.tinpet.model.Authority;
import com.cbasalloteteba.tinpet.model.AuthorityName;
import com.cbasalloteteba.tinpet.model.User;
import com.cbasalloteteba.tinpet.repository.AuthorityRepository;
import com.cbasalloteteba.tinpet.repository.BaseService;
import com.cbasalloteteba.tinpet.repository.UserRepository;

@Service
public class UserService extends BaseService<User, Long, UserRepository> {
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private AuthorityRepository authorityRepository;

	
	/*public User findOne(Long id) {
		return repositorio.findById(id).orElse(null);
	}

	public List<User> findAll() {
		return repositorio.findAll();
	}

	public User save(User u) {
		return repositorio.save(u);
	}

	public User edit(User u) {
		return repositorio.save(u);
	}*/

	/*public User delete(User u) {
		User deleteU = repositorio.findById(u.getId()).orElse(null);
		if (deleteU != null)
			repositorio.delete(u);

		return deleteU;
	}*/
	
	public User registrarUsuario(RegisterFormBean usuario) {
		System.out.println(usuario.getFirstname());
		User newUser = save(new User( usuario.getEmail(),usuario.getFirstname(), usuario.getLastname(), encoder.encode(usuario.getPassword()),usuario.getPhone(),usuario.getUsername()));
		/*for (int i = 0; i < newUser.getAuthorities().size(); ++i) {
			System.out.println("HOLA ADIOOS "+newUser.getAuthorities().get(i).getName());
		}*/
		Authority authority = authorityRepository.findFirstByName(AuthorityName.ROLE_USER);
		//System.out.println(authority.getName()+" AQUI");

		newUser.getAuthorities().add(authority);
		newUser = edit(newUser);
		
		/*for (int i = 0; i < newUser.getAuthorities().size(); ++i) {
			System.out.println("HOLA ADIOOS "+newUser.getAuthorities().get(i).getName());
    		}*/
		
		return newUser;
	}
	
	public boolean checkUsernameRegistered(String username) {
		boolean verify = false;
		if (findByUsername(username) != null) {
			verify = true;
		}
		return verify;
	}
	
	public User findByUsername(String username) {
		return repositorio.findByUsername(username);
	}
	

}
