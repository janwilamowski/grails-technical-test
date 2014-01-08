<title>GitHub Public Repositories</title>

<h3>Repository listing</h3>

<g:form name="repoListForm" action="query">
    <label for="username">user: </label><g:textField name="username" value="${username}" />
    <g:submitButton name="repoListSubmit" value="Query"/>
</g:form>

<g:if test="${repos}">
<table>
    <tr><th>name</th><th>watchers</th></tr>
    <g:each in="${repos}" var="repo">
    <tr><td>${repo?.name}</td><td>${repo?.watchers}</td></tr>
    </g:each>
</table>
</g:if>
<g:elseif test="${username}">
<p>No public repositories found.</p>
</g:elseif>
