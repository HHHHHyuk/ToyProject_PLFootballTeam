function save_check(){
    var valChk = false;
    var ajaxChk = false;

    if($("#teamName").val()==null || ''==$("#teamName").val()){
        alert("팀 이름은 필수항목 입니다.");
    }else{
        valChk=true;
        var saveRequestDto = {
            "teamName" : $("#teamName").val()
            ,"teamArea" : $("#teamArea").val()
            ,"stadium" : $("#stadium").val()
            ,"manager" : $("#manager").val()
            ,"foundingDate" : $("#foundingDate").val()
        }
        $.ajax({
            url : "/api/v1/teams"
            ,data : JSON.stringify(saveRequestDto)
            ,dataType : "json"
            ,cache : false
            ,async : false
            ,contentType:"application/json;charset=UTF-8"
            ,type : "POST"
        }).done(function(r){
            ajaxChk = true;
        }).fail(function(r){
            alert(r.responseJSON.error.message);
        });
    }
    return valChk && ajaxChk;
}