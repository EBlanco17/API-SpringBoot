package med.volt.api.infra.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import med.volt.api.domain.usuarios.UsuariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuariosRepository usuariosRepository;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //obtener token
        var authHeader = request.getHeader("Authorization");//.replace("Bearer ", "");

        if(authHeader != null) {
            var token = authHeader.replace("Bearer ", "");
            //validar token
            var nombreUsuario = tokenService.getSubject(token);
            if (nombreUsuario != null) {
                //obtener usuario
                var usuario = usuariosRepository.findByLogin(nombreUsuario);
                //crear autenticacion
                var autenticacion = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
                //agregar autenticacion al contexto
                SecurityContextHolder.getContext().setAuthentication(autenticacion);
            }
        }
        filterChain.doFilter(request, response);
    }
}
