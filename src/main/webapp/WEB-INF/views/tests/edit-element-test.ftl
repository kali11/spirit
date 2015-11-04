<@common.page>
<div id="main-container" class="container">
    <h3>${element.title}</h3>
    <div class="col-md-6 col-md-offset-2">
        <label>Pytania:</label>
        <div id="questions-list">
            <#include "/tests/questions-list.ftl" />
        </div>
        <div class="form-group">
            <button type="button" class="btn btn-success" data-toggle="modal" data-target="#add-question"><span class="glyphicon glyphicon-plus"></span>&nbsp;Dodaj pytanie</button>
        </div>
    </div>
</div>

<div class="modal fade" id="add-question" tabindex="-1"role="dialog" aria-labelledby="modal-label">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" id="modal-label">Dodaj pytanie</h4>
            </div>
            <div class="modal-body">
                <div class="form-group">
                    <label for="question-type">Wybierz typ pytania:</label>
                    <select id="question-type" class="form-control" onchange="questionTypeChange(this)">
                        <option value="single">Pytanie jednokrotnego wyboru</option>
                        <option value="multi">Pytanie wielokrotnego wyboru</option>
                        <option value="open">Pytanie otwarte</option>
                    </select>
                </div>
                <div class="form-group">
                    <label for="question">Treść pytania</label>
                    <textarea id="question" class="form-control"></textarea>
                </div>
                <@single />
                <@multi />
                <@open />
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Anuluj</button>
                <button type="button" class="btn btn-success" onclick="addQuestion()"><span class="glyphicon glyphicon-floppy-disk"></span>&nbsp;Zapisz</button>
            </div>
        </div>
    </div>
</div>
<script>
    var questionTypeChange = function(select){
        type = $(select).val();
        $(".question-edit").hide();
        $("#" + type + "-question").show();
    }

    var answer = '<div class="form-inline test-answer"><input type="text" class="form-control" style="width: 80%" /><input type="text" class="form-control" style="width: 15%" /><input type="checkbox" class="form-control" /></div><br />';

    var addAnswer = function(type) {
        $(answer).insertAfter($("#" + type + "-question .br").last());
    }

    var addQuestion = function(){
        var type = $("#question-type").val();
        var answers = new Array();
        if(type != "open") {
            $("#" + type + "-question .test-answer").each(function(i,v) {
                var answerData = {
                    answer: $(v).find("input:first-child").val(),
                    points: $(v).find("input:nth-child(2)").val(),
                    correct: $(v).find("input:last-child").is(':checked')
                }
                answers.push(answerData);
            })
        } else {
            var answerData = {
                    answer: $("#open-question").find("textarea").val(),
                    points: $("#open-question").find("input").val()
                }
                answers.push(answerData);
        }
        var questionData = {
            element: ${element.id},
            question: $("#question").val(),
            type: type,
            answers: answers
        };

        $.ajax({
          type: "POST",
          url: "<@spring.url '/tests/add-question' />?${_csrf.parameterName}=${_csrf.token}",
          contentType: "application/json",
          data: JSON.stringify(questionData),
          success: function(response) {
            $("#add-question").modal('hide');
            $("#questions-list").html(response);
          }
        });
    }
</script>
</@common.page>

<#macro single>
    <div id="single-question" class="question-edit">
        <label>Odpowiedzi:</label>
        <label style="margin-left:65%">Punkty:</label>
        <div class="form-inline test-answer">
            <input type="text" class="form-control" style="width: 80%" />
            <input type="text" class="form-control" style="width: 15%" />
            <input type="checkbox" class="form-control" />
        </div>
        <br class="br" />
        <button class="btn btn-success" type="button" onclick="addAnswer('single')"><span class="glyphicon glyphicon-plus"></span>&nbsp;Dodaj odpowiedź</button>
    </div>
</#macro>
<#macro multi>
    <div id="multi-question" class="question-edit" style="display:none">
        <label>Odpowiedzi:</label>
        <label style="margin-left:65%">Punkty:</label>
        <div class="form-inline test-answer">
            <input type="text" class="form-control" style="width: 80%" />
            <input type="text" class="form-control" style="width: 15%" />
            <input type="checkbox" class="form-control" />
        </div>
        <br class="br" />
        <button class="btn btn-success" type="button" onclick="addAnswer('multi')"><span class="glyphicon glyphicon-plus"></span>&nbsp;Dodaj odpowiedź</button>
    </div>
</#macro>
<#macro open>
    <div id="open-question" class="question-edit" style="display:none">
        <label>Odpowiedz:</label>
        <textarea class="form-control"></textarea>
        <label>Punkty:</label>
        <input type="text" class="form-control" style="width: 10%">
    </div>
</#macro>
