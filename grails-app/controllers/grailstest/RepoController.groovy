package grailstest


import grails.plugins.springsecurity.Secured

@Secured(['ROLE_USER'])
class RepoController {

    def index() { }
}

