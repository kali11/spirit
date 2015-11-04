<@common.page>
  <div class="jumbotron">
    <div class="container">
      <h1>Witaj na pltaformie E-learningowej!</h1>
      <form role="form" action="" method="POST">
        <div class="form-group">
          <label for="login">Login</label>
          <input class="form-control" id="login" name="login" placeholder="Login">
        </div>
        <div class="form-group">
          <label for="password">Hasło</label>
          <input type="password" class="form-control" id="password" name="password" placeholder="Hasło">
        </div>
        <button type="submit" class="btn btn-success">Zaloguj</button>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
      </form>
    </div>
  </div>
</@common.page> 