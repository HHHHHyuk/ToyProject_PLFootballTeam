$(document).ready(function(){
    season_val();
});
function season_val(){
    var season = $("#season option:checked").val();
    var id = $("#teamId").val();
    if(season!=null && ""!==season){
        $.ajax({
            url : "/api/v1/team/rank/"+id
            ,data : {
                "season":season
            }
            ,cache : false
            ,async : false
            ,type : "GET"
        }).done(function(r){
            if(r.response!=null){
                $("#idVal").val(r.response.id);
                $("#gameCount").val(r.response.gameCount);
                $("#victory").val(r.response.victory);
                $("#draw").val(r.response.draw);
                $("#lose").val(r.response.lose);
                $("#score").val(r.response.score);
                $("#loss").val(r.response.loss);
            }
        });
    }
}

function save_check(){
    var result = false;
    var saveRequestDto = {
        "id" : $("#idVal").val()
        ,"teamId" : $("#teamId").val()
        ,"season" : $("#season option:checked").val()
        ,"victory" : $("#victory").val()
        ,"draw" : $("#draw").val()
        ,"lose" : $("#lose").val()
        ,"score" : $("#score").val()
        ,"loss" : $("#loss").val()
    }
    $.ajax({
        url : "/api/v1/team/rank/"
        ,data : JSON.stringify(saveRequestDto)
        ,dataType : "json"
        ,cache : false
        ,async : false
        ,contentType:"application/json;charset=UTF-8"
        ,type : "POST"
    }).done(function(r){
        result = true;
    }).fail(function(r){
        alert(r.responseJSON.error.message);
    });
    return result;
}

function delete_season(){
    var id = $("#idVal").val();
    var teamId = $("#teamId").val();
    if(id!=null && ""!==id){
        var result = confirm("시즌 기록을 삭제하시겠습니까?");
        if(result){
                $.ajax({
                    url : "/api/v1/team/rank/"+id
                    ,cache : false
                    ,async : false
                    ,type : "DELETE"
                }).done(function(r){
                    window.location.href="/team/settings/rank/save/"+teamId;
                }).fail(function(r){
                    alert(r);
                });
        }
    }
}


