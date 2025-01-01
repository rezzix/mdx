package mdx

import grails.converters.JSON

class SecurityController {
    SecurityService securityService

    def auth() { // API authentification
        def params = request.JSON

        if (!params.username || !params.password) {
            response.status = 403
            render([error: 'bad paramaters'] as JSON)
            return
        }

        String token = securityService.auth(params.username, params.password)

        if (token) {
            render([token: token] as JSON)
        } else {
            response.status = 403
            render([error: 'Unauthorized'] as JSON)
        }
    }

    def login() { // web authentification
        if (session.username) {
            redirect(controller: "index")
            return
        } else {
            if (params.username) {
                try {
                    def warning = securityService.login(params.username, params.password)
                    session.loggedin = true
                    session.username = params.username
                    session.userid = AppUser.findByUsername(params.username).id
                    flash.error = warning
                    redirect(controller: "index", action: "index")
                    return

                } catch (Exception e) {
                    e.printStackTrace()
                    flash.error = e.getMessage()
                    render(view: "auth")
                    return
                }
            } else {
                render(view: "auth")
                return
            }
        }

    }


    def logout() {
        session.invalidate()
        flash.message = "You have been logged out successfully."
        redirect(controller: "auth", action: "login")
    }

}
