package mdx

import grails.gorm.transactions.Transactional
import org.springframework.http.HttpHeaders

class SecurityInterceptor {
    SecurityService securityService

    int order = 1

    SecurityInterceptor() {
        matchAll().excludes(controller: "security")
    }

    boolean before() {

        /*request["start_counter"] = System.currentTimeMillis()
        if (controllerName?.equals("security")) {
            return true
        }

        println "\ncontrollerName: $controllerName\n"*/

        if (controllerName?.endsWith("Api")) { // api controllers
            String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION)
            String token
            if (authHeader && authHeader.startsWith("Bearer ")) {
                token = authHeader.substring(7)
                def authenticated = securityService.verifyJWT(token)
                if (authenticated instanceof UserDto) {
                    request.setAttribute("user", authenticated)
                } else {
                    render status: 403, text: "Access Denied"
                    return false
                }
            }
            if (!token || !securityService.verifyJWT(token)) {
                render status: 403, text: "Access Denied"
                return false
            }
        } else { // web controllers
            if (session.username) {
                return true
            } else {
                redirect(controller: "security", action: "login")
                return false
            }

        }

        return true
    }

    boolean after() {
        
    }

    void afterView() {
        
    }

}