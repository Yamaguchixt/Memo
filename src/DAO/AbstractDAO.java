package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import util.Config;


/*全てのDAOクラスの共通要素。
 *abstract クラスなのでなにかしらに実装される。
 */
public abstract class AbstractDAO {
  protected Connection getConnection() {
		Logger logger = LoggerFactory.getLogger(AbstractDAO.class);
    try{
      Config config = Config.getInstance();
      Class.forName( config.getDriverName() );
      return DriverManager.getConnection(config.getConnectionName(), config.getUserName(), config.getPass() );
    } catch ( ClassNotFoundException | SQLException e){
      e.printStackTrace();
    }
    logger.error( "getConnection で nullを返しました。");
    return null;
  }
}
