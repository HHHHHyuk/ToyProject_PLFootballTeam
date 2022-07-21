$(document).ready(function(){
    var page = $("#page").val();
    set_page(page);
});

function set_page(page){
    var teamId = $("#teamList option:checked").val();
    $.ajax({
        url : "/api/v1/player"
        ,data:{"page":page, "size":10,"teamId":teamId}
        ,cache : false
        ,async : false
        ,type : "GET"
    }).done(function(r){
        if(r!=null && r.success && r.response.content!=null && r.response.content.length>0){
            var html ="";
            var pageHtml ="";
            for (var i=0; i<r.response.numberOfElements; i++){
                var imgPath = r.response.content[i]['imgPath'];
                if(r.response.content[i]['saveFileName']==null || ""===r.response.content[i]['saveFileName']){
                    imgPath = "/webapp/images/empty_player.png";
                }
                html += "<tr><td>"+r.response.content[i]['id']+"</td><td><img class=\"list_logo\" src=\""+imgPath+"\"></td><td>"+
                    "<a class=\"customA\" href=\"/team/settings/view/"+r.response.content[i]['id']+"\">"+r.response.content[i]['playerName']+"</a></td>" +
                    "<td>"+r.response.content[i]['teamName']+"</td><td>"+r.response.content[i]['backNumber']+"</td><td>"+r.response.content[i]['lastModifiedDate']+"</td><td>"+
                    "<a  class=\"btn btn-outline-danger btn-sm\" href=\"/player/settings/update/"+r.response.content[i]['id']+"\">수정</a> <a  class=\"btn btn-primary btn-sm\" role=\"button\" href=\"/player/settings/rank/save/"+r.response.content[i]['id']+"\">시즌기록관리</a></td></tr>";
            }
            $("#playerListArea").html("");
            $("#playerListArea").append(html);

            for(var i=0; i<r.response.totalPages; i++){
                pageHtml += "<li class='page-item ";
                pageHtml +=  r.response.number===i ? "active" : "";
                pageHtml += " '><a class=\"page-link\" onclick=\"javascript:set_page("+(i)+");\">"+(i+1)+"</a></li>";
            }
            $("#pageArea").html("");
            $("#pageArea").append(pageHtml);
        }else{
            $("#playerListArea").html("");
            var html = "<tr><td colspan='7' style='text-align: center;'><strong>등록된 선수가 없습니다.</strong></td></tr>";
            $("#playerListArea").append(html);
        }
    });
}