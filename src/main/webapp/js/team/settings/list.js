$(document).ready(function(){
    $.ajax({
        url : "/api/v1/teams"
        ,cache : false
        ,async : false
        ,type : "GET"
    }).done(function(r){
        if(r!=null && r.success && r.response!=null && r.response.length>0){
            var html ="";
            for (var i=0; i<r.response.length; i++){
                var imgPath = r.response[i]['imgPath'];
                if(r.response[i]['saveFileName']==null || ""===r.response[i]['saveFileName']){
                    imgPath = "/webapp/images/empty_team.png";
                }
                html += "<tr><td>"+r.response[i]['id']+"</td><td><img class=\"list_teamLogo\" src=\""+imgPath+"\"></td><td>"+
                    "<a class=\"customA\" href=\"/team/settings/view/"+r.response[i]['id']+"\">"+r.response[i]['teamName']+"</a></td><td>"+r.response[i]['lastModifiedDate']+"</td><td>"+
                    "<a  class=\"btn btn-outline-danger btn-sm\" href=\"/team/settings/update/"+r.response[i]['id']+"\">수정</a> <a  class=\"btn btn-primary btn-sm\" role=\"button\" href=\"/team/settings/rank/save/"+r.response[i]['id']+"\">시즌기록관리</a></td></tr>";
            }
            $("#teamListArea").append(html);
        }
    });
});