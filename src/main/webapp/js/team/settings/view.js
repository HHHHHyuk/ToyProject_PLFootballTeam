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
                $("#deleteYn").val("Y");
            });
        }
    });
});

function update_check(){
    if($("#teamName").val()==null || ''==$("#teamName").val()){
        alert("팀 이름은 필수항목 입니다.");
    }else{
        var teamId = $("#teamId").val();
        var updateRequestDto = {
            "teamName" : $("#teamName").val()
            ,"teamArea" : $("#teamArea").val()
            ,"stadium" : $("#stadium").val()
            ,"manager" : $("#manager").val()
            ,"foundingDate" : $("#foundingDate").val()
            ,"originalFileName" : $("#originalFileName").val()
            ,"saveFileName" : $("#saveFileName").val()
            ,"tempFileName" : $("#tempFileName").val()
            ,"deleteYn" : $("#deleteYn").val()
        }
        $.ajax({
            url : "/api/v1/teams/"+teamId
            ,data : JSON.stringify(updateRequestDto)
            ,dataType : "json"
            ,cache : false
            ,async : false
            ,contentType:"application/json;charset=UTF-8"
            ,type : "PUT"
        }).done(function(r){
            window.location.href="/team/settings/update/"+r;
        }).fail(function(r){
            alert(r);
        });
    }
}

function delete_check(){
    var teamId = $("#teamId").val();
    $.ajax({
        url : "/api/v1/teams/"+teamId
        ,cache : false
        ,async : false
        ,type : "DELETE"
    }).done(function(r){
        window.location.href="/team/settings/list";
    }).fail(function(r){
        alert(r);
    });
}

