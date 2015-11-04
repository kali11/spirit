<@common.page>
  <div class="jumbotron">
    <div class="container">
      <form role="form" action="<@spring.url '/admin/users/save'/>" method="POST">
      <@spring.formHiddenInput 'user.id' />
        <div class="form-group">
          <label for="login">Login</label>
          <@spring.formInput 'user.login' 'id="login" class="form-control" placeholder="Login" required' />
        </div>
        <div class="form-group">
          <label for="password">Hasło</label>
          <@spring.formPasswordInput 'user.password' 'id="password" class="form-control" placeholder="Hasło" required' />
        </div>
        <div class="form-group">
          <label for="mail">E-mail</label>
          <@spring.formInput 'user.mail' 'type="email" class="form-control" id="mail" placeholder="E-mail" required' />
        </div>
        <div class="form-group">
          <label for="first_name">Imię</label>
          <@spring.formInput 'user.firstName' 'class="form-control" id="first_name" placeholder="Imię"' />
        </div>
        <div class="form-group">
          <label for="lastName">Nazwisko</label>
          <@spring.formInput 'user.lastName' 'class="form-control" id="lastName" placeholder="Nazwisko"' />
        </div>
        <div class="form-group">
          <label for="active">Aktywny</label>
          <@spring.formCheckbox 'user.active' 'class="form-control" id="active"' />
        </div>
        <div class="form-group">
          <label for="role">Rola</label>
          <select name="roleId" class="form-control" id="role">
            <#list roles?keys as id>
                <option value=${id}>${roles[id]}</option>
            </#list>
          </select>
        </div>
        <button type="submit" class="btn btn-default">Zapisz</button>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
      </form>
    </div>
  </div>
</@common.page> 