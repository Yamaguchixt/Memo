package model;

import org.json.simple.JSONObject;

public class Memo implements Jsonable {
  public String   created_date;
  public String   deleted_date;
  public String   name;
  public String   content;
  public String   priority;
  public boolean  is_complete;
  public String   id;

  public Memo(){};

  public JSONObject toJson(){
    JSONObject json = new JSONObject();
    json.put("id",this.id);
    json.put("is_complete", this.is_complete);
    json.put("priority",this.priority);
    json.put("content", this.content);
    json.put("name", this.name);
    json.put("created_date", this.created_date);

    return json;
  }


}
