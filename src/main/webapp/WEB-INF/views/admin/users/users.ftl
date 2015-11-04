<@common.page>
    <div class="container">
      Użytkownicy:
      <table class="table">
        <thead>
          <tr>
            <td>Login</td>
            <td>E-mail</td>
            <td>Imię</td>
            <td>Nazwisko</td>
            <td>Aktywny</td>
            <td>Rola</td>
            <td>Operacje</td>
          </tr>
        </thead>
        <tbody>
          <#list users as user>
            <tr>
              <td>${user.login}</td>
              <td>${user.mail}</td>
              <td>${user.firstName}</td>
              <td>${user.lastName}</td>
              <td>${user.active?string('tak', 'nie')}</td>
              <td>${user.role.name}</td>
              <td>
                <a class="btn btn-primary btn-xs" href="<@spring.url '/admin/users/edit/' />${user.id}">Edytuj</a>
                <a class="btn btn-danger btn-xs confirm" data-placement="right" data-title="Czy na pewno?" data-btnOkLabel="Usuń" data-btnCancelLabel="Anuluj" data-href="<@spring.url '/admin/users/delete/' />${user.id}">Usuń</a>
              </td>
            </tr>
          </#list>
        </tbody>
      </table>
      <a class="btn btn-success" href="<@spring.url '/admin/users/add'/>">Dodaj użytkownika</a>
    </div>

  <@common.confirmation />
</@common.page>