let index = {
	init:function(){
		$("#btn-save").on("click", ()=>{ //this를 바인딩하기 위해서 ()=> 를 사용
			this.save();
		});
		
	},
	
	save:function(){
		let data = {
			title: $("#title").val(),
			content: $("#content").val(),
			
		};
		
		$.ajax({
			type:"POST",
			url:"/api/board",
			data: JSON.stringify(data),
			contentType:"application/json; charset=utf-8", //body데이터가 어떤 타입인지(MIME)
			dataType:"json",
		}).done(function(response){
			alert("글쓰기가 완료되었습니다.");
			location.href="/";
		}).fail(function(error){
			alert(JSON.stringify(error));
		}); 
	},
	
}

index.init();