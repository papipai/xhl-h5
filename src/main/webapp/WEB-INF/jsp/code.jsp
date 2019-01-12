<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<script type="text/javascript">
window.onload = function(){
	//异步请求视频
	$.ajax({
		async: false,
		type:'get',
		url:'main',
		dataType:'json',
		success:function(result){
			$("#video_list2").empty();
            var thisListValueStr = "";
            for (var i = 0; i < result.length; i++) {
            	
                var caseList = result[i]; //获取Map
               // console.log('图片：'+caseList.main_pic);
                //thisListValueStr = "<li><table border='0'><tr><td class='pic_list'><img src='"+caseList.main_pic+"' /></td><td class='title_list'><a onclick='changeVideo(this,"+'"'+caseList.src+'"'+","+'"'+caseList.main_pic+'"'+")'>"+caseList.title+"</a></td></tr></table></li>";
                thisListValueStr = "<li><table border='0'><tr><td class='pic_list'><img src='"+caseList.main_pic+"' /></td><td class='title_list'><a href='main?videoId="+caseList.id+"'>"+caseList.title+"</a></td></tr></table></li>";
                $("#video_list2").append(thisListValueStr);
                thisListValueStr = "";
            }
            
		}
	})
}
</script>
</body>
</html>