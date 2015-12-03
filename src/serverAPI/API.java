package serverAPI;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Jsonable;
import model.Memo;

import org.json.simple.JSONArray;

import DAO.MemoDAO;

/**
 * Servlet implementation class API
 */
@WebServlet("/API")
public class API extends HttpServlet {

    public API() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  final String action = Optional.ofNullable(request.getParameter("action")).orElse("none");
	  response.setContentType("application/json; charset=utf-8");
    PrintWriter out = response.getWriter();

	  if ( Objects.equals(action,"getMemos")){
	     JSONArray jarray = new JSONArray();
	     for(Jsonable memo : new MemoDAO().findAll()){
	       jarray.add(memo.toJson());
	     }
	     jarray.writeJSONString(out);
	  }

	  if (Objects.equals(action,"deleteMemo")) {
	    System.out.println("API delete invoked");
	    String id = Optional.ofNullable(request.getParameter("memo_id")).orElse("none");
	    MemoDAO dao = new MemoDAO();
	    dao.delete(id);
	  }


	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  final String action = Optional.ofNullable(request.getParameter("action")).orElse("none");

	  if (Objects.equals(action,"createMemo")) {
      request.setCharacterEncoding("UTF-8");
      String name      = Optional.ofNullable(request.getParameter("name")).orElse("none");
      String priority  = Optional.ofNullable(request.getParameter("priority")).orElse("normal");
      String content   = Optional.ofNullable(request.getParameter("content")).orElse("none");
      Memo memo     = new Memo();
      memo.name     = name;
      memo.priority = priority;
      memo.content  = content;
      new MemoDAO().create(memo);
    }
	}

}
