$(document).ready(function(){
    $.ajax({
        url : "/api/v1/teams"
        ,cache : false
        ,async : false
        ,type : "GET"
    }).done(function(r){
        if(r!=null && r.length>0){
            var html ="";
            for (var i=0; i<r.length; i++){
                html += "<tr><td>"+r[i]['id']+"</td><td>"+r[i]['teamName']+"</td><td>"+r[i]['lastModifiedDate']+"</td><td>"+
                    "<a  class=\"btn btn-outline-danger btn-sm\" href=\"/team/settings/update/"+r[i]['id']+"\">수정</a></td></tr>";
            }
            $("#teamListArea").append(html);
        }
    });
});