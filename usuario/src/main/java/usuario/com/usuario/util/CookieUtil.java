package usuario.com.usuario.util;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.util.WebUtils;
@Component
public class CookieUtil {

    public Cookie gerarCookieJwt(UserDetails userDetails){
      String token =  new JwtUtil().gerarToken(userDetails);
        System.out.println(token);
      Cookie cookie = new Cookie("JWT", token);
      cookie.setPath("/");
      cookie.setMaxAge(300);
      return cookie;
    }

    public Cookie getCookie(HttpServletRequest request,
                            String name) throws Exception{
        Cookie cookie = WebUtils.getCookie(request, name);
        if(cookie != null){
            return cookie;
        }
        throw new Exception("Cookie não encontrado");
    }

    //nao era p ser necessario, mas serve para excluir um cookie
    public Cookie gerarCookieNull( ){
//        String token = new JwtUtil().gerarToken(userDetails);
        Cookie cookie = new Cookie("JWT", "");

        //O cookie fica disponivel na rota setada e  qualquer outra apos ela (no caso todas as rotas)
        cookie.setPath("/");
        //duração em segundos do cookie
        cookie.setMaxAge(0);
        return cookie;
    }

}
