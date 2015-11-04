<#import "/lib/elements.ftl" as elements />
<form role="form" action="<@spring.url '/elements/save?lessonId=' + lessonId />" method="POST">
  <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
  <input type="hidden" name="elementType" id="elementType" value="" />
  <div class="form-group">
    <label for="title">Tytuł:</label>
    <@spring.formInput 'element.title' 'id="title" class="form-control" placeholder="Tytuł" required' />
  </div>
  <div class="form-group">
    <label for="active">Aktywny</label>
    <@spring.formCheckbox 'element.active' 'id="active" class="form-control"' />
  </div>
  <div class="form-group">
    <label for="type">Typ elementu:</label><br />
    <button name="text" type="button" class="btn btn-lg element-type"><span class="glyphicon glyphicon-font" aria-hidden="true"></span></button>
    <button name="video" type="button" class="btn btn-lg element-type"><span class="glyphicon glyphicon-facetime-video" aria-hidden="true"></span></button>
    <button name="audio" type="button" class="btn btn-lg element-type"><span class="glyphicon glyphicon-volume-up" aria-hidden="true"></span></button>
    <button name="test" type="button" class="btn btn-lg element-type"><span class="glyphicon glyphicon-check" aria-hidden="true"></span></button>
    <button name="file" type="button" class="btn btn-lg element-type"><span class="glyphicon glyphicon-file" aria-hidden="true"></span></button>
    &nbsp;<span id="display-text"></span>
  </div>
  <div id="element-details">
  </div>
  <div class="form-group">
    <button id="element-submit" style="display: none" type="submit" class="btn btn-success"><span class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span>&nbsp;Zapisz</button>
  </div>
</form>

<script>
  $('button.element-type').hover(function(){
    $(this).addClass("active");
    type = $(this).attr("name");
    switch(type) {
      case 'text':
        displayText = "Tekst z obrazkami";
      break;
      case 'test':
        displayText = "Test";
      break;
      case 'video':
        displayText = "Film z YouTube";
      break;
      case 'audio':
        displayText = "Klip dźwiękowy";
      break;
      case 'file':
        displayText = "Plik do pobrania";
      break;
    }
    $('#display-text').text(displayText);
  }, function(){
    $(this).removeClass("active");
    $('#display-text').text("");
  });
  
  $('button.element-type').click(function(){
    $("#element-submit").show();
    type = $(this).attr("name");
    switch(type) {
      case 'text':
        $("#element-details").html(<@common.jsStr><@elements.editElementText /></@>);
        $("#elementType").val(type);
      break;
      case 'video':
        $("#element-details").html(<@common.jsStr><@elements.editElementVideo /></@>);
        $("#elementType").val(type);
      break;
      case 'audio':
        $("#element-details").html(<@common.jsStr><@elements.editElementAudio /></@>);
        $("#elementType").val(type);
      break;
      case 'file':
        $("#element-details").html(<@common.jsStr><@elements.editElementFile /></@>);
        $("#elementType").val(type);
      break;
      case 'test':
        $("#element-details").html(<@common.jsStr><@elements.editElementTest /></@>);
        $("#elementType").val(type);
      break;
    }
  })
</script>
