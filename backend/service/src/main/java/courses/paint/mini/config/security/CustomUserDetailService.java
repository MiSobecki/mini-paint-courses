package courses.paint.mini.config.security;

import courses.paint.mini.model.User;
import courses.paint.mini.usecase.user.GetUserByUsernameUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private final GetUserByUsernameUseCase getUserByUsernameUseCase;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = getUserByUsernameUseCase.execute(username);

        return org.springframework.security.core.userdetails.User.withUsername(user.getUsername())
                .password(user.getPassword())
                .authorities(getAuthorities(user))
                .build();
    }

    private Collection<GrantedAuthority> getAuthorities(User user) {
        return user.getRoles().stream()
                .map(x -> new SimpleGrantedAuthority(x.getName().toUpperCase()))
                .collect(Collectors.toList());
    }

}
