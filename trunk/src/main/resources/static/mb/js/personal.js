$(function() {
	// 保存个人信息
	$("#save").click(function() {
		var nick = $("#nick").text();
		var phone = $("#phone").text();
		var gender = $("#gender").text();
		var email = $("#email").text();
		var nick_ = $.trim($("#nick_").val());
		var phone_ = $.trim($("#phone_").val());
		var gender_ = $.trim($('input:radio:checked').val());
		var email_ = $.trim($("#email_").val());
		if (nick != nick_ || phone != phone_ || gender != gender_ || email != email_) {
			if (nick_ == "") {
				layer.open({
					time : 2,
					content : '昵称不能为空！'
				});
				return false;
			}
			if (phone_ == "") {
				layer.open({
					time : 2,
					content : '手机号码不能为空！'
				});
				return false;
			}
			if (gender_ == "") {
				layer.open({
					time : 2,
					content : '性别不能为空！'
				});
				return false;
			}
			var reg = /^1[3578]\d{9}$/;
			if(!reg.test(phone_)) {
				layer.open({
					time : 2,
					content : '请输入正确的手机号码！'
				});
				return false;
			}
			if(!/^(\w)+(\.\w+)*@(\w)+((\.\w+)+)$/.test(email_)) {
				layer.open({
					time : 2,
					content : '请输入正确的邮箱！'
				});
				return false;
			}

            if($("#carouselChangeId").val()!=''&&$("#carouselChangeId").val()!=undefined&&$("#carouselChangeId").val()!=null){
                $("#action").ajaxSubmit({
                    type: 'post',
                    dataType: "jsonp",
                    url: "/ueditor/controller?action=uploadimage",
                    success: function(data) {
                        if(data.state=='SUCCESS'){
                            picTitle = data.url;
                            $.post('/user/update1',{nick:nick_, gender:gender_,phone:phone_,email:email_,portrait:picTitle},function (json) {
                                if(json.returnStatus.status==0){
                                    document.location.href = "personal_center.html";
                                }else{
                                    layer.open({
                                        time : 2,
                                        content : json.returnStatus.info
                                    })
                                }
                            });
                        }else{
                            layer.msg('保存失败！');
                        }
                    },
                    error : function(XmlHttpRequest, textStatus, errorThrown){
                        layer.msg('保存失败！');
                    }
                })
            }else{
                $.post('/user/update1',{nick:nick_, gender:gender_,phone:phone_,email:email_},function (json) {
                    if(json.returnStatus.status==0){
                        document.location.href = "personal_center.html";
                    }else{
                        layer.open({
                            time : 2,
                            content : json.returnStatus.info
                        })
                    }
                });
            }
		}
	});
})
