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
                html += "<tr><td>"+r.response[i]['id']+"</td><td>"+r.response[i]['teamName']+"</td><td>"+r.response[i]['lastModifiedDate']+"</td><td>"+
                    "<a  class=\"btn btn-outline-danger btn-sm\" href=\"/team/settings/update/"+r.response[i]['id']+"\">수정</a></td></tr>";
            }
            $("#teamListArea").append(html);
        }
    });
});