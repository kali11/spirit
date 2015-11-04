<#import "/lib/navbar.ftl" as navbar>
<@common.page>
  <div id="main-container" class="container">
    <@common.breadcrumb />
    <@navbar.courseNavbar />
    <div class="row">
        <div class="col-md-7">
          <div class="panel panel-primary">
            <div class="panel-heading">Aktualności</div>
            <div class="panel-body">
              Już jutro rozpoczynają się zajęcia z Wieloagentowych systemów decyzyjnych!
              <br />03.03.2013
            </div>
          </div>
        </div>

        <div class="col-md-5">
          <div class="panel panel-primary">
            <div class="panel-heading">Moduły</div>
            <div class="panel-body">
              <#if course.modules?size != 0>
              <h4>Wybierz moduł i zacznij się uczyć!</h4>
              <table class="modules-progress">
                <#assign counter = 1>
                <#list course.modules as module>
                  <tr>
                    <td>${counter}.</td>
                    <td><a href="<@spring.url '/modules/' + module.id />">${module.title}</a></td>
                    <td>0%</td>
                  </tr>
                  <#assign counter = counter + 1>
                </#list>
              </table>
              <#else>
                <h4>Brak modułów w kursie</h4>
              </#if>
            </div>
          </div>
        </div>
    </div>
  </div>
</@common.page>