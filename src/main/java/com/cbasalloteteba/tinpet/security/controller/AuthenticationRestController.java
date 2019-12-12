package com.cbasalloteteba.tinpet.security.controller;
import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cbasalloteteba.tinpet.formbean.NewUserDto;
import com.cbasalloteteba.tinpet.formbean.RegisterFormBean;
import com.cbasalloteteba.tinpet.model.Authority;
import com.cbasalloteteba.tinpet.model.AuthorityName;
import com.cbasalloteteba.tinpet.model.User;
import com.cbasalloteteba.tinpet.repository.AuthorityRepository;
import com.cbasalloteteba.tinpet.security.JwtAuthenticationRequest;
import com.cbasalloteteba.tinpet.security.JwtTokenUtil;
import com.cbasalloteteba.tinpet.security.JwtUser;
import com.cbasalloteteba.tinpet.security.service.JwtAuthenticationResponse;
import com.cbasalloteteba.tinpet.service.UserService;

@RestController
public class AuthenticationRestController {

	@Value("${jwt.header}")
	private String tokenHeader;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	UserService userService;
	
	@Autowired
	@Qualifier("jwtUserDetailsService")
	private UserDetailsService userDetailsService;

	

	// @RequestMapping(value = "${jwt.route.authentication.path}", method =
	// RequestMethod.POST)
	//@CrossOrigin(origins = "*")
	//@PostMapping("${jwt.route.authentication.path}/login")
    @RequestMapping(value = "${jwt.route.authentication.path}", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtAuthenticationRequest authenticationRequest)
			throws AuthenticationException {

		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

		// Reload password post-security so we can generate the token
		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		final String token = jwtTokenUtil.generateToken(userDetails);

		/*User e = userService.findByUsername(authenticationRequest.getUsername());
		System.out.println(e.getAuthorities().size());
		String rol = buscarRol(e.getAuthorities());*/

		// Return the token
		return ResponseEntity.ok(new JwtAuthenticationResponse(token/*, e.getUsername(), rol, e.getEmail()*/));
	}
	
	//@CrossOrigin(origins = "*")
	//@PostMapping("${jwt.route.authentication.path}/register")
	//public ResponseEntity<?> register(@RequestBody RegisterFormBean r) throws AuthenticationException, Exceptions  {
    @PostMapping("/auth/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterFormBean r) throws Exceptions {
    		
		if (userService.checkUsernameRegistered(r.getUsername())) {
			throw new Exceptions("Username Exists");
		}
		
		User u =  userService.registrarUsuario(r);
    	authenticate(u.getUsername(), r.getPassword());

		final UserDetails userDetails = userDetailsService.loadUserByUsername(r.getUsername());
		final String token = jwtTokenUtil.generateToken(userDetails);
		String rol = buscarRol(u.getAuthorities());
		System.out.println("HOLA "+rol);
		//String rol = buscarRol(e.getAuthorities());
		//JwtAuthenticationResponse j = new JwtAuthenticationResponse(token, u.getUsername(), rol, u.getEmail());
		//return ResponseEntity.ok(j);
		//return ResponseEntity.status(HttpStatus.CREATED).body(j);
    	return ResponseEntity.ok(new NewUserDto(u.getId(), u.getFirstname(), u.getEmail(), token));

    }

    
    @PostMapping("/auth/login")
    public ResponseEntity<?> login(@RequestBody JwtAuthenticationRequest authenticationRequest) throws AuthenticationException{

		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

		// Reload password post-security so we can generate the token
		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		final String token = jwtTokenUtil.generateToken(userDetails);

		User e = userService.findByUsername(authenticationRequest.getUsername());
		String rol = buscarRol(e.getAuthorities());
		//System.out.println("HOLA "+rol);

		// Return the token
		return ResponseEntity.ok(new NewUserDto(e.getId(), e.getEmail(), e.getUsername(), token ));
	}
    
    public String buscarRol(List<Authority> lista) {
		boolean encontrado = false;
		int i = 0;

		while (!encontrado && i < lista.size()) {
			if (lista.get(i).getName() == AuthorityName.ROLE_ADMIN) {
				encontrado = true;
			} else {
				i++;
			}

		}

		if (encontrado) {
			return "ADMIN";
		} else {
			return "USER";
		}
	}
    
	@RequestMapping(value = "${jwt.route.authentication.refresh}", method = RequestMethod.GET)
	public ResponseEntity<?> refreshAndGetAuthenticationToken(HttpServletRequest request) {
		String authToken = request.getHeader(tokenHeader);
		final String token = authToken.substring(7);
		String username = jwtTokenUtil.getUsernameFromToken(token);
		JwtUser user = (JwtUser) userDetailsService.loadUserByUsername(username);

		String refreshedToken = jwtTokenUtil.refreshToken(token);
		return ResponseEntity.ok(new JwtAuthenticationResponse(refreshedToken));

	}

	@ExceptionHandler({ AuthenticationException.class })
	public ResponseEntity<String> handleAuthenticationException(AuthenticationException e) {
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
	}

	/**
	 * Authenticates the user. If something is wrong, an
	 * {@link AuthenticationException} will be thrown
	 */
	private void authenticate(String username, String password) {
		Objects.requireNonNull(username);
		Objects.requireNonNull(password);
		
		/*try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new AuthenticationException("User is disabled!", e);
		} catch (BadCredentialsException e) {
			throw new AuthenticationException("Bad credentials!", e);
		}*/
	}
}
