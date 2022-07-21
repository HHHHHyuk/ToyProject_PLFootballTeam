$(document).ready(function(){
    $("#fileDelete").on("click", function(){
        var tempFileName = $("#tempFileName").val();
        if(tempFileName!=null){
            $.ajax({
                url : "/file/upload"
                ,data : {"fileName":tempFileName}
                ,cache : false
                ,async : false
                ,type : "DELETE"
            }).done(function(r){
                $("#tempFileName").val("");
                $("#originalFileName").val("");
                $("#saveFileName").val("");
                $("#deleteYn").val("");
            });
        }
    });
});

function save_check(){
    var valChk = false;
    var ajaxChk = false;

    if($("#playerName").val()==null || ''==$("#playerName").val()){
        alert("선수 이름은 필수항목 입니다.");
    }else if($("#teamId option:checked").val()==null || ''==$("#teamId option:checked").val()){
        alert("팀은 필수항목 입니다.");
    }else{
        valChk=true;
        var saveRequestDto = {
            "teamId" : $("#teamId option:checked").val()
            ,"playerName" : $("#playerName").val()
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
            url : "/api/v1/player"
            ,data : JSON.stringify(saveRequestDto)
            ,dataType : "json"
            ,cache : false
            ,async : false
            ,contentType:"application/json;charset=UTF-8"
            ,type : "POST"
        }).done(function(r){
            ajaxChk = true;
            $("#playerSaveForm").attr("action", "/player/settings/view/"+r);
        }).fail(function(r){
            alert(r.responseJSON.error.message);
        });
    }
    return valChk && ajaxChk;
}