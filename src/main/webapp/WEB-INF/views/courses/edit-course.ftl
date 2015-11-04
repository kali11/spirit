<#import "/lib/course-helper.ftl" as courseHelper />
<@common.page styles=['/resources/css/bootstrap-multiselect.css',
'/webjars/blueimp-file-upload/9.9.3/css/jquery.fileupload.css']>
<@common.fileUpload />
<@common.overlay />
<div id="main-container" class="container">
    <@common.breadcrumb "Edytujesz:" />
    <form role="form" id="edit-course" action="<@spring.url '/courses/save'/>" method="POST">
        <div class="row">
            <div class="col-md-6">
                <@courseHelper.editCourse true />
            </div>
            <div class="col-md-6">
                <@courseHelper.editCourseDetails />
            </div>
            <!--
                <a data-href="<@spring.url '/courses/delete/'+course.id />" class="btn btn-danger confirm" data-placement="bottom" data-title="Czy na pewno?" data-btnOkLabel="Usuń" data-btnCancelLabel="Anuluj"><span class="glyphicon glyphicon-remove"></span>&nbsp;Usuń kurs</a>
    >-->
        </div>
        <button onclick="$('#edit-course').submit()" type="button" class="btn btn-success">
            <span class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span>&nbsp;Zapisz
        </button>
    </form>
    <@common.confirmation />
</@common.page> 
