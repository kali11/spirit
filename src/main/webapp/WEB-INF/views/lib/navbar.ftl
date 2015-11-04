<#macro courseNavbar>
<nav class="navbar navbar-default">
      <div class="container-fluid">
        <div class="btn-group">
          <button type="button" class="btn btn-default navbar-btn"><span class="glyphicon glyphicon-share-alt"></span>&nbsp;Wybierz moduł</button>
          <button type="button" class="btn btn-default dropdown-toggle navbar-btn" data-toggle="dropdown">
            <span class="caret"></span>
          </button>
          <ul class="dropdown-menu">
            <#list course.modules as module>
              <li><a href="<@spring.url '/modules/' + module.id />">${module.title}</a></li>
            </#list>
          </ul>
        </div>
        
        <#if module??>
          <a class="btn btn-warning" href="<@spring.url '/modules/edit/'+module.id />"><span class="glyphicon glyphicon-edit"></span>&nbsp;Edytuj moduł</a>
          <!--<div class="btn-group navbar-btn">
              <button type="button" class="btn btn-warning dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
                Zarządzaj modułem<span class="caret"></span>
              </button>
              <ul class="dropdown-menu" role="menu">
                <li><a href="<@spring.url '/modules/edit/'+module.id />"><span class="glyphicon glyphicon-pencil"></span>&nbsp;Edytuj moduł</a></li>
                <li class="divider"></li>
                <li><a href="<@spring.url '/modules/edit/'+module.id />"><span class="glyphicon glyphicon-remove"></span>&nbsp;Usuń moduł</a></li>
              </ul>
          </div>-->
        <#else>
          <a class="btn btn-warning" href="<@spring.url '/courses/edit/'+course.id />"><span class="glyphicon glyphicon-edit"></span>&nbsp;Edytuj kurs</a>
          <!--
          <div class="btn-group navbar-btn">
              <button type="button" class="btn btn-warning dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
                Zarządzaj kursem<span class="caret"></span>
              </button>
              <ul class="dropdown-menu" role="menu">
                <li><a href="<@spring.url '/courses/edit/'+course.id />"><span class="glyphicon glyphicon-pencil"></span>&nbsp;Edytuj kurs</a></li>
                <li><a href="<@spring.url '/modules/add?courseId='+course.id />"><span class="glyphicon glyphicon-plus"></span>&nbsp;Dodaj moduł</a></li>
                <li class="divider"></li>
                <li><a href="<@spring.url '/courses/edit/'+course.id />"><span class="glyphicon glyphicon-remove"></span>&nbsp;Usuń kurs</a></li>
              </ul>
          </div>-->
        </#if>
        
        <div class="btn-group navbar-right navbar-btn">
          <a class="btn btn-default" href="<@spring.url '/courses/'+course.id />"><span class="glyphicon glyphicon-home"></span>&nbsp;Główna strona kursu</a>
          <a class="btn btn-default" href=""><span class="glyphicon glyphicon-bookmark"></span>&nbsp;O kursie</a>
          <a class="btn btn-default"><span class="glyphicon glyphicon-calendar"></span>&nbsp;Kalendarz</a>
          <a class="btn btn-default"><span class="glyphicon glyphicon-stats"></span>&nbsp;Oceny</a>
        </div>
        <div class="navbar-left navbar-positioned">
        </div>
      </div>
    </nav>
</#macro>

<#macro editCourseNavbar>
  <nav class="navbar navbar-default">
    <div class="container-fluid">
    </div>
  </nav>
</#macro>