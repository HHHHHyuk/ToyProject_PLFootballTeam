$(document).ready(function(){
    $("#fileDelete").on("click", function(){
        var tempFileName = $("#tempFileName").val();
        $.ajax({
            url : "/file/upload"
            ,data : {"fileName":tempFileName}
            ,cache : false
            ,async : false
            ,type : "DELETE"
        }).done(function(r){
            if(r.success){
                $("#tempFileName").val("");
                $("#originalFileName").val("");
                $("#deleteYn").val("Y");
            }
        });
    });
});

function update_check(){
    if($("#playerName").val()==null || ''==$("#playerName").val()){
        alert("선수 이름은 필수항목 입니다.");
    }else if($("#teamId option:checked").val()==null || ''==$("#teamId option:checked").val()){
        alert("팀은 필수항목 입니다.");
    }else{
        var playerId = $("#playerId").val();
        var updateRequestDto = {
            "playerName" : $("#playerName").val()
            ,"teamId" : $("#teamId option:checked").val()
            ,"nationality" : $("#nationality").val()
            ,"height" : $("#height").val()
            ,"weight" : $("#weight").val()
            ,"position" : $("#position").val()
            ,"backNumber" : $("#backNumber").val()
            ,"originalFileName" : $("#originalFileName").val()
            ,"saveFileName" : $("#saveFileName").val()
            ,"tempFileName" : $("#tempFileName").val()
            ,"deleteYn" : $("#deleteYn").val()
        }
        $.ajax({
            url : "/api/v1/player/"+playerId
            ,data : JSON.stringify(updateRequestDto)
            ,dataType : "json"
            ,cache : false
            ,async : false
            ,contentType:"application/json;charset=UTF-8"
            ,type : "PUT"
        }).done(function(r){
            window.location.href="/player/settings/view/"+r;
        }).fail(function(r){
            alert(r.responseJSON.error.message);
        });
    }
}

function delete_check(){
    var playerId = $("#playerId").val();
    $.ajax({
        url : "/api/v1/player/"+playerId
        ,cache : false
        ,async : false
        ,type : "DELETE"
    }).done(function(r){
        window.location.href="/player/settings/list";
    }).fail(function(r){
        alert(r.responseJSON.error.message);
    });
}

