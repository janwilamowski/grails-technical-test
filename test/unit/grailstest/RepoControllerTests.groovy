package grailstest

import grails.test.mixin.*
import org.junit.*
import grails.plugins.rest.client.RestBuilder

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(RepoController)
class RepoControllerTests {

    void testShow() {
        params.username = "carl"
        def model = controller.show()

        assert model.username == "carl"
    }

    void testQuerySuccess() {
        def mockRest = mockFor(RestBuilder)
        mockRest.demand.get { String url ->
            assert url == "https://api.github.com/users/carl/repos"
            [status: 200, json: [myRepo: 42]]
        }
        controller.rest = mockRest.createMock()

        controller.query("carl")

        assert view == "/repo/show"
        assert model.repos.myRepo == 42
    }

    void testQueryFailure() {
        def mockRest = mockFor(RestBuilder)
        mockRest.demand.get { String url ->
            assert url == "https://api.github.com/users/carl/repos"
            [status: 404, json: []]
        }
        controller.rest = mockRest.createMock()

        controller.query("carl")

        assert view == "/repo/show"
        assert model.repos == []
    }
}
