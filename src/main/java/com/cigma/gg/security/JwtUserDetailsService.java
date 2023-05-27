package com.cigma.gg.security;

import java.util.ArrayList;
import java.util.Date;
import java.util.NoSuchElementException;

import com.cigma.gg.dao.backoffice.IUtilisateurDao;
import com.cigma.gg.entity.backoffice.UtilisateurEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private IUtilisateurDao utilisateurDao;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UtilisateurEntity utilisateurEntity = utilisateurDao.findByLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
        return new User(utilisateurEntity.getLogin(), utilisateurEntity.getMot_de_passe(),
                new ArrayList<>());
    }

    /**
     * @param username
     * @param password
     * @return
     */
    public UserDetails authenticateUser(String username, String password) {
        UserDetails userDetails = loadUserByUsername(username);
        if (userDetails != null) {
            boolean isAuth = bcryptEncoder.matches(password, userDetails.getPassword());
            if (!isAuth) {
                throw new BadCredentialsException("Username/Password incorrect");
            }
        }
        return userDetails;
    }
}
