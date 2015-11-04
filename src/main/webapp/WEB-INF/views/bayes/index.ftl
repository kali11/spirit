<@common.page styles=['/resources/css/bootstrap-multiselect.css']>

<div id="main-container" class="container">
    <form role="form" action="<@spring.url '/bayes/result' />" method="POST">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
        <div class="form-group">
            <label for="lessons">Lekcje:</label>
            <select name="lesson" class="multiselect" id="lessons">
                <#list lessons as lesson>
                    <option value=${lesson.id}>${lesson.title}</option>
                </#list>
            </select>
        </div>
        <div class="form-group">
            <label for="users">Użytkownicy:</label>
            <select name="user" class="multiselect" id="users">
                <#list users as user>
                    <option value=${user.id}>${user.login}</option>
                </#list>
            </select>
        </div>
        <button type="submit" class="btn btn-primary">Sprawdź</button>

        <#if user??>
            <h4>
                <p>Wyniki dla użytkownika "${user.login}" i dla lekcji "${current.title}":</p>
                <p>Lekcja poprzednia: "${parent.title}" </p>
                <p>Ocena z lekcji poprzedniej: "${note}" </p>
                <p>Przewidywane prawdopodobieństwa ocen z lekcji:</p>
            </h4>
        </#if>
        <h4><p>${result!}</p></h4>

    </form>
</div>
<@common.multiselect />
</@common.page>
