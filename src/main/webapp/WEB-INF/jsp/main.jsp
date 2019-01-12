<%@ page language="java" import="java.util.*"  contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/basepase/BasePath.jsp"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page language="java" import="com.yc.wechat.WeixinUtil" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="initial-scale=1, width=device-width, maximum-scale=1, user-scalable=no">
<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" name="viewport"> 
<meta content="yes" name="apple-mobile-web-app-capable"> 
<meta content="black" name="apple-mobile-web-app-status-bar-style"> 
<meta content="telephone=no" name="format-detection"> 
<base href<%= basePath %>>
<title>${sessionScope.video.title}</title>
<link rel="stylesheet" type="text/css" href="resource/css/mobile/reset.css" />
<link rel="stylesheet" href="resource/bootstrap/css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="resource/css/mobile/willesPlay.css" />
<script src="resource/js/jquery-1.11.3.min.js" type="text/javascript"charset="utf-8"></script>
<script src="resource/js/willesPlay.js" type="text/javascript" charset="utf-8"defer="defer"></script>
<script>
//获取连接后拼的参数
function GetVal(name){
var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
 var r = window.location.search.substr(1).match(reg);
if(r!=null)return  unescape(r[2]); return null;
};
var Afrom = GetVal('from')||0;
if(Afrom != 0)window.location.href = "poster.html";
</script>
<style type="text/css">
#mark {
	float: left;
	position: fixed;
	left: 0;
	top: 0;
	z-index: 2147483647;
	width: 100%;
	height: 100%;
	background-color: #000000;
	opacity: 1;
	display: none;
	
}
.markdisplay{
	display: block;
}

#mark img {
width:100%;
height:100%;
	display: block;
	float: left;
	position: fixed;
	z-index: 1001;
	top: 50%;
	left: 50%;
	transform:translate(-50%,-50%);
	-webkit-transform:translate(-50%,-50%);
}
.video_full{
	object-fit: fill;
}
</style>

</head>
<body>
	<div id="mark">
		<img src="resource/img/fenxaing.png" />
	</div>
	<div class="container">

		<div id="title">${sessionScope.video.title}</div>
		<div id="time"></div>
		<!--视频播放区域-->
		<div class="row video">
			<div class="col-md-12">
				<div id="willesPlay" class="video-wrap">

					<div id="playContent" class="playContent">
						<input type="hidden" id="videoId" value="${sessionScope.video.id}" />
						<input type="hidden" id="keyPoint" value="${sessionScope.video.key_point}" />
						<input type="hidden" id="videoDerc" value="${sessionScope.video.video_derc}" />
						<video width="100%" height="100%" id="playVideo" class="video_full" preload="auto"
							poster="${sessionScope.video.main_pic}" playsinline="isiPhoneShowPlaysinline"
							 webkit-playsinline="isiPhoneShowPlaysinline" x-webkit-airplay preload="none">
							<source src="${sessionScope.video.src}" type="video/mp4" id="source"></source>
							<!--<source src="test.ogg" type="video/ogg" id="source"></source>-->
						</video>
						
					</div>
					<div class="playTip"></div>
					<div class="playControll" id="playControll">
						<div class="playPause playIcon"></div>
						<div class="timebar">
							<span class="currentTime">0:00:00</span> <span>/</span>
							<span class="duration">0:00:00</span>
							<div id="progress-bar" class="progress">
								<div id="play-progress-bar" class="progress-bar progress-bar-danger progress-bar-striped"
									role="progressbar" aria-valuemin="0" aria-valuemax="100"
									style="width: 0%"></div>
								<div id="drag-btn" class="drag-btn"></div>
							</div>
						</div>
						<div class="full_btn fullscreen"></div>
					</div>
					<!-- <div id="control" class="foot-pannel " data-show="1">
				        <div id="oper-btn" class="oper-btn play"></div>
				        <div class="drag-wrap">
				            <div id="progress-bar" class="drag-line">
				                <div id="drag-btn" class="drag-btn"></div>
				                <div id="play-progress-bar" class="drag-line-bg"></div>
				            </div>
				            <div class="video-time"><span id="current-time">0:00:00</span>/<span id="duration">0:00:00</span></div>
				        </div>
				        <div id="full-btn" class="full-btn"></div>
				    </div> -->
					
				</div>

			</div>
		</div>
		<div id="more">更多精彩：</div>
		<div id="video_list">
			<ul id="video_list2">
				<%-- <c:forEach  items="${videos}" var="item" varStatus="status">
					<li>
						<table border="0" cellspacing="" cellpadding="">
							<tr>
								<td class="pic_list"><img src="resource/img/${item.main_pic}.png" /></td>
								<td class="title_list"><a onclick="changeVideo(this,'${item.src}')">${item.title}</a></td>
							</tr>
						</table>
					</li>
				</c:forEach> --%>
			</ul>
		</div>
		<!-- <div class="code">
			<table border="0">
				<tr>
					<td style="width: 40%; text-align: center;"><img
						src="resource/img/program_logo.png" /></td>
					<td style="width: 60%;"><span>长按识别二维码</span><br /> <span>获取更多有趣内容</span>
					</td>
				</tr>
			</table>
		</div> -->
	</div>
</body>
<script type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.4.0.js"></script>

<script type="text/javascript">
$(document).ready(function() {
	//获取code
	var currurl = location.href.split('#')[0];
	var code = sessionStorage.getItem("code");//取出这次会话中session的值
	
	var point = sessionStorage.getItem("point");
  	/* if (point == null) {
		//alert("我是第一次来哦。。。。");
		sessionStorage.setItem("point","point");
		window.location.href = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx0fc83cabd74c9566&redirect_uri="+encodeURIComponent(currurl)+"&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect";
	} */  
	if (code != null) {
		sessionStorage.setItem("code",getUrlParam("code"));
		
		var isEmpty = sessionStorage.getItem("code");
		//alert("isEmpty="+isEmpty+"isEmpty != null="+(isEmpty != "null"));
		//通过ajax传到后台获取openId
		 if (isEmpty != "null" && isEmpty != null) {
			 $.ajax({
					async: false,
					type:'get',
					url:'getOpenId',
					data : {'code' : sessionStorage.getItem("code")}
				})
		}
		
	}else{
		sessionStorage.setItem("code","233");
	}
	
	//正则匹配去除URL中code的值
	function getUrlParam(name) {
		var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); 
		var r = location.href.substr(1).match(reg); 
		if (r != null) 
			return unescape(r[2]);
		return null; 
	}
		
	var title = $("#title").text();
	var videoDerc = $("#videoDerc").val();
	var src = $("source").attr("src");
	//var img = $("video").attr("poster");
	var img = "http://p8v8q53wo.bkt.clouddn.com/daniu/yugaposter.png";
	var keyPoint = $("#keyPoint").val();
	//alert("title="+title+" videoDerc="+videoDerc+" src="+src+" img="+img+" keyPoint="+keyPoint);
	//ajax注入权限验证  
	$.ajax({
		async: true,
		type:'get',
		url : "initWXJSInterface",
		dataType : 'json',
		data : {'url' : currurl},
		success : function(res) {
			var nonceStr = res.nonceStr;
			var jsapi_ticket = res.jsapi_ticket;
			var timestamp = res.timestamp;
			var signature = res.signature;
			var appId = res.appId;
			wx.config({
				debug : false, //开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。  
				appId : appId, //必填，公众号的唯一标识  
				timestamp : timestamp, // 必填，生成签名的时间戳  
				nonceStr : nonceStr, //必填，生成签名的随机串  
				signature : signature, // 必填，签名，见附录1  
				jsApiList : [ 'onMenuShareAppMessage', 'onMenuShareTimeline' ] //必填，需要使用的JS接口列表，所有JS接口列表 见附录2  
			});
			
			wx.ready(function() {
				wx.onMenuShareAppMessage({
					//title : title, // 分享标题
					//desc : videoDerc, // 分享描述
					//link : currurl, // 分享链接，该链接域名或路径必须与当前页面对应的公众号JS安全域名一致
					imgUrl : img, // 分享图标
					//type : '', // 分享类型,music、video或link，不填默认为link
					//dataUrl : '', // 如果type是music或video，则要提供数据链接，默认为空
					success : function() {
						// 用户确认分享后执行的回调函数
						 $.ajax({
								async: false,
								type:'get',
								url:'addVideoShare',
								data : {'videoId' : $("#videoId").val()},
								success:function(result){
									if (result == "yes") {
										if ($("#mark").hasClass("markdisplay")) {
											//alert("hasClass");
											if (browser.versions.android) {
												//alert("android");
												$("#mark").removeClass("markdisplay").fadeOut();
												$(".playPause").toggleClass("playIcon");
												$("#playVideo").css("display","block");
											}
											if (browser.versions.iPhone || browser.versions.iPad || browser.versions.ios) {
												//alert("iPhone");
												$("#mark").removeClass("markdisplay").fadeOut();
												$(".playPause").toggleClass("playIcon");
											}
											
										}
										
										
									}
								}
							})
					},
					cancel : function() {
						// 用户取消分享后执行的回调函数
						alert('分享失败，请重新分享！');
					}
				}); 
 
				wx.onMenuShareTimeline({
					//title : title, // 分享标题
					//link : currurl, // 分享链接，该链接域名或路径必须与当前页面对应的公众号JS安全域名一致
					imgUrl : img, // 分享图标
					success : function() {
						// 用户确认分享后执行的回调函数
						$.ajax({
								async: false,
								type:'get',
								url:'addVideoShare',
								data : {'videoId' : $("#videoId").val()},
								success:function(result){
									if (result == "yes") {
										if ($("#mark").hasClass("markdisplay")) {
											//alert("hasClass");
											if (browser.versions.android) {
												//alert("android");
												$("#mark").removeClass("markdisplay").fadeOut();
												$(".playPause").toggleClass("playIcon");
												$("#playVideo").css("display","block");
											}
											if (browser.versions.iPhone || browser.versions.iPad || browser.versions.ios) {
												//alert("iPhone");
												$("#mark").removeClass("markdisplay").fadeOut();
												$(".playPause").toggleClass("playIcon");
											}
											
										}
									}
								}
							})
					},
					cancel : function() {
						// 用户取消分享后执行的回调函数
						alert('分享失败，请重新分享！');
					}
				}); 
			}); 
 
			wx.error(function(res) {
				alert('config错误'+res);
			});
		} 
	}); 
 
}); 
 
</script>


<!-- <script src="resource/js/controller/zepto.min.js"></script>
<script src="resource/js/controller/TweenMax.min.js"></script>
<script src="resource/js/controller/function.js"></script>
<script src="resource/js/controller/videoplay.js"></script>
<script src="resource/js/controller/hammer.min.js"></script>
<script src="resource/js/controller/hls.min.js"></script> -->
</html>