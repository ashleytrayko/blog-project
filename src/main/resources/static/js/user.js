let index = {
	init:function(){
		$("#btn-save").on("click", ()=>{ //this를 바인딩하기 위해서 ()=> 를 사용
			this.save();
		});
		$("#btn-update").on("click", ()=>{
			this.update();
		});	
	},
	
	save:function(){
		let data = {
			username: $("#username").val(),
			password: $("#password").val(),
			email: $("#email").val()
			
		};
		
		// ajax호출시 default가 비동기호
		//ajax 통신을 이용해서 3개의 데이터를 json으로 변경하여 insert요청 
		$.ajax({
			//회원가입 수행 요청 
			type:"POST",
			url:"/auth/joinProc",
			data: JSON.stringify(data),
			contentType:"application/json; charset=utf-8", //body데이터가 어떤 타입인지(MIME)
			dataType:"json",
		}).done(function(response){
			alert("회원가입이 완료되었습니다.");
			location.href="/";
		}).fail(function(error){
			alert(JSON.stringify(error));
		}); 
	},
	
	update:function(){
		let data = {
			id: $("#id").val(),
			username: $("#username").val(),
			password: $("#password").val(),
			email: $("#email").val()
			
		};
		
		// ajax호출시 default가 비동기호
		//ajax 통신을 이용해서 3개의 데이터를 json으로 변경하여 insert요청 
		$.ajax({
			//회원가입 수행 요청 
			type:"PUT",
			url:"/user",
			data: JSON.stringify(data),
			contentType:"application/json; charset=utf-8", //body데이터가 어떤 타입인지(MIME)
			dataType:"json",
		}).done(function(response){
			alert("회원수정이 완료되었습니다.");
			location.href="/";
		}).fail(function(error){
			alert(JSON.stringify(error));
		}); 
	},
}

index.init();