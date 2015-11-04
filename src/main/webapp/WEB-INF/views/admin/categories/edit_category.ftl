<@common.page>
  <div class="jumbotron">
    <div class="container">
      <form role="form" action="<@spring.url '/admin/categories/save'/>" method="POST">
      <@spring.formHiddenInput 'category.id' />
        <div class="form-group">
          <label for="name">Nazwa</label>
          <@spring.formInput 'category.name' 'id="name" class="form-control" placeholder="Nazwa" required' />
        </div>
        <div class="form-group">
          <label for="description">Opis</label>
          <@spring.formTextarea 'category.description' 'id="description" class="form-control" placeholder="Opis" required' />
        </div>
        <button type="submit" class="btn btn-default">Zapisz</button>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
      </form>
    </div>
  </div>
</@common.page> 