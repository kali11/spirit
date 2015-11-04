<#import "/lib/course-helper.ftl" as courseHelper>
    <@common.page styles=['/resources/css/bootstrap-multiselect.css']>
    <div id="main-container" class="container">
        <h2>Kursy</h2>
        <nav class="navbar navbar-default">
            <div class="container-fluid">
                <p class="navbar-text">Wybierz kategorię:</p>

                <div class="navbar-left navbar-positioned">
                    <select name="category" class="multiselect" id="category" onchange=loadCourses($(this).val())>
                        <option value=0>Wszystkie</option>
                        <#list categories?keys as id>
                            <option value=${id}>${categories[id]}</option>
                        </#list>
                    </select>
                </div>
                <button type="button" class="btn btn-success navbar-btn navbar-right" data-toggle="modal"
                        data-target="#add-course-modal"><span class="glyphicon glyphicon-plus"></span>&nbspDodaj kurs
                </button>
            </div>
        </nav>
        <div id="courses">
            <#include "/courses/courses-list.ftl">
        </div>
    </div>

    <div class="modal fade" id="add-course-modal" tabindex="-1" role="dialog" aria-labelledby="add-course-label"
         aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                            aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="add-course-label">Dodaj kurs</h4>
                </div>
                <div class="modal-body">
                    <form role="form" id="edit-course" action="<@spring.url '/courses/save'/>" method="POST">
                        <@courseHelper.editCourse />
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Anuluj</button>
                    <button onclick="$('#edit-course').submit()" type="button" class="btn btn-success"><span
                            class="glyphicon glyphicon-plus" aria-hidden="true"></span>&nbsp;Dodaj
                    </button>
                </div>

            </div>
        </div>
    </div>

    <@common.multiselect />
    <script src="<@spring.url '/resources/scripts/jquery.plainoverlay.min.js' />"></script>
    <script>
  var loadCourses = function(categ){
    $('#courses').plainOverlay('show');
    $.ajax({
        url: "<@spring.url '/courses' />",
        type: "POST",
        data: "${_csrf.parameterName}" + "=" + "${_csrf.token}&category="+categ,
        success: function(response){
            $("#courses").html(response);
            $('#courses').plainOverlay('hide');
        }
    });
  }

    </script>
</@common.page>
