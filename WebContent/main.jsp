<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
  <meta charset="UTF-8">
  <script src="<%=request.getContextPath() %>/js/jquery-1.11.3.min.js"></script>
  <script src="<%=request.getContextPath() %>/js/bootstrap.js"></script>
  <script src="<%=request.getContextPath() %>/js/underscore.js"></script>
  <script src="<%=request.getContextPath() %>/js/main.js"></script>
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/bootstrap.css">
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/main.css">
  <title>Memo</title>
  <style>
    .memo {
      border :solid 1px #eee;
      margin-top : 20px;
      margin-bottom : 10px;
      background-color : rgb(255,255,255);
      border-radius: 10px;
    }
    .memo_title {
      text-align : center;
    }
    .
  </style>
  </head>
  <body>

    <header style="background-color: #777;" class="container">ヘッダー</header>
    <section class="container">
      <div class="row">
        <div class="col-sm-8" style="background-color: rgba(225,225,225,0.8);">
          <div id="Memo_target"></div>
           <button id="memo_get_button" class="btn btn-primary">メモを取得</button>
        </div>
        <div class="col-sm-4 panel panel-primary" style="background-color: rgba(240,240,240,0.6);">
          <div class="panel-heading">
            <h1 class="panel-title">メモ登録</h1>
           </div>
           <div class="panel-body">
            <form method="get" action="API">
              <input type="hidden" name="action" value="createMemo">
              <div class="form-group">
                <label for="name" class="control-rabel">タイトル</label>
                <input class="form-control" type="text" name="name" placeholder="タイトル" id="input_memo_name">
              </div>
              <div class="form-group">
                <label for="priority" class="control-rabel">優先度</label>
                <input type="text" name="priority" placeholder="優先度" class="form-control" id="input_memo_priority">
              </div>
              <div class="form-group">
                <label for="content" class="control-rabel">コンテンツ</label>
                <textarea class="form-control textarea" name="content" id="input_memo_content" placeholder="内容"></textarea>
              </div>
              <input type="button" value="メモ作成" id="create_memo_button" class="btn btn-success">
            </form>
          </div>
   </section>
   <footer style="background-color: #777;" class="container">フッター</footer>



  </body>
</html>