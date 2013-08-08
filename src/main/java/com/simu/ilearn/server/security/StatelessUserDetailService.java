package com.simu.ilearn.server.security;

import com.simu.ilearn.server.repos.UserRepo;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

import static com.simu.ilearn.server.repos.spec.UserSpec.emailIs;
import static com.simu.ilearn.server.repos.spec.UserSpec.usernameIs;
import static org.springframework.data.jpa.domain.Specifications.where;

@Component("userDetailsService")
public class StatelessUserDetailService implements UserDetailsService {
    @Inject
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        com.simu.ilearn.server.business.User user = userRepo.findOne(where(emailIs(login)).or(usernameIs(login)));

        if (user == null) {
            throw new UsernameNotFoundException("Bad credentials");
        } else {
            List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
            authorities.add(new SimpleGrantedAuthority(user.getAuthority().name()));
            return new User(user.getEmail(), user.getPassword(), authorities);
        }
    }
}
