package grailstest

import grails.plugins.springsecurity.Secured
import grails.plugins.rest.client.RestBuilder

@Secured(['ROLE_USER'])
class RepoController {

    static defaultAction = 'show'

    static rest = new RestBuilder()

    def query(String username) { // TODO: add validation
        log.info "querying ${username}"
        def response = rest.get "https://api.github.com/users/${username}/repos"
        log.debug "status=${response.status}"
        def repos = response.status == 200 ? response.json : []
        render(view: "show", model: [repos: repos, username: username])
    }

    def show() {
        log.info "show, params=${params}"
        [repos: params.repos, username: params.username]
    }
}
