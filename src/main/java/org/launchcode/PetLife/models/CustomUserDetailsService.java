package org.launchcode.PetLife.models;

import org.launchcode.PetLife.models.data.RoleRepository;
import org.launchcode.PetLife.models.data.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class CustomUserDetailsService implements UserDetailsService {

        @Autowired
        private UserRepository userRepo;

        @Autowired
        private RoleRepository roleRepository;

        @Override
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            User user = userRepo.findByEmail(username);
            if (user == null) {
                return new org.springframework.security.core.userdetails.User(
                        " ", " ", true, true, true, true,
                        getAuthorities(Arrays.asList(
                                roleRepository.findByName("ROLE_USER"))));
            }

            return new org.springframework.security.core.userdetails.User(
                    user.getEmail(), user.getPassword(), user.isEnabled(), true, true,
                    true, getAuthorities(user.getRoles()));

        }
            private Collection<? extends GrantedAuthority> getAuthorities (
                    Collection< Role > roles) {

                return getGrantedAuthorities(getPrivileges(roles));
            }

            private List<String> getPrivileges (Collection < Role > roles) {

                List<String> privileges = new ArrayList<>();
                List<Privilege> collection = new ArrayList<>();
                for (Role role : roles) {
                    privileges.add(role.getName());
                    collection.addAll(role.getPrivileges());
                }
                for (Privilege item : collection) {
                    privileges.add(item.getName());
                }
                return privileges;
            }

            private List<GrantedAuthority> getGrantedAuthorities (List < String > privileges) {
                List<GrantedAuthority> authorities = new ArrayList<>();
                for (String privilege : privileges) {
                    authorities.add(new SimpleGrantedAuthority(privilege));
                }
                return authorities;
            }




    @Bean
    public RoleHierarchy roleHierarchy() {
        RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
        String hierarchy = "ROLE_ADMIN > ROLE_STAFF \n ROLE_STAFF > ROLE_USER";
        roleHierarchy.setHierarchy(hierarchy);
        return roleHierarchy;
    }

    @Bean
    public DefaultWebSecurityExpressionHandler webSecurityExpressionHandler() {
        DefaultWebSecurityExpressionHandler expressionHandler = new DefaultWebSecurityExpressionHandler();
        expressionHandler.setRoleHierarchy(roleHierarchy());
        return expressionHandler;
    }
        }





