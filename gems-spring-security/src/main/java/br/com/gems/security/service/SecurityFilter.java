package br.com.gems.security.service;

import br.com.gems.exception.SecurityException;
import br.com.gems.security.repository.UserSecurityRepository;
import br.com.gems.security.util.JwtClaimEnum;
import br.com.gems.utils.ObjectUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class SecurityFilter extends OncePerRequestFilter {

    private final TokenService tokenService;
    private final UserSecurityRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var token = getToken(request);

        if(!ObjectUtil.isNullOrEmpty(token)){
            setSecurityContextByToken(token);
        }

        filterChain.doFilter(request, response);
    }

    private void setSecurityContextByToken(String token) {
        var userId = tokenService.getUserIdFromToken(token);
        var user = userRepository.findById(userId).orElseThrow(() -> new SecurityException("Usuário associado ao token não foi encontrado!"));

        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken(user.getUsername(), null, getRolesByDecodedToken(token))
        );
    }

    private List<SimpleGrantedAuthority> getRolesByDecodedToken(String token){
        DecodedJWT decodedJWT = JWT.decode(token);
        List<String> roles = decodedJWT.getClaim( JwtClaimEnum.ROLES.getClaimCode() ).asList(String.class);

        return roles.stream()
                .map(role -> new SimpleGrantedAuthority( "ROLE_" + role.toUpperCase()))
                .toList();
    }


    private String getToken(HttpServletRequest request){
        var authorizationHeader = request.getHeader("Authorization");

        if(authorizationHeader == null){
            return null;
        }

        return authorizationHeader.replace("Bearer ", "");
    }

}
