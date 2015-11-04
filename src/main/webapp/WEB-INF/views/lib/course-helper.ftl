<#macro editCourse active = false>
    <@spring.formHiddenInput 'course.id' />
    <div class="form-group">
        <label for="title">Tytuł kursu</label>
        <@spring.formInput 'course.title' 'id="title" class="form-control" placeholder="Tytuł" required' />
    </div>
    <#if active>
        <div class="form-group">
            <label for="active">Aktywny</label>
            <@spring.formCheckbox 'course.active' 'id="active" class="form-control"' />
        </div>
    </#if>
    <div class="form-group">
        <label for="category">Kategoria</label>
        <select name="categoryIds" class="form-control multiselect" id="category" multiple="multiple" required>
            <#list categories?keys as id>
                <#if course_categories?? && course_categories?seq_contains(id)>
                    <option value=${id} selected>${categories[id]}</option>
                    <#else>
                        <option value=${id}>${categories[id]}</option>
                </#if>
            </#list>
        </select>
    </div>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</#macro>

<#macro editCourseDetails>
    <div class="form-group">
        <label for="file-upload">Obrazek kursu:</label><br/>
            <span name="file-upload" id="file-upload" class="btn btn-info fileinput-button">
                <span class="glyphicon glyphicon-upload"></span>&nbsp;Dodaj plik
                <input id="fileupload" type="file" name="file" accept="image/*"/>
            </span>
        <span id="filename"></span>
    </div>
    <div class="form-group">
        <div id="upload-progress"></div>
        <span id="upload-progress-text"></span>
        <#if course.thumbnail??>
            <img src="<@spring.url '/files/' + course.thumbnail.id />" id="thumbnail" />
            <input id="fileId" type="hidden" name="fileId" value="${course.thumbnail.id}" />
            <#else>
                <img style="display: none;" src="" id="thumbnail" />
                <input id="fileId" type="hidden" name="fileId"/>
        </#if>

    </div>
    <div class="form-group">
        <label for="description">Opis</label>
        <@spring.formTextarea 'course.description' 'id="description" class="form-control" placeholder="Opis"' />
    </div>

    <script>
    $('#fileupload').fileupload({
      url: "<@spring.url '/files/upload/image?${_csrf.parameterName}=${_csrf.token}' />",

      send: function(e, data) {
        $("#upload-progress").plainOverlay("show");
        $("#upload-progress-text").text("Trwa wysyłanie...");
      },
      done: function(e, data) {
        $("#upload-progress").plainOverlay("hide");
        $("#upload-progress-text").text("");
        $("#filename").text(data.result.fileName);
        $("#thumbnail").attr("src", "<@spring.url '/files/' />" + data.result.fileId);
        $("#thumbnail").show();
        $("#fileId").val(data.result.fileId);
        $('#fileupload').fileupload(
          'option',
          'formData',
          {oldFileId: data.result.fileId}
        );
      },
      fail: function(e, data) {
        $("#filename").text("Wysyłanie nie powiodło się!");
      }
    });
    </script>
</#macro>
