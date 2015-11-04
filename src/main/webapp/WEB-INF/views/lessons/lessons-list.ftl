              <div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
                <#list module.lessons as lesson>
                  <div class="panel panel-default">
                    <div class="panel-heading" role="tab" id="heading${lesson.id}">
                      <a class="lesson-title" data-toggle="collapse" data-parent="#accordion" href="#collapse${lesson.id}" aria-expanded="false" aria-controls="collapse${lesson.id}">
                      ${lesson.title}
                      </a>
                    </div>
                    <div id="collapse${lesson.id}" class="panel-collapse collapse" role="tabpanel" aria-labelledby="heading${lesson.id}">
                      <div class="panel-body">
                      ${lesson.description}
                      <hr />
                      W tej lekcji:
                      <div class="list-group">
                          <#list lesson.elements as element>
                          <div id="element-${element.id}" class="list-group-item list-group-item-info element-title">
                            ${element.title}
                          </div>
                          </#list>
                      </div>
                      <ul>
                      </ul>
                      <button class="btn btn-xs btn-success" onclick=editElement(${lesson.id})><span class="glyphicon glyphicon-plus"></span>&nbsp;Dodaj element</button>
                      </div>
                      <div class="panel-footer">
                        <div class="modules-edit">
                          <a class="btn btn-xs btn-success"><span class="glyphicon glyphicon-arrow-up"></span></a>
                          <a class="btn btn-xs btn-success"><span class="glyphicon glyphicon-arrow-down"></span></a>
                          <a class="btn btn-xs btn-info" href="<@spring.url '/lessons/edit/'+lesson.id />"><span class="glyphicon glyphicon-pencil"></span>&nbsp;Edytuj</a>
                          <a class="btn btn-xs btn-danger"><span class="glyphicon glyphicon-remove"></span>&nbsp;Usuń</a>
                        </div>
                      </div>
                    </div>
                  </div>
                </#list>
              </div>
