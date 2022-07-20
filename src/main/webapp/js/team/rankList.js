$(document).ready(function(){
    selected_list(null);
});

function selected_season(){
    selected_list(null);
}

function selected_list(orderValue){
    var teamRankSearchCondition = {
        "season" : $("#season option:checked").val(),
        "orderValue" :orderValue
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

            var tdIndex = 3;
            switch (orderValue){
                case "victory" : tdIndex=4; break;
                case "draw" : tdIndex=5; break;
                case "lose" : tdIndex=6; break;
                case "score" : tdIndex=7; break;
                case "loss" : tdIndex=8; break;
                case "scoreAndLoss" : tdIndex=9; break;
            }
            $("tr>th").removeClass("selected_th");
            $("tr>th:eq("+tdIndex+")").addClass("selected_th");
            $("tr").each(function(index, item){
                $(this).children("td").eq(tdIndex).addClass("selected_td");
            });
        }else{
            $("#teamRankListArea").html("");
            var html = "<tr><td colspan='10' style='text-align: center;'><strong>해당 시즌에 대한 데이터가 없습니다.</strong></td></tr>";
            $("#teamRankListArea").append(html);
        }
    });
}