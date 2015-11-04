<#import "/lib/navbar.ftl" as navbar>
<#import "/lib/elements.ftl" as elements>
<@common.page styles=['/webjars/font-awesome/4.3.0/css/font-awesome.min.css', 
'/webjars/FroalaWysiwygEditor/1.2.6/css/froala_editor.min.css', 
'/webjars/FroalaWysiwygEditor/1.2.6/css/froala_style.min.css',
'/webjars/FroalaWysiwygEditor/1.2.6/css/froala_content.min.css',
'/webjars/blueimp-file-upload/9.9.3/css/jquery.fileupload.css']>
<@common.froala />
<@common.fileUpload />
  <div id="main-container" class="container">
   <@common.breadcrumb />
   <@navbar.courseNavbar />
   <div class="row">
        <div class="col-md-3">
          <div class="panel panel-primary">
            <div class="panel-heading">
              Lekcje w module
            </div>
            <div class="panel-body" id="lessons-list">
              <#include '/lessons/lessons-list.ftl' />
            </div>
            <div class="panel-footer">
            <a class="btn btn-success" href="<@spring.url '/lessons/add?moduleId='+module.id />"><span class="glyphicon glyphicon-plus"></span>&nbsp;Dodaj lekcję</a>
            </div>
          </div>
        </div>
        <div class="col-md-9">
          <div id="panel-element-content" class="panel panel-primary" style="display:none">
            <div id="element-title" class="panel-heading">
            </div>
            <div id="element-content" class="panel-body">
            </div>
          </div>
        </div>
    </div>
  </div>
    <@common.overlay />
  <script>
  $("#collapse${activeLessonId!}").addClass('in');
  var editElement = function(lessonId){
    $('#element-title').text("Dodaj element");
    $('#panel-element-content').show();
    $('#element-content').plainOverlay('show');
    $.ajax({
        url: "<@spring.url '/elements/add' />",
        type: "POST",
        data: "${_csrf.parameterName}" + "=" + "${_csrf.token}&lessonId="+lessonId,
        success: function(response){
            $("#element-content").html(response);
            $('#element-content').plainOverlay('hide');
        }
    });
  }
  
  $(".element-title").click(function(){
    id = $(this).attr("id").split("-")[1];
    $('#element-title').text($(this).text());
    $('#panel-element-content').show();
    $('#element-content').plainOverlay('show');
    $.ajax({
        url: "<@spring.url '/elements/' />" + id,
        type: "POST",
        data: "${_csrf.parameterName}" + "=" + "${_csrf.token}",
        success: function(response){
            $("#element-content").html(response);
            $('#element-content').plainOverlay('hide');
        }
    });
  });

  </script>
</@common.page>
