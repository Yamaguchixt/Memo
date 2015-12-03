package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import model.Memo;
import util.Util;

public class MemoDAO extends AbstractDAO {
  public static final String tableName = "memo";

  public List<Memo>findAll() {
    List<Memo> list = new ArrayList<>();
    final String sql = "SELECT * FROM "+tableName+" WHERE deleted_date IS NULL ORDER BY created_date";

    try ( Connection conn = getConnection();
          PreparedStatement pst = conn.prepareStatement(sql)){

      ResultSet rs = pst.executeQuery();
      while (rs.next()){
        Memo memo = new Memo();
        memo.id           = rs.getString("id");
        memo.is_complete  = Optional.ofNullable(rs.getString("is_complete")).orElse("false").equals("true") ? true : false;
        memo.priority     = rs.getString("priority");
        memo.name         = rs.getString("name");
        memo.content      = rs.getString("content");
        memo.created_date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(rs.getTimestamp("created_date")).replaceAll("-","-");

        list.add(memo);
      }
    }catch (SQLException e){ e.printStackTrace(); }
    return list;
  }

  public void delete(String id){
    final String sql = "DELETE FROM "+tableName+ " WHERE id like ?";
    System.out.println(sql);
    System.out.println("in dao delete" + id);
    try ( Connection conn = getConnection();
          PreparedStatement pst = conn.prepareStatement(sql)){
      pst.setString(1, id);
      int result = pst.executeUpdate();
      System.out.println(result);
    }catch(SQLException e){ e.printStackTrace(); }
  }

  public void createDemo(String memo, String content) {
    final String sql = "INSERT INTO "+tableName+"(id,name,content,created_date,priority) values(?,?,?,?,?)";
    Timestamp datetime = new Timestamp(System.currentTimeMillis());
    String dateTimeStr = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss").format(datetime);

    try ( Connection conn = getConnection();
          PreparedStatement pst = conn.prepareStatement(sql)){
      pst.setString(1,Util.getUUID());
      pst.setString(2, memo);
      pst.setString(3,content);
      pst.setString(4,dateTimeStr);
      pst.setString(5, "normal");

      pst.executeUpdate();

    }catch (SQLException e){ e.printStackTrace(); }
  }

  public void create(Memo memo) {
    final String sql = "INSERT INTO " + tableName+"(id,name,content,created_date,priority) values(?,?,?,?,?)";
    Timestamp datetime = new Timestamp(System.currentTimeMillis());
    String dateTimeStr = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss").format(datetime);

    try ( Connection conn = getConnection();
          PreparedStatement pst = conn.prepareStatement(sql)) {
      pst.setString(1, Util.getUUID());
      pst.setString(2, memo.name);
      pst.setString(3, memo.content);
      pst.setString(4, dateTimeStr);
      pst.setString(5, memo.priority);

      pst.executeUpdate();

    } catch (SQLException e){e.printStackTrace();}

  }


}
