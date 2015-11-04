<@common.page>
  <div id="main-container" class="container">
    <h2>Moje Kursy</h2>
    <nav class="navbar navbar-default">
      <div class="container-fluid">
      </div>
    </nav>
    <div id="courses">
      <div class="row">
        <#list courses as course>
            <div class="col-md-4">
              <div class="panel panel-default">
                <div class="panel-heading">
                  ${course.title}
                </div>
                <div class="panel-body">
                  <p>${course.description!}</p>
                  <a class="btn btn-info" href="<@spring.url '/courses/' />${course.id}"><span class="glyphicon glyphicon-upload"></span>&nbsp;Wejdź</a>
                </div>
              </div>
            </div>
        </#list>
      </div>
    </div>
  </div>
</@common.page>