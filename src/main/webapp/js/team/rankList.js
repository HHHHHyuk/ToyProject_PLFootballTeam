$(document).ready(function(){
    var teamRankSearchCondition = {
       "season" : $("#season option:checked").val()
    }
    $.ajax({
        url : "/api/v1/teams/rank"
        ,cache : false
        ,async : false
        ,data : teamRankSearchCondition
        ,type : "GET"
    }).done(function(r){
        $("#teamRankListArea").html("");
        if(r!=null && r.success && r.response!=null && r.response.length>0){
            var html ="";
            for (var i=0; i<r.response.length; i++){
                html += "<tr><td>"+(i+1)+"</td><td><img src=\""+r.response[i]['imgPath']+"\" class='teamRankList_logo' >"+r.response[i]['teamName']+"</td>" +
                    "<td>"+r.response[i]['gameCount']+"</td><td>"+r.response[i]['victoryPoint']+"</td><td>"+r.response[i]['victory']+"</td><td>"+r.response[i]['draw']+"</td>"+
                    "<td>"+r.response[i]['lose']+"</td><td>"+r.response[i]['score']+"</td><td>"+r.response[i]['loss']+"</td><td>"+r.response[i]['scoreAndLoss']+"</td>"+
                    "</tr>";
            }
            $("#teamRankListArea").append(html);
        }
    });
});