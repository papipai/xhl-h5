$(function() {
	var playVideo = $('video');
	var oDragBtn=$("#drag-btn");
	var oProgressBar=$("#progress-bar");
	var oPlayProgressBar=$("#play-progress-bar");
	//var playTip = $('playTip');
	var playPause = $('.playPause'); //播放和暂停
	var currentTime = $('.timebar .currentTime'); //当前时间
	var duration = $('.timebar .duration'); //总时间
	var progress = $('.timebar .progress-bar'); //进度条
	var volumebar = $('.volumeBar .volumewrap').find('.progress-bar');
	playVideo[0].volume = 0.4; //初始化音量
	playPause.on('click', function() {
		playControl();
	});
	$('.playTip').on('click', function() {
		playControl();
	}).hover(function() {
		$('.turnoff').stop().animate({
			'right': 0
		}, 500);
	}, function() {
		$('.turnoff').stop().animate({
			'right': -40
		}, 500);
	});
//	$(document).click(function() {
//		$('.volumeBar').hide();
//	});
	
	
	playVideo.on('loadedmetadata', function() {
		duration.text(formatSeconds(playVideo[0].duration));
	});
	
	
	playVideo.on('timeupdate', function() {
		currentTime.text(formatSeconds(playVideo[0].currentTime));
		progress.css('width', 100 * playVideo[0].currentTime / playVideo[0].duration + '%');
		var left_width = (playVideo[0].currentTime / playVideo[0].duration)*$("#progress-bar").width();
		$("#drag-btn").css('left',left_width-$("#drag-btn").width() + 'px');
		
		//视频播放一半暂停播放并弹出小程序二维码
		var video = document.getElementById('playVideo');
		var keyPoint = $("#keyPoint").val();
		//alert("keyPoint="+keyPoint);
		var timeDisplay;
		timeDisplay = Math.floor(video.currentTime);
		if(timeDisplay >= keyPoint){
			//先查询当前用户是否已经分享了该视频
			$.ajax({
				async: false,
				type:'get',
				url:'isShareVideo',
				dataType:'text',
				data : {'videoId' : $("#videoId").val()},
				success:function(result){
					if (result == "no") {
					//根据手机设备类型做出不同判断
						//安卓
						if (browser.versions.android) {
							//alert("安卓手机");
							$("#mark").addClass("markdisplay").fadeIn();
							playVideo[0].pause();
							$(".playPause").toggleClass("playIcon");
			    			$(".playTip").removeClass("glyphicon-pause").addClass("glyphicon-play").fadeIn();
							$("#playVideo").css("display","none");
						}
						//苹果
						if (browser.versions.iPhone || browser.versions.iPad || browser.versions.ios) {
							//alert("苹果手机");
							$("#mark").addClass("markdisplay").fadeIn();
							playVideo[0].pause();
							$(".playPause").toggleClass("playIcon");
			    			$(".playTip").removeClass("glyphicon-pause").addClass("glyphicon-play").fadeIn();
						}
					}
				}
			})
		}
	});
	playVideo.on('ended', function() {
		$('.playTip').removeClass('glyphicon-pause').addClass('glyphicon-play').fadeIn();
		playPause.toggleClass('playIcon');
	});
	
	$(window).keyup(function(event){
		event = event || window.event;
			if(event.keyCode == 32)playControl();
			if(event.keyCode == 27){
			$('.fullScreen').removeClass('cancleScreen');
			$('#willesPlay .playControll').css({
				'bottom': -48
			}).removeClass('fullControll');
			};
		event.preventDefault();
	});
    
	//全屏
	$('.full_btn').on('click', function() {
		var _this=this;
		if($(this).hasClass("fullscreen")){
			if ($('#playVideo').hasClass("video_full")) {
				$('#playVideo').removeClass("video_full")
			}
			$('.full_btn').removeClass("fullscreen").addClass("miniscreen");
//			$('video').removeAttr("playsinline");
//			if (document.exitFullscreen) {
//				document.exitFullscreen();
//			} else if (document.mozExitFullScreen) {
//				document.mozExitFullScreen();
//			} else if (document.webkitExitFullscreen) {
//				document.webkitExitFullscreen();
//			}
//			setTimeout(function(){
//				$('.full_btn').removeClass("fullscreen").addClass("miniscreen");
//				iWindowHeight = $(window).height() + "px";
//				$('.full_btn').css({"width":"100%","height":iWindowHeight});
//		        
//		       // _this.oVideoWrap.css({"width":"100%","height":iWindowHeight,"position":"absolute","left":"0","top":"0","z-index":99});
//		    },300);
        }else{
        	$('video').attr("playsinline");
        	if (!$('#playVideo').hasClass("video_full")) {
				$('#playVideo').addClass("video_full")
			}
        	$('.full_btn').removeClass("miniscreen").addClass("fullscreen");
//        	if (document.exitFullscreen) {
//				document.exitFullscreen();
//			} else if (document.mozExitFullScreen) {
//				document.mozExitFullScreen();
//			} else if (document.webkitExitFullscreen) {
//				document.webkitExitFullscreen();
//			}
        }
		
//		if ($(this).hasClass('cancleScreen')) {
//			if (document.exitFullscreen) {
//				document.exitFullscreen();
//			} else if (document.mozExitFullScreen) {
//				document.mozExitFullScreen();
//			} else if (document.webkitExitFullscreen) {
//				document.webkitExitFullscreen();
//			}
//			$(this).removeClass('cancleScreen');
//			$('#willesPlay .playControll').css({
//				'bottom': 0
//			}).removeClass('fullControll');
//		} else {
//			if (playVideo[0].requestFullscreen) {
//				playVideo[0].requestFullscreen();
//			} else if (playVideo[0].mozRequestFullScreen) {
//				playVideo[0].mozRequestFullScreen();
//			} else if (playVideo[0].webkitRequestFullscreen) {
//				playVideo[0].webkitRequestFullscreen();
//			} else if (playVideo[0].msRequestFullscreen) {
//				playVideo[0].msRequestFullscreen();
//			}
//			if ($(this).hasClass('video_full')){
//				$(this).removeClass('video_full');
//			}
//			$(this).addClass('cancleScreen');
//			$('#willesPlay .playControll').css({
//				'left': 0,
//				'bottom': 0
//			}).addClass('fullControll');
//		}
		return false;
	});

	
	function playControl() {
		playPause.toggleClass('playIcon');
		if (playVideo[0].paused) {
			playVideo[0].play();
			var title = $("#title").text();
			var src = $("source").attr("src");
			var img = $("video").attr("poster");
			console.log("title="+title+" src="+src+" img="+img);
			$('.playTip').removeClass('glyphicon-play').addClass('glyphicon-pause').fadeOut();
		} else {
			playVideo[0].pause();
			$('.playTip').removeClass('glyphicon-pause').addClass('glyphicon-play').fadeIn();
		}
	}
	
	//视频播放一半暂停播放并弹出小程序二维码
	/*var video = document.getElementById('playVideo');
	video.addEventListener("timeupdate",function(){
	    var timeDisplay;
		timeDisplay = Math.floor(video.currentTime);
		if(timeDisplay == Math.floor(video.duration/2)){
			mark.style.display = 'block';
			playVideo[0].pause();
		}
	},false);*/
	
	//点击进度条快进
	$('.timebar .progress').mousedown(function(e) {
		e = e || window.event;
		updatebar(e.pageX);
	});
	var updatebar = function(x) {
		var maxduration = playVideo[0].duration; //Video 
		var positions = x - progress.offset().left; //Click pos
		var percentage = 100 * positions / $('.timebar .progress').width();
		//Check within range
		if (percentage > 100) {
			percentage = 100;
		}
		if (percentage < 0) {
			percentage = 0;
		}

		//Update progress bar and video currenttime
		progress.css('width', percentage + '%');
		$("#drag-btn").css('left',percentage + '%');
		playVideo[0].currentTime = maxduration * percentage / 100;
	};
});

//秒转时间
function formatSeconds(value) {
	value = parseInt(value);
	var time;
	if (value > -1) {
		hour = Math.floor(value / 3600);
		min = Math.floor(value / 60) % 60;
		sec = value % 60;
		day = parseInt(hour / 24);
		if (day > 0) {
			hour = hour - 24 * day;
			time = day + "day " + hour + ":";
		} else time = hour + ":";
		if (min < 10) {
			time += "0";
		}
		time += min + ":";
		if (sec < 10) {
			time += "0";
		}
		time += sec;
	}
	return time;
}

//隐藏底部按钮
var playControll = document.getElementById('playControll');
function shouMenu(){
	playControll.style.display = 'block';
}
function closeMenu(){
	setTimeout(function(){ playControll.style.display = 'none'; }, 1500);
	
}


function getNowFormatDate() {
    var date = new Date();
    var seperator1 = "-";
    //var seperator2 = ":";
    var month = date.getMonth() + 1;
    var strDate = date.getDate();
    if (month >= 1 && month <= 9) {
        month = "0" + month;
    }
    if (strDate >= 0 && strDate <= 9) {
        strDate = "0" + strDate;
    }
    var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate;
    return currentdate;
}

window.onbeforeunload = function(){
	$('#time').html(getNowFormatDate());
}
//更换视频
var title = document.getElementById('title');
function changeVideo(videoId){
	window.location.href = "main?videoId="+videoId;
}

var playContent = document.getElementById('playContent');
window.onresize = function(){
	//alert(playContent.offsetWidth);
	playContent.style.height = playContent.offsetWidth*0.5797 + 'px';
}
window.onload = function(){
	$('#time').html(getNowFormatDate());
	playContent.style.height = playContent.offsetWidth*0.5797 + 'px';
	//异步请求视频
	$.ajax({
		async: false,
		type:'get',
		url:'getVideo',
		dataType:'json',
		success:function(result){
			$("#video_list2").empty();
            var thisListValueStr = "";
            for (var i = 0; i < result.length; i++) {
            	
                var caseList = result[i]; //获取Map
                thisListValueStr = "<li><table border='0'><tr onclick='changeVideo("+caseList.id+")'><td class='pic_list'><img src='"+caseList.main_pic+"' /></td><td class='title_list'><a>"+caseList.title+"</a></td></tr></table></li>";
                $("#video_list2").append(thisListValueStr);
                thisListValueStr = "";
            }
            
		}
	})
}

//判断手机设备类型
var browser = {
	  versions: function () {
	  var u = navigator.userAgent, app = navigator.appVersion;
	  return {//移动终端浏览器版本信息
	   trident: u.indexOf('Trident') > -1, //IE内核
	   presto: u.indexOf('Presto') > -1, //opera内核
	   webKit: u.indexOf('AppleWebKit') > -1, //苹果、谷歌内核
	   gecko: u.indexOf('Gecko') > -1 && u.indexOf('KHTML') == -1, //火狐内核
	   mobile: !!u.match(/AppleWebKit.*Mobile/i) || !!u.match(/MIDP|SymbianOS|NOKIA|SAMSUNG|LG|NEC|TCL|Alcatel|BIRD|DBTEL|Dopod|PHILIPS|HAIER|LENOVO|MOT-|Nokia|SonyEricsson|SIE-|Amoi|ZTE/), //是否为移动终端
	   ios: !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/), //ios终端
	   android: u.indexOf('Android') > -1 || u.indexOf('Linux') > -1, //android终端或者uc浏览器
	   iPhone: u.indexOf('iPhone') > -1 || u.indexOf('Mac') > -1, //是否为iPhone或者QQHD浏览器
	   iPad: u.indexOf('iPad') > -1, //是否iPad
	   webApp: u.indexOf('Safari') == -1 //是否web应该程序，没有头部与底部
	  };
	  } (),
	  language: (navigator.browserLanguage || navigator.language).toLowerCase()
}