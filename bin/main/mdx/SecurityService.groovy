package mdx

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.exceptions.JWTVerificationException
import com.auth0.jwt.interfaces.DecodedJWT
import grails.gorm.transactions.Transactional

class SecurityService {
    static String secureKey = "poiuy6azert";

    String generateJWT(String username, Long id) {
        // Create a token using the provided username
        Algorithm algorithm = Algorithm.HMAC256(secureKey)
        Date expirationDate = new Date(System.currentTimeMillis() + (60 * 60 * 1000))// 60 minutes
        String token = JWT.create()
                .withClaim('username', username)
                .withClaim('userId', id)
                .withExpiresAt(expirationDate)
                .sign(algorithm)
        return token
    }

    def verifyJWT(String token) {
        try {
            //println "virifying token: " + token
            Algorithm algorithm = Algorithm.HMAC256(secureKey)
            DecodedJWT jwt = JWT.require(algorithm).build().verify(token)

            //println "jwt: $jwt , claims $jwt.claims"
            if (jwt && jwt.claims.containsKey('userId')) {
                return new UserDto(jwt.getClaim('userId').asLong(), jwt.getClaim('username').asString())
            } else {
                return null
            }
        } catch (JWTVerificationException exception) {
            return null // Token verification failed
        }
    }

    def encodePassword(String s) {
        return s.encodeAsSHA256()
    }

    String auth(String username, String password) throws SecurityException {
        AppUser user = AppUser.findByUsername(username)
        if (user && user.password.equals(password.encodeAsSHA256()) && user.enabled) {
            return generateJWT(user.username, user.id)
        }
        return null
    }

    
    String login(String username, String password) throws SecurityException {
        AppUser user = AppUser.findByUsername(username)
        def warning = ""
        org.grails.web.servlet.mvc.GrailsWebRequest request = org.springframework.web.context.request.RequestContextHolder.currentRequestAttributes()
        grails.web.servlet.mvc.GrailsHttpSession session = request.session

        if (password == username) {
            warning = "Default password should be changed"
        }

        //if (username.equals("admin")) {
        if (user && (password.encodeAsSHA256() == user.password) && user.enabled) {

            session.user = user.username
            return warning

        } else {
            throw new SecurityException("password incorrect ou utilisateur désactivé")
        }
    }

}