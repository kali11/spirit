<#macro editElementText>
    <div class="form-group">
        <label for="text">Tekst:</label>
        <textarea class="form-control" id="text" name="text" type="text"></textarea>
        </textarea>
    </div>
    <script>
      $(function() {
          $('#text').editable({
          language: 'pl',
          spellcheck: true,
          minHeight: 200,
          imageUploadURL: "<@spring.url '/files/upload/image?${_csrf.parameterName}=${_csrf.token}' />",
          imageDeleteURL: "<@spring.url '/files/delete/image?${_csrf.parameterName}=${_csrf.token}' />",
          buttons: ["bold", "italic", "underline", "strikeThrough", "subscript", "superscript", 
          "fontFamily", "fontSize", "color", "formatBlock", "blockStyle", "inlineStyle", "align", 
          "insertOrderedList", "insertUnorderedList", "outdent", "indent", "createLink", 
          "insertImage", "table", "insertHorizontalRule", "undo", "redo", "removeFormat", "sep", 
          "html"],
          inlineMode: false
          })
          .on('editable.imageError', function (e, editor, error) {
            console.log(error);
          })
          .on('editable.afterRemoveImage', function (e, editor, $img) {
            editor.deleteImage($img);
          });
      });

    </script>
</#macro>

<#macro editElementVideo>
    <div class="form-group">
        <label for="description">Opis:</label>
        <textarea class="form-control" id="description" name="description" type="text"></textarea>
    </div>
    <div class="form-group">
        <label for="src">Link do filmu z portalu YouTube:</label>
        <input class="form-control" id="src" name="src" type="text" required>
    </div>
</#macro>

<#macro editElementAudio>
    <div class="form-group">
        <label for="description">Opis:</label>
        <textarea class="form-control" id="description" name="description" type="text"></textarea>
    </div>
    <div class="form-group">
        <label for="audio-upload">Plik audio (.mp3, .ogg lub .wav):</label><br/>
    <span name="audio-upload" id="audio-upload" class="btn btn-info fileinput-button">
      <span class="glyphicon glyphicon-upload"></span>&nbsp;Dodaj plik
      <input id="fileupload" type="file" name="file" accept="audio/*">
    </span><span id="filename"></span>
    </div>
    <div class="form-group">
        <div id="upload-progress"></div>
        <span id="upload-progress-text"></span>
    </div>
    <input id="fileId" type="hidden" name="fileId"/>
    <script>
    $('#fileupload').fileupload({
      url: "<@spring.url '/files/upload/audio?${_csrf.parameterName}=${_csrf.token}' />",
      acceptFileTypes: /(\.|\/)(mp3|ogg|wav)$/i,
      
      send: function(e, data) {
        $("#upload-progress").plainOverlay("show");
        $("#upload-progress-text").text("Trwa wysyłanie...");
      },
      done: function(e, data) {
        $("#upload-progress").plainOverlay("hide");
        $("#upload-progress-text").text("");
        $("#filename").text(data.result.fileName);
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

<#macro editElementFile>
    <div class="form-group">
        <label for="text">Tekst (Aby dodać plik naciśnik ikonę agrafki):</label>
        <textarea class="form-control" id="text" name="text" type="text"></textarea>
        </textarea>
    </div>
    <script>
      $(function() {
          $('#text').editable({
          language: 'pl',
          spellcheck: true,
          minHeight: 200,
          fileUploadURL: "<@spring.url '/files/upload/other?${_csrf.parameterName}=${_csrf.token}' />",
          buttons: ["bold", "italic", "underline", "insertOrderedList", "insertUnorderedList",
          "insertHorizontalRule", "undo", "redo", "sep", "uploadFile"],
          inlineMode: false
          })
          .on('editable.imageError', function (e, editor, error) {
            console.log(error);
          })
          .on('editable.afterRemoveImage', function (e, editor, img) {
            editor.deleteImage(img);
          });
      });

    </script>
</#macro>

<#macro editElementTest>
    <div class="form-group">
        <label for="description">Opis:</label>
        <textarea class="form-control" id="description" name="description" type="text"></textarea>
    </div>
</#macro>

<#macro modifyElement element>
    <div class="btn-group">
        <a data-href="<@spring.url '/elements/delete/'+element.id />" class="btn btn-danger confirm"
           data-placement="bottom" data-title="Czy na pewno?" data-btnOkLabel="Usuń" data-btnCancelLabel="Anuluj"><span
                class="glyphicon glyphicon-remove"></span>&nbsp;Usuń element</a>
        <#if elementType == 'test'>
            <a href="<@spring.url '/tests/edit/'+element.id />" class="btn btn-warning"><span
                    class="glyphicon glyphicon-edit"></span>&nbsp;Edytuj element</a>
            <#else>
                <a class="btn btn-warning"><span class="glyphicon glyphicon-edit"></span>&nbsp;Edytuj element</a>
        </#if>
    </div>
    <hr/>
    <@common.confirmation />
</#macro>

<#macro displayElementText element>
    <@modifyElement element />
    ${element.text}
    <script>
  $(function(){
    $("table").attr("border", "1");
  });

    </script>
</#macro>

<#macro displayElementVideo element>
    <@modifyElement element />
    <iframe style="display:block; margin:auto;" width="640" height="360" src="${element.src}" frameborder="0"
            allowfullscreen=""></iframe>
    <br/>

    <p>${element.description}</p>
</#macro>

<#macro displayElementAudio element>
    <@modifyElement element />
    <audio controls>
        <source src="${filePath}">
    </audio>
    <p>${element.description}</p>
</#macro>

<#macro displayElementFile element>
    <@modifyElement element />
    ${element.text}
</#macro>

<#macro displayElementTest element>
    <@modifyElement element />
    <p>${element.description}</p>
    <#list testQuestions as question>
        <div class="test-question" id="test-question-${question.id}">
            <h3>${question.orderSeq}.&nbsp;${question.question}</h3>
            <#if question.questionType == "single">
                <#list question.testAnswers as answer>
                    <div class="radio">
                        <label><input type="radio" name="${question.question}" value="${answer.id}">${answer.answer}</label>
                    </div>
                </#list>
                <#elseif question.questionType == "multi">
                    <#list question.testAnswers as answer>
                        <div class="checkbox">
                            <label><input type="checkbox" value="${answer.id}">${answer.answer}</label>
                        </div>
                    </#list>
                    <#elseif question.questionType == "open">
                        <label>Odpowiedź:</label>
                        <textarea class="form-control"></textarea>
            </#if>
            <button type="button" class="btn btn-info" onclick="checkQuestion(${question.id}, '${question.questionType}')">Sprawdź</button>
            <span class="score">Wynik:</span>
        </div>
    </#list>
    <script>
        var checkQuestion = function(id, type){
            var question = $("#test-question-" + id);
            switch(type){
                case 'single':
                    var responseData = {
                        userResponse: question.find("input[type='radio']:checked").val()
                    }
                break;
                case 'multi':
                    var responses = [];
                    question.find("input[type='checkbox']:checked").each(function(){
                            responses.push($(this).val());
                        })
                    var responseData = {
                        userResponse: responses
                    }
                break;
                case 'open':
                    var responseData = {
                        userResponse: question.find("textarea").val()
                    }
                break;
            }
            console.log(responseData);
            responseData.questionId = id;
            $.ajax({
              type: "POST",
              url: "<@spring.url '/tests/check' />?${_csrf.parameterName}=${_csrf.token}",
              contentType: "application/json",
              data: JSON.stringify(responseData),
              success: function(response) {
                console.log(response.score);
                question.find(".score").html("Wynik: " + response.score);
              }
            });
        }
    </script>
</#macro>
