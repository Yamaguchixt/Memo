$(function(){
	var getMemos = function(){
		console.log("getMemos invoked");
		var
			path     = "http://localhost:8080/Memo/API?action=getMemos",
			$target  = $("#Memo_target");
			$target.empty();

		$.getJSON(path,function(json){
			json.forEach(function(memo,index){ //memoは配列の要素 ⇔memoオブジェクト
				var html = createMemoHtml(memo);
				$target.append(html);
			})
			setDeleteHandeler();
		});
	};

	var createMemoHtml = function(memo){
		var complete_html = memo.is_complete == 'true' ? '完了' : '未完了' ;
		var html = '<div class="memo" data-memo_id="' +memo.id+ '">'
							+		'<div class="memo_title">タイトル : ' + memo.name + '</div>'
							+		'<div class="memo_is_completed" data-is_complete="' + memo.is_complete +'">' + complete_html + '</div>'
							+		'<div class="memo_created_date">作成日 : ' + memo.created_date + '</div>'
							+   '<div class="memo_priority">優先度 : ' + memo.priority + '</div>'
							+   '<div class="memo_content">' + memo.content + '</div>'
							+   '<button  class="btn btn-danger memo_delete_button" data-memo_id=" '+memo.id+' ">削除</button>'
							+'</div>'
		return html;
	};
	//formじゃなくてjsonでmemoつくる
	var createMemo = function() {
		var name     = $('#input_memo_name').val(),
				priority = $('#input_memo_priority').val(),
				content  = $('#input_memo_content').val(),
				path     = 'http://localhost:8080/Memo/API?action=createMemo&name='+name+'&priority='+priority+'&content='+content;
				console.log(path);
		var	data = {name : name,priority : priority, content : content};
				console.log(data);
		$.post(path,data,function(){
			console.log("post invoked");
			$('#input_memo_name').val("");
			$('#input_memo_priority').val("");
      $('#input_memo_content').val("");
		})
	};

	var setHandler = function() {
		//Memo_targetはとれてる。
		$('#memo_get_button').on('click',getMemos);
		$('#create_memo_button').on('click',createMemo);
	};
	var setDeleteHandeler = function() {
		$('.memo_delete_button').one('click',function(event){
			console.log(event);
			var id = $(this).attr('data-memo_id').trim();  //なぜか空白がまぎれこむ。
			console.log(id);
			$('div[data-memo_id='+id+']').remove();
			var path = 'http://localhost:8080/Memo/API?action=deleteMemo&memo_id='+id;
			console.log(path);
			$.get(path);
		})
	}

	setHandler();

}); //即実行